package featurecreep.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.jboss.modules.ConcreteModuleSpec;
import org.jboss.modules.DelegatingModuleLoader;
import org.jboss.modules.DependencySpec;
import org.jboss.modules.FileSystemClassPathModuleFinder;
import org.jboss.modules.LocalDependencySpecBuilder;
import org.jboss.modules.LocalLoader;
import org.jboss.modules.LocalModuleFinder;
import org.jboss.modules.ModuleDependencySpecBuilder;
import org.jboss.modules.ModuleLoadException;
import org.jboss.modules.ModuleLoader;
import org.jboss.modules.ModuleSpec;
import org.jboss.modules.Resource;
import org.jboss.modules.ResourceLoader;
import org.jboss.modules.ResourceLoaderModuleFinder;
import org.jboss.modules.ResourceLoaderSpec;
import org.jboss.modules.Version;
import org.jboss.modules.filter.MultiplePathFilterBuilder;
import org.jboss.modules.filter.PathFilter;
import org.jboss.modules.filter.PathFilters;
import org.jboss.modules.xml.ModuleXmlParser;
import org.jboss.modules.xml.PermissionsXmlParser;
import org.jboss.modules.xml.XmlPullParserException;

import featurecreep.loader.finder.PKZipResourceLoader;
import featurecreep.loader.finder.PathResourceLoader;
import featurecreep.loader.utils.ConcreteModuleSpecAccessor;
import featurecreep.loader.utils.JBMUtilsAccessors;

public class FCFileSystemClassPathFinder  extends FileSystemClassPathModuleFinder{

	
    FCLoaderBasic load;
    ModuleLoader baseModuleLoader;
    ModuleLoader extensionModuleLoader;
    
    public static final PathFilter NO_MODULES_DIR;
    public NestedResourceRootFactory factory;
    @SuppressWarnings("removal")
	public AccessControlContext context = AccessController.getContext();;
    
    static {
        final MultiplePathFilterBuilder builder = PathFilters.multiplePathFilterBuilder(true);
        builder.addFilter(PathFilters.is("modules"), false);
        builder.addFilter(PathFilters.isChildOf("modules"), false);
        NO_MODULES_DIR = builder.create();
    }
    
    
    
    public FCFileSystemClassPathFinder(ModuleLoader baseModuleLoader, FCLoaderBasic load) {
            super(baseModuleLoader);
            // TODO Auto-generated constructor stub
            this.load = load;
            this.baseModuleLoader=baseModuleLoader;
            this.extensionModuleLoader=new ModuleLoader(ModuleLoader.NO_FINDERS);
    }
	
    public FCFileSystemClassPathFinder(ModuleLoader baseModuleLoader, ModuleLoader extensionModuleLoader, FCLoaderBasic load) {
        super(baseModuleLoader);
        // TODO Auto-generated constructor stub
        this.load = load;
        this.baseModuleLoader=baseModuleLoader;
        this.extensionModuleLoader=extensionModuleLoader;
    }
	
	
	 public ModuleSpec findModule(final String name, final ModuleLoader delegateLoader) throws ModuleLoadException {
	        final Path path = Paths.get(name);
	        if (! path.isAbsolute()) {
	            return null;
	        }
	        final Path normalizedPath = path.normalize();
	        if (! path.equals(normalizedPath)) {
	            return null;
	        }
	        try {
	            final Manifest manifest;
	            final String fileName = path.toString();
	            final ResourceLoader resourceLoader;
	            final ModuleLoader fatModuleLoader;
	            ModuleSpec.Builder builder;
	            if (Files.isDirectory(path)) {
	                manifest = new Manifest();
	                final Path manifestPath = path.resolve("META-INF/MANIFEST.MF");
	                if (Files.exists(manifestPath)) try {
	                    try (InputStream stream = Files.newInputStream(manifestPath, StandardOpenOption.READ)) {
	                        manifest.read(stream);
	                    }
	                } catch (NoSuchFileException | FileNotFoundException ignored) {
	                } catch (IOException e) {
	                    throw new ModuleLoadException("Failed to load MANIFEST from " + path, e);
	                }
	                
	                

	                resourceLoader = new PathResourceLoader(fileName, path, context);
	                
	                if (path == null) {
	                    return null; // not valid, so not found
	                }
	                
	                factory = new NestedResourceRootFactory(resourceLoader);
	                String basePath = "modules/" + path;
	                Resource moduleXmlResource = resourceLoader.getResource(basePath + "/module.xml");
	                moduleXmlResource.openStream();
	                
	                try (final InputStream inputStream = moduleXmlResource.openStream()) {
	                   ModuleSpec moduleSpec = ModuleXmlParser.parseModuleXml(factory, basePath, inputStream, moduleXmlResource.getName(), delegateLoader, name);
	                   builder=ConcreteModuleSpecAccessor.getBuilder((ConcreteModuleSpec)moduleSpec);
	                }catch (Exception e) {
	                	 builder = ModuleSpec.build(fileName);
	                }
	                
	                
		           
	                
	                
	                fatModuleLoader = new DelegatingModuleLoader(baseModuleLoader, new LocalModuleFinder(new File[]{ path.resolve("modules").toFile() }));
	            } else {
	                // assume some kind of JAR file
	                final JarFile jarFile = JBMUtilsAccessors.getJarFile(path.toFile(), true);
	                try {
	                    try {
	                        manifest = jarFile.getManifest();
	                    } catch (IOException e) {
	                        throw new ModuleLoadException("Failed to load MANIFEST from " + path, e);
	                    }
	                    resourceLoader = new PKZipResourceLoader(fileName, jarFile);
	                } catch (Throwable t) {
	                    try {
	                        jarFile.close();
	                    } catch (Throwable e2) {
	                        e2.addSuppressed(t);
	                        throw e2;
	                    }
	                    throw t;
	                }
	                fatModuleLoader = new DelegatingModuleLoader(baseModuleLoader, new ResourceLoaderModuleFinder(resourceLoader));
	                
	                builder = ModuleSpec.build(fileName);
	            }
	            // now build the module specification from the manifest information
	            try {
	                addSelfContent(builder, resourceLoader);
	                addSelfDependency(builder);
	                final Attributes mainAttributes = manifest.getMainAttributes();
	                setMainClass(builder, mainAttributes);
	                addExtensionDependencies(builder, mainAttributes, extensionModuleLoader);
	                addModuleDependencies(builder, fatModuleLoader, mainAttributes);
	                setModuleVersion(builder, mainAttributes);
	                addSystemDependencies(builder);
	                addPermissions(builder, resourceLoader, delegateLoader);
	            } catch (Throwable t) {
	                resourceLoader.close();
	                throw t;
	            }
	            return builder.create();
	        } catch (IOException e) {
	            throw new ModuleLoadException(e);
	        }
	    }
   
	    void addSelfContent(final ModuleSpec.Builder builder, final ResourceLoader resourceLoader) {
	        // add our own content
	        builder.addResourceRoot(ResourceLoaderSpec.createResourceLoaderSpec(resourceLoader, NO_MODULES_DIR));
	    }
    
	 
	 
	    void setModuleVersion(final ModuleSpec.Builder builder, final Attributes mainAttributes) {
	        final String versionString = mainAttributes.getValue("Module-Version");
	        if (versionString != null) {
	            builder.setVersion(Version.parse(versionString));
	        }
	    }
	 
	    
	    void setMainClass(final ModuleSpec.Builder builder, final Attributes mainAttributes) {
	        final String mainClass = mainAttributes.getValue(Attributes.Name.MAIN_CLASS);
	        if (mainClass != null) {
	            builder.setMainClass(mainClass);
	        }
	    }

	    void addSelfDependency(final ModuleSpec.Builder builder) {
	        // add our own dependency
	        builder.addDependency(DependencySpec.OWN_DEPENDENCY);
	    }
    
	    
	    void addExtensionDependencies(final ModuleSpec.Builder builder, final Attributes mainAttributes, final ModuleLoader extensionModuleLoader) {
	        final String extensionList = mainAttributes.getValue(Attributes.Name.EXTENSION_LIST);
	        final String[] extensionListEntries = extensionList == null ? new String[0] : extensionList.split("\\s+");
	        for (String entry : extensionListEntries) {
	            if (! entry.isEmpty()) {
	                builder.addDependency(new ModuleDependencySpecBuilder()
	                    .setImportFilter(PathFilters.acceptAll())
	                    .setModuleLoader(extensionModuleLoader)
	                    .setName(entry)
	                    .setOptional(true)
	                    .build());
	            }
	        }
	    }
	    
	    
    //@Override
    public void addSystemDependencies(final ModuleSpec.Builder builder) {

            //This need to be reworked to account for already loaded modules

    	//ModuleSpec spec = load.getCustomRootSpecs().get(builder.getName());

    	builder.setClassFileTransformer(load.getMainTransformer());
    	
            LocalLoader lod = JBMUtilsAccessors.getSystemLocalLoader();

            builder.addDependency(new LocalDependencySpecBuilder()
                    .setLocalLoader(lod)
                    .setLoaderPaths(load.getNeededPackages())
                    .build());


            }
    
    void addModuleDependencies(final ModuleSpec.Builder builder, final ModuleLoader fatModuleLoader, final Attributes mainAttributes) {
        final String dependencies = mainAttributes.getValue("Dependencies");
        final String[] dependencyEntries = dependencies == null ? new String[0] : dependencies.split("\\s*,\\s*");
        for (String dependencyEntry : dependencyEntries) {
            boolean optional = false;
            boolean export = false;
            boolean services = false;
            dependencyEntry = dependencyEntry.trim();
            if (! dependencyEntry.isEmpty()) {
                String[] fields = dependencyEntry.split("\\s+");
                if (fields.length < 1) {
                    continue;
                }
                String moduleName = fields[0];
                for (int i = 1; i < fields.length; i++) {
                    String field = fields[i];
                    if (field.equals("optional")) {
                        optional = true;
                    } else if (field.equals("export")) {
                        export = true;
                    } else if (field.equals("services")) {
                        services = true;
                    }
                    // else ignored
                }
                builder.addDependency(new ModuleDependencySpecBuilder()
                    .setImportServices(services)
                    .setExport(export)
                    .setModuleLoader(fatModuleLoader)
                    .setName(moduleName)
                    .setOptional(optional)
                    .build());
            }
        }
    }

    
    public     void addPermissions(final ModuleSpec.Builder builder, final ResourceLoader resourceLoader, final ModuleLoader moduleLoader) {
        final Resource resource = resourceLoader.getResource("META-INF/permissions.xml");
        if (resource != null) {
            try {
                try (InputStream stream = resource.openStream()) {
                    builder.setPermissionCollection(PermissionsXmlParser.parsePermissionsXml(stream, moduleLoader, builder.getName()));
                }
            } catch (XmlPullParserException | IOException ignored) {
            }
        }
    }
    
    
    
    
    
    
    
   public static class NestedResourceRootFactory implements ModuleXmlParser.ResourceRootFactory {
        private final ResourceLoader resourceLoader;

        NestedResourceRootFactory(final ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
        }

        public ResourceLoader createResourceLoader(final String rootPath, final String loaderPath, final String loaderName) throws IOException {
            final ResourceLoader subloader = resourceLoader.createSubloader(rootPath + "/" + loaderPath, loaderName);
            if (subloader == null) {
                throw new IllegalArgumentException("Nested resource loaders not supported by " + resourceLoader);
            }
            return subloader;
        }
    }
    
    
    
    
}
