package featurecreep.legacy;



import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.Tag;


import java.util.Map;


public class FuelRegistryImpl  implements FuelRegistry {
	public static final FuelRegistryImpl INSTANCE = new FuelRegistryImpl();
	private final Object2IntMap<ItemConvertible> itemCookTimes = new Object2IntLinkedOpenHashMap<>();
	private final Object2IntMap<Tag<Item>> tagCookTimes = new Object2IntLinkedOpenHashMap<>();

	public FuelRegistryImpl() {

	}

	@Override
	public Integer get(ItemConvertible item) {
		return AbstractFurnaceBlockEntity.createFuelTimeMap().get(item.asItem());
	}

	@Override
	public void add(ItemConvertible item, Integer cookTime) {

		itemCookTimes.put(item, cookTime.intValue());
	}

//	@Override
//	public void add(Tag<Item> tag, Integer cookTime) {
	
//		tagCookTimes.put(tag, cookTime.intValue());
//	}

//	@Override
//	public void remove(ItemConvertible item) {
//		add(item, 0);
//	}

//	@Override
//	public void remove(Tag<Item> tag) {
//		add(tag, 0);
//	}

	@Override
	public void clear(ItemConvertible item) {
		itemCookTimes.removeInt(item);
	}

	@Override
	public void clear(Tag<Item> tag) {
		tagCookTimes.removeInt(tag);
	}

	public void apply(Map<Item, Integer> map) {
		// tags take precedence before blocks
		for (Tag<Item> tag : tagCookTimes.keySet()) {
			int time = tagCookTimes.getInt(tag);
			if (time <= 0) {
				for (Item i : tag.values()) {
					map.remove(i);
				}
			} else {
				for (Item i : tag.values()) {
					map.put(i, time);
				}
			}
		}

		for (ItemConvertible item : itemCookTimes.keySet()) {
			int time = itemCookTimes.getInt(item);
			if (time <= 0) {
				map.remove(item.asItem());
			} else {
				map.put(item.asItem(), time);
			}
		}
	}
	

	
	
	
}
