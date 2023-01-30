package featurecreep.api.registries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import featurecreep.api.items.datafied.dmr.DMRItem;
import featurecreep.api.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCAxeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCHoeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCPickaxeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCShovelAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCSwordAsDMR;

public class DatafiedObjectRegistration {

		
	
	public static void registerDMRItem(DMRItem item)
	{
		
		System.out.print(item.getModId() + item.getUnlocName() + item.getNumberID() + item.getDefaultCreativeTab());
        ModelNode node = item.toModelNode();
		
				
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.getModId() + "/" + item.getUnlocName() + ".dmr");
	  	 
	  	if (!myObj.exists()) {
	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  		
	  		FileWriter myWriter = new FileWriter(myObj);
	        myWriter.write(node.asString());
			myWriter.close();
	  	}
			
			
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	public static void registerDMRBinaryItem(FCItemAsDMR item)
	{
		
		System.out.print(item.getModId() + item.getUnlocName() + item.getNumberID() + item.getDefaultCreativeTab());
        ModelNode node = item.toModelNode();


		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.getModId() + "/" + item.getUnlocName() + ".dmr");
	  	  System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
		  	if (!myObj.exists()) {

	  	    FileOutputStream fout = new FileOutputStream(myObj);
	  		//FileWriter myWriter = new FileWriter(myObj);
	        //myWriter.write(node.toString());
			//myWriter.close();
		
	  	    node.writeExternal(fout);
		  	}
	  	    
	  	    
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}

