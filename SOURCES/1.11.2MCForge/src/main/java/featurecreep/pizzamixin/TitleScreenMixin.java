package featurecreep.pizzamixin;



import java.util.List;

import featurecreep.FeatureCreep;
import game.GameConfig;
import net.pizzacrust.mixin.Inject;
import net.pizzacrust.mixin.Mixin;
import net.pizzacrust.mixin.MixinBridge;

@Mixin("net.minecraft.client.settings.GameSettings")
public class TitleScreenMixin{

	
	public static GameConfig INSTANCE;
	
	@MixinBridge
	public List<String> field_151453_l;
	
	//This is for PizzaMixin to work
    public TitleScreenMixin(Object thisObj) {INSTANCE = (GameConfig)thisObj;}
	
    
    @Inject(Inject.Execution.AFTER)
    public void func_74300_a ()
    {
    //	fcpackadd();
		FeatureCreep.LOGGER.info("This line is printed by an example mod mixin!");
		FeatureCreep.LOGGER.info("Boycott Modrinth");
		this.field_151453_l.add("fcpack_3");
    System.out.println("Pack Added");
    }
    
    
/*
@Inject(Inject.Execution.BEFORE)
    private float parseFloat(String str)
    {
    	fcpackadd();
    	 if ("true".equals(str))
         {
             return 1.0F;

         }
         else
         {
             return "false".equals(str) ? 0.0F : Float.parseFloat(str);
         }
    }
    */
    
    
    
//    public void fcpackadd() {
//		FeatureCreep.LOGGER.info("This line is printed by an example mod mixin!");
//		FeatureCreep.LOGGER.info("Boycott Modrinth");
//		this.field_151453_l.add("fcpack_3");
//    System.out.println("Pack Added");
//    }
//TunaByte gets rid of buttons

}
