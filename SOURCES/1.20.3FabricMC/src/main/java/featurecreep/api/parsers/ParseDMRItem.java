package featurecreep.api.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.bg.items.tools.FCAxe;
import featurecreep.api.bg.items.tools.FCHoe;
import featurecreep.api.bg.items.tools.FCPickaxe;
import featurecreep.api.bg.items.tools.FCShovel;
import featurecreep.api.bg.items.tools.FCSword;
import featurecreep.api.bg.items.tools.FCToolMaterial;
import featurecreep.api.bg.registries.FCRegistries;
import featurecreep.api.bg.registries.UniversalRegistryGettersAndSetters;
import featurecreep.api.io.BasicIO;
import featurecreep.content.FCItems;

public class ParseDMRItem {

  public static ModelNode getModelNodeFromDMRItem(FCItemAsDMR item) {
    String datafieditems = new String(FeatureCreep.gamepath.toString() + ("/datafiedcontents/items/"));
    File item_file = new File(datafieditems + item.getModId() + "/" + item.getUnlocName() + ".dmr");
    File item_file_json = new File(datafieditems + item.getModId() + "/" + item.getUnlocName() + ".json");

    if (item_file.exists()) {
      try {
        InputStream in = new FileInputStream(item_file);
        ModelNode binarynode = new ModelNode();
        binarynode.readExternal( in );
        return binarynode; // For Binary DMR
      } catch (java.io.InvalidObjectException e) {
        return ModelNode.fromString(BasicIO.getFileContentsOneLine(item_file));

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return item.toModelNode(); //Initial ModelNode
      }

    } else if (item_file_json.exists()) {
      try {
        InputStream in = new FileInputStream(item_file_json);
        ModelNode binarynode = new ModelNode();
        binarynode.readExternal( in );
        return binarynode; // For Binary DMR
      } catch (java.io.InvalidObjectException e) {
        return ModelNode.fromString(BasicIO.getFileContentsOneLine(item_file_json));

      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
return item.toModelNode(); //Initial ModelNode
      }

    } else {
      return item.toModelNode(); //Initial ModelNode
    }

    
    
  }

  public static void parseDMRItems() {
    // TODO Auto-generated method stub

    String datafieditems = new String(FeatureCreep.gamepath.toString() + ("/datafiedcontents/items/"));

    File file = new File(datafieditems);

    if(FeatureCreep.debug_mode) {
    System.out.println(file.toString());
    }
    
    String contents[] = file.list();
    if(FeatureCreep.debug_mode) {
    System.out.println("List of files and directories in the specified directory:");
    }
    
    //I need to make this multicore

    if (contents != null) {
      for (int i = 0; i < contents.length; i++) {

    	  if(FeatureCreep.debug_mode) {
        System.out.println("FeatureCreep is trying to load " + contents[i]);

        System.out.println(datafieditems + contents[i] + "/");
    	  }
        parseDMRFiles(datafieditems + contents[i] + "/");

      }

    } else {
      FeatureCreep.LOGGER.info("No DMR Items Found");
    }

  }

  public static void parseDMRFiles(String string) {

    File file = new File(string);

    String contents[] = file.list();

    if (contents != null) {
      for (int i = 0; i < contents.length; i++) {

        if (contents[i].contains(".dmr")) {
          File myObj = new File(string + contents[i]);

          //if (!BasicIO.getFileContentsOneLine(myObj).contains("=>"))
          {
            try {
              InputStream in = new FileInputStream(myObj);
              ModelNode binarynode = new ModelNode();
              binarynode.readExternal( in );
              NodetoItem(binarynode);
            } catch (java.io.InvalidObjectException e) {
              DMRStringtoItemNode(BasicIO.getFileContentsOneLine(myObj));

            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();

            }

            //}else {
            //DMRStringtoItemNode(BasicIO.getFileContentsOneLine(myObj));
          }

        } else if (contents[i].contains(".json")) {
          File myObj = new File(string + contents[i]);
          DMRStringtoItemNode(BasicIO.getFileContentsOneLine(myObj));

        } else {
          FeatureCreep.LOGGER.info(contents[i] + "is not a DMR or JSON File");
        }

      }
    }

  }

  public static void DMRStringtoItemNode(String string) {

	  if(FeatureCreep.debug_mode) {
    System.out.println(string);
	  }
    ModelNode node = new ModelNode();
    node = ModelNode.fromString(string);
    if(FeatureCreep.debug_mode) {
    System.out.println(node);
    }
    
    NodetoItem(node);

  }

  public static void NodetoItem(ModelNode node) {

    if (node.get("type").asString().equals("generic_item")) {
      FCRegistries.registerItem(new FCItem(node.get("id").asInt(), node.get("modid").asString(), node.get("item_name").asString(), UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString())));
    } else if (node.get("type").asString().equals("generic_axe")) {
      FCRegistries.registerItem( new FCAxe(node.get("id").asInt(), node.get("modid").asString(), node.get("item_name").asString(), UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()), new FCToolMaterial(node.get("material").get("harvest_level").asInt(), node.get("material").get("max_uses").asInt(), node.get("material").get("efficiency").asInt(), node.get("material").get("attack_damage").asInt(), node.get("material").get("enchantability").asInt(), FCItems.AMETHYST), node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

    } else if (node.get("type").asString().equals("generic_hoe")) {
        FCRegistries.registerItem( new FCHoe(node.get("id").asInt(), node.get("modid").asString(), node.get("item_name").asString(), UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()), new FCToolMaterial(node.get("material").get("harvest_level").asInt(), node.get("material").get("max_uses").asInt(), node.get("material").get("efficiency").asInt(), node.get("material").get("attack_damage").asInt(), node.get("material").get("enchantability").asInt(), FCItems.AMETHYST), node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

    } else if (node.get("type").asString().equals("generic_pickaxe")) {
        FCRegistries.registerItem( new FCPickaxe(node.get("id").asInt(), node.get("modid").asString(), node.get("item_name").asString(), UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()), new FCToolMaterial(node.get("material").get("harvest_level").asInt(), node.get("material").get("max_uses").asInt(), node.get("material").get("efficiency").asInt(), node.get("material").get("attack_damage").asInt(), node.get("material").get("enchantability").asInt(), FCItems.AMETHYST), node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

    } else if (node.get("type").asString().equals("generic_sword")) {
        FCRegistries.registerItem( new FCSword(node.get("id").asInt(), node.get("modid").asString(), node.get("item_name").asString(), UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()), new FCToolMaterial(node.get("material").get("harvest_level").asInt(), node.get("material").get("max_uses").asInt(), node.get("material").get("efficiency").asInt(), node.get("material").get("attack_damage").asInt(), node.get("material").get("enchantability").asInt(), FCItems.AMETHYST), node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

    } else if (node.get("type").asString().equals("generic_shovel")) // Shovel 
    {
        FCRegistries.registerItem( new FCShovel(node.get("id").asInt(), node.get("modid").asString(), node.get("item_name").asString(), UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()), new FCToolMaterial(node.get("material").get("harvest_level").asInt(), node.get("material").get("max_uses").asInt(), node.get("material").get("efficiency").asInt(), node.get("material").get("attack_damage").asInt(), node.get("material").get("enchantability").asInt(), FCItems.AMETHYST), node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

    } else {
      System.out.println("Unknown type " + node.get("type").asString());
    }

    //node.get("type") we need to make an 
    //.set(item.public_modid);
    //node.get("item_name"); //.set(item.public_name);
    //	node.get("group"); //.set(item.default_tab);

  }

}
