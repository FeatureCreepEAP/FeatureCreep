package featurecreep.legacy;


import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraftforge.fml.RegistryObject;

public class FCBlockLootTables extends BlockLootTableGenerator
{
	 @Override
	    protected void addTables()
	    {
	        addDrop(FCBlocks.AMETHYST_ORE.get(), (ore) -> oreDrops(ore,featurecreep.content.FCItems.AMETHYST));
	        addDrop(FCBlocks.RUBY_ORE.get(), (ore) -> oreDrops(ore, FCItems.RUBY.get()));
	        addDrop(FCBlocks.UNPROCESSED_OIL_ORE.get(), (ore) -> oreDrops(ore, FCItems.Oil.get()));
	        addDrop(FCBlocks.GASOLINE_PETROL_ORE.get(), (ore) -> oreDrops(ore, FCItems.GASOLINE_PETROL.get()));
	      
	        addDrop(FCBlocks.ALUMINIUM_ORE.get());
	        addDrop(FCBlocks.ALUMINIUM_BLOCK.get());
	        addDrop(FCBlocks.AMETHYST_BLOCK.get());
	    //         addDrop(FCBlocks.COPPER_BLOCK.get());
	    //    addDrop(FCBlocks.COPPER_ORE.get());
	       
	    //    addDrop(FCBlocks.PINK_TOURMALINE_BLOCK.get());
	    //    addDrop(FCBlocks.PLATINUM_BLOCK.get());
	      //  addDrop(FCBlocks.PLATINUM_ORE.get());

	        addDrop(FCBlocks.RUBY_BLOCK.get());
	   //     addDrop(FCBlocks.SILVER_BLOCK.get());
	   //     addDrop(FCBlocks.SILVER_ORE.get());
	        addDrop(FCBlocks.TIGERS_EYE_BLOCK.get());
	    //    addDrop(FCBlocks.TIN_BLOCK.get());
	    //    addDrop(FCBlocks.TIN_ORE.get());
	        addDrop(FCBlocks.TITANIUM_BLOCK.get());
	        addDrop(FCBlocks.TITANIUM_ORE.get());
	        addDrop(FCBlocks.URANIUM_BLOCK.get());
	        addDrop(FCBlocks.URANIUM_ORE.get());
	    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return FCBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}