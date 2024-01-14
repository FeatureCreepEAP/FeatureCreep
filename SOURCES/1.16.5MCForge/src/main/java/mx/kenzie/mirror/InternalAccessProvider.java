package mx.kenzie.mirror;

import org.objectweb.asm.Type;
import sun.misc.Unsafe;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.security.*;

@SuppressWarnings({"removal", "deprecated", "unchecked", "Duplicates", "TypeParameterHidesVisibleType"})
class InternalAccessProvider implements ClassProvider {
    
    final Object javaLangAccess;
    final Unsafe unsafe;
    final Method defineClass;
 //   final Method addReads0;
  //  final Method addExports0;
  //  final Method addExportsToAllUnnamed0;
    final Method newInstanceWithCaller;
    
    InternalAccessProvider() throws ClassNotFoundException, PrivilegedActionException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Class<?> secrets = Class.forName("sun.misc.SharedSecrets", false, ClassLoader.getSystemClassLoader());
        unsafe = AccessController.doPrivileged((PrivilegedExceptionAction<Unsafe>) () -> {
            final Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        });
 //       final Field field = Class.class.getDeclaredField("module");
  //      final long offset = unsafe.objectFieldOffset(field);
   //     unsafe.putObject(InternalAccessProvider.class, offset, Object.class.getModule());
        final Method setAccessible0 = AccessibleObject.class.getDeclaredMethod("setAccessible", boolean.class);// without0
        setAccessible0.setAccessible(true);
     //   final Method implAddExportsOrOpens = Module.class.getDeclaredMethod("implAddExportsOrOpens", String.class, Module.class, boolean.class, boolean.class);
     //   setAccessible0.invoke(implAddExportsOrOpens, true);
     //   addReads0 = Module.class.getDeclaredMethod("addReads0", Module.class, Module.class);
       // setAccessible0.invoke(addReads0, true);
//        addExports0 = Module.class.getDeclaredMethod("addExports0", Module.class, String.class, Module.class);
 //       setAccessible0.invoke(addExports0, true);
  //      addExportsToAllUnnamed0 = Module.class.getDeclaredMethod("addExportsToAllUnnamed0", Module.class, String.class);
   //     setAccessible0.invoke(addExportsToAllUnnamed0, true);
        final Method getJavaLangAccess = secrets.getDeclaredMethod("getJavaLangAccess");
        setAccessible0.invoke(getJavaLangAccess, true);
        javaLangAccess = getJavaLangAccess.invoke(null);
       // defineClass = javaLangAccess.getClass()
        //    .getMethod("defineClass", ClassLoader.class, String.class, byte[].class, ProtectionDomain.class, String.class);
        //defineClass = javaLangAccess.getClass().getClassLoader().getClass().getDeclaredMethod("defineClass", ClassLoader.class, String.class, byte[].class, ProtectionDomain.class, String.class);

        Class cl = Class.forName("java.lang.ClassLoader");
 
        defineClass = cl.getDeclaredMethod("defineClass",
                new Class[] { String.class, byte[].class,
                      int.class, int.class});
        
        
        
        
        setAccessible0.invoke(defineClass, true);
//        newInstanceWithCaller = Constructor.class.getDeclaredMethod("newInstanceWithCaller", Object[].class, boolean.class, Class.class);
        newInstanceWithCaller = Constructor.class.getDeclaredMethod("newInstance", Object[].class);

        setAccessible0.invoke(newInstanceWithCaller, true);
    }
    
    public static Object make(Class<?> type, Object target) {
        try {
            final Constructor<?> constructor = type.getConstructor(Object.class);
            return constructor.newInstance(target);
        } catch (Throwable ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    Class<?> findClass(Class<?> target, String name) {
        final ClassLoader loader = this.getClass().getClassLoader(); //(target.getModule());
        try {
            return Class.forName(name, true, loader);
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
 //   ClassLoader getClassLoader(Module target) {
 //       PrivilegedAction<ClassLoader> pa = target::getClassLoader;
 //       return AccessController.doPrivileged(pa);
 //   }
    
    @Override
    public Class<?> loadClass(Class<?> target, String name, byte[] bytes) {
        try {
            final ClassLoader loader;
            if (target.getClassLoader() != null) loader = target.getClassLoader();
            else loader = this.getClass().getClassLoader();
            try {
                return Class.forName(name, true, loader);
            } catch (ClassNotFoundException ex) {
                return (Class<?>) defineClass.invoke(this.getClass().getClassLoader(), name, bytes, new Integer(0),
                        new Integer(bytes.length)); //For some reason Java Access returns null, i may need to use it some other time to accessing internal classes
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Unable to load class.", e);
        }
    }
    
    void assureAvailable(Class<?> resource, Class<?> target) {
        if (resource == null) return;
        for (final Class<?> template : resource.getInterfaces()) {
            this.assureAvailable(template, target);
        }
        if (resource.getSuperclass() != Object.class && resource.getSuperclass() != Class.class)
            this.assureAvailable(resource.getSuperclass(), target);
        final ClassLoader loader;
        if (target.getClassLoader() != null) loader = target.getClassLoader();
        else loader = this.getClass().getClassLoader();
        try {
            Class.forName(resource.getName(), true, loader);
        } catch (ClassNotFoundException ex) {
     //       this.addReads(target.getModule(), resource.getModule());
    //        this.addReads(target.getModule(), null);
            final byte[] bytecode = this.getSource(Type.getInternalName(resource));
            if (bytecode.length == 0) return;
            this.loadClass(target, resource.getName(), bytecode);
        }
    }
    
    byte[] getSource(final String type) {
        try (final InputStream stream = ClassLoader.getSystemResourceAsStream(type + ".class")) {
            if (stream == null) return new byte[0];
            byte [] byteArray = new byte[0];
            stream.read(byteArray);
            return byteArray; //stream.readAllBytes();
        } catch (IOException ex) {
            return new byte[0];
        }
    }
    
 //   void addReads(final Module reader, final Module target) {
 //       try {
 //           addReads0.invoke(null, reader, target);
 //       } catch (InvocationTargetException | IllegalAccessException e) {
 //           reader.addReads(target);
 //       }
 //   }
    
//    void export(final Module module, final String namespace) {
//        try {
//            addExports0.invoke(null, module, namespace, LookingGlass.class.getModule());
//            addExportsToAllUnnamed0.invoke(null, module, namespace);
//        } catch (InvocationTargetException | IllegalAccessException e) {
//            module.addExports(namespace, LookingGlass.class.getModule());
//        }
 //   }
    
    <Type> Type newInstance(Constructor<Type> constructor, Object... parameters) {
        try {
          //  return (Type) this.newInstanceWithCaller.invoke(constructor, parameters, false, constructor.getDeclaringClass());
        	return (Type) constructor.newInstance(parameters);//this.newInstanceWithCaller.invoke(constructor, parameters);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
