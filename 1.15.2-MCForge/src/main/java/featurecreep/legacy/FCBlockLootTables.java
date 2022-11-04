package featurecreep.legacy;


import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraftforge.fml.RegistryObject;

public class FCBlockLootTables extends BlockLootTableGenerator
{
    @Override
    protected void addTables()
    {
       
    }

//    protected static LootTable.Builder randomDropping(ItemConvertible item, float random1, float random2) {
//        return LootTable.builder().addLootPool(withSurvivesExplosion(item, LootPool.builder().rolls(ItemEntry.builder(random1, random2))).addEntry(ItemEntry.builder(item)));
//    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return FCBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}