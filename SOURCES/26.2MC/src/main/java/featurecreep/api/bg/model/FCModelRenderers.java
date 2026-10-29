package featurecreep.api.bg.model;

import static java.lang.invoke.MethodType.methodType;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemModels;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * Registers model renderers through Minecraft's private registration methods. Members are resolved once by
 * {@link #verify()}, which fails with the missing member named when the Minecraft version does not match.
 */
public final class FCModelRenderers {

	public static final String VERSION = "26.2";

	private static Handles handles;

	private FCModelRenderers() {
	}

	public static synchronized void verify() {
		if (handles == null) {
			VanillaModelImport.verify();
			handles = Handles.resolve();
		}
	}

	public static <T extends BlockEntity & ModelHolder> void registerBlockEntity(BlockEntityType<T> type) {
		verify();
		BlockEntityRendererProvider<T, FCModelBlockEntityRenderer.State> provider =
				context -> new FCModelBlockEntityRenderer<T>();
		call(handles.blockEntityRegister(), type, provider);
	}

	public static <T extends Entity & ModelHolder> void registerEntity(EntityType<T> type) {
		verify();
		EntityRendererProvider<T> provider = context -> new FCModelEntityRenderer<T>(context);
		call(handles.entityRegister(), type, provider);
	}

	public static void registerItemModelType() {
		verify();
		Object mapper = call(handles.itemMapperGetter());
		call(handles.itemMapperPut(), mapper, FCModelItem.TYPE, FCModelItem.CODEC);
	}

	private static Object call(MethodHandle handle, Object... args) {
		try {
			return handle.invokeWithArguments(args);
		} catch (Throwable t) {
			throw new IllegalStateException("featurecreep model bridge call failed on Minecraft " + VERSION, t);
		}
	}

	private record Handles(MethodHandle blockEntityRegister, MethodHandle entityRegister,
			MethodHandle itemMapperGetter, MethodHandle itemMapperPut) {

		static Handles resolve() {
			return new Handles(
					privateStatic(BlockEntityRenderers.class, "register",
							methodType(void.class, BlockEntityType.class, BlockEntityRendererProvider.class)),
					privateStatic(EntityRenderers.class, "register",
							methodType(void.class, EntityType.class, EntityRendererProvider.class)),
					privateStaticGetter(ItemModels.class, "ID_MAPPER", ExtraCodecs.LateBoundIdMapper.class),
					privateVirtual(ExtraCodecs.LateBoundIdMapper.class, "put",
							methodType(ExtraCodecs.LateBoundIdMapper.class, Object.class, Object.class)));
		}

		private static MethodHandle privateStatic(Class<?> owner, String name, MethodType type) {
			try {
				return MethodHandles.privateLookupIn(owner, MethodHandles.lookup()).findStatic(owner, name, type);
			} catch (ReflectiveOperationException | RuntimeException e) {
				throw missing(owner, name, type, e);
			}
		}

		private static MethodHandle privateStaticGetter(Class<?> owner, String name, Class<?> type) {
			try {
				return MethodHandles.privateLookupIn(owner, MethodHandles.lookup())
						.findStaticGetter(owner, name, type);
			} catch (ReflectiveOperationException | RuntimeException e) {
				throw missing(owner, name, type.getName(), e);
			}
		}

		private static MethodHandle privateVirtual(Class<?> owner, String name, MethodType type) {
			try {
				return MethodHandles.privateLookupIn(owner, MethodHandles.lookup()).findVirtual(owner, name, type);
			} catch (ReflectiveOperationException | RuntimeException e) {
				throw missing(owner, name, type, e);
			}
		}

		private static IllegalStateException missing(Class<?> owner, String name, Object type, Exception cause) {
			return new IllegalStateException("Minecraft " + VERSION + " is missing " + owner.getName() + "." + name
					+ " " + type + "; the featurecreep model bridge targets Minecraft " + VERSION, cause);
		}
	}
}
