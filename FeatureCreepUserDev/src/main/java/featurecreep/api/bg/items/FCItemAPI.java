package featurecreep.api.bg.items;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

import featurecreep.api.bg.blocknitem.BlockOrItem;

public interface FCItemAPI<T> extends BlockOrItem<T> {

@Override public ItemFieldHolder holder();


@Override
 public default void registerModels() {

  }


	//public default Item get()	{	} internal API


}
