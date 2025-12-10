package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;

public class FCAxe extends AxeItem implements ToolsAPI<FCAxe> {

    public ToolFieldHolder holder = new ToolFieldHolder();

    @Override
    public ToolFieldHolder holder() {
        return holder;
    }

    public FCAxe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
                 float attackDamage, float attackSpeed) {
        // Llama al constructor moderno de AxeItem (1.21.10+)
        super(material.toMinecraftToolMaterial(), attackDamage, attackSpeed, new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),ResourceLocation.fromNamespaceAndPath(modid, name)) ));

        // Inicialización personalizada
        initialise(id, modid, name, group, material, (int) attackDamage, (int) attackSpeed);
    }
}