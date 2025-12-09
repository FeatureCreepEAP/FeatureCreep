package asbestosstar.fabricmcinit;

import asbestosstar.bootstrap.BootstrapCommon;
import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.LanguageAdapterException;
import net.fabricmc.loader.api.ModContainer;

public class FabricMCEntryPoint implements LanguageAdapter {
	
	public static boolean init_agent=BootstrapCommon.initDefault();
	
	//This ultimatly does nothing, it only exists because lanugage adapter is earliest point
    @Override
    public <T> T create(ModContainer mod, String value, Class<T> type) throws LanguageAdapterException {
        try {
            // Attempt to create a new instance of the specified type
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // If instance creation fails, throw a LanguageAdapterException
            throw new LanguageAdapterException("Could not create instance of " + type.getName(), e);
        }
    }
}
