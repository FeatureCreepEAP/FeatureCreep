package featurecreep.api.bg.items;

import dangerzone.items.Item;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public interface FCItemAPI<T> extends BlockOrItem<T> {

@Override public ItemFieldHolder holder();


@Override
public  default void registerModels() {

  }



	public default Item get()
	{

		if (this instanceof Item) {
	return (Item)this;	
		}else
		{
	System.out.println("Tried to use an internal API referencing a Non-Item when Item was required.");		
	return null;
		}
	
	}

	
	@Override
	public default Object getObject() {
return get();		
	}
	

}
