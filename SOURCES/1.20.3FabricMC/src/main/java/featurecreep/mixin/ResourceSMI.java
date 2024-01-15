package featurecreep.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.bg.PackLoader;
import net.minecraft.client.option.GameOptions;

//I gotta remove all the SpongeMixins eventually
//From Fabric API

@Mixin(GameOptions.class)
public class ResourceSMI {
	@Shadow
	public List<String> resourcePacks;

	@Inject(method = "load", at = @At("RETURN"))
	private void onLoad(CallbackInfo ci) {

		this.resourcePacks.add("fcpack_13");		
		System.out.println("Adding FCPack");
	}
	
	
	
	
}
