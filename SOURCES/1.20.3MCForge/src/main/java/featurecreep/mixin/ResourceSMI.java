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
	public List<String> f_92117_;

	@Inject(method = "m_92140_", at = @At("RETURN"))
	private void onLoad(CallbackInfo ci) {

		this.f_92117_.add("fcpack_22");		
		System.out.println("Adding FCPack");
	}
	
	
	
	
}
