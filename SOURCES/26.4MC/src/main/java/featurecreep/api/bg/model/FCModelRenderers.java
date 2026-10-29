package featurecreep.api.bg.model;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.core.registries.ClientRegistries;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Registers model renderers into the client registries Minecraft builds during bootstrap. Each method takes the registry
 * its vanilla bootstrap method receives, because those registries are frozen when that bootstrap finishes.
 */
public final class FCModelRenderers {

	public static final String VERSION = "26.4";

	private FCModelRenderers() {
	}

	public static void verify() {
		VanillaModelImport.verify();
	}

	public static <T extends BlockEntity & ModelHolder> void registerBlockEntity(
			Registry<BlockEntityRendererProvider<?, ?>> registry, BlockEntityType<T> type) {
		ResourceKey<BlockEntityRendererProvider<?, ?>> key = ResourceKey.create(
				ClientRegistries.BLOCK_ENTITY_RENDERER, BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(type));
		BlockEntityRendererProvider<T, FCModelBlockEntityRenderer.State> provider =
				context -> new FCModelBlockEntityRenderer<T>();
		Registry.register(registry, key, provider);
	}

	public static <T extends Entity & ModelHolder> void registerEntity(
			Registry<EntityRendererProvider<?>> registry, EntityType<T> type) {
		ResourceKey<EntityRendererProvider<?>> key = ResourceKey.create(
				ClientRegistries.ENTITY_RENDERER, BuiltInRegistries.ENTITY_TYPE.getKey(type));
		EntityRendererProvider<T> provider = context -> new FCModelEntityRenderer<T>(context);
		Registry.register(registry, key, provider);
	}

	public static void registerItemModelType(Registry<MapCodec<? extends ItemModel.Unbaked>> registry) {
		Registry.register(registry, FCModelItem.TYPE, FCModelItem.CODEC);
	}
}
