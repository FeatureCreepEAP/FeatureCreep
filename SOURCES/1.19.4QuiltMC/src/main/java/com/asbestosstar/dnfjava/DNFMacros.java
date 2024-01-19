package com.asbestosstar.dnfjava;

public class DNFMacros implements Macros{

	public static void setDefaultMacros(DnfJava instance) {
		instance.addMacro("%{_base}", "/");
	    instance.addMacro("%{_sysconfdir}", "%{_base}/etc");
	    instance.addMacro("%{_prefix}", "%{_base}/usr");
	    instance.addMacro("%{_exec_prefix}", "%{_prefix}");
	    instance.addMacro("%{_includedir}", "%{_prefix}/include");
	    instance.addMacro("%{_bindir}", "%{_exec_prefix}/bin");
	    instance.addMacro("%{_libdir}", "%{_exec_prefix}/%{_lib}");
	    instance.addMacro("%{_libexecdir}", "%{_exec_prefix}/libexec");
	    instance.addMacro("%{_sbindir}", "%{_exec_prefix}/sbin");
	    instance.addMacro("%{_datadir}", "%{_datarootdir}");
	    instance.addMacro("%{_datarootdir}", "/usr/share");
	    instance.addMacro("%{_infodir}", "%{_datarootdir}/info");
	    instance.addMacro("%{_mandir}", "%{_datarootdir}/man");
	    instance.addMacro("%{_docdir}", "%{_datadir}/doc");
	    instance.addMacro("%{_rundir}", "%{_base}/run");
	    instance.addMacro("%{_localstatedir}", "%{_base}/var");
	    instance.addMacro("%{_sharedstatedir}", "%{_localstatedir}/var/lib");
	    if(System.getProperty("os.arch").contains("64")) {
	    instance.addMacro("%{_lib}", "lib64"); // or "lib" on 32-bit platforms
	    }else {
	    instance.addMacro("%{_lib}", "lib");
	    }
	    
	    instance.addMacro("%{_datarootdir}", "%{_prefix}/share");
	    instance.addMacro("%{_var}", "%{_base}/var");
	    instance.addMacro("%{_tmppath}", "%{_var}/tmp");
	    instance.addMacro("%{_usr}", "%{_base}/usr");
	    instance.addMacro("%{_usrsrc}", "%{_usr}/src");
	    instance.addMacro("%{_initddir}", "%{_sysconfdir}/rc.d/init.d");
	    instance.addMacro("%{_initrddir}", "%{_initddir}");
	    
	    instance.addMacro("%{buildroot}", "%{_buildrootdir}/%{name}-%{version}-%{release}.%{_arch}");
	    instance.addMacro("%{_topdir}", "%{getenv:HOME}/rpmbuild");
	    instance.addMacro("%{_builddir}", "%{_topdir}/BUILD");
	    instance.addMacro("%{_rpmdir}", "%{_topdir}/RPMS");
	    instance.addMacro("%{_sourcedir}", "%{_topdir}/SOURCES");
	    instance.addMacro("%{_specdir}", "%{_topdir}/SPECS");
	 
	    //If a Username needs to be defined the tag should be %{_username}, if you want a username but to use root then %{_whoami}
	 if(instance.macros.containsKey("%{_username}")) {
	    instance.addMacro("%{getenv:HOME}", "%{_base}/home/%{_username}");
	 }else {
		instance.addMacro("%{getenv:HOME}", "%{_base}/root");  //root
	 }
	    
	    
	    //TODO
	    instance.addMacro("%{optflags}","");
	    instance.addMacro("%{__global_cflags}","");
	    instance.addMacro("%{build_ldflags}","");
		
	}
	
	
}
