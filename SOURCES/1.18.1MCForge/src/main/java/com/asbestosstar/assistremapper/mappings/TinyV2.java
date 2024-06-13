package com.asbestosstar.assistremapper.mappings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.asbestosstar.assistremapper.Mappings;

public class TinyV2 implements Mappings{

	
	public Map<String, String> classes = new HashMap<String, String>();
	public Map<String, String> jvm_classes = new HashMap<String, String>();
	public Map<String, String> defs = new HashMap<String, String>();
	public Map<String, String> vars = new HashMap<String, String>();
	public Map<String, String> params = new HashMap<String, String>();
	public Map<String, String[]> includes = new HashMap<String, String[]>();

	public Mappings reverse;
	
	
	private final TinyV2SubType subType;
	
	
	/*
	 * Keep in mind that this returns a null reverse.
	 */
	public TinyV2() {
		this(TinyV2SubType.OBF_TO_CLEAN);
	}
	
	/*
	 * Keep in mind that this returns a null reverse.
	 */
	public TinyV2(TinyV2SubType sub) {
		this.subType=sub;
	}
	
	public TinyV2(InputStream map_file) {
		this(map_file, TinyV2SubType.OBF_TO_CLEAN);
	}

	public TinyV2(InputStream map_file, TinyV2SubType sub) {
		this.subType=sub;		
		System.out.println("Parsing Mappings");
		BufferedReader reader = new BufferedReader(new InputStreamReader(map_file));
		String line;
		String currentClass = null;
		String currentDef = null;
		try {
			while ((line = reader.readLine()) != null) {
				//Modified from Recaf's parser
				
				
				// Skip initial header
				if (line.startsWith("tiny\t"))
					continue;
				String lineStrTrim = line.trim();
				int strIndent = line.indexOf(lineStrTrim);
				String[] args = lineStrTrim.split("\t");
				String type = args[0];
				try {
					// A note on the "intermediate" values... I have seen cases of the format where this column
					// does not exist... so the fix here will be to check for the number of columns. If there are
					// enough, we assume it contains the intermediate in the middle. Otherwise, there is none.
					switch(type) {
						case "c":
							// TinyV2 reuses "c" for "comment" too
							// These are indented to indicate they belong to members/types, so skip em.
							if (strIndent > 0)
								continue;
							// [1] = current
							// [2*] = intermediate
							// [3] = renamed
							int[] clsRenameIndices = subType.getFromXToYOffsets(Context.CLASS, args.length);
							currentClass = args[clsRenameIndices[0]];
							String renamedClass = args[clsRenameIndices[1]];
							addClass(currentClass.replace("/", "."), renamedClass.replace("/", "."));
							break;
						case "f":
							// [1] = desc
							// [2] = current
							// [3*] = intermediate
							// [4] = renamed
							int[] fldRenameIndices = subType.getFromXToYOffsets(Context.FIELD, args.length);
							String currentField = args[fldRenameIndices[0]];
							String renamedField = args[fldRenameIndices[1]];
							vars.put(currentClass.replace("/", ".") + "." + currentField+":"+args[1], renamedField);
							break;
						case "m":
							// [1] = desc
							// [2] = current
							// [3*] = intermediate
							// [4] = renamed
							int[] mtdRenameIndices = subType.getFromXToYOffsets(Context.METHOD, args.length);
							String methodType = args[1];
							String currentMethod = args[mtdRenameIndices[0]];
							currentDef = currentMethod;
							String renamedMethod = args[mtdRenameIndices[1]];
							defs.put(currentClass.replace("/", ".") + "." + currentMethod + methodType, renamedMethod);
							break;
						case "p":
							// [1] = num
							// [2]/[3] = name it seems there are sometimes 2 indents
							if(args.length==4) {
							params.put(currentDef + "@" + args[1], args[3]);
							}else {
								params.put(currentDef + "@" + args[1], args[4]);
							}
					}
				} catch(IndexOutOfBoundsException ex) {
					throw new IllegalArgumentException("failed parsing line " + line, ex);
				}
			}
				
				
				
				
				
				
				
				
			
			
			
			// Close the BufferedReader
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parseSubClasses();
		System.out.println("Getting reverse mappings");
		this.refreshReverse();
		
	}
	




/**
 * Subtype for TinyV2 handling.
 *
 * @author Matt from recaff
 */
public enum TinyV2SubType {
	OBF_TO_CLEAN("Obfuscated to named"),
	OBF_TO_INTERMEDIATE("Obfuscated to intermediate"),
	INTERMEDIATE_TO_CLEAN("Intermediate to named"),
	INTERMEDIATE_TO_OBF("Intermediate to obfuscated"),
	CLEAN_TO_INTERMEDIATE("Named to intermediate"),
	CLEAN_TO_OBF("Named to obfuscated");

	private final String display;

	TinyV2SubType(String display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return display;
	}

	/**
	 * @param ctx
	 * 		Mapping context, class, field, or method.
	 * @param columns
	 * 		The number of columns in the row.
	 * 		Used to determine if the input matches the specs
	 * 		<i>(And if not, limit the return value to be inside the range of columns)</i>.
	 *
	 * @return Pair of integers for the before name and after name indices.
	 */
	public int[] getFromXToYOffsets(Context ctx, int columns) {
		int base = ctx == Context.CLASS ? 1 : 2;
		// Get offsets from base for sort of context
		int from = -1;
		int to = -1;
		switch (this) {
			case OBF_TO_INTERMEDIATE:
				from = 0;
				to = 1;
				break;
			case OBF_TO_CLEAN:
				from = 0;
				to = 2;
				break;
			case INTERMEDIATE_TO_CLEAN:
				from = 1;
				to = 2;
				break;
			case INTERMEDIATE_TO_OBF:
				from = 1;
				to = 1;
				break;
			case CLEAN_TO_OBF:
				from = 2;
				to = 0;
				break;
			case CLEAN_TO_INTERMEDIATE:
				from = 1;
				to = 0;
				break;
			default:
				throw new IllegalStateException();
		}
		// Cap indices if no intermediate column exists
		if (!hasIntermediateColumn(ctx, columns)) {
			from = Math.min(1, from);
			to = Math.min(1, to);
		}
		return new int[]{base+from, base+to};
	}

	private boolean hasIntermediateColumn(Context ctx, int columns) {
		switch (ctx){
			case CLASS:
				return columns >= 4;
			case FIELD:
			case METHOD:
				return columns >= 5;
			default:
				throw new IllegalStateException();
		}
	}
}

private enum Context {
	CLASS, FIELD, METHOD;
}




	
	
	
	
	
	
	@Override
	public Map<String, String> getClasses() {
		// TODO Auto-generated method stub
		return classes;
	}

	@Override
	public Map<String, String> getDefs() {
		// TODO Auto-generated method stub
		return defs;
	}

	@Override
	public Map<String, String> getVars() {
		// TODO Auto-generated method stub
		return vars;
	}

	@Override
	public Map<String, String> getParams() {
		// TODO Auto-generated method stub
		return params;
	}

	@Override
	public Mappings getReverse() {
		// TODO Auto-generated method stub
		return reverse;
	}

	@Override
	public Map<String, String[]> getIncludes() {
		// TODO Auto-generated method stub
		return includes;
	}

	
	
	/*
	 * UNSUPPORTED AT THIS TIME
	 */
	@Override
	public void write(OutputStream stream) {
		// TODO Auto-generated method stub
			
	}
	
	@Override
	public void setReverse(Mappings rev) {
		// TODO Auto-generated method stub
		this.reverse=rev;
	}

	@Override
	public Mappings getPlainMappings() {
		// TODO Auto-generated method stub
		return new TinyV2();
	}
	
	@Override
	public Map<String, String> getJVMClasses() {
		// TODO Auto-generated method stub
		return jvm_classes;
	}
	
}
