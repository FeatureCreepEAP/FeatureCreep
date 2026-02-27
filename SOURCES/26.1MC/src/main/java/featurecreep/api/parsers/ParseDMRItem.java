package featurecreep.api.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

	public static Map<String,ModelNode> entries = new HashMap<String,ModelNode>();
	
	public static ModelNode getModelNodeFromDMRItem(FCItemAsDMR item) {
		String rl = item.getModId()+":"+item.getUnlocName();
		if(entries.containsKey(rl)){
			return entries.get(rl);
		}else {
			return item.toModelNode(); // Initial ModelNode
		}

	}

	public static void parseDMRItems() {
		// TODO Auto-generated method stub
//
//		String datafieditems = new String(FeatureCreep.gamepath.toString() + ("/datafiedcontents/items/"));
//
//		File file = new File(datafieditems);
//
//		if (FeatureCreep.debug_mode) {
//			System.out.println(file.toString());
//		}
//		String contents[] = file.list();
//
//		if (FeatureCreep.debug_mode) {
//			System.out.println("List of files and directories in the specified directory:");
//		}
//
//		// I need to make this multicore
//
//		if (contents != null) {
//			for (int i = 0; i < contents.length; i++) {
//				if (FeatureCreep.debug_mode) {
//					System.out.println("FeatureCreep is trying to load " + contents[i]);
//
//					System.out.println(datafieditems + contents[i] + "/");
//					parseDMRFiles(datafieditems + contents[i] + "/");
//				}
//			}
//
//		} else {
//			FeatureCreep.LOGGER.info("No DMR Items Found");
//		}

	}

	/**
	 * Parses DMR/JSON files in a folder
	 * @param folder the folder to search
	 */
	public static void parseDMRFiles(String folder) {

		File file = new File(folder);

		String contents[] = file.list();

		if (contents != null) {
			for (int i = 0; i < contents.length; i++) {

				if (contents[i].contains(".dmr")) {
					File myObj = new File(folder + contents[i]);

					// if (!BasicIO.getFileContentsOneLine(myObj).contains("=>"))
					{
						try {
							InputStream in = new FileInputStream(myObj);
							ModelNode binarynode = new ModelNode();
							binarynode.readExternal(in);
							NodetoItem(binarynode);
						} catch (java.io.InvalidObjectException e) {
							DMRStringtoItemNode(BasicIO.getFileContentsOneLine(myObj));

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();

						}

						// }else {
						// DMRStringtoItemNode(BasicIO.getFileContentsOneLine(myObj));
					}

				} else if (contents[i].contains(".json")) {
					File myObj = new File(folder + contents[i]);
					DMRStringtoItemNode(BasicIO.getFileContentsOneLine(myObj));

				} else {
					FeatureCreep.LOGGER.info(contents[i] + "is not a DMR or JSON File");
				}

			}
		}

	}

	/**
	 * Gets a DMR item from stream and registers it if its correct format.
	 * 
	 * @param stream
	 */
	public static void registerFromStream(InputStream stream) {
		try {
			ModelNode binarynode = new ModelNode();
			binarynode.readExternal(stream);
			NodetoItem(binarynode);
		} catch (java.io.InvalidObjectException e) {
			DMRStringtoItemNode(BasicIO.inputstreamToString(stream));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DMRStringtoItemNode(String string) {
		ModelNode node = new ModelNode();
		node = ModelNode.fromString(string);
		NodetoItem(node);
	}

	public static void NodetoItem(ModelNode node) {
		String modid=node.get("modid").asString();//Should be safe in mostcases
		String name=node.get("item_name").asString();//Should be safe in mostcases
		
		if (node.get("type").asString().equals("generic_item")) {
			FCRegistries.registerItem(
					new FCItem(node.get("id").asInt(), modid, name,
							UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString())));
		} else if (node.get("type").asString().equals("generic_axe")) {
			FCRegistries.registerItem(
					new FCAxe(node.get("id").asInt(), modid, name,
							UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()),
							new FCToolMaterial(node.get("material").get("harvest_level").asInt(),
									node.get("material").get("max_uses").asInt(),
									node.get("material").get("efficiency").asInt(),
									node.get("material").get("attack_damage").asInt(),
									node.get("material").get("enchantability").asInt(), FCItems.AMETHYST),
							node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

		} else if (node.get("type").asString().equals("generic_hoe")) {
			FCRegistries.registerItem(
					new FCHoe(node.get("id").asInt(), modid, name,
							UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()),
							new FCToolMaterial(node.get("material").get("harvest_level").asInt(),
									node.get("material").get("max_uses").asInt(),
									node.get("material").get("efficiency").asInt(),
									node.get("material").get("attack_damage").asInt(),
									node.get("material").get("enchantability").asInt(), FCItems.AMETHYST),
							node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

		} else if (node.get("type").asString().equals("generic_pickaxe")) {
			FCRegistries.registerItem(new FCPickaxe(node.get("id").asInt(), modid,
					node.get("item_name").asString(),
					UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()),
					new FCToolMaterial(node.get("material").get("harvest_level").asInt(),
							node.get("material").get("max_uses").asInt(),
							node.get("material").get("efficiency").asInt(),
							node.get("material").get("attack_damage").asInt(),
							node.get("material").get("enchantability").asInt(), FCItems.AMETHYST),
					node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

		} else if (node.get("type").asString().equals("generic_sword")) {
			FCRegistries.registerItem(
					new FCSword(node.get("id").asInt(), modid, name,
							UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()),
							new FCToolMaterial(node.get("material").get("harvest_level").asInt(),
									node.get("material").get("max_uses").asInt(),
									node.get("material").get("efficiency").asInt(),
									node.get("material").get("attack_damage").asInt(),
									node.get("material").get("enchantability").asInt(), FCItems.AMETHYST),
							node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

		} else if (node.get("type").asString().equals("generic_shovel")) // Shovel
		{
			FCRegistries.registerItem(
					new FCShovel(node.get("id").asInt(), modid, name,
							UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()),
							new FCToolMaterial(node.get("material").get("harvest_level").asInt(),
									node.get("material").get("max_uses").asInt(),
									node.get("material").get("efficiency").asInt(),
									node.get("material").get("attack_damage").asInt(),
									node.get("material").get("enchantability").asInt(), FCItems.AMETHYST),
							node.get("attack_damage").asInt(), node.get("attack_speed").asInt()));

		} else {
			System.out.println("Unknown type " + node.get("type").asString());
		}
		entries.put(modid+":"+name,node);
		// node.get("type") we need to make an
		// .set(item.public_modid);
		// node.get("item_name"); //.set(item.public_name);
		// node.get("group"); //.set(item.default_tab);

	}

}
