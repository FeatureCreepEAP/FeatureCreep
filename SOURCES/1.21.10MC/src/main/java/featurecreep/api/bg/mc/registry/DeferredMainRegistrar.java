package featurecreep.api.bg.mc.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class DeferredMainRegistrar {

    private static final List<Runnable> TASKS = new ArrayList<>();
    private static boolean committed = false;

    private DeferredMainRegistrar() {
    }

    public static <T> DeferredHolder<T> register(
        Registry<T> registry,
        ResourceLocation id,
        Supplier<? extends T> factory
    ) {
        if (committed) {
            throw new IllegalStateException("Tried to register after commit: " + id);
        }

        DeferredHolder<T> holder = new DeferredHolder<>(registry, id);

        TASKS.add(() -> {
            T value = factory.get();

            if (value == null) {
                throw new IllegalStateException("Factory returned null for " + id);
            }

            T registered = Registry.register(registry, id, value);
            holder.bind(registered);
        });

        return holder;
    }

    public static <T> DeferredHolder<T> register(
        Registry<T> registry,
        ResourceKey<T> key,
        Supplier<? extends T> factory
    ) {
        if (committed) {
            throw new IllegalStateException("Tried to register after commit: " + key.registry());
        }

        DeferredHolder<T> holder = new DeferredHolder<>(registry, key);

        TASKS.add(() -> {
            T value = factory.get();

            if (value == null) {
                throw new IllegalStateException("Factory returned null for " + key.registry());
            }

            T registered = Registry.register(registry, key, value);
            holder.bind(registered);
        });

        return holder;
    }

    public static DeferredHolder<Item> registerItem(
    		ResourceLocation id,
        Function<Item.Properties, ? extends Item> factory
    ) {
        if (committed) {
            throw new IllegalStateException("Tried to register item after commit: " + id);
        }

        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        DeferredHolder<Item> holder = new DeferredHolder<>(BuiltInRegistries.ITEM, key);

        TASKS.add(() -> {
            Item.Properties properties = new Item.Properties().setId(key);
            Item value = factory.apply(properties);

            if (value == null) {
                throw new IllegalStateException("Factory returned null for " + id);
            }

            Item registered = Registry.register(BuiltInRegistries.ITEM, key, value);
            holder.bind(registered);
        });

        return holder;
    }

    public static DeferredHolder<Block> registerBlock(
    		ResourceLocation id,
        Function<BlockBehaviour.Properties, ? extends Block> factory
    ) {
        if (committed) {
            throw new IllegalStateException("Tried to register block after commit: " + id);
        }

        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);
        DeferredHolder<Block> holder = new DeferredHolder<>(BuiltInRegistries.BLOCK, key);

        TASKS.add(() -> {
            BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().setId(key);
            Block value = factory.apply(properties);

            if (value == null) {
                throw new IllegalStateException("Factory returned null for " + id);
            }

            Block registered = Registry.register(BuiltInRegistries.BLOCK, key, value);
            holder.bind(registered);
        });

        return holder;
    }

    public static void commit() {
        if (committed) {
            return;
        }

        committed = true;

        for (Runnable task : TASKS) {
            task.run();
        }

        TASKS.clear();
    }
}