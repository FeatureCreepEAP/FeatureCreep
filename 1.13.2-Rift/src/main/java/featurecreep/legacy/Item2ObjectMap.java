package featurecreep.legacy;

import org.lwjgl.system.CallbackI.V;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.Tag;

public interface Item2ObjectMap<T> {
	T get(ItemConvertible item);

	//void add(ItemConvertible item, V value);

	void add(ItemConvertible item, Integer cookTime);

	//void add(Tag<Item> tag, V value);

//	void remove(ItemConvertible item);
    
//	void remove(Tag<Item> tag);

	void clear(ItemConvertible item);

	void clear(Tag<Item> tag);
	
	
}
