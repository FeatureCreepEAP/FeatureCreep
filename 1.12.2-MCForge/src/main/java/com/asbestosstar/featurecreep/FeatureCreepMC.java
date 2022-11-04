package com.asbestosstar.featurecreep;

//import com.example.examplemod.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import featurecreep.content.FCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

//@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class FeatureCreepMC
{
   // private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
      //  logger = event.getModLog();
		GameRegistry.registerWorldGenerator( new OreGen(), 0);

    }

    @EventHandler
    public static void initi()
    {
    	RegistryHandler.otherRegistries();
        // some example code
       // logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
   
    
        GameRegistry.registerFuelHandler(new Fuels());
        GameRegistry.addSmelting(FeatureCreepMC.RAW_BACON, new ItemStack(FeatureCreepMC.COOKED_BACON), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.CORN, new ItemStack(FeatureCreepMC.POPCORN), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.CORN_DOG, new ItemStack(FeatureCreepMC.COOKED_CORN_DOG), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.RAW_CRAB_MEAT, new ItemStack(FeatureCreepMC.COOKED_CRAB_MEAT), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.RAW_PEACOCK, new ItemStack(FeatureCreepMC.COOCKED_PEACOCK), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.RAW_MOOSE_MEAT, new ItemStack(FeatureCreepMC.COOKED_MOOSE_MEAT), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.TIGERS_EYE_ORE, new ItemStack(FeatureCreepMC.TIGERS_EYE), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.TITANIUM_ORE, new ItemStack(FeatureCreepMC.TITANIUM), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.URANIUM_ORE, new ItemStack(FeatureCreepMC.URANIUM), 2.0F);
        GameRegistry.addSmelting(FeatureCreepMC.ALUMINIUM_ORE, new ItemStack(FeatureCreepMC.ALUMINIUM), 2.0F);
        GameRegistry.addSmelting(Items.IRON_INGOT, new ItemStack(FeatureCreepMC.STEEL_INGOT), 2.0F);
        GameRegistry.addSmelting(new ItemStack(FeatureCreepMC.ALUMINIUM_ORE, 1), new ItemStack(FeatureCreepMC.ALUMINIUM, 1), 2.0F);
        //FurnaceRecipes.instance().addSmeltingRecipe(new ItemStack(ALUMINIUM_ORE), new ItemStack(FeatureCreepMC.ALUMINIUM), 1);




        /*

        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOBZILLA_SCALE_HELMET, 1), new Object [] {
        "XXX", "X X", "   ", 'X', FeatureCreepMC.MobzillaScale		
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOBZILLA_SCALE_CHESTPLATE, 1), new Object [] {
        "X X", "XXX", "XXX", 'X', FeatureCreepMC.MobzillaScale		
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOBZILLA_SCALE_LEGGINGS, 1), new Object [] {
        "XXX", "X X", "X X", 'X', FeatureCreepMC.MobzillaScale		
        });
        GameRegistry.addShapedRecipe(null, null,new ItemStack (MOBZILLA_SCALE_BOOTS, 1), new Object [] {
        "X X", "X X", "   ", 'X', FeatureCreepMC.MobzillaScale		
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (MOBZILLA_SCALE_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.MobzillaScale		
        });



        GameRegistry.addShapedRecipe(null, null,new ItemStack (QUEEN_SCALE_HELMET, 1), new Object [] {
        "XXX", "X X", "   ", 'X', FeatureCreepMC.QueenScale		
        });
        GameRegistry.addShapedRecipe(null, null,new ItemStack (QUEEN_SCALE_CHESTPLATE, 1), new Object [] {
        "X X", "XXX", "XXX", 'X', FeatureCreepMC.QueenScale	
        });
        GameRegistry.addShapedRecipe(null, null,new ItemStack (QUEEN_SCALE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.QueenScale	
        });
        GameRegistry.addShapedRecipe(null, null,new ItemStack (QUEEN_SCALE_BOOTS, 1), new Object [] {
        "X X", "X X", "   ", 'X', FeatureCreepMC.QueenScale	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (MOTH_SCALE_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.MothScale	
        });

        GameRegistry.addShapedRecipe(null, null,new ItemStack (MOTH_SCALE_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.MothScale		
        });
        GameRegistry.addShapedRecipe(null, null,new ItemStack (MOTH_SCALE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.MothScale	
        });
        GameRegistry.addShapedRecipe(null, null,new ItemStack (MOTH_SCALE_BOOTS, 1), new Object [] {
        "XXX", "X X", "X X", 'X', FeatureCreepMC.MothScale	
        });




        GameRegistry.addShapedRecipe(null, null,new ItemStack (PEACOCK_FEATHER_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.PEACKOCK_FEATHER	
        });

        GameRegistry.addShapedRecipe(null, null,new ItemStack (PEACOCK_FEATHER_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.PEACKOCK_FEATHER		
        });
        GameRegistry.addShapedRecipe(null, null,new ItemStack (PEACOCK_FEATHER_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.PEACKOCK_FEATHER	
        });
        GameRegistry.addShapedRecipe(null, null,new ItemStack (PEACOCK_FEATHER_BOOTS, 1), new Object [] {
        "XXX", "X X", "X X", 'X', FeatureCreepMC.PEACKOCK_FEATHER	
        });




        GameRegistry.addShapedRecipe(null, null,new ItemStack (PINK_TOURMALINE_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.PinkTourmaline	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (PINK_TOURMALINE_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.PinkTourmaline	
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (PINK_TOURMALINE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.PinkTourmaline	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (PINK_TOURMALINE_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.PinkTourmaline	
        });

        GameRegistry.addShapedRecipe(null, null,new ItemStack (LAVA_EEL_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.LAVA_EEL	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (LAVA_EEL_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.LAVA_EEL		
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (LAVA_EEL_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.LAVA_EEL		
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (LAVA_EEL_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.LAVA_EEL	
        });






        GameRegistry.addShapedRecipe(null, null,new ItemStack (LAPIS_LAZUI_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', Blocks.LAPIS_BLOCK	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (LAPIS_LAZUI_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', Blocks.LAPIS_BLOCK			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (LAPIS_LAZUI_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', Blocks.LAPIS_BLOCK			
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (LAPIS_LAZUI_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', Blocks.LAPIS_BLOCK	
        });




        GameRegistry.addShapedRecipe(null, null,new ItemStack (ALUMINIUM_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.ALUMINIUM	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (ALUMINIUM_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.ALUMINIUM			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (ALUMINIUM_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.ALUMINIUM			
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (ALUMINIUM_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.ALUMINIUM		
        });



        GameRegistry.addShapedRecipe(null, null,new ItemStack (COPPER_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.COPPER_INGOT	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (COPPER_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.COPPER_INGOT			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (COPPER_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.COPPER_INGOT			
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (COPPER_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.COPPER_INGOT		
        });




        GameRegistry.addShapedRecipe(null, null,new ItemStack (SILVER_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.SILVER_INGOT	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (SILVER_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.SILVER_INGOT			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (SILVER_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.SILVER_INGOT			
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (SILVER_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.SILVER_INGOT		
        });




        GameRegistry.addShapedRecipe(null, null,new ItemStack (TIN_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.TIN_INGOT	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (TIN_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.TIN_INGOT			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (TIN_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.TIN_INGOT			
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (TIN_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.TIN_INGOT		
        });





        GameRegistry.addShapedRecipe(null, null,new ItemStack (PLATINUM_HELMET, 1), new Object [] {
        "X X", "X X", "   ", 'X', FeatureCreepMC.PLATINUM_INGOT	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (PLATINUM_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.PLATINUM_INGOT			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (PLATNIUM_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.PLATINUM_INGOT			
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (PLATINUM_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.PLATINUM_INGOT		
        });




        GameRegistry.addShapedRecipe(null, null,new ItemStack (RUBY_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.RUBY	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (RUBY_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.RUBY			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (RUBY_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.RUBY			
        });

        GameRegistry.addShapedRecipe(null, null,new ItemStack (RUBY_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.RUBY
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (AMETHYST_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.AMETHYST	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (AMETHYST_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.AMETHYST			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (AMETHYST_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.AMETHYST			
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (AMETHYST_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.AMETHYST
        });







        GameRegistry.addShapedRecipe(null, null,new ItemStack (TIGERS_EYE_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.TIGERS_EYE	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (TIGERS_EYE_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.TIGERS_EYE			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (TIGERS_EYE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.TIGERS_EYE			
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (TIGERS_EYE_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.TIGERS_EYE
        });



        GameRegistry.addShapedRecipe(null, null,new ItemStack (EMERALD_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', Items.EMERALD	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (EMERALD_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', Items.EMERALD			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (EMERALD_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', Items.EMERALD		
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (EMERALD_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', Items.EMERALD
        });









        //Tool Recipies

        GameRegistry.addShapedRecipe(null, null,new ItemStack (EMERALD_PICKAXE, 1), new Object [] {
        "XXX", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (EMERALD_SWORD, 1), new Object [] {
        " X ", " X ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (EMERALD_HOLE, 1), new Object [] {
        "XX ", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK			
        });
        	


        GameRegistry.addShapedRecipe(null, null,new ItemStack (EMERALD_SHOVEL, 1), new Object [] {
        " X ", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK	
        });



        GameRegistry.addShapedRecipe(null, null,new ItemStack (AMETHYST_PICKAXE, 1), new Object [] {
        "XXX", " Y ", " Y ", 'X', FeatureCreepMC.AMETHYST, 'Y', Items.STICK	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (AMETHYST_SWORD, 1), new Object [] {
        " X ", " X ", " Y ", 'X', FeatureCreepMC.AMETHYST, 'Y', Items.STICK			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (AMETHYST_HOE, 1), new Object [] {
        "XX ", " Y ", " Y ", 'X', FeatureCreepMC.AMETHYST, 'Y', Items.STICK			
        });
        	


        GameRegistry.addShapedRecipe(null, null,new ItemStack (AMETHYST_SHOVEL, 1), new Object [] {
        " X ", " Y ", " Y ", 'X', FeatureCreepMC.AMETHYST, 'Y', Items.STICK	
        });



        GameRegistry.addShapedRecipe(null, null,new ItemStack (RUBY_PICKAXE, 1), new Object [] {
        "XXX", " Y ", " Y ", 'X', FeatureCreepMC.RUBY, 'Y', Items.STICK	
        });
        	
        GameRegistry.addShapedRecipe(null, null,new ItemStack (RUBY_SWORD, 1), new Object [] {
        " X ", " X ", " Y ", 'X', FeatureCreepMC.RUBY, 'Y', Items.STICK			
        });


        GameRegistry.addShapedRecipe(null, null,new ItemStack (RUBY_HOE, 1), new Object [] {
        "XX ", " Y ", " Y ", 'X', FeatureCreepMC.RUBY, 'Y', Items.STICK			
        });
        	


        GameRegistry.addShapedRecipe(null, null,new ItemStack (RUBY_SHOVEL, 1), new Object [] {
        " X ", " Y ", " Y ", 'X', FeatureCreepMC.RUBY, 'Y', Items.STICK	
        });



        	
        	
        	
        	
        //Crafting Recepies

        //Armour Recepies
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOBZILLA_SCALE_HELMET, 1), new Object [] {
        "XXX", "X X", "   ", 'X', FeatureCreepMC.MobzillaScale		
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOBZILLA_SCALE_CHESTPLATE, 1), new Object [] {
        "X X", "XXX", "XXX", 'X', FeatureCreepMC.MobzillaScale		
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOBZILLA_SCALE_LEGGINGS, 1), new Object [] {
        "XXX", "X X", "X X", 'X', FeatureCreepMC.MobzillaScale		
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOBZILLA_SCALE_BOOTS, 1), new Object [] {
        "X X", "X X", "   ", 'X', FeatureCreepMC.MobzillaScale		
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOBZILLA_SCALE_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.MobzillaScale		
        });



        GameRegistry.addShapedRecipe(null, null, new ItemStack (QUEEN_SCALE_HELMET, 1), new Object [] {
        "XXX", "X X", "   ", 'X', FeatureCreepMC.QueenScale		
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (QUEEN_SCALE_CHESTPLATE, 1), new Object [] {
        "X X", "XXX", "XXX", 'X', FeatureCreepMC.QueenScale	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (QUEEN_SCALE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.QueenScale	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (QUEEN_SCALE_BOOTS, 1), new Object [] {
        "X X", "X X", "   ", 'X', FeatureCreepMC.QueenScale	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOTH_SCALE_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.MothScale	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOTH_SCALE_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.MothScale		
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOTH_SCALE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.MothScale	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (MOTH_SCALE_BOOTS, 1), new Object [] {
        "XXX", "X X", "X X", 'X', FeatureCreepMC.MothScale	
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (PEACOCK_FEATHER_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.PEACKOCK_FEATHER	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (PEACOCK_FEATHER_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.PEACKOCK_FEATHER		
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (PEACOCK_FEATHER_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.PEACKOCK_FEATHER	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (PEACOCK_FEATHER_BOOTS, 1), new Object [] {
        "XXX", "X X", "X X", 'X', FeatureCreepMC.PEACKOCK_FEATHER	
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (PINK_TOURMALINE_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.PinkTourmaline	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (PINK_TOURMALINE_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.PinkTourmaline	
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (PINK_TOURMALINE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.PinkTourmaline	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (PINK_TOURMALINE_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.PinkTourmaline	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (LAVA_EEL_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.LAVA_EEL	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (LAVA_EEL_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.LAVA_EEL		
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (LAVA_EEL_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.LAVA_EEL		
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (LAVA_EEL_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.LAVA_EEL	
        });






        GameRegistry.addShapedRecipe(null, null, new ItemStack (LAPIS_LAZUI_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', Blocks.LAPIS_BLOCK
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (LAPIS_LAZUI_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', Blocks.LAPIS_BLOCK			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (LAPIS_LAZUI_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', Blocks.LAPIS_BLOCK			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (LAPIS_LAZUI_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', Blocks.LAPIS_BLOCK	
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (ALUMINIUM_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.ALUMINIUM	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (ALUMINIUM_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.ALUMINIUM			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (ALUMINIUM_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.ALUMINIUM			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (ALUMINIUM_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.ALUMINIUM		
        });



        GameRegistry.addShapedRecipe(null, null, new ItemStack (COPPER_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.COPPER_INGOT	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (COPPER_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.COPPER_INGOT			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (COPPER_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.COPPER_INGOT			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (COPPER_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.COPPER_INGOT		
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (SILVER_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.SILVER_INGOT	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (SILVER_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.SILVER_INGOT			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (SILVER_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.SILVER_INGOT			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (SILVER_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.SILVER_INGOT		
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIN_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.TIN_INGOT	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIN_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.TIN_INGOT			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIN_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.TIN_INGOT			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIN_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.TIN_INGOT		
        });





        GameRegistry.addShapedRecipe(null, null, new ItemStack (PLATINUM_HELMET, 1), new Object [] {
        "X X", "X X", "   ", 'X', FeatureCreepMC.PLATINUM_INGOT	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (PLATINUM_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.PLATINUM_INGOT			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (PLATNIUM_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.PLATINUM_INGOT			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (PLATINUM_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.PLATINUM_INGOT		
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.RUBY	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.RUBY			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.RUBY			
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.RUBY
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (AMETHYST_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.AMETHYST	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (AMETHYST_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.AMETHYST			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (AMETHYST_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.AMETHYST			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (AMETHYST_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.AMETHYST
        });







        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIGERS_EYE_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.TIGERS_EYE	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIGERS_EYE_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.TIGERS_EYE			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIGERS_EYE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.TIGERS_EYE			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIGERS_EYE_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.TIGERS_EYE
        });



        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', Items.EMERALD	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', Items.EMERALD			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', Items.EMERALD		
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', Items.EMERALD
        });









        //Tool Recipies

        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_PICKAXE, 1), new Object [] {
        "XXX", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_SWORD, 1), new Object [] {
        " X ", " X ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_HOLE, 1), new Object [] {
        "XX ", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK			
        });
        	


        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_SHOVEL, 1), new Object [] {
        " X ", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK	
        });



        GameRegistry.addShapedRecipe(null, null, new ItemStack (AMETHYST_PICKAXE, 1), new Object [] {
        "XXX", " Y ", " Y ", 'X', FeatureCreepMC.AMETHYST, 'Y', Items.STICK	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (AMETHYST_SWORD, 1), new Object [] {
        " X ", " X ", " Y ", 'X', FeatureCreepMC.AMETHYST, 'Y', Items.STICK			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (AMETHYST_HOE, 1), new Object [] {
        "XX ", " Y ", " Y ", 'X', FeatureCreepMC.AMETHYST, 'Y', Items.STICK			
        });
        	


        GameRegistry.addShapedRecipe(null, null, new ItemStack (AMETHYST_SHOVEL, 1), new Object [] {
        " X ", " Y ", " Y ", 'X', FeatureCreepMC.AMETHYST, 'Y', Items.STICK	
        });



        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_PICKAXE, 1), new Object [] {
        "XXX", " Y ", " Y ", 'X', FeatureCreepMC.RUBY, 'Y', Items.STICK	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_SWORD, 1), new Object [] {
        " X ", " X ", " Y ", 'X', FeatureCreepMC.RUBY, 'Y', Items.STICK			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_HOE, 1), new Object [] {
        "XX ", " Y ", " Y ", 'X', FeatureCreepMC.RUBY, 'Y', Items.STICK			
        });
        	


        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_SHOVEL, 1), new Object [] {
        " X ", " Y ", " Y ", 'X', FeatureCreepMC.RUBY, 'Y', Items.STICK	
        });



        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_PICKAXE, 1), new Object [] {
        "XXX", " Y ", " Y ", 'X', FeatureCreepMC.SAPPHIRE, 'Y', Items.STICK	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_SWORD, 1), new Object [] {
        " X ", " X ", " Y ", 'X', FeatureCreepMC.SAPPHIRE, 'Y', Items.STICK			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_HOE, 1), new Object [] {
        "XX ", " Y ", " Y ", 'X', FeatureCreepMC.SAPPHIRE, 'Y', Items.STICK			
        });
        	


        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_SHOVEL, 1), new Object [] {
        " X ", " Y ", " Y ", 'X', FeatureCreepMC.SAPPHIRE, 'Y', Items.STICK	
        });
        	
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', FeatureCreepMC.SAPPHIRE	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', FeatureCreepMC.SAPPHIRE			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', FeatureCreepMC.SAPPHIRE			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', FeatureCreepMC.SAPPHIRE
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_PICKAXE, 1), new Object [] {
        "XXX", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_SWORD, 1), new Object [] {
        " X ", " X ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_HOLE, 1), new Object [] {
        "XX ", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK			
        });
        	


        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_SHOVEL, 1), new Object [] {
        " X ", " Y ", " Y ", 'X', Items.EMERALD, 'Y', Items.STICK	
        });
        	
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_HELMET, 1), new Object [] {
        		"XXX", "X X", "   ", 'X', Items.EMERALD	
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_CHESTPLATE, 1), new Object [] {
        		"X X", "XXX", "XXX", 'X', Items.EMERALD			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_LEGGINGS, 1), new Object [] {
        		"XXX", "X X", "X X", 'X', Items.EMERALD			
        });
        	
        GameRegistry.addShapedRecipe(null, null, new ItemStack (EMERALD_BOOTS, 1), new Object [] {
        "   ", "X X", "X X", 'X', Items.EMERALD
        });



        GameRegistry.addShapedRecipe(null, null, new ItemStack (SAPPHIRE_BLOCK, 1), new Object [] {
        	"XXX", "XXX", "XXX", 'X', FeatureCreepMC.SAPPHIRE			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (RUBY_BLOCK, 1), new Object [] {
        		"XXX", "XXX", "XXX", 'X', FeatureCreepMC.RUBY			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (TIGERS_EYE_BLOCK, 1), new Object [] {
        		"XXX", "XXX", "XXX", 'X', FeatureCreepMC.TIGERS_EYE			
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (TITANIUM_BLOCK, 1), new Object [] {
        		"XXX", "XXX", "XXX", 'X', FeatureCreepMC.TITANIUM			
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (URANIUM_BLOCK, 1), new Object [] {
        		"XXX", "XXX", "XXX", 'X', FeatureCreepMC.URANIUM			
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (ALUMINIUM_BLOCK, 1), new Object [] {
        		"XXX", "XXX", "XXX", 'X', FeatureCreepMC.ALUMINIUM			
        });





        GameRegistry.addShapedRecipe(null, null, new ItemStack (OPTIMISED_PICKAXE, 1), new Object [] {
        "WXW", "VZ ", " Y ", 'X', Items.DIAMOND, 'Y', Items.STICK, 'Z', Items.IRON_INGOT, 'V', FeatureCreepMC.TITANIUM, 'W', FeatureCreepMC.STEEL_INGOT	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (OPTIMISED_SHOVEL, 1), new Object [] {
        "WX ", "VZ ", " Y ", 'X', Items.DIAMOND, 'Y', Items.STICK, 'Z', Items.IRON_INGOT, 'V', FeatureCreepMC.TITANIUM, 'W', FeatureCreepMC.STEEL_INGOT	
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (DRINKABLE_GASOLINE_PETROL, 1), new Object [] {
        " X ", " X ", " Y ", 'X', FeatureCreepMC.GASOLINE_PETROL, 'Y', Items.GLASS_BOTTLE	
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (RAW_BACON, 2), new Object [] {
        "   ", " X ", " Y ", 'X', FeatureCreepMC.Salt, 'Y', Items.PORKCHOP	
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (BUTTER_FOOD, 4), new Object [] {
        "   ", " Y ", " Y ", 'Y', Items.MILK_BUCKET	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (BUTTER_CANDY, 2), new Object [] {
        "   ", " X ", " Y ", 'X', FeatureCreepMC.BUTTER_FOOD, 'Y', Items.SUGAR	
        });



        GameRegistry.addShapedRecipe(null, null, new ItemStack (CORN_DOG, 4), new Object [] {
        "   ", " XW", " YZ", 'X', FeatureCreepMC.CORN, 'Y', Items.PORKCHOP	, 'W', Items.CHICKEN , 'Z', Items.STICK
        });






        GameRegistry.addShapedRecipe(null, null, new ItemStack (SALAD, 1), new Object [] {
        "   ", " XW", "VYZ", 'X', FeatureCreepMC.TOMATO, 'Y', 	FeatureCreepMC.LETTUCE, 'W', FeatureCreepMC.RADISH , 'Z', Items.CARROT, 'V', Items.BOWL
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (CHEESE, 4), new Object [] {
        "   ", "YYY", " Y ", 'Y', Items.MILK_BUCKET	
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (BLT, 1), new Object [] {
        "   ", " XW", "VYZ", 'X', FeatureCreepMC.TOMATO, 'Y', 	FeatureCreepMC.LETTUCE, 'W', FeatureCreepMC.COOKED_BACON , 'Z', Items.BREAD, 'V', FeatureCreepMC.BUTTER_FOOD
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (CRAB_PATTY, 1), new Object [] {
        "   ", " XW", " YZ", 'X', FeatureCreepMC.TOMATO, 'Y', 	FeatureCreepMC.LETTUCE, 'W', FeatureCreepMC.COOKED_CRAB_MEAT , 'Z', Items.BREAD
        });






        GameRegistry.addShapedRecipe(null, null, new ItemStack (SALTED_POPCORN, 1), new Object [] {
        "   ", " X ", " Y ", 'X', FeatureCreepMC.Salt, 'Y',FeatureCreepMC.POPCORN 	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (BUTTERED_POPCORN, 1), new Object [] {
        "   ", " X ", " Y ", 'X', FeatureCreepMC.BUTTER_FOOD, 'Y',FeatureCreepMC.POPCORN 	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (BUTTERED_AND_SALTED_POPCORN, 1), new Object [] {
        " Z ", " X ", " Y ", 'X', FeatureCreepMC.BUTTER_FOOD, 'Y',FeatureCreepMC.POPCORN , 'Z',FeatureCreepMC.Salt	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (POPCORN_BAG, 1), new Object [] {
        "XXX", "YYY", "YYY", 'Y', FeatureCreepMC.BUTTERED_AND_SALTED_POPCORN, 'X', Items.PAPER	
        });




        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_BREAD, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.BREAD, 'X', Items.GOLD_NUGGET	
        });


        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_CHICKEN, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.CHICKEN, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_COD, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.COOKED_FISH, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_PORKCHOP, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.PORKCHOP, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (WATERMELON_SLICE, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.MELON, 'X', Items.GOLD_NUGGET	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_MUSHROOM_STEW, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.MUSHROOM_STEW, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_STEAK, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.COOKED_BEEF, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_COOKIE, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.COOKIE, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_POTATO, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.POTATO, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_PUMPKIN_PIE, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.PUMPKIN_PIE, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_ROTTON_FLESH, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.ROTTEN_FLESH, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_CARROT, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.CARROT, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_CANDYCANE, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', FeatureCreepMC.CANDY_CANE, 'X', Items.GOLD_NUGGET	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (GOLDEN_ROTTON_FLESH, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.ROTTEN_FLESH, 'X', Items.GOLD_NUGGET	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (ENCHANTED_GOLDEN_CARROT, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.CARROT, 'X', Items.GOLD_INGOT	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (ENCHANTED_GOLDEN_STEAK, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.COOKED_BEEF, 'X', Items.GOLD_INGOT	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (ENCHANTED_GOLDEN_COD, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.COOKED_FISH, 'X', Items.GOLD_INGOT	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (ENCHANTED_GOLDEN_COOKIE, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', Items.COOKIE, 'X', Items.GOLD_INGOT	
        });
        GameRegistry.addShapedRecipe(null, null, new ItemStack (ENCHANTED_GOLDEN_CANDYCANE, 1), new Object [] {
        "XXX", "XYX", "XXX", 'Y', FeatureCreepMC.CANDY_CANE, 'X', Items.GOLD_INGOT	
        });

        GameRegistry.addShapedRecipe(null, null, new ItemStack (RADDISH_STEW, 1), new Object [] {
        "XXX", "XXX", " Y ", 'X', FeatureCreepMC.RADISH, 'Y', Items.BOWL	
        });


        //GOLDEN_PUFFERFISH = new RawBacon(6060, 10, true).setUnlocalizedName("golden_pufferfish");
        //GOLDEN_SALMON = new RawBacon(6061, 10, true).setUnlocalizedName("golden_salmon");

        //ULTIMATE_APPLE = new RawBacon(6063, 15, true).setUnlocalizedName("ultimate_apple");


          GameRegistry.addShapedRecipe(null, null, new ItemStack(ULTIMATE_SWORD), new Object[] 
          		{ " X ", " Y ", " Z ", Character.valueOf('Z'), Items.IRON_INGOT, Character.valueOf('Y'), FeatureCreepMC.URANIUM, Character.valueOf('X'), FeatureCreepMC.TITANIUM });
           
        GameRegistry.addShapedRecipe(null, null, new ItemStack(ULTIMATE_PICKAXE), new Object[] 
        		  { "XYX", " Y ", " Z ", Character.valueOf('Z'), Items.IRON_INGOT, Character.valueOf('Y'), FeatureCreepMC.URANIUM, Character.valueOf('X'), FeatureCreepMC.TITANIUM });
        GameRegistry.addShapedRecipe(null, null, new ItemStack(ULTIMATE_SHOVEL), new Object[] 
        		 { " X ", " X ", " Z ", Character.valueOf('Z'), Items.IRON_INGOT, Character.valueOf('Y'), FeatureCreepMC.URANIUM, Character.valueOf('X'), FeatureCreepMC.TITANIUM });
        GameRegistry.addShapedRecipe(null, null, new ItemStack(ULTIMATE_HOE), new Object[] 
        		{ "XY ", " Z ", " Z ", Character.valueOf('Z'), Items.IRON_INGOT, Character.valueOf('Y'), FeatureCreepMC.URANIUM, Character.valueOf('X'), FeatureCreepMC.TITANIUM });
        */
        	
    
    }
  //  @Instance
//public static FeatureCreepMC instance;

@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

//@EventHandler
//public static void preInit(FMLPreInitializationEvent event) {}
//@EventHandler
//public static void init(FMLInitializationEvent event) {}
//@EventHandler
//public static void postInit(FMLPostInitializationEvent event) {}

    
//    
public static final List<Item> ITEMS = new ArrayList<Item>();
public static final List<Block> BLOCKS = new ArrayList<Block>();
public static final List<GemOre> GEM_ORE = new ArrayList<GemOre>();
public static final List<IngotOre> INGOT_ORE = new ArrayList<IngotOre>();
public static final List<DustOre> DUST_ORE = new ArrayList<DustOre>();



//public static final Item AMETHYST = new Amathest("amethyst");
public static final Block AMETHYST_BLOCK = new AmathestBlock("amethyst_block", Material.IRON, 3f, 3f, 3, null);
public static final Item RUBY = new Amathest("ruby");
public static final Block RUBY_BLOCK = new AmathestBlock("ruby_block", Material.IRON, 3f, 3f, 3, null);
public static final Item TIGERS_EYE = new Amathest("tigers_eye");
public static final Block TIGERS_EYE_BLOCK = new AmathestBlock("tigers_eye_block", Material.IRON, 3f, 3f, 3, null);
public static final Item Oil = new Amathest("oil");
public static final Item Salt = new Amathest("salt"); //Ant Functionality Delayed
public static final Item TITANIUM = new Amathest("titanium_ingot");
public static final Item TITANIUM_NUGGET = new Amathest("titanium_nugget");
public static final Block TITANIUM_BLOCK = new AmathestBlock("titanium_block", Material.IRON, 3f, 3f, 3, null);
public static final Item URANIUM = new Amathest("uranium_ingot");
public static final Item URANIUM_NUGGET = new Amathest("uranium_nugget");
public static final Block URANIUM_BLOCK = new AmathestBlock("uranium_block", Material.IRON, 3f, 3f, 3, null);
public static final Item ALUMINIUM = new Amathest("aluminium_ingot");
public static final Block ALUMINIUM_BLOCK = new AmathestBlock("aluminium_block", Material.IRON, 3f, 3f, 3, null);
public static final Item MobzillaScale = new Amathest("mobzilla_scale");
public static final Item QueenScale = new Amathest("queen_scale");
public static final Item MothScale = new Amathest("moth_scale");
public static final Item TIN_INGOT = new Amathest("tin_ingot");
public static final Item SILVER_INGOT = new Amathest("silver_ingot");
public static final Item PLATINUM_INGOT = new Amathest("platinum_ingot");
public static final Item COPPER_INGOT = new Amathest("copper_ingot");
public static final Item PinkTourmaline = new Amathest("pink_tourmaline_ingot");
//public static final Item PinkTourmalineNugget = new Amathest("pink_tourmaline_nugget");
public static final Item LAVA_EEL = new Amathest("lava_eel");
public static final Item PEACKOCK_FEATHER = new Amathest("peackock_feather");
public static final Item SAPPHIRE = new Amathest("sapphire");
public static final Item MINERS_DREAM = new MinersDream("miners_dream", -5, 5, 0, 5, 0, 50);
public static final Item LARGE_MINERS_DREAM = new MinersDream("large_miners_dream", -250, 250, -250, 250, -50, 250);
public static final Block SAPPHIRE_BLOCK = new AmathestBlock("sapphire_block", Material.IRON, 3f, 3f, 3, null);
public static final Block EYE_OF_ENDER_BLOCK = new AmathestBlock("eye_of_ender_block", Material.IRON, 3f, 3f, 3, null);
public static final Block ENDER_PEARL_BLOCK = new AmathestBlock("ender_pearl_block", Material.IRON, 3f, 3f, 3, null);
public static final Block COMPRESSED_POPPY_BLOCK = new AmathestBlock("compressed_poppy_block", Material.IRON, 3f, 3f, 3, null);
public static final Block ORANGE_BLOCK = new AmathestBlock("orange_block", Material.IRON, 3f, 3f, 3, null);
public static final Block BROWN_BLOCK = new AmathestBlock("brown_block", Material.IRON, 3f, 3f, 3, null);
public static final Block DARK_ORANGE_BLOCK = new AmathestBlock("dark_orange_block", Material.IRON, 3f, 3f, 3, null);
public static final Block DARK_BROWN_BLOCK = new AmathestBlock("dark_brown_block", Material.IRON, 3f, 3f, 3, null);
public static final Item GASOLINE_PETROL = new Amathest("gasoline_petrol");
public static final Item STEEL_INGOT = new Amathest("steel_ingot");

public static final Item PINK_TOURMALINE_NUGGET = new Amathest("pink_tourmaline_nugget");
public static final Item CATS_EYE_INGOT = new Amathest("cats_eye_ingot");
public static final Item CATS_EYE_NUGGET = new Amathest("cats_eye_nugget");
public static final Item WHITE_TOURMALINE_INGOT = new Amathest("white_torumaline_ingot");
public static final Item WHITE_TOURMALINE_NUGGET = new Amathest("white_torumaline_nugget");
public static final Item RUBY_NUGGET = new Amathest("ruby_nugget");
public static final Item ALUMINIUM_NUGGET = new Amathest("aluminium_nugget");
public static final Item COPPER_NUGGET = new Amathest("copper_nugget");
public static final Item SILVER_NUGGET = new Amathest("silver_nugget");
public static final Item PLATINUM_NUGGET = new Amathest("platinum_nugget");
public static final Item TIN_NUGGET = new Amathest("tin_nugget");
public static final Item STEEL_NUGGET = new Amathest("steel_nugget");
public static final Item SAPPHIRE_NUGGET = new Amathest("sapphire_nugget");
public static final Item AMETHYST_NUGGET = new Amathest("amethyst_nugget");
public static final Item TOURMALINE_INGOT = new Amathest("torumaline_ingot");
public static final Item TOURMALINE_NUGGET = new Amathest("tourmaline_nugget");







//Ores
//name material hardness resistance miningLevel tool dimension minY maxY group tries drop

public static final Block AMETHYST_ORE = new GemOre("amethyst_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 5, 15,BlockMatcher.forBlock(Blocks.STONE), FCItems.AMETHYST);
public static final Block RUBY_ORE = new GemOre("ruby_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 5, 10,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.RUBY);
public static final Block ALUMINIUM_ORE = new GemOre("aluminium_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 10, 20,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.ALUMINIUM);
public static final Block TIGERS_EYE_ORE = new GemOre("tigers_eye_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 5, 18,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.TIGERS_EYE);
public static final Block TITANIUM_ORE = new GemOre("titanium_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 4, 8,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.TITANIUM);
public static final Block URANIUM_ORE = new GemOre("uranium_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 3, 5,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.URANIUM);
public static final Block UNPROCESSED_OIL_ORE = new DustOre("unprocessed_oil_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 50, 20,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.Oil);
public static final Block SALT_ORE = new DustOre("salt_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 8, 20,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.Salt);
public static final Block SAPPHIRE_ORE = new GemOre("sapphire_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 5, 15,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.SAPPHIRE);
public static final Block GASOLINE_PETROL_ORE = new DustOre("gasoline_petrol_ore", Material.IRON, 4, 10, 10, null, DimensionType.OVERWORLD, 0, 200, 25, 25,BlockMatcher.forBlock(Blocks.STONE), FeatureCreepMC.GASOLINE_PETROL);



//Block order = String name, Material material, float hardness, float resistance, int miningLevel, String tool
//I will do troll blocks later but also with better Ores like Uranium or Titanium and worser Ores Like Coal/Oil or Salt






////Armour Materials
public static final ArmorMaterial SAND_ARMOUR = EnumHelper.addArmorMaterial("SAND", Reference.MODID + ":sand", 3, new int[] {1, 1, 1, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial RED_SAND_ARMOUR = EnumHelper.addArmorMaterial("RED_SAND", Reference.MODID + ":red+sand", 3, new int[] {1, 1, 1, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial DIRT_ARMOUR = EnumHelper.addArmorMaterial("DIRT", Reference.MODID + ":dirt", 5, new int[] {1, 2, 2, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial COARSE_DIRT_ARMOUR = EnumHelper.addArmorMaterial("COARSE_DIRT", Reference.MODID + ":coarse_dirt",  6, new int[] {1, 2, 2, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F ); 
public static final ArmorMaterial GRAVEL_ARMOUR = EnumHelper.addArmorMaterial("GRAVEL", Reference.MODID + ":gravel", 8, new int[] {1, 2, 2, 1}, 7, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial CACTUS_ARMOUR = EnumHelper.addArmorMaterial("CACTUS", Reference.MODID + ":cactus", 4, new int[] {2, 2, 2, 1}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial COBBLE_STONE_ARMOUR = EnumHelper.addArmorMaterial("COBBLE_STONE", Reference.MODID + ":cobble_stone", 15, new int[] {2, 3, 3, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial SUN_STONE_ARMOUR = EnumHelper.addArmorMaterial("SUN_STONE", Reference.MODID + ":sun_stone", 8, new int[] {2, 3, 2, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial CRYSTAL_WOOD_PLANK_ARMOUR = EnumHelper.addArmorMaterial("CRYSTAL_WOOD_PLANK", Reference.MODID + ":crystal_wood_plank",  20, new int[] {2, 3, 3, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial BONE_ARMOUR = EnumHelper.addArmorMaterial("BONE", Reference.MODID + ":bone",  7, new int[] {2, 3, 2, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial WOOD_PLANK_ARMOUR = EnumHelper.addArmorMaterial("WOOD_PLANK", Reference.MODID + ":wood_plank", 12, new int[] {2, 2, 2, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial WOOD_BLOCK_ARMOUR = EnumHelper.addArmorMaterial("WOOD_BLOCK", Reference.MODID + ":wood_block", 20, new int[] {3, 3, 3, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial NETHERRACK_BLOODSTONE_ARMOUR = EnumHelper.addArmorMaterial("NETHERRACK_BLOODSTONE", Reference.MODID + ":netherrack_bloodstone", 17, new int[] {3, 4, 4, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial STONE_ARMOUR = EnumHelper.addArmorMaterial("STONE", Reference.MODID + ":stone", 24, new int[] {3, 4, 3, 3}, 13, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial CRYSTAL_WOOD_ARMOUR = EnumHelper.addArmorMaterial("CRYSTAL_WOOD", Reference.MODID + ":crystal_wood", 20, new int[] {4, 4, 4, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial FLINT_ARMOUR = EnumHelper.addArmorMaterial("FLINT", Reference.MODID + ":flint",  25, new int[] {4, 4, 4, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F ); 
public static final ArmorMaterial COAL_ARMOUR = EnumHelper.addArmorMaterial("COAL", Reference.MODID + ":coal", 17, new int[] {4, 4, 4, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial KYANITE_ARMOUR = EnumHelper.addArmorMaterial("KYANITE", Reference.MODID + ":kyanite", 21, new int[] {4, 5, 4, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F ); 
public static final ArmorMaterial WAX_ARMOUR = EnumHelper.addArmorMaterial("WAX", Reference.MODID + ":wax", 17, new int[] {4, 5, 5, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial LAPIS_ARMOUR = EnumHelper.addArmorMaterial("LAPIS", Reference.MODID + ":lapis", 17, new int[] {5, 5, 5, 5}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial RED_STONE_ARMOUR = EnumHelper.addArmorMaterial("RED_STONE", Reference.MODID + ":red_stone", 15, new int[] {5, 6, 5, 5}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial RED_STONE_BLOCK_ARMOUR = EnumHelper.addArmorMaterial("RED_STONE_BLOCK", Reference.MODID + ":red_stone_block", 22, new int[] {10, 10, 10, 10}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial QUARTZ_ARMOUR = EnumHelper.addArmorMaterial("QUARTZ", Reference.MODID + ":quartz", 22, new int[] {6, 7, 7, 6}, 43, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial QUARTZ_BLOCK_ARMOUR = EnumHelper.addArmorMaterial("QUARTZ_BLOCK", Reference.MODID + ":quartz_block", 30, new int[] {11, 12, 11, 11}, 45, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial END_STONE_ARMOUR = EnumHelper.addArmorMaterial("END_STONE", Reference.MODID + ":end_stone", 20, new int[] {8, 8, 8, 7}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial LEAD_ARMOUR = EnumHelper.addArmorMaterial("LEAD", Reference.MODID + ":lead",  30, new int[] {8, 8, 8, 8}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial CALCIUM_ARMOUR = EnumHelper.addArmorMaterial("CALCIUM", Reference.MODID + ":calcium", 30, new int[] {8, 9, 9, 8}, 32, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial URANIUM_ARMOUR = EnumHelper.addArmorMaterial("URANIUM", Reference.MODID + ":uranium", 25, new int[] {21, 22, 21, 21}, 35, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial TITANIUM_ARMOUR = EnumHelper.addArmorMaterial("TITANIUM", Reference.MODID + ":titanium",  35, new int[] {22, 23, 23, 22}, 27, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial CELESTIAL_ARMOUR = EnumHelper.addArmorMaterial("CELESTIAL", Reference.MODID + ":celestial", 35, new int[] {75, 75, 75, 75}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial EXTRA_CELESTIAL_ARMOUR = EnumHelper.addArmorMaterial("EXTRA_CELESTIAL", Reference.MODID + ":extra_celestial", 50, new int[] {150, 150, 150, 150}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial STEEL_ARMOUR = EnumHelper.addArmorMaterial("STEEL", Reference.MODID + ":steel", 45, new int[] {25, 25, 25, 25}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial OBSIDIAN_ARMOUR = EnumHelper.addArmorMaterial("OBSIDIAN", Reference.MODID + ":obsidian", 43, new int[] {22, 23, 23, 22}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourSetOSEmerald = EnumHelper.addArmorMaterial("EMERALD", Reference.MODID + ":emerald", 25, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourSetAmethyst = EnumHelper.addArmorMaterial("AMETHYST", Reference.MODID + ":amethyst", 25, new int[] {10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial RubyArmour = EnumHelper.addArmorMaterial("RUBY", Reference.MODID + ":ruby", 50, new int[] {15, 25, 25, 15}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial RUBY_ON_RAILS_Armour= EnumHelper.addArmorMaterial("RUBY_ON_RAILS", Reference.MODID + ":ruby_on_rails", 55, new int[] {30, 70, 70, 30}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F ); 
public static final ArmorMaterial TigerEyeArmour = EnumHelper.addArmorMaterial("TIGERS_EYE", Reference.MODID + ":tigers_eye", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial UltimateArmour = EnumHelper.addArmorMaterial("ULTIMATE", Reference.MODID + ":ultimate", 35, new int[] {50, 75, 75, 50}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourExperience = EnumHelper.addArmorMaterial("EXPERIENCE", Reference.MODID + ":experience", 25, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ROYAL_GUARDIAN_ARMOUR = EnumHelper.addArmorMaterial("ROYAL_GUARDIAN", Reference.MODID + ":royal_guardian", 35, new int[] {100, 150, 150, 100}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial QUEEN_SCALE_ARMOUR = EnumHelper.addArmorMaterial("QUEEN_SCALE", Reference.MODID + ":queen_scale", 20, new int[] {150, 200, 200, 150}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial MOBZILLA_SCALE_ARMOUR = EnumHelper.addArmorMaterial("MOBZILLA_SCALE", Reference.MODID + ":mobzilla_scale", 20, new int[] {75, 100, 100, 75}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial LAPIS_LAZUI_ARMOUR = EnumHelper.addArmorMaterial("LAPIS_BLOCK", Reference.MODID + ":lapis_block", 25, new int[] {5, 10, 10, 5}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial LAVA_Eel_ARMOUR = EnumHelper.addArmorMaterial("LAVA_EEL", Reference.MODID + ":lava_eel", 35, new int[] {20, 25, 25, 20}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial PEACOCK_FEATHER_ARMOUR = EnumHelper.addArmorMaterial("PEACOCK", Reference.MODID + ":peacock_feather", 50, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourPinkTourmaline = EnumHelper.addArmorMaterial("PINK_TOURMALINE", Reference.MODID + ":pink_tourmaline", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourTourmaline = EnumHelper.addArmorMaterial("TOURMALINE", Reference.MODID + ":tourmaline", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourWhiteTourmaline = EnumHelper.addArmorMaterial("WHITE_TOURMALINE", Reference.MODID + ":white_tourmaline", 35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourCopper = EnumHelper.addArmorMaterial("COPPER", Reference.MODID + ":copper", 15, new int[] {2, 5, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourSilver = EnumHelper.addArmorMaterial("SILVER", Reference.MODID + ":silver", 20, new int[] {4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourAluminium = EnumHelper.addArmorMaterial("ALUMINIUM", Reference.MODID + ":aluminium", 18, new int[] {3, 13, 13, 8}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourPlatinum = EnumHelper.addArmorMaterial("PLATINUM", Reference.MODID + ":platinum", 20, new int[] {4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial MOTH_SCALE_Armour = EnumHelper.addArmorMaterial("MOTH_SCALE", Reference.MODID + ":moth_scale", 18, new int[] {20, 30, 30, 20}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourSetSapphire = EnumHelper.addArmorMaterial("SAPPHIRE", Reference.MODID + ":sapphire", 25, new int[] {10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial CZArmourSlow = EnumHelper.addArmorMaterial("CZArmourSlow", Reference.MODID + ":czslow", 25, new int[] {2, 3, 3, 2}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );
public static final ArmorMaterial ArmourTin = EnumHelper.addArmorMaterial("TIN", Reference.MODID + ":tin", 35, new int[] {15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F );




//ToolMaterials

public static final ToolMaterial LEATHERTool = EnumHelper.addToolMaterial("LEATHER", 0, 59, 2.0F, 0.0F, 15);
public static final ToolMaterial CHAINMAILTool = EnumHelper.addToolMaterial("CHAINMAIL", 1, 131, 4.0F, 1.0F, 5);
public static final ToolMaterial SANDTool = EnumHelper.addToolMaterial("SAND", 0, 17, 1.0F, 0.0F, 10);
public static final ToolMaterial RED_SANDTool = EnumHelper.addToolMaterial("RED_SAND", 0, 17, 1.0F, 0.0F, 10);
public static final ToolMaterial DIRTTool = EnumHelper.addToolMaterial("DIRT", 0, 23, 1.0F, 0.0F, 10);
public static final ToolMaterial COARSE_DIRTTool = EnumHelper.addToolMaterial("COARSE", 1, 25, 5.0F, 1.0F, 10);
public static final ToolMaterial GRAVELTool = EnumHelper.addToolMaterial("GRAVEL", 1, 28, 5.0F, 1.0F, 10);
public static final ToolMaterial CACTUSTool = EnumHelper.addToolMaterial("CACTUS", 1, 20, 5.0F, 25.0F, 10);
public static final ToolMaterial SUN_STONETool = EnumHelper.addToolMaterial("SUN_STONE", 1, 30, 3.0F, 3.0F, 10);
public static final ToolMaterial BONETool = EnumHelper.addToolMaterial("BONE", 1, 27, 3.0F, 4.0F, 10);
public static final ToolMaterial CRYSTAL_WOOD_PLANKTool = EnumHelper.addToolMaterial("CRYSTAL_WOOD_PLANK", 2, 160, 5.0F, 5.0F, 10);
public static final ToolMaterial WOOD_BLOCKTool = EnumHelper.addToolMaterial("WOOD", 2, 175, 5.0F, 6.0F, 10);
public static final ToolMaterial STONETool = EnumHelper.addToolMaterial("STONE", 2, 200, 5.0F, 5.0F, 10);
public static final ToolMaterial NETHERRACK_BLOODSTONETool = EnumHelper.addToolMaterial("NETHERRACK_BLOODSTONE", 2, 145, 5.0F, 8.0F, 10);
public static final ToolMaterial CRYSTAL_WOODTool = EnumHelper.addToolMaterial("CRYSTAL_WOOD", 3, 165, 6.0F, 8.0F, 10);
public static final ToolMaterial FLINTTool = EnumHelper.addToolMaterial("FLINT", 3, 2500, 6.0F, 5.0F, 10);
public static final ToolMaterial COALTool = EnumHelper.addToolMaterial("COAL", 3, 2000, 8.0F, 7.0F, 10);
public static final ToolMaterial KYANITETool = EnumHelper.addToolMaterial("KYANITE", 4, 2000, 6.0F, 8.0F, 10);
public static final ToolMaterial WAXTool = EnumHelper.addToolMaterial("WAX", 4, 1000, 7.0F, 9.0F, 10);
public static final ToolMaterial LAPISTool = EnumHelper.addToolMaterial("LAPIS", 4,1100 , 7.0F, 9.0F, 10);
public static final ToolMaterial RED_STONETool = EnumHelper.addToolMaterial("RED_STONE", 5, 1200, 8.0F, 10.0F, 10);
public static final ToolMaterial QUARTZTool = EnumHelper.addToolMaterial("QUARTZ", 5, 1500, 8.0F, 10.0F, 10);
public static final ToolMaterial END_STONETool = EnumHelper.addToolMaterial("END_STONE", 6, 1100, 9.0F, 12.0F, 10);
public static final ToolMaterial LEADTool = EnumHelper.addToolMaterial("LEAD", 6, 5000, 9.0F, 11.0F, 10);
public static final ToolMaterial CALCIUMTool = EnumHelper.addToolMaterial("CALCIUM", 6, 1200, 9.0F, 15.0F, 10);
public static final ToolMaterial RED_STONE_BLOCKTool = EnumHelper.addToolMaterial("RED_STONE", 7, 3000, 9.0F, 13.0F, 10);
public static final ToolMaterial QUARTZ_BLOCKTool = EnumHelper.addToolMaterial("QUARTZ", 8, 4500, 14.0F, 17.0F, 10);
public static final ToolMaterial URANIUMTool = EnumHelper.addToolMaterial("URANIUM", 13, 4000, 30.0F, 23.0F, 10);
public static final ToolMaterial TITANIUMTool = EnumHelper.addToolMaterial("TITANIUM", 13, 5500, 27.0F, 20.0F, 10);
public static final ToolMaterial OBSIDIANTool = EnumHelper.addToolMaterial("OBSIDIAN", 13, 8000, 28.0F, 20.0F, 10);
public static final ToolMaterial STEELTool = EnumHelper.addToolMaterial("STEEL", 13, 8000, 29.0F, 24.0F, 10);
public static final ToolMaterial CELESTIALTool = EnumHelper.addToolMaterial("CELESTIAL", 35, 25000, 80.0F, 34.0F, 10);
public static final ToolMaterial EXTRA_CELESTIALTool = EnumHelper.addToolMaterial("EXTRA_CELESTIAL", 50, 30000, 95.0F, 50.0F, 10);

public static final ToolMaterial OSTOOLEmerald = EnumHelper.addToolMaterial("EMERALD", 9, 2000, 10.0F, 25.0F, 10);
//public static final ToolMaterial AmethystTool = EnumHelper.addToolMaterial("AMETHYST", 8, 5000, 20.0F, 18.0F, 20);
public static final ToolMaterial RubyTool = EnumHelper.addToolMaterial("RUBY", 12, 8000, 20.0F, 30.0F, 20);
public static final ToolMaterial RUBY_ON_RAILSTool = EnumHelper.addToolMaterial("RUBY_ON_RAILS", 25, 25000, 60.0F, 30.0F, 25);
public static final ToolMaterial EXPERIENCETool = EnumHelper.addToolMaterial("EXPERIENCE", 9, 2000, 10.0F, 25.0F, 10);
public static final ToolMaterial SapphireTool = EnumHelper.addToolMaterial("SAPPHIRE", 8, 5000, 20.0F, 18.0F, 20);
public static final ToolMaterial TigerEyeTool = EnumHelper.addToolMaterial("TIGERS_EYE",  10, 6000, 15.0F, 20.0F, 15);
public static final ToolMaterial COPPERTool = EnumHelper.addToolMaterial("COPPER",  6, 3000, 9.0F, 10.0F, 15);
public static final ToolMaterial LAPIS_BLOCKTool = EnumHelper.addToolMaterial("LAPIS_BLOCK",  6, 4000, 9.0F, 11.0F, 15);
public static final ToolMaterial ALUMINIUMTool = EnumHelper.addToolMaterial("ALUMINIUM",  6, 4000, 9.0F, 12.0F, 15);
public static final ToolMaterial SILVERTool = EnumHelper.addToolMaterial("SILVER",  7, 4000, 10.0F, 13.0F, 15);
public static final ToolMaterial  PLATINUMTool = EnumHelper.addToolMaterial("PLATINUM",  7, 5000, 10.0F, 14.0F, 15);
public static final ToolMaterial  TINTool = EnumHelper.addToolMaterial("TIN",  9, 1000, 21.0F, 15.0F, 15);
public static final ToolMaterial PEACOCK_FEATHERTool = EnumHelper.addToolMaterial("PEACOCK_FEATHER",  10, 3000, 23.0F, 18.0F, 15);
public static final ToolMaterial PINK_TOURMALINETool = EnumHelper.addToolMaterial("PINK_TOURMALINE",  10, 4000, 22.0F, 16.0F, 15);
public static final ToolMaterial WHITE_TOURMALINETool = EnumHelper.addToolMaterial("WHITE_TOURMALINE",  10, 4000, 22.0F, 16.0F, 15);
public static final ToolMaterial TOURMALINETool = EnumHelper.addToolMaterial("TOURMALINE",  10, 4000, 22.0F, 16.0F, 15);
public static final ToolMaterial UltimateTool = EnumHelper.addToolMaterial("ULTIMATE", 30, 30000, 30.0F, 70.0F, 45);
public static final ToolMaterial LAVA_EELTool = EnumHelper.addToolMaterial("LAVA_EEL", 13, 3000, 30.0F, 25.0F, 20);
public static final ToolMaterial MOTH_SCALETool = EnumHelper.addToolMaterial("MOTH_SCALE", 14, 2000, 33.0F, 28.0F, 20);
public static final ToolMaterial MOBZILLA_SCALETool = EnumHelper.addToolMaterial("MOBZILLA_SCALE", 40, 20000, 85.0F, 30.0F, 20);
public static final ToolMaterial ROYAL_GUARDIANTool = EnumHelper.addToolMaterial("ROYAL_GUARDIAN", 45, 30000, 90.0F, 45.0F, 20);
public static final ToolMaterial QUEEN_SCALETool = EnumHelper.addToolMaterial("QUEEN_SCALE", 55, 20000, 100.0F, 55.0F, 20);
public static final ToolMaterial OptimisedTool = EnumHelper.addToolMaterial("OPTIMISED", 250, 25000, 100.0F, 8.0F, 45);



//public static final Item EmeraldAxe = new EmeraldAxe("EmeraldAxe", OSTOOLEmerald);
public static final Item EMERALD_PICKAXE = new EmeraldPic("emerald_pickaxe", OSTOOLEmerald);
public static final Item EMERALD_HOLE = new EmeraldHoe("emerald_hoe", OSTOOLEmerald);
public static final Item EMERALD_SWORD = new EmeraldSword("emerald_sword", OSTOOLEmerald);
public static final Item EMERALD_SHOVEL = new EmeraldShovel("emerald_shovel", OSTOOLEmerald);
public static final Item EMERALD_HELMET = new EmeraldArmour("emerald_helmet", ArmourSetOSEmerald, 1, EntityEquipmentSlot.HEAD);
public static final Item EMERALD_CHESTPLATE = new EmeraldArmour("emerald_chestplate", ArmourSetOSEmerald, 1, EntityEquipmentSlot.CHEST);
public static final Item EMERALD_LEGGINGS = new EmeraldArmour("emerald_leggings", ArmourSetOSEmerald, 2, EntityEquipmentSlot.LEGS);
public static final Item EMERALD_BOOTS = new EmeraldArmour("emerald_boots", ArmourSetOSEmerald, 1, EntityEquipmentSlot.FEET);
public static final Item EMERALD_AXE = new EmeraldAxe("emerald_axe", OSTOOLEmerald);

//same properties as Emeralds
//public static final Item AMETHYST_PICKAXE = new EmeraldPic("amethyst_pickaxe", AmethystTool);
//public static final Item AMETHYST_HOE = new EmeraldHoe("amethyst_hoe", AmethystTool);
//public static final Item AMETHYST_SWORD = new EmeraldSword("amethyst_sword", AmethystTool);
//public static final Item AMETHYST_SHOVEL = new EmeraldShovel("amethyst_shovel", AmethystTool);
//public static final Item AMETHYST_HELMET = new EmeraldArmour("amethyst_helmet", ArmourSetAmethyst, 1, EntityEquipmentSlot.HEAD);
//public static final Item AMETHYST_CHESTPLATE = new EmeraldArmour("amethyst_chestplate", ArmourSetAmethyst, 1, EntityEquipmentSlot.CHEST);
//public static final Item AMETHYST_LEGGINGS = new EmeraldArmour("amethyst_leggings", ArmourSetAmethyst, 2, EntityEquipmentSlot.LEGS);
//public static final Item AMETHYST_BOOTS = new EmeraldArmour("amethyst_boots", ArmourSetAmethyst, 1, EntityEquipmentSlot.FEET);
//public static final Item AMETHYST_AXE = new EmeraldAxe("amethyst_axe", AmethystTool);


//same properties as AMETHYST
public static final Item SAPPHIRE_PICKAXE = new EmeraldPic("sapphire_pickaxe", SapphireTool);
public static final Item SAPPHIRE_HOE = new EmeraldHoe("sapphire_hoe", SapphireTool);
public static final Item SAPPHIRE_SWORD = new EmeraldSword("sapphire_sword", SapphireTool);
public static final Item SAPPHIRE_SHOVEL = new EmeraldShovel("sapphire_shovel", SapphireTool);
public static final Item SAPPHIRE_HELMET = new EmeraldArmour("sapphire_helmet", ArmourSetSapphire, 1, EntityEquipmentSlot.HEAD);
public static final Item SAPPHIRE_CHESTPLATE = new EmeraldArmour("sapphire_chestplate", ArmourSetSapphire, 1, EntityEquipmentSlot.CHEST);
public static final Item SAPPHIRE_LEGGINGS = new EmeraldArmour("sapphire_leggings", ArmourSetSapphire, 2, EntityEquipmentSlot.LEGS);
public static final Item SAPPHIRE_BOOTS = new EmeraldArmour("sapphire_boots", ArmourSetSapphire, 1, EntityEquipmentSlot.FEET);
public static final Item SAPPHIRE_AXE = new EmeraldAxe("sapphire_axe", SapphireTool);




//Same Properties as Emeralds
public static final Item RUBY_PICKAXE = new EmeraldPic("ruby_pickaxe", RubyTool);
public static final Item RUBY_HOE = new EmeraldHoe("ruby_hoe", RubyTool);
public static final Item RUBY_SWORD = new EmeraldSword("ruby_sword", RubyTool);
public static final Item RUBY_SHOVEL = new EmeraldShovel("ruby_shovel", RubyTool);
public static final Item RUBY_HELMET = new EmeraldArmour("ruby_helmet", RubyArmour, 1, EntityEquipmentSlot.HEAD);
public static final Item RUBY_CHESTPLATE = new EmeraldArmour("ruby_chestplate", RubyArmour, 1, EntityEquipmentSlot.CHEST);
public static final Item RUBY_LEGGINGS = new EmeraldArmour("ruby_leggings", RubyArmour, 2, EntityEquipmentSlot.LEGS);
public static final Item RUBY_BOOTS = new EmeraldArmour("ruby_boots", RubyArmour, 1, EntityEquipmentSlot.FEET);
public static final Item RUBY_AXE = new EmeraldAxe("ruby_axe", RubyTool);


//Same Properties as Emeralds
public static final Item TIGERS_EYE_PICKAXE = new EmeraldPic("tigers_eye_pickaxe", TigerEyeTool);
public static final Item TIGERS_EYE_HOE = new EmeraldHoe("tigers_eye_hoe", TigerEyeTool);
public static final Item TIGERS_EYE_SWORD = new EmeraldSword("tigers_eye_sword", TigerEyeTool);
public static final Item TIGERS_EYE_SHOVEL = new EmeraldShovel("tigers_eye_shovel", TigerEyeTool);
public static final Item TIGERS_EYE_HELMET = new EmeraldArmour("tigers_eye_helmet", TigerEyeArmour, 1, EntityEquipmentSlot.HEAD);
public static final Item TIGERS_EYE_CHESTPLATE = new EmeraldArmour("tigers_eye_chestplate", TigerEyeArmour, 1, EntityEquipmentSlot.CHEST);
public static final Item TIGERS_EYE_LEGGINGS = new EmeraldArmour("tigers_eye_leggings", TigerEyeArmour, 2, EntityEquipmentSlot.LEGS);
public static final Item TIGERS_EYE_BOOTS = new EmeraldArmour("tigers_eye_boots", TigerEyeArmour, 1, EntityEquipmentSlot.FEET);
public static final Item TIGERS_EYE_AXE = new EmeraldAxe("tigers_eye_axe", TigerEyeTool);

//Same Properties as Emeralds
public static final Item OPTIMISED_PICKAXE = new EnchantedPic("optimised_pickaxe", OptimisedTool);
public static final Item OPTIMISED_SHOVEL = new EnchantedShovel("optimised_shovel", OptimisedTool);


public static final Item ULTIMATE_HELMET = new EnchantedArmour("ultimate_helmet", UltimateArmour, 1, EntityEquipmentSlot.HEAD);
public static final Item ULTIMATE_CHESTPLATE = new EnchantedArmour("ultimate_chestplate", UltimateArmour, 1, EntityEquipmentSlot.CHEST);
public static final Item ULTIMATE_LEGGINGS = new EnchantedArmour("ultimate_leggings", UltimateArmour, 2, EntityEquipmentSlot.LEGS);
public static final Item ULTIMATE_BOOTS = new EnchantedArmour("ultimate_boots", UltimateArmour, 1, EntityEquipmentSlot.FEET);
public static final Item ULTIMATE_PICKAXE = new EnchantedPic("ultimate_pickaxe", UltimateTool);
public static final Item ULTIMATE_HOE = new EnchantedHoe("ultimate_hoe", UltimateTool);
public static final Item ULTIMATE_SWORD = new EnchantedSword("ultimate_sword", UltimateTool);
public static final Item ULTIMATE_SHOVEL = new EnchantedShovel("ultimate_shovel", UltimateTool);
public static final Item ULTIMATE_AXE = new EnchantedAxe("ultimate_axe", UltimateTool);





public static final Item TIN_HELMET = new EmeraldArmour("tin_helmet", ArmourTin, 1, EntityEquipmentSlot.HEAD);
public static final Item TIN_CHESTPLATE = new EmeraldArmour("tin_chestplate", ArmourTin, 1, EntityEquipmentSlot.CHEST);
public static final Item TIN_LEGGINGS = new EmeraldArmour("tin_leggings", ArmourTin, 2, EntityEquipmentSlot.LEGS);
public static final Item TIN_BOOTS = new EmeraldArmour("tin_boots", ArmourTin, 1, EntityEquipmentSlot.FEET);
public static final Item TIN_AXE = new EmeraldAxe("tin_axe", TINTool);
public static final Item TIN_PICKAXE = new EmeraldPic("tin_pickaxe", TINTool);
public static final Item TIN_HOE = new EmeraldHoe("tin_hoe", TINTool);
public static final Item TIN_SWORD = new EmeraldSword("tin_sword", TINTool);
public static final Item TIN_SHOVEL = new EmeraldShovel("tin_shovel", TINTool);

public static final Item COPPER_HELMET = new EmeraldArmour("copper_helmet", ArmourCopper, 1, EntityEquipmentSlot.HEAD);
public static final Item COPPER_CHESTPLATE = new EmeraldArmour("copper_chestplate", ArmourCopper, 1, EntityEquipmentSlot.CHEST);
public static final Item COPPER_LEGGINGS = new EmeraldArmour("copper_leggings", ArmourCopper, 2, EntityEquipmentSlot.LEGS);
public static final Item COPPER_BOOTS = new EmeraldArmour("copper_boots", ArmourCopper, 1, EntityEquipmentSlot.FEET);
public static final Item COPPER_AXE = new EmeraldAxe("copper_axe", COPPERTool);
public static final Item COPPER_PICKAXE = new EmeraldPic("copper_pickaxe", COPPERTool);
public static final Item COPPER_HOE = new EmeraldHoe("copper_hoe", COPPERTool);
public static final Item COPPER_SWORD = new EmeraldSword("copper_sword", COPPERTool);
public static final Item COPPER_SHOVEL = new EmeraldShovel("copper_shovel", COPPERTool);

public static final Item PLATINUM_HELMET = new EmeraldArmour("platinum_helmet", ArmourPlatinum, 1, EntityEquipmentSlot.HEAD);
public static final Item PLATINUM_CHESTPLATE = new EmeraldArmour("platinum_chestplate", ArmourPlatinum, 1, EntityEquipmentSlot.CHEST);
public static final Item PLATNIUM_LEGGINGS = new EmeraldArmour("platinum_leggings", ArmourPlatinum, 2, EntityEquipmentSlot.LEGS);
public static final Item PLATINUM_BOOTS = new EmeraldArmour("platinum_boots", ArmourPlatinum, 1, EntityEquipmentSlot.FEET);
public static final Item PLATINUM_AXE = new EmeraldAxe("platinum_axe", PLATINUMTool);
public static final Item PLATINUM_PICKAXE = new EmeraldPic("platinum_pickaxe", PLATINUMTool);
public static final Item PLATINUM_HOE = new EmeraldHoe("platinum_hoe", PLATINUMTool);
public static final Item PLATINUM_SWORD = new EmeraldSword("platinum_sword", PLATINUMTool);
public static final Item PLATINUM_SHOVEL = new EmeraldShovel("platinum_shovel", PLATINUMTool);


public static final Item SILVER_HELMET = new EmeraldArmour("silver_helmet", ArmourSilver, 1, EntityEquipmentSlot.HEAD);
public static final Item SILVER_CHESTPLATE = new EmeraldArmour("silver_chestplate", ArmourSilver, 1, EntityEquipmentSlot.CHEST);
public static final Item SILVER_LEGGINGS = new EmeraldArmour("silver_leggings", ArmourSilver, 2, EntityEquipmentSlot.LEGS);
public static final Item SILVER_BOOTS = new EmeraldArmour("silver_boots", ArmourSilver, 1, EntityEquipmentSlot.FEET);
public static final Item SILVER_AXE = new EmeraldAxe("silver_axe", SILVERTool);
public static final Item SILVER_PICKAXE = new EmeraldPic("silver_pickaxe", SILVERTool);
public static final Item SILVER_HOE = new EmeraldHoe("silver_hoe", SILVERTool);
public static final Item SILVER_SWORD = new EmeraldSword("silver_sword", SILVERTool);
public static final Item SILVER_SHOVEL = new EmeraldShovel("silver_shovel", SILVERTool);

public static final Item ALUMINIUM_HELMET = new EmeraldArmour("aluminium_helmet", ArmourAluminium, 1, EntityEquipmentSlot.HEAD);
public static final Item ALUMINIUM_CHESTPLATE = new EmeraldArmour("aluminium_chestplate", ArmourAluminium, 1, EntityEquipmentSlot.CHEST);
public static final Item ALUMINIUM_LEGGINGS = new EmeraldArmour("aluminium_leggings", ArmourAluminium, 2, EntityEquipmentSlot.LEGS);
public static final Item ALUMINIUM_BOOTS = new EmeraldArmour("aluminium_boots", ArmourAluminium, 1, EntityEquipmentSlot.FEET);
public static final Item ALUMINIUM_AXE = new EmeraldAxe("aluminium_axe", ALUMINIUMTool);
public static final Item ALUMINIUM_PICKAXE = new EmeraldPic("aluminium_pickaxe", ALUMINIUMTool);
public static final Item ALUMINIUM_HOE = new EmeraldHoe("aluminium_hoe", ALUMINIUMTool);
public static final Item ALUMINIUM_SWORD = new EmeraldSword("aluminium_sword", ALUMINIUMTool);
public static final Item ALUMINIUM_SHOVEL = new EmeraldShovel("aluminium_shovel", ALUMINIUMTool);

public static final Item EXPERIENCE_HELMET = new EnchantedArmour("experience_helmet", ArmourExperience, 1, EntityEquipmentSlot.HEAD);
public static final Item EXPERIENCE_CHESTPLATE = new EnchantedArmour("experience_chestplate", ArmourExperience, 1, EntityEquipmentSlot.CHEST);
public static final Item EXPERIENCE_LEGGINGS = new EnchantedArmour("experience_leggings", ArmourExperience, 2, EntityEquipmentSlot.LEGS);
public static final Item EXPERIENCE_BOOTS = new EnchantedArmour("experience_boots", ArmourExperience, 1, EntityEquipmentSlot.FEET);
public static final Item EXPERIENCE_AXE = new EnchantedAxe("experience_axe", EXPERIENCETool);
public static final Item EXPERIENCE_PICKAXE = new EnchantedPic("experience_pickaxe", EXPERIENCETool);
public static final Item EXPERIENCE_HOE = new EnchantedHoe("experience_hoe", EXPERIENCETool);
public static final Item EXPERIENCE_SWORD = new EnchantedSword("experience_sword", EXPERIENCETool);
public static final Item EXPERIENCE_SHOVEL = new EnchantedShovel("experience_shovel", EXPERIENCETool);

public static final Item ROYAL_GUARDIAN_HELMET = new EnchantedArmour("royal_guardian_helmet", ROYAL_GUARDIAN_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item ROYAL_GUARDIAN_CHESTPLATE = new EnchantedArmour("royal_guardian_chestplate", ROYAL_GUARDIAN_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item ROYAL_GUARDIAN_LEGGINGS = new EnchantedArmour("royal_guardian_leggings", ROYAL_GUARDIAN_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item ROYAL_GUARDIAN_BOOTS = new EnchantedArmour("royal_guardian_boots", ROYAL_GUARDIAN_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item ROYAL_GUARDIAN_AXE = new EnchantedAxe("royal_guardian_axe", ROYAL_GUARDIANTool);
public static final Item ROYAL_GUARDIAN_PICKAXE = new EnchantedPic("royal_guardian_pickaxe", ROYAL_GUARDIANTool);
public static final Item ROYAL_GUARDIAN_HOE = new EnchantedHoe("royal_guardian_hoe", ROYAL_GUARDIANTool);
public static final Item ROYAL_GUARDIAN_SWORD = new EnchantedSword("royal_guardian_sword", ROYAL_GUARDIANTool);
public static final Item ROYAL_GUARDIAN_SHOVEL = new EnchantedShovel("royal_guardian_shovel", ROYAL_GUARDIANTool);

public static final Item QUEEN_SCALE_HELMET = new EnchantedArmour("queen_scale_helmet", QUEEN_SCALE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item QUEEN_SCALE_CHESTPLATE = new EnchantedArmour("queen_scale_chestplate", QUEEN_SCALE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item QUEEN_SCALE_LEGGINGS = new EnchantedArmour("queen_scale_leggings", QUEEN_SCALE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item QUEEN_SCALE_BOOTS = new EnchantedArmour("queen_scale_boots", QUEEN_SCALE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item QUEEN_SCALE_AXE = new EnchantedAxe("queen_scale_axe", QUEEN_SCALETool);
public static final Item QUEEN_SCALE_PICKAXE = new EnchantedPic("queen_scale_pickaxe", QUEEN_SCALETool);
public static final Item QUEEN_SCALE_HOE = new EnchantedHoe("queen_scale_hoe", QUEEN_SCALETool);
public static final Item QUEEN_SCALE_SWORD = new EnchantedSword("queen_scale_sword", QUEEN_SCALETool);
public static final Item QUEEN_SCALE_SHOVEL = new EnchantedShovel("queen_scale_shovel", QUEEN_SCALETool);

public static final Item MOBZILLA_SCALE_HELMET = new EnchantedArmour("mobzilla_scale_helmet", MOBZILLA_SCALE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item MOBZILLA_SCALE_CHESTPLATE = new EnchantedArmour("mobzilla_scale_chestplate", MOBZILLA_SCALE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item MOBZILLA_SCALE_LEGGINGS = new EnchantedArmour("mobzilla_scale_leggings", MOBZILLA_SCALE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item MOBZILLA_SCALE_BOOTS = new EnchantedArmour("mobzilla_scale_boots", MOBZILLA_SCALE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item MOBZILLA_SCALE_AXE = new EnchantedAxe("mobzilla_scale_axe", MOBZILLA_SCALETool);
public static final Item MOBZILLA_SCALE_PICKAXE = new EnchantedPic("mobzilla_scale_pickaxe", MOBZILLA_SCALETool);
public static final Item MOBZILLA_SCALE_HOE = new EnchantedHoe("mobzilla_scale_hoe", MOBZILLA_SCALETool);
public static final Item MOBZILLA_SCALE_SWORD = new EnchantedSword("mobzilla_scale_sword", MOBZILLA_SCALETool);
public static final Item MOBZILLA_SCALE_SHOVEL = new EnchantedShovel("mobzilla_scale_shovel", MOBZILLA_SCALETool);

public static final Item LAPIS_LAZUI_HELMET = new EnchantedArmour("lapis_block_helmet", LAPIS_LAZUI_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item LAPIS_LAZUI_CHESTPLATE = new EnchantedArmour("lapis_block_chestplate", LAPIS_LAZUI_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item LAPIS_LAZUI_LEGGINGS = new EnchantedArmour("lapis_block_leggings", LAPIS_LAZUI_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item LAPIS_LAZUI_BOOTS = new EnchantedArmour("lapis_block_boots", LAPIS_LAZUI_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item LAPIS_LAZULI_AXE = new EnchantedAxe("lapis_block_axe", LAPIS_BLOCKTool);
public static final Item LAPIS_LAZULI_PICKAXE = new EnchantedPic("lapis_block_pickaxe", LAPIS_BLOCKTool);
public static final Item LAPIS_LAZULI_HOE = new EnchantedHoe("lapis_block_hoe", LAPIS_BLOCKTool);
public static final Item LAPIS_LAZULI_SWORD = new EnchantedSword("lapis_block_sword", LAPIS_BLOCKTool);
public static final Item LAPIS_LAZULI_SHOVEL = new EnchantedShovel("lapis_block_shovel", LAPIS_BLOCKTool);


public static final Item LAVA_EEL_HELMET = new EnchantedArmour("lava_eel_helmet", LAVA_Eel_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item LAVA_EEL_CHESTPLATE = new EnchantedArmour("lava_eel_chestplate", LAVA_Eel_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item LAVA_EEL_LEGGINGS = new EnchantedArmour("lava_eel_leggings", LAVA_Eel_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item LAVA_EEL_BOOTS = new EnchantedArmour("lava_eel_boots", LAVA_Eel_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item LAVA_EEL_AXE = new EnchantedAxe("lava_eel_axe", LAVA_EELTool);
public static final Item LAVA_EEL_PICKAXE = new EnchantedPic("lava_eel_pickaxe", LAVA_EELTool);
public static final Item LAVA_EEL_HOE = new EnchantedHoe("lava_eel_hoe", LAVA_EELTool);
public static final Item LAVA_EEL_SWORD = new EnchantedSword("lava_eel_sword", LAVA_EELTool);
public static final Item LAVA_EEL_SHOVEL = new EnchantedShovel("lava_eel_shovel", LAVA_EELTool);

public static final Item PEACOCK_FEATHER_HELMET = new EnchantedArmour("peacock_feather_helmet", PEACOCK_FEATHER_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item PEACOCK_FEATHER_CHESTPLATE = new EnchantedArmour("peacock_feather_chestplate", PEACOCK_FEATHER_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item PEACOCK_FEATHER_LEGGINGS = new EnchantedArmour("peacock_feather_leggings", PEACOCK_FEATHER_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item PEACOCK_FEATHER_BOOTS = new EnchantedArmour("peacock_feather_boots", PEACOCK_FEATHER_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item PEACOCK_FEATHER_AXE = new EnchantedAxe("peacock_feather_axe", PEACOCK_FEATHERTool);
public static final Item PEACOCK_FEATHER_PICKAXE = new EnchantedPic("peacock_feather_pickaxe", PEACOCK_FEATHERTool);
public static final Item PEACOCK_FEATHER_HOE = new EnchantedHoe("peacock_feather_hoe", PEACOCK_FEATHERTool);
public static final Item PEACOCK_FEATHER_SWORD = new EnchantedSword("peacock_feather_sword", PEACOCK_FEATHERTool);
public static final Item PEACOCK_FEATHER_SHOVEL = new EnchantedShovel("peacock_feather_shovel", PEACOCK_FEATHERTool);

public static final Item PINK_TOURMALINE_HELMET = new EmeraldArmour("pink_tourmaline_helmet", ArmourPinkTourmaline, 1, EntityEquipmentSlot.HEAD);
public static final Item PINK_TOURMALINE_CHESTPLATE = new EmeraldArmour("pink_tourmaline_chestplate", ArmourPinkTourmaline, 1, EntityEquipmentSlot.CHEST);
public static final Item PINK_TOURMALINE_LEGGINGS = new EmeraldArmour("pink_tourmaline_leggings", ArmourPinkTourmaline, 2, EntityEquipmentSlot.LEGS);
public static final Item PINK_TOURMALINE_BOOTS = new EmeraldArmour("pink_tourmaline_boots", ArmourPinkTourmaline, 1, EntityEquipmentSlot.FEET);
public static final Item PINK_TOURMALINE_AXE = new EmeraldAxe("pink_tourmaline_axe", PINK_TOURMALINETool);
public static final Item PINK_TOURMALINE_PICKAXE = new EmeraldPic("pink_tourmaline_pickaxe", PINK_TOURMALINETool);
public static final Item PINK_TOURMALINE_HOE = new EmeraldHoe("pink_tourmaline_hoe", PINK_TOURMALINETool);
public static final Item PINK_TOURMALINE_SWORD = new EmeraldSword("pink_tourmaline_sword", PINK_TOURMALINETool);
public static final Item PINK_TOURMALINE_SHOVEL = new EmeraldShovel("pink_tourmaline_shovel", PINK_TOURMALINETool);


public static final Item MOTH_SCALE_HELMET = new EnchantedArmour("moth_scale_helmet", MOTH_SCALE_Armour, 1, EntityEquipmentSlot.HEAD);
public static final Item MOTH_SCALE_CHESTPLATE = new EnchantedArmour("moth_scale_chestplate", MOTH_SCALE_Armour, 1, EntityEquipmentSlot.CHEST);
public static final Item MOTH_SCALE_LEGGINGS = new EnchantedArmour("moth_scale_leggings", MOTH_SCALE_Armour, 2, EntityEquipmentSlot.LEGS);
public static final Item MOTH_SCALE_BOOTS = new EnchantedArmour("moth_scale_boots", MOTH_SCALE_Armour, 1, EntityEquipmentSlot.FEET);
public static final Item MOTH_SCALE_AXE = new EnchantedAxe("moth_scale_axe", MOTH_SCALETool);
public static final Item MOTH_SCALE_PICKAXE = new EnchantedPic("moth_scale_pickaxe", MOTH_SCALETool);
public static final Item MOTH_SCALE_HOE = new EnchantedHoe("moth_scale_hoe", MOTH_SCALETool);
public static final Item MOTH_SCALE_SWORD = new EnchantedSword("moth_scale_sword", MOTH_SCALETool);
public static final Item MOTH_SCALE_SHOVEL = new EnchantedShovel("moth_scale_shovel", MOTH_SCALETool);

public static final Item CZ_SLOW_BOOTS = new CZSlowArmour("cz_slow_boots", CZArmourSlow, 1, EntityEquipmentSlot.FEET);








public static final Item RUBY_ON_RAILS_HELMET = new EnchantedArmour("ruby_on_rails_helmet", RUBY_ON_RAILS_Armour, 1, EntityEquipmentSlot.HEAD);
public static final Item RUBY_ON_RAILS_CHESTPLATE = new EnchantedArmour("ruby_on_rails_chestplate", RUBY_ON_RAILS_Armour, 1, EntityEquipmentSlot.CHEST);
public static final Item RUBY_ON_RAILS_LEGGINGS = new EnchantedArmour("ruby_on_rails_leggings", RUBY_ON_RAILS_Armour, 2, EntityEquipmentSlot.LEGS);
public static final Item RUBY_ON_RAILS_BOOTS = new EnchantedArmour("ruby_on_rails_boots", RUBY_ON_RAILS_Armour, 1, EntityEquipmentSlot.FEET);
public static final Item RUBY_ON_RAILS_AXE = new EnchantedAxe("ruby_on_rails_axe", RUBY_ON_RAILSTool);
public static final Item RUBY_ON_RAILS_PICKAXE = new EnchantedPic("ruby_on_rails_pickaxe", RUBY_ON_RAILSTool);
public static final Item RUBY_ON_RAILS_HOE = new EnchantedHoe("ruby_on_rails_hoe", RUBY_ON_RAILSTool);
public static final Item RUBY_ON_RAILS_SWORD = new EnchantedSword("ruby_on_rails_sword", RUBY_ON_RAILSTool);
public static final Item RUBY_ON_RAILS_SHOVEL = new EnchantedShovel("ruby_on_rails_shovel", RUBY_ON_RAILSTool);









public static final Item WHITE_TOURMALINE_HELMET = new EmeraldArmour("white_tourmaline_helmet", ArmourWhiteTourmaline, 1, EntityEquipmentSlot.HEAD);
public static final Item WHITE_TOURMALINE_CHESTPLATE = new EmeraldArmour("white_tourmaline_chestplate", ArmourWhiteTourmaline, 1, EntityEquipmentSlot.CHEST);
public static final Item WHITE_TOURMALINE_LEGGINGS = new EmeraldArmour("white_tourmaline_leggings", ArmourWhiteTourmaline, 2, EntityEquipmentSlot.LEGS);
public static final Item WHITE_TOURMALINE_BOOTS = new EmeraldArmour("white_tourmaline_boots", ArmourWhiteTourmaline, 1, EntityEquipmentSlot.FEET);
public static final Item WHITE_TOURMALINE_AXE = new EmeraldAxe("white_tourmaline_axe", WHITE_TOURMALINETool);
public static final Item WHITE_TOURMALINE_PICKAXE = new EmeraldPic("white_tourmaline_pickaxe", WHITE_TOURMALINETool);
public static final Item WHITE_TOURMALINE_HOE = new EmeraldHoe("white_tourmaline_hoe", WHITE_TOURMALINETool);
public static final Item WHITE_TOURMALINE_SWORD = new EmeraldSword("white_tourmaline_sword", WHITE_TOURMALINETool);
public static final Item WHITE_TOURMALINE_SHOVEL = new EmeraldShovel("white_tourmaline_shovel", WHITE_TOURMALINETool);

public static final Item TOURMALINE_HELMET = new EmeraldArmour("tourmaline_helmet", ArmourTourmaline, 1, EntityEquipmentSlot.HEAD);
public static final Item TOURMALINE_CHESTPLATE = new EmeraldArmour("tourmaline_chestplate", ArmourTourmaline, 1, EntityEquipmentSlot.CHEST);
public static final Item TOURMALINE_LEGGINGS = new EmeraldArmour("tourmaline_leggings", ArmourTourmaline, 2, EntityEquipmentSlot.LEGS);
public static final Item TOURMALINE_BOOTS = new EmeraldArmour("tourmaline_boots", ArmourTourmaline, 1, EntityEquipmentSlot.FEET);
public static final Item TOURMALINE_AXE = new EmeraldAxe("tourmaline_axe", TOURMALINETool);
public static final Item TOURMALINE_PICKAXE = new EmeraldPic("tourmaline_pickaxe", TOURMALINETool);
public static final Item TOURMALINE_HOE = new EmeraldHoe("tourmaline_hoe", TOURMALINETool);
public static final Item TOURMALINE_SWORD = new EmeraldSword("tourmaline_sword", TOURMALINETool);
public static final Item TOURMALINE_SHOVEL = new EmeraldShovel("tourmaline_shovel", TOURMALINETool);



public static final Item DIRT_HELMET = new EmeraldArmour("dirt_helmet", DIRT_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item DIRT_CHESTPLATE = new EmeraldArmour("dirt_chestplate", DIRT_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item DIRT_LEGGINGS = new EmeraldArmour("dirt_leggings", DIRT_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item DIRT_BOOTS = new EmeraldArmour("dirt_boots", DIRT_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item DIRT_AXE = new EmeraldAxe("dirt_axe", DIRTTool);
public static final Item DIRT_PICKAXE = new EmeraldPic("dirt_pickaxe", DIRTTool);
public static final Item DIRT_HOE = new EmeraldHoe("dirt_hoe", DIRTTool);
public static final Item DIRT_SWORD = new EmeraldSword("dirt_sword", DIRTTool);
public static final Item DIRT_SHOVEL = new EmeraldShovel("dirt_shovel", DIRTTool);


public static final Item STONE_HELMET = new EmeraldArmour("stone_helmet", STONE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item STONE_CHESTPLATE = new EmeraldArmour("stone_chestplate", STONE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item STONE_LEGGINGS = new EmeraldArmour("stone_leggings", STONE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item STONE_BOOTS = new EmeraldArmour("stone_boots", STONE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item STONE_AXE = new EmeraldAxe("stone_axe", STONETool);
public static final Item STONE_PICKAXE = new EmeraldPic("stone_pickaxe", STONETool);
public static final Item STONE_HOE = new EmeraldHoe("stone_hoe", STONETool);
public static final Item STONE_SWORD = new EmeraldSword("stone_sword", STONETool);
public static final Item STONE_SHOVEL = new EmeraldShovel("stone_shovel", STONETool);

public static final Item COBBLE_STONE_HELMET = new EmeraldArmour("cobble_stone_helmet", COBBLE_STONE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item COBBLE_STONE_CHESTPLATE = new EmeraldArmour("cobble_stone_chestplate", COBBLE_STONE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item COBBLE_STONE_LEGGINGS = new EmeraldArmour("cobble_stone_leggings", COBBLE_STONE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item COBBLE_STONE_BOOTS = new EmeraldArmour("cobble_stone_boots", COBBLE_STONE_ARMOUR, 1, EntityEquipmentSlot.FEET);

public static final Item WOOD_PLANK_HELMET = new EmeraldArmour("wood_plank_helmet", WOOD_PLANK_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item WOOD_PLANK_CHESTPLATE = new EmeraldArmour("wood_plank_chestplate", WOOD_PLANK_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item WOOD_PLANK_LEGGINGS = new EmeraldArmour("wood_plank_leggings", WOOD_PLANK_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item WOOD_PLANK_BOOTS = new EmeraldArmour("wood_plank_boots", WOOD_PLANK_ARMOUR, 1, EntityEquipmentSlot.FEET);


public static final Item WOOD_BLOCK_HELMET = new EmeraldArmour("wood_block_helmet", WOOD_BLOCK_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item WOOD_BLOCK_CHESTPLATE = new EmeraldArmour("wood_block_chestplate", WOOD_BLOCK_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item WOOD_BLOCK_LEGGINGS = new EmeraldArmour("wood_block_leggings", WOOD_BLOCK_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item WOOD_BLOCK_BOOTS = new EmeraldArmour("wood_block_boots", WOOD_BLOCK_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item WOOD_BLOCK_AXE = new EmeraldAxe("wood_block_axe", WOOD_BLOCKTool);
public static final Item WOOD_BLOCK_PICKAXE = new EmeraldPic("wood_block_pickaxe", WOOD_BLOCKTool);
public static final Item WOOD_BLOCK_HOE = new EmeraldHoe("wood_block_hoe", WOOD_BLOCKTool);
public static final Item WOOD_BLOCK_SWORD = new EmeraldSword("wood_block_sword", WOOD_BLOCKTool);
public static final Item WOOD_BLOCK_SHOVEL = new EmeraldShovel("wood_block_shovel", WOOD_BLOCKTool);


public static final Item NETHERRACK_BLOODSTONE_HELMET = new EmeraldArmour("netherrack_bloodstone_helmet", NETHERRACK_BLOODSTONE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item NETHERRACK_BLOODSTONE_CHESTPLATE = new EmeraldArmour("netherrack_bloodstone_chestplate", NETHERRACK_BLOODSTONE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item NETHERRACK_BLOODSTONE_LEGGINGS = new EmeraldArmour("netherrack_bloodstone_leggings", NETHERRACK_BLOODSTONE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item NETHERRACK_BLOODSTONE_BOOTS = new EmeraldArmour("netherrack_bloodstone_boots", NETHERRACK_BLOODSTONE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item NETHERRACK_BLOODSTONE_AXE = new EmeraldAxe("netherrack_bloodstone_axe", NETHERRACK_BLOODSTONETool);
public static final Item NETHERRACK_BLOODSTONE_PICKAXE = new EmeraldPic("netherrack_bloodstone_pickaxe", NETHERRACK_BLOODSTONETool);
public static final Item NETHERRACK_BLOODSTONE_HOE = new EmeraldHoe("netherrack_bloodstone_hoe", NETHERRACK_BLOODSTONETool);
public static final Item NETHERRACK_BLOODSTONE_SWORD = new EmeraldSword("netherrack_bloodstone_sword", NETHERRACK_BLOODSTONETool);
public static final Item NETHERRACK_BLOODSTONE_SHOVEL = new EmeraldShovel("netherrack_bloodstone_shovel", NETHERRACK_BLOODSTONETool);


public static final Item LAPIS_HELMET = new EmeraldArmour("lapis_helmet", LAPIS_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item LAPIS_CHESTPLATE = new EmeraldArmour("lapis_chestplate", LAPIS_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item LAPIS_LEGGINGS = new EmeraldArmour("lapis_leggings", LAPIS_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item LAPIS_BOOTS = new EmeraldArmour("lapis_boots", LAPIS_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item LAPIS_AXE = new EmeraldAxe("lapis_axe", LAPISTool);
public static final Item LAPIS_PICKAXE = new EmeraldPic("lapis_pickaxe", LAPISTool);
public static final Item LAPIS_HOE = new EmeraldHoe("lapis_hoe", LAPISTool);
public static final Item LAPIS_SWORD = new EmeraldSword("lapis_sword", LAPISTool);
public static final Item LAPIS_SHOVEL = new EmeraldShovel("lapis_shovel", LAPISTool);

public static final Item COARSE_DIRT_HELMET = new EmeraldArmour("coarse_dirt_helmet", COARSE_DIRT_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item COARSE_DIRT_CHESTPLATE = new EmeraldArmour("coarse_dirt_chestplate", COARSE_DIRT_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item COARSE_DIRT_LEGGINGS = new EmeraldArmour("coarse_dirt_leggings", COARSE_DIRT_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item COARSE_DIRT_BOOTS = new EmeraldArmour("coarse_dirt_boots", COARSE_DIRT_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item COARSE_DIRT_AXE = new EmeraldAxe("coarse_dirt_axe", COARSE_DIRTTool);
public static final Item COARSE_DIRT_PICKAXE = new EmeraldPic("coarse_dirt_pickaxe", COARSE_DIRTTool);
public static final Item COARSE_DIRT_HOE = new EmeraldHoe("coarse_dirt_hoe", COARSE_DIRTTool);
public static final Item COARSE_DIRT_SWORD = new EmeraldSword("coarse_dirt_sword", COARSE_DIRTTool);
public static final Item COARSE_DIRT_SHOVEL = new EmeraldShovel("coarse_dirt_shovel", COARSE_DIRTTool);

public static final Item CACTUS_HELMET = new EmeraldArmour("cactus_helmet", CACTUS_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item CACTUS_CHESTPLATE = new EmeraldArmour("cactus_chestplate", CACTUS_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item CACTUS_LEGGINGS = new EmeraldArmour("cactus_leggings", CACTUS_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item CACTUS_BOOTS = new EmeraldArmour("cactus_boots", CACTUS_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item CACTUS_AXE = new EmeraldAxe("cactus_axe", CACTUSTool);
public static final Item CACTUS_PICKAXE = new EmeraldPic("cactus_pickaxe", CACTUSTool);
public static final Item CACTUS_HOE = new EmeraldHoe("cactus_hoe", CACTUSTool);
public static final Item CACTUS_SWORD = new EmeraldSword("cactus_sword", CACTUSTool);
public static final Item CACTUS_SHOVEL = new EmeraldShovel("cactus_shovel", CACTUSTool);

public static final Item GRAVEL_HELMET = new EmeraldArmour("gravel_helmet", GRAVEL_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item GRAVEL_CHESTPLATE = new EmeraldArmour("gravel_chestplate", GRAVEL_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item GRAVEL_LEGGINGS = new EmeraldArmour("gravel_leggings", GRAVEL_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item GRAVEL_BOOTS = new EmeraldArmour("gravel_boots", GRAVEL_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item GRAVEL_AXE = new EmeraldAxe("gravel_axe", GRAVELTool);
public static final Item GRAVEL_PICKAXE = new EmeraldPic("gravel_pickaxe", GRAVELTool);
public static final Item GRAVEL_HOE = new EmeraldHoe("gravel_hoe", GRAVELTool);
public static final Item GRAVEL_SWORD = new EmeraldSword("gravel_sword", GRAVELTool);
public static final Item GRAVEL_SHOVEL = new EmeraldShovel("gravel_shovel", GRAVELTool);



public static final Item FLINT_HELMET = new EmeraldArmour("flint_helmet", FLINT_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item FLINT_CHESTPLATE = new EmeraldArmour("flint_chestplate", FLINT_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item FLINT_LEGGINGS = new EmeraldArmour("flint_leggings", FLINT_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item FLINT_BOOTS = new EmeraldArmour("flint_boots", FLINT_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item FLINT_AXE = new EmeraldAxe("flint_axe", FLINTTool);
public static final Item FLINT_PICKAXE = new EmeraldPic("flint_pickaxe", FLINTTool);
public static final Item FLINT_HOE = new EmeraldHoe("flint_hoe", FLINTTool);
public static final Item FLINT_SWORD = new EmeraldSword("flint_sword", FLINTTool);
public static final Item FLINT_SHOVEL = new EmeraldShovel("flint_shovel", FLINTTool);

public static final Item RED_STONE_HELMET = new EmeraldArmour("red_stone_helmet", RED_STONE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item RED_STONE_CHESTPLATE = new EmeraldArmour("red_stone_chestplate", RED_STONE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item RED_STONE_LEGGINGS = new EmeraldArmour("red_stone_leggings", RED_STONE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item RED_STONE_BOOTS = new EmeraldArmour("red_stone_boots", RED_STONE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item RED_STONE_AXE = new EmeraldAxe("red_stone_axe", RED_STONETool);
public static final Item RED_STONE_PICKAXE = new EmeraldPic("red_stone_pickaxe", RED_STONETool);
public static final Item RED_STONE_HOE = new EmeraldHoe("red_stone_hoe", RED_STONETool);
public static final Item RED_STONE_SWORD = new EmeraldSword("red_stone_sword", RED_STONETool);
public static final Item RED_STONE_SHOVEL = new EmeraldShovel("red_stone_shovel", RED_STONETool);


public static final Item RED_STONE_BLOCK_HELMET = new EmeraldArmour("red_stone_block_helmet", RED_STONE_BLOCK_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item RED_STONE_BLOCK_CHESTPLATE = new EmeraldArmour("red_stone_block_chestplate", RED_STONE_BLOCK_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item RED_STONE_BLOCK_LEGGINGS = new EmeraldArmour("red_stone_block_leggings", RED_STONE_BLOCK_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item RED_STONE_BLOCK_BOOTS = new EmeraldArmour("red_stone_block_boots", RED_STONE_BLOCK_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item RED_STONE_BLOCK_AXE = new EmeraldAxe("red_stone_block_axe", RED_STONE_BLOCKTool);
public static final Item RED_STONE_BLOCK_PICKAXE = new EmeraldPic("red_stone_block_pickaxe", RED_STONE_BLOCKTool);
public static final Item RED_STONE_BLOCK_HOE = new EmeraldHoe("red_stone_block_hoe", RED_STONE_BLOCKTool);
public static final Item RED_STONE_BLOCK_SWORD = new EmeraldSword("red_stone_block_sword", RED_STONE_BLOCKTool);
public static final Item RED_STONE_BLOCK_SHOVEL = new EmeraldShovel("red_stone_block_shovel", RED_STONE_BLOCKTool);

public static final Item QUARTZ_HELMET = new EmeraldArmour("quartz_helmet", QUARTZ_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item QUARTZ_CHESTPLATE = new EmeraldArmour("quartz_chestplate", QUARTZ_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item QUARTZ_LEGGINGS = new EmeraldArmour("quartz_leggings", QUARTZ_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item QUARTZ_BOOTS = new EmeraldArmour("quartz_boots", QUARTZ_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item QUARTZ_AXE = new EmeraldAxe("quartz_axe", QUARTZTool);
public static final Item QUARTZ_PICKAXE = new EmeraldPic("quartz_pickaxe", QUARTZTool);
public static final Item QUARTZ_HOE = new EmeraldHoe("quartz_hoe", QUARTZTool);
public static final Item QUARTZ_SWORD = new EmeraldSword("quartz_sword", QUARTZTool);
public static final Item QUARTZ_SHOVEL = new EmeraldShovel("quartz_shovel", QUARTZTool);


public static final Item QUARTZ_BLOCK_HELMET = new EmeraldArmour("quartz_block_helmet", QUARTZ_BLOCK_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item QUARTZ_BLOCK_CHESTPLATE = new EmeraldArmour("quartz_block_chestplate", QUARTZ_BLOCK_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item QUARTZ_BLOCK_LEGGINGS = new EmeraldArmour("quartz_block_leggings", QUARTZ_BLOCK_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item QUARTZ_BLOCK_BOOTS = new EmeraldArmour("quartz_block_boots", QUARTZ_BLOCK_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item QUARTZ_BLOCK_AXE = new EmeraldAxe("quartz_block_axe", QUARTZ_BLOCKTool);
public static final Item QUARTZ_BLOCK_PICKAXE = new EmeraldPic("quartz_block_pickaxe", QUARTZ_BLOCKTool);
public static final Item QUARTZ_BLOCK_HOE = new EmeraldHoe("quartz_block_hoe", QUARTZ_BLOCKTool);
public static final Item QUARTZ_BLOCK_SWORD = new EmeraldSword("quartz_block_sword", QUARTZ_BLOCKTool);
public static final Item QUARTZ_BLOCK_SHOVEL = new EmeraldShovel("quartz_block_shovel", QUARTZ_BLOCKTool);




public static final Item RED_SAND_HELMET = new EmeraldArmour("red_sand_helmet", RED_SAND_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item RED_SAND_CHESTPLATE = new EmeraldArmour("red_sand_chestplate", RED_SAND_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item RED_SAND_LEGGINGS = new EmeraldArmour("red_sand_leggings", RED_SAND_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item RED_SAND_BOOTS = new EmeraldArmour("red_sand_boots", RED_SAND_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item RED_SAND_AXE = new EmeraldAxe("red_sand_axe", RED_SANDTool);
public static final Item RED_SAND_PICKAXE = new EmeraldPic("red_sand_pickaxe", RED_SANDTool);
public static final Item RED_SAND_HOE = new EmeraldHoe("red_sand_hoe", RED_SANDTool);
public static final Item RED_SAND_SWORD = new EmeraldSword("red_sand_sword", RED_SANDTool);
public static final Item RED_SAND_SHOVEL = new EmeraldShovel("red_sand_shovel", RED_SANDTool);

public static final Item SAND_HELMET = new EmeraldArmour("sand_helmet", SAND_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item SAND_CHESTPLATE = new EmeraldArmour("sand_chestplate", SAND_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item SAND_LEGGINGS = new EmeraldArmour("sand_leggings", SAND_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item SAND_BOOTS = new EmeraldArmour("sand_boots", SAND_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item SAND_AXE = new EmeraldAxe("sand_axe", SANDTool);
public static final Item SAND_PICKAXE = new EmeraldPic("sand_pickaxe", SANDTool);
public static final Item SAND_HOE = new EmeraldHoe("sand_hoe", SANDTool);
public static final Item SAND_SWORD = new EmeraldSword("sand_sword", SANDTool);
public static final Item SAND_SHOVEL = new EmeraldShovel("sand_shovel", SANDTool);



public static final Item COAL_HELMET = new EmeraldArmour("coal_helmet", COAL_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item COAL_CHESTPLATE = new EmeraldArmour("coal_chestplate", COAL_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item COAL_LEGGINGS = new EmeraldArmour("coal_leggings", COAL_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item COAL_BOOTS = new EmeraldArmour("coal_boots", COAL_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item COAL_AXE = new EmeraldAxe("coal_axe", COALTool);
public static final Item COAL_PICKAXE = new EmeraldPic("coal_pickaxe", COALTool);
public static final Item COAL_HOE = new EmeraldHoe("coal_hoe", COALTool);
public static final Item COAL_SWORD = new EmeraldSword("coal_sword", COALTool);
public static final Item COAL_SHOVEL = new EmeraldShovel("coal_shovel", COALTool);

public static final Item LEAD_HELMET = new EmeraldArmour("lead_helmet", LEAD_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item LEAD_CHESTPLATE = new EmeraldArmour("lead_chestplate", LEAD_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item LEAD_LEGGINGS = new EmeraldArmour("lead_leggings", LEAD_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item LEAD_BOOTS = new EmeraldArmour("lead_boots", LEAD_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item LEAD_AXE = new EmeraldAxe("lead_axe", LEADTool);
public static final Item LEAD_PICKAXE = new EmeraldPic("lead_pickaxe", LEADTool);
public static final Item LEAD_HOE = new EmeraldHoe("lead_hoe", LEADTool);
public static final Item LEAD_SWORD = new EmeraldSword("lead_sword", LEADTool);
public static final Item LEAD_SHOVEL = new EmeraldShovel("lead_shovel", LEADTool);

public static final Item CALCIUM_HELMET = new EmeraldArmour("calcium_helmet", CALCIUM_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item CALCIUM_CHESTPLATE = new EmeraldArmour("calcium_chestplate", CALCIUM_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item CALCIUM_LEGGINGS = new EmeraldArmour("calcium_leggings", CALCIUM_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item CALCIUM_BOOTS = new EmeraldArmour("calcium_boots", CALCIUM_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item CALCIUM_AXE = new EmeraldAxe("calcium_axe", CALCIUMTool);
public static final Item CALCIUM_PICKAXE = new EmeraldPic("calcium_pickaxe", CALCIUMTool);
public static final Item CALCIUM_HOE = new EmeraldHoe("calcium_hoe", CALCIUMTool);
public static final Item CALCIUM_SWORD = new EmeraldSword("calcium_sword", CALCIUMTool);
public static final Item CALCIUM_SHOVEL = new EmeraldShovel("calcium_shovel", CALCIUMTool);




public static final Item BONE_HELMET = new EmeraldArmour("bone_helmet", BONE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item BONE_CHESTPLATE = new EmeraldArmour("bone_chestplate", BONE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item BONE_LEGGINGS = new EmeraldArmour("bone_leggings", BONE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item BONE_BOOTS = new EmeraldArmour("bone_boots", BONE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item BONE_AXE = new EmeraldAxe("bone_axe", BONETool);
public static final Item BONE_PICKAXE = new EmeraldPic("bone_pickaxe", BONETool);
public static final Item BONE_HOE = new EmeraldHoe("bone_hoe", BONETool);
public static final Item BONE_SWORD = new EmeraldSword("bone_sword", BONETool);
public static final Item BONE_SHOVEL = new EmeraldShovel("bone_shovel", BONETool);

public static final Item STEEL_HELMET = new EmeraldArmour("steel_helmet", STEEL_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item STEEL_CHESTPLATE = new EmeraldArmour("steel_chestplate", STEEL_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item STEEL_LEGGINGS = new EmeraldArmour("steel_leggings", STEEL_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item STEEL_BOOTS = new EmeraldArmour("steel_boots", STEEL_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item STEEL_AXE = new EmeraldAxe("steel_axe", STEELTool);
public static final Item STEEL_PICKAXE = new EmeraldPic("steel_pickaxe", STEELTool);
public static final Item STEEL_HOE = new EmeraldHoe("steel_hoe", STEELTool);
public static final Item STEEL_SWORD = new EmeraldSword("steel_sword", STEELTool);
public static final Item STEEL_SHOVEL = new EmeraldShovel("steel_shovel", STEELTool);

public static final Item TITANIUM_HELMET = new EmeraldArmour("titanium_helmet", TITANIUM_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item TITANIUM_CHESTPLATE = new EmeraldArmour("titanium_chestplate", TITANIUM_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item TITANIUM_LEGGINGS = new EmeraldArmour("titanium_leggings", TITANIUM_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item TITANIUM_BOOTS = new EmeraldArmour("titanium_boots", TITANIUM_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item TITANIUM_AXE = new EmeraldAxe("titanium_axe", TITANIUMTool);
public static final Item TITANIUM_PICKAXE = new EmeraldPic("titanium_pickaxe", TITANIUMTool);
public static final Item TITANIUM_HOE = new EmeraldHoe("titanium_hoe", TITANIUMTool);
public static final Item TITANIUM_SWORD = new EmeraldSword("titanium_sword", TITANIUMTool);
public static final Item TITANIUM_SHOVEL = new EmeraldShovel("titanium_shovel", TITANIUMTool);

public static final Item URANIUM_HELMET = new EmeraldArmour("uranium_helmet", URANIUM_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item URANIUM_CHESTPLATE = new EmeraldArmour("uranium_chestplate", URANIUM_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item URANIUM_LEGGINGS = new EmeraldArmour("uranium_leggings", URANIUM_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item URANIUM_BOOTS = new EmeraldArmour("uranium_boots", URANIUM_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item URANIUM_AXE = new EmeraldAxe("uranium_axe", URANIUMTool);
public static final Item URANIUM_PICKAXE = new EmeraldPic("uranium_pickaxe", URANIUMTool);
public static final Item URANIUM_HOE = new EmeraldHoe("uranium_hoe", URANIUMTool);
public static final Item URANIUM_SWORD = new EmeraldSword("uranium_sword", URANIUMTool);
public static final Item URANIUM_SHOVEL = new EmeraldShovel("uranium_shovel", URANIUMTool);



public static final Item WAX_HELMET = new EmeraldArmour("wax_helmet", WAX_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item WAX_CHESTPLATE = new EmeraldArmour("wax_chestplate", WAX_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item WAX_LEGGINGS = new EmeraldArmour("wax_leggings", WAX_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item WAX_BOOTS = new EmeraldArmour("wax_boots", WAX_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item WAX_AXE = new EmeraldAxe("wax_axe", WAXTool);
public static final Item WAX_PICKAXE = new EmeraldPic("wax_pickaxe", WAXTool);
public static final Item WAX_HOE = new EmeraldHoe("wax_hoe", WAXTool);
public static final Item WAX_SWORD = new EmeraldSword("wax_sword", WAXTool);
public static final Item WAX_SHOVEL = new EmeraldShovel("wax_shovel", WAXTool);



public static final Item OBSIDIAN_HELMET = new EmeraldArmour("obsidian_helmet", OBSIDIAN_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item OBSIDIAN_CHESTPLATE = new EmeraldArmour("obsidian_chestplate", OBSIDIAN_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item OBSIDIAN_LEGGINGS = new EmeraldArmour("obsidian_leggings", OBSIDIAN_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item OBSIDIAN_BOOTS = new EmeraldArmour("obsidian_boots", OBSIDIAN_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item OBSIDIAN_AXE = new EmeraldAxe("obsidian_axe", OBSIDIANTool);
public static final Item OBSIDIAN_PICKAXE = new EmeraldPic("obsidian_pickaxe", OBSIDIANTool);
public static final Item OBSIDIAN_HOE = new EmeraldHoe("obsidian_hoe", OBSIDIANTool);
public static final Item OBSIDIAN_SWORD = new EmeraldSword("obsidian_sword", OBSIDIANTool);
public static final Item OBSIDIAN_SHOVEL = new EmeraldShovel("obsidian_shovel", OBSIDIANTool);


public static final Item CELESTIAL_HELMET = new EmeraldArmour("celestial_helmet", CELESTIAL_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item CELESTIAL_CHESTPLATE = new EmeraldArmour("celestial_chestplate", CELESTIAL_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item CELESTIAL_LEGGINGS = new EmeraldArmour("celestial_leggings", CELESTIAL_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item CELESTIAL_BOOTS = new EmeraldArmour("celestial_boots", CELESTIAL_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item CELESTIAL_AXE = new EmeraldAxe("celestial_axe", CELESTIALTool);
public static final Item CELESTIAL_PICKAXE = new EmeraldPic("celestial_pickaxe", CELESTIALTool);
public static final Item CELESTIAL_HOE = new EmeraldHoe("celestial_hoe", CELESTIALTool);
public static final Item CELESTIAL_SWORD = new EmeraldSword("celestial_sword", CELESTIALTool);
public static final Item CELESTIAL_SHOVEL = new EmeraldShovel("celestial_shovel", CELESTIALTool);

public static final Item EXTRA_CELESTIAL_HELMET = new EmeraldArmour("extra_celestial_helmet", EXTRA_CELESTIAL_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item EXTRA_CELESTIAL_CHESTPLATE = new EmeraldArmour("extra_celestial_chestplate", EXTRA_CELESTIAL_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item EXTRA_CELESTIAL_LEGGINGS = new EmeraldArmour("extra_celestial_leggings", EXTRA_CELESTIAL_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item EXTRA_CELESTIAL_BOOTS = new EmeraldArmour("extra_celestial_boots", EXTRA_CELESTIAL_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item EXTRA_CELESTIAL_AXE = new EmeraldAxe("extra_celestial_axe", EXTRA_CELESTIALTool);
public static final Item EXTRA_CELESTIAL_PICKAXE = new EmeraldPic("extra_celestial_pickaxe", EXTRA_CELESTIALTool);
public static final Item EXTRA_CELESTIAL_HOE = new EmeraldHoe("extra_celestial_hoe", EXTRA_CELESTIALTool);
public static final Item EXTRA_CELESTIAL_SWORD = new EmeraldSword("extra_celestial_sword", EXTRA_CELESTIALTool);
public static final Item EXTRA_CELESTIAL_SHOVEL = new EmeraldShovel("extra_celestial_shovel", EXTRA_CELESTIALTool);

public static final Item SUN_STONE_HELMET = new EmeraldArmour("sun_stone_helmet", SUN_STONE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item SUN_STONE_CHESTPLATE = new EmeraldArmour("sun_stone_chestplate", SUN_STONE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item SUN_STONE_LEGGINGS = new EmeraldArmour("sun_stone_leggings", SUN_STONE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item SUN_STONE_BOOTS = new EmeraldArmour("sun_stone_boots", SUN_STONE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item SUN_STONE_AXE = new EmeraldAxe("sun_stone_axe", SUN_STONETool);
public static final Item SUN_STONE_PICKAXE = new EmeraldPic("sun_stone_pickaxe", SUN_STONETool);
public static final Item SUN_STONE_HOE = new EmeraldHoe("sun_stone_hoe", SUN_STONETool);
public static final Item SUN_STONE_SWORD = new EmeraldSword("sun_stone_sword", SUN_STONETool);
public static final Item SUN_STONE_SHOVEL = new EmeraldShovel("sun_stone_shovel", SUN_STONETool);


public static final Item CRYSTAL_WOOD_HELMET = new EmeraldArmour("crystal_wood_helmet", CRYSTAL_WOOD_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item CRYSTAL_WOOD_CHESTPLATE = new EmeraldArmour("crystal_wood_chestplate", CRYSTAL_WOOD_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item CRYSTAL_WOOD_LEGGINGS = new EmeraldArmour("crystal_wood_leggings", CRYSTAL_WOOD_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item CRYSTAL_WOOD_BOOTS = new EmeraldArmour("crystal_wood_boots", CRYSTAL_WOOD_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item CRYSTAL_WOOD_AXE = new EmeraldAxe("crystal_wood_axe", CRYSTAL_WOODTool);
public static final Item CRYSTAL_WOOD_PICKAXE = new EmeraldPic("crystal_wood_pickaxe", CRYSTAL_WOODTool);
public static final Item CRYSTAL_WOOD_HOE = new EmeraldHoe("crystal_wood_hoe", CRYSTAL_WOODTool);
public static final Item CRYSTAL_WOOD_SWORD = new EmeraldSword("crystal_wood_sword", CRYSTAL_WOODTool);
public static final Item CRYSTAL_WOOD_SHOVEL = new EmeraldShovel("crystal_wood_shovel", CRYSTAL_WOODTool);

public static final Item CRYSTAL_WOOD_PLANK_HELMET = new EmeraldArmour("crystal_wood_plank_helmet", CRYSTAL_WOOD_PLANK_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item CRYSTAL_WOOD_PLANK_CHESTPLATE = new EmeraldArmour("crystal_wood_plank_chestplate", CRYSTAL_WOOD_PLANK_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item CRYSTAL_WOOD_PLANK_LEGGINGS = new EmeraldArmour("crystal_wood_plank_leggings", CRYSTAL_WOOD_PLANK_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item CRYSTAL_WOOD_PLANK_BOOTS = new EmeraldArmour("crystal_wood_plank_boots", CRYSTAL_WOOD_PLANK_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item CRYSTAL_WOOD_PLANK_AXE = new EmeraldAxe("crystal_wood_plank_axe", CRYSTAL_WOOD_PLANKTool);
public static final Item CRYSTAL_WOOD_PLANK_PICKAXE = new EmeraldPic("crystal_wood_plank_pickaxe", CRYSTAL_WOOD_PLANKTool);
public static final Item CRYSTAL_WOOD_PLANK_HOE = new EmeraldHoe("crystal_wood_plank_hoe", CRYSTAL_WOOD_PLANKTool);
public static final Item CRYSTAL_WOOD_PLANK_SWORD = new EmeraldSword("crystal_wood_plank_sword", CRYSTAL_WOOD_PLANKTool);
public static final Item CRYSTAL_WOOD_PLANK_SHOVEL = new EmeraldShovel("crystal_wood_plank_shovel", CRYSTAL_WOOD_PLANKTool);


public static final Item KYANITE_HELMET = new EmeraldArmour("kyanite_helmet", KYANITE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item KYANITE_CHESTPLATE = new EmeraldArmour("kyanite_chestplate", KYANITE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item KYANITE_LEGGINGS = new EmeraldArmour("kyanite_leggings", KYANITE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item KYANITE_BOOTS = new EmeraldArmour("kyanite_boots", KYANITE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item KYANITE_AXE = new EmeraldAxe("kyanite_axe", KYANITETool);
public static final Item KYANITE_PICKAXE = new EmeraldPic("kyanite_pickaxe", KYANITETool);
public static final Item KYANITE_HOE = new EmeraldHoe("kyanite_hoe", KYANITETool);
public static final Item KYANITE_SWORD = new EmeraldSword("kyanite_sword", KYANITETool);
public static final Item KYANITE_SHOVEL = new EmeraldShovel("kyanite_shovel", KYANITETool);



public static final Item END_STONE_HELMET = new EmeraldArmour("end_stone_helmet", END_STONE_ARMOUR, 1, EntityEquipmentSlot.HEAD);
public static final Item END_STONE_CHESTPLATE = new EmeraldArmour("end_stone_chestplate", END_STONE_ARMOUR, 1, EntityEquipmentSlot.CHEST);
public static final Item END_STONE_LEGGINGS = new EmeraldArmour("end_stone_leggings", END_STONE_ARMOUR, 2, EntityEquipmentSlot.LEGS);
public static final Item END_STONE_BOOTS = new EmeraldArmour("end_stone_boots", END_STONE_ARMOUR, 1, EntityEquipmentSlot.FEET);
public static final Item END_STONE_AXE = new EmeraldAxe("end_stone_axe", END_STONETool);
public static final Item END_STONE_PICKAXE = new EmeraldPic("end_stone_pickaxe", END_STONETool);
public static final Item END_STONE_HOE = new EmeraldHoe("end_stone_hoe", END_STONETool);
public static final Item END_STONE_SWORD = new EmeraldSword("end_stone_sword", END_STONETool);
public static final Item END_STONE_SHOVEL = new EmeraldShovel("end_stone_shovel", END_STONETool);


public static final Item CHAINMAIL_AXE = new EmeraldAxe("chainmail_axe", CHAINMAILTool);
public static final Item CHAINMAIL_PICKAXE = new EmeraldPic("chainmail_pickaxe", CHAINMAILTool);
public static final Item CHAINMAIL_HOE = new EmeraldHoe("chainmail_hoe", CHAINMAILTool);
public static final Item CHAINMAIL_SWORD = new EmeraldSword("chainmail_sword", CHAINMAILTool);
public static final Item CHAINMAIL_SHOVEL = new EmeraldShovel("chainmail_shovel", CHAINMAILTool);


public static final Item LEATHER_AXE = new EmeraldAxe("leather_axe", LEATHERTool);
public static final Item LEATHER_PICKAXE = new EmeraldPic("leather_pickaxe", LEATHERTool);
public static final Item LEATHER_HOE = new EmeraldHoe("leather_hoe", LEATHERTool);
public static final Item LEATHER_SWORD = new EmeraldSword("leather_sword", LEATHERTool);
public static final Item LEATHER_SHOVEL = new EmeraldShovel("leather_shovel", LEATHERTool);



//Foods
public static final Item RAW_BACON = new RawBacon("raw_bacon", 5, true);
public static final Item COOKED_BACON = new RawBacon("cooked_bacon", 10, true);
public static final Item BUTTER_CANDY = new RawBacon("butter_candy", 3, true);
public static final Item CRYSTAL_APPLE = new RawBacon("crystal_apple", 10, true);
public static final Item LOVE_FOOD = new RawBacon("love_food", 15, true);
public static final Item POPCORN = new RawBacon("popcorn", 3, true);
public static final Item BUTTER_FOOD = new RawBacon("butter_food", 3, true);
public static final Item CORN_DOG = new RawBacon("corn_dog", 5, true);
public static final Item COOKED_CORN_DOG = new RawBacon("cooked_corn_dog", 6, true);
public static final Item RAW_CRAB_MEAT = new RawBacon("raw_crab_meat", 5, true);
public static final Item COOKED_CRAB_MEAT = new RawBacon("cooked_crab_meat", 10, true);
public static final Item CHEESE = new RawBacon("cheese", 3, true);
public static final Item SALAD = new RawBacon("salad", 15, true);
public static final Item BLT = new RawBacon("blt", 13, true);
public static final Item CRAB_PATTY = new RawBacon("crab_patty", 13, true);
public static final Item MAGIC_APPLE = new RawBacon("magic_apple", 15, true);
public static final Item PEACH = new RawBacon("peach", 5, true);
public static final Item RAW_PEACOCK = new RawBacon("raw_peacock", 8, true);
public static final Item COOCKED_PEACOCK = new RawBacon("coocked_peacock", 13, true);
public static final Item BLUE_FISH = new RawBacon("blue_fish", 5, true);
public static final Item BUTTERED_POPCORN = new RawBacon("buttered_popcorn", 10, true);
public static final Item SALTED_POPCORN = new RawBacon("salted_popcorn", 10, true);
public static final Item BUTTERED_AND_SALTED_POPCORN = new RawBacon("buttered_and_salted_popcorn", 15, true);
public static final Item CHERRY = new RawBacon("cherry", 5, true);
public static final Item CORN = new RawBacon("corn", 5, true);
public static final Item POPCORN_BAG = new RawBacon("popcorn_bag", 15, true);
public static final Item QUINOA = new RawBacon("quinoa", 5, true);
public static final Item RADISH = new RawBacon("radish", 5, true);
public static final Item RICE = new RawBacon("rice", 5, true);
public static final Item ROCK_FISH = new RawBacon("rock_fish", 5, true);
public static final Item FIRE_FISH = new RawBacon("fire_fish", 5, true);
public static final Item SPARK_FISH = new RawBacon("spark_fish", 5, true);
public static final Item GREEN_FISH = new RawBacon("green_fish", 5, true);
public static final Item GREY_FISH = new RawBacon("grey_fish", 5, true);
public static final Item PINK_FISH = new RawBacon("pink_fish", 5, true);
public static final Item SUN_FISH = new RawBacon("sun_fish", 5, true);
public static final Item STRAWBERRY = new RawBacon("strawberry", 5, true);
public static final Item LETTUCE = new RawBacon("lettuce", 5, true);
public static final Item TOMATO = new RawBacon("tomato", 5, true);
public static final Item WOOD_FISH = new RawBacon("wood_fish", 5, true);
public static final Item RAW_MOOSE_MEAT = new RawBacon("raw_moose_meat", 5, true);
//public static final Item COOKED_MOOSE_MEAT = new RawBacon("raw_bacon", 2, true);
public static final Item DEAD_BUG = new RawBacon("dead_bug", 2, true);
public static final Item MAGIC_FROG_OF_STRENGTH = new RawBacon("magic_frog_of_strength", 5, true);
public static final Item MAGIC_FROG_OF_WEAKNESS = new RawBacon("magic_frog_of_weakness", 5, true);
public static final Item MAGIC_FROG_OF_SPEED = new RawBacon("magic_frog_of_speed", 5, true);
public static final Item MAGIC_FROG_OF_SLOWNESS = new RawBacon("magic_frog_of_slowness", 5, true);
public static final Item MAGIC_FROG_OF_REGENERATION = new RawBacon("magic_frog_of_regeneration", 5, true);
public static final Item MAGIC_FROG_OF_POISON = new RawBacon("magic_frog_of_poison", 5, true);
public static final Item MAGIC_FROG_OF_MORPH = new RawBacon("magic_frog_of_morph", 5, true);
public static final Item MAGIC_FROG_OF_CONFUSION = new RawBacon("magic_frog_of_confusion", 5, true);
public static final Item COOKED_MOOSE_MEAT = new RawBacon("cooked_moose_meat", 10, true);
public static final Item CANDY_CANE = new RawBacon("candy_cane", 10, true);
public static final Item GOLDEN_BREAD = new RawBacon("golden_bread", 10, true);
public static final Item GOLDEN_CHICKEN = new RawBacon("golden_chicken", 10, true);
public static final Item GOLDEN_TROPICAL_FISH = new RawBacon("golden_tropical_fish", 10, true);
public static final Item GOLDEN_COD = new RawBacon("golden_cod", 10, true);
public static final Item GOLDEN_PORKCHOP = new RawBacon("golden_porkchop", 10, true);
public static final Item WATERMELON_SLICE = new RawBacon("watermelon_slice", 10, true);
public static final Item GOLDEN_MUSHROOM_STEW = new RawBacon("golden_mushroom_stew", 15, true);
public static final Item GOLDEN_STEAK = new RawBacon("golden_steak", 15, true);
public static final Item GOLDEN_COOKIE = new RawBacon("golden_cookie", 10, true);
public static final Item GOLDEN_POTATO = new RawBacon("golden_potato", 10, true);
public static final Item GOLDEN_PUMPKIN_PIE = new RawBacon("golden_pumpkin_pie", 15, true);
public static final Item GOLDEN_ROTTON_FLESH = new RawBacon("golden_rotton_flesh", 10, true);
public static final Item GOLDEN_CARROT = new RawBacon("golden_carrot", 10, true);
public static final Item GOLDEN_PUFFERFISH = new RawBacon("golden_pufferfish", 10, true);
public static final Item GOLDEN_SALMON = new RawBacon("golden_salmon", 10, true);
public static final Item GOLDEN_CANDYCANE = new RawBacon("golden_candycane", 15, true);
public static final Item ULTIMATE_APPLE = new RawBacon("ultimate_apple", 15, true);
public static final Item ENCHANTED_GOLDEN_CARROT = new RawBacon("enchanted_golden_carrot", 15, true);
public static final Item ENCHANTED_GOLDEN_STEAK = new RawBacon("enchanted_golden_steak", 15, true);
public static final Item ENCHANTED_GOLDEN_COD = new RawBacon("enchanted_golden_cod", 15, true);
public static final Item ENCHANTED_GOLDEN_COOKIE = new RawBacon("enchanted_golden_cookie", 15, true);
public static final Item ENCHANTED_GOLDEN_CANDYCANE = new RawBacon("enchanted_golden_candycane", 15, true);
public static final Item RADDISH_STEW = new RawBacon("raddish_stew", 15, true);
public static final Item DRINKABLE_GASOLINE_PETROL = new RawBacon("drinkable_gasoline_petrol", 15, true);





//GameRegistry.addSmelting(RAW_BACON.itemID, new ItemStack(FeatureCreepMC.COOKED_BACON), 2.0F);










}
