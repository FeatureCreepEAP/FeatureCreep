package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class FCPickaxe extends Item implements ToolsAPI<FCPickaxe> {

    public ToolFieldHolder holder = new ToolFieldHolder();

    @Override
    public ToolFieldHolder holder() {
        return holder;
    }

    public FCPickaxe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
                     float attackDamageBonus, float attackSpeed) {
        super(new Item.Properties().pickaxe(material.toMinecraftToolMaterial(), attackDamageBonus, attackSpeed).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),ResourceLocation.fromNamespaceAndPath(modid, name)) ));

        // Inicialización personalizada
        initialise(id, modid, name, group, material, (int) attackDamageBonus, (int) attackSpeed);
    }
}