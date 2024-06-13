package com.asbestosstar.dnfjava;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

import com.asbestosstar.dnfjava.dnf.DnfCommandParser;
import com.asbestosstar.dnfjava.packages.InstalledPackageNamesParser;
import com.asbestosstar.dnfjava.yum.YumRepo;

public class DnfJava {
	
	
	public ArrayList<com.asbestosstar.dnfjava.packages.Package> installed_packages = new ArrayList<com.asbestosstar.dnfjava.packages.Package>();
	public Map<String, String> macros = new java.util.HashMap<String, String>();
	public String base_location = macros.get("%{_base}");
	public Path package_database = new File(base_location+"/var/lib/dnf/").toPath();
	public Path xmlSpecLocation = new File(base_location+"/var/lib/dnf/specs/").toPath();
public Path repos_dir = new File(macros.get("%{_sysconfdir}")+"/etc/yum.repos.d/").toPath();
	public ArrayList<YumRepo> repos = new ArrayList<YumRepo>();
	
	//Constructor
	public DnfJava() {
		DNFMacros.setDefaultMacros(this);
		InstalledPackageNamesParser.getPackageNames(this);		
	System.out.println("Init DnfJava");
	}
	
	
	
	public void addRepos() {
		repos_dir.toFile().mkdirs();
		//Get all the Files in repos_dir
		for(File repo : repos_dir.toFile().listFiles()) {
			repos.add(YumRepo.fromFile(repo));
		}
		
	}
	
	public void parseArgs(String[] args) {
		if (args[0].equals("dnf")){
			new DnfCommandParser(args, this);
		}
	}

	public ArrayList<com.asbestosstar.dnfjava.packages.Package> getInstalledPackages() {
        return installed_packages;
    }
	
    public Path getPackageDatabase() {
        return package_database;
    }
    
    public Path getXmlSpecLocation() {
        return xmlSpecLocation;
    }
	public void setDBPath(String path) {
		package_database = new File(path).toPath();
	}

	
	public String replaceMacrosInString(String original) {
//print every entry in macros
		String ret = original;
		for (Map.Entry<String, String> entry : macros.entrySet()) {
			 ret = original.replace(entry.getKey(), entry.getValue());
		}
		if(ret.contains("%{")) {
		ret =	replaceMacrosInString(ret);
		}
		
		
		return ret;
	}
	
	public void setBaseLocation(String location, boolean parse_macro) {
		base_location = location;
        if (parse_macro) {
            for (Map.Entry<String, String> entry : macros.entrySet()) {
                base_location = base_location.replace(entry.getKey(), entry.getValue());
            }
        }
	}
	
	public void addMacro(String key, String value) {
   //String lleva = replaceMacrosInString(key);
   //String solucion = replaceMacrosInString(value);

	macros.put(key, value);
    
	}
	
	public void replaceMacro(String key, String value) {
		macros.replace(key, value);
	}
	
	
}
