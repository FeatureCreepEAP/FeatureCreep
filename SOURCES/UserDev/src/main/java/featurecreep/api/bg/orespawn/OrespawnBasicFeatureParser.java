package featurecreep.api.bg.orespawn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import featurecreep.api.io.BasicIO;

public class OrespawnBasicFeatureParser {

	
	public static void spawnOresFromDefaultConfig() {

	}

	public static ModelNode getModelNodesFromFile(String file) {
		if (file.contains(".json")) {
			try {
				return ModelNode.fromJSONStream(new FileInputStream(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelNode();
			}
		} else {
			try {
				return ModelNode.fromStream(new FileInputStream(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ModelNode();
			}
		}

	}

	public static void splitOS3Basic(ModelNode node) {
		List<ModelNode> list = node.get("spawns").asList();
		for (ModelNode rowNode : list) {
			if (FeatureCreep.debug_mode) {
				System.out.println(rowNode.asString().split("\"")[1]);
			}
			parseOS3Basic(rowNode.get(0), rowNode.asString().split("\"")[1]);

		}

	}
	
	
	/**
	 * Register from JSON or DMR Plaintext InputStream
	 * @param stream
	 * @param is_json is JSON  rather than DMR Plaintext
	 */
	public static void registerFromStream(InputStream stream, boolean is_json) {
		try {
			ModelNode binarynode;
			if(is_json) {
				binarynode= ModelNode.fromJSONStream(stream);
			}else {
				binarynode = new ModelNode();
			binarynode.readExternal(stream);
			}
			splitOS3Basic(binarynode);
		} catch (java.io.InvalidObjectException e) {
			splitOS3Basic(DMRStringtoNode(BasicIO.inputstreamToString(stream),is_json));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses a Plaintext DMR String or JSON String
	 * @param string
	 * @param is_json Is JSON rather than DMR plaintext
	 * @return
	 */
	public static ModelNode DMRStringtoNode(String string, boolean is_json) {
		ModelNode node;
		if(is_json) {node = ModelNode.fromJSONString(string);}
		else {node = ModelNode.fromString(string);}
		return node;
	}
	
	

	public static void parseOS3Basic(ModelNode node, String name) {

		
	}

	public static String getCorrectNameSpace(String old) {
		String new_string = new String(old);

		if (new_string.contains("vanilla:")) {
			new_string = new_string.replace("vanilla:", "dangerzone:");
		}

		if (new_string.contains("minecraft:")) {
			new_string = new_string.replace("minecraft:", "dangerzone:");
		}

		return new_string;
	}

}
