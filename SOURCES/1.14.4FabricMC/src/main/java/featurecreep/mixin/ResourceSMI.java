package featurecreep.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import game.GameConfig;

//I gotta remove all the SpongeMixins eventually
//From Fabric API

@Mixin(GameConfig.class)
public class ResourceSMI {
	@Shadow
	public List<String> field_1887;

	@Inject(method = "method_1636", at = @At("RETURN"))
	private void onLoad(CallbackInfo ci) {

		this.field_1887.add("fcpack_4");		
		System.out.println("Adding FCPack");
	}
	
	
	
	
}
