Name:           featurecreep#The Package name. This is used for things like package management when that becomes more common. Should be all lowercase with no special chars except - and _
Version:        4.0AM18#A version For the Package
Release:        1.20.1QuiltMC.el9%{?dist}#A release number for if you have more than 1 release. The dist part is to indicate which loader and version is being used, such as FeatureCreep 4 would be fc4
Summary:        Modloader for making version independent mods#This is a single line summary of the mod

License:        JSharp4Life Licence 1#Put the name of the Licence here
URL:            https://featurecreepmc.downwithamerica.com/#Website
Source0:        https://featurecreepmc.downwithamerica.com/src/#Source URL

BuildRequires:  #This is for featurecreep packages required to build, leave this blank for now as packages have not fully been implemented
Requires:       #Packages required to run the mod, leave blank as packages have not been setup yet
Main-Class:

#Description is a longer description
%description

#Some preparation stuff, not yet implemented
%prep
#%autosetup

#Where most build stuff goes
%build
#%configure
#%make_build
buildfpm_maven %{?sources_location}/1.20.1QuiltMC #This is the main command you need to look at as it is responsible for packaging your maven project and adding all the extra required files to fpm and sfpm files
remap(/home/rhel/fcdependencies/featurecreep-intermediary-fabric-intermediary.pdme,/home/rhel/fcdependencies/1.20.1FabricMC/)

#Some stuff for installing, not yet implemented
%install
#rm -rf $RPM_BUILD_ROOT
#%make_install

#This is not implemented and may not be nessesary as you wont need to specify all files unless they are in a custom location
%files
#%license add-license-file-here
#%doc add-docs-here

#A Changelog
%changelog
* Thu Sep 01 2022 rhel
- 
