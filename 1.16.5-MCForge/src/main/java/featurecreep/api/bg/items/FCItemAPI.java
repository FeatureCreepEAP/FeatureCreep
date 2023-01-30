package featurecreep.api.bg.items;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.minecraft.item.Item;

import org.jboss.dmr.ModelNode;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

import featurecreep.api.bg.blocknitem.BlockOrItem;

public interface FCItemAPI<T> extends BlockOrItem<T> {


@Override public ItemFieldHolder holder();


@Override
 public default void registerModels() {

    //I could just do a long string but i will need to use this format for some other things so may as well start 
    ModelNode node = new ModelNode();
    node.get("parent").set("item/generated");
    node.get("textures").get("layer0").set(this.getModId() + ":items/" + this.getUnlocName());

    System.out.print(node.toJSONString(false));

    try {

      File myObj = new File(featurecreep.api.bg.PackLoader.fc_pack_location + "/assets/" + this.getModId() + "/models/item/" + this.getUnlocName() + ".json");

      if (!myObj.exists()) {

        System.out.println(myObj.toString());
        myObj.getParentFile().mkdirs();

        FileWriter myWriter = new FileWriter(myObj);
        myWriter.write(node.toJSONString(true));
        myWriter.close();

      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

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

}
