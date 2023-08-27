Name:           examplemod#The Package name. This is used for things like package management when that becomes more common. Should be all lowercase with no special chars except - and _
Version:        4#A version For the Package
Release:        14%{?dist}#A release number for if you have more than 1 release. The dist part is to indicate which loader and version is being used, such as FeatureCreep 4 would be fc4
Summary:        This is a tutorial mod for teaching how to use  FeatureCreep#This is a single line summary of the mod

License:        JSharp4Life Licence 1#Put the name of the Licence here
URL:            https://pagure.io/featurecreep_tutorial_mod#Website
Source0:        https://pagure.io/featurecreep_tutorial_mod#Source URL

BuildRequires:  #This is for featurecreep packages required to build, leave this blank for now as packages have not fully been implemented
Requires:       #Packages required to run the mod, leave blank as packages have not been setup yet
Main-Class: examplemod.ExampleMod#This is the main class

#Description is a longer description
%description

#Some preparation stuff, not yet implemented
%prep
#%autosetup

#Where most build stuff goes
%build
#%configure
#%make_build
buildfpm_maven %{?sources_location}/examplemod #This is the main command you need to look at as it is responsible for packaging your maven project and adding all the extra required files to fpm and sfpm files


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
