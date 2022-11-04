package featurecreep.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProfile.Factory;
import net.minecraft.resource.ResourcePackProvider;

public class FCPackLoad implements ResourcePackProvider{

	
	
	//From OpenLoader
public FCPackLoad(File location) {
        
        
        try {
            
            Files.createDirectories(location.toPath());
        }
        
        catch (final IOException e) {
            e.printStackTrace();
        }
        
    }
	


	@Override
	public <T extends ResourcePackProfile> void register(Map<String, T> var1, Factory<T> var2) {
		// TODO Auto-generated method stub
		T pack = ResourcePackProfile.of(PackLoader.pack_name, true, () -> new DirectoryResourcePack(new File (PackLoader.fc_pack_location)), var2, ResourcePackProfile.InsertionPosition.TOP);
		
		 if (pack != null) {
            
			 var1.put(PackLoader.pack_name, pack);
        }
	}

}
