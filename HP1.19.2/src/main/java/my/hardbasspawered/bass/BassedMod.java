package my.hardbasspawered.bass;

import my.hardbasspawered.bass.transformer.MainInjector;
import my.hardbasspawered.commons.logging.IHardbassPaweredLogger;
import my.hardbasspawered.commons.logging.LogManagerService;
import my.hardbasspawered.empawered.pluginapi.AbstractPlugin;
import my.hardbasspawered.empawered.pluginapi.BootstrapData;
import my.hardbasspawered.empawered.pluginapi.loader.AbstractEssentialOils;
import my.hardbasspawered.fluffyasm.processor.transformer.AbstractTransformer;
import my.hardbasspawered.fluffyasm.processor.util.TransformerProvider;
import my.hardbasspawered.midnight.MidnightService;
import my.hardbasspawered.midnight.transformers.BuiltInRunAfter;
import my.hardbasspawered.midnight.transformers.TransformerBuilder;

public class BassedMod extends AbstractPlugin {
    private static final IHardbassPaweredLogger LOGGER = LogManagerService.getLoggerStatic(BassedMod.class);

    @Override
    public void initializePreLoader(AbstractEssentialOils essentialOils, BootstrapData data) {
        LOGGER.info("Hello minecraft its time to torture you >:P");
        MidnightService.SERVICE.addTransformer(
                TransformerBuilder.create(this, "bass", "main", true, true)
                        .addTransformer(MainInjector::new)
                        .setExecuteAfter(BuiltInRunAfter.MODDING)
                        .build()
        );
    }
}
