package featurecreep.legacy;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.LootTablesProvider;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTableReporter;
import net.minecraft.loot.context.LootContextTypes;

public class LootTablesProvide extends LootTablesProvider {
    public LootTablesProvide(DataGenerator dataGeneratorIn)
    {
        super(dataGeneratorIn);
    }

/*    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ModelIdentifier, Builder>>>, LootContextTypes>> getTables() {
        return ImmutableList.of(Pair.of(FCBlockLootTables::new, LootContextTypes.BLOCK));
                              
    }

    @Override
    protected void validate(Map<ModelIdentifier, LootTable> map, LootTableReporter validationtracker) {

    }
*/

}