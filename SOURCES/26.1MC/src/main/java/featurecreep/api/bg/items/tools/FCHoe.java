package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;

public class FCHoe extends HoeItem implements ToolsAPI<FCHoe> {

    public ToolFieldHolder holder = new ToolFieldHolder();

    @Override
    public ToolFieldHolder holder() {
        return holder;
    }

    public FCHoe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
                 float attackDamage, float attackSpeed) {
        super(material.toMinecraftToolMaterial(), attackDamage, attackSpeed, new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),Identifier.fromNamespaceAndPath(modid, name)) ));

        // Luego haces tu inicialización personal (registros, etc.)
        initialise(id, modid, name, group, material, (int) attackDamage, (int) attackSpeed);
    }
}