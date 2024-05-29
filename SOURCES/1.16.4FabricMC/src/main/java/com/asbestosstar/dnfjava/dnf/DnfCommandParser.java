package com.asbestosstar.dnfjava.dnf;

import com.asbestosstar.dnfjava.DnfJava;

public class DnfCommandParser {

	
	public DnfCommandParser(String[] args, DnfJava instance) {
		
		if(args[1].equals("install")) {
			new DNFInstallCommand(args, instance);
		}
		
		
	}
	
	
	
	
}
