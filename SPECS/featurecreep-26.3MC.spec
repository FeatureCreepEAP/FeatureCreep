Name:           featurecreep
Version:        12
Release:        26.3MC.fc12
Summary:        FeatureCreep 12 implementation for Minecraft 26.3
License:        JSharp4Life Licence 1
URL:            https://featurecreepmc.downwithamerica.com/
Source0:        https://featurecreepmc.downwithamerica.com/src/
BuildRequires:  java-25,maven
Requires:       featurecreep-loader=12,featurecreep-api=12,featurecreep-bootstrap=12
Main-Class:
SuperInjection: false

%description
FeatureCreep 12 target package for Minecraft 26.3.

%prep

%build
buildfpm %{?sources_location}/26.3MC --build

%install

%files

%changelog
* Fri Sep 25 2026 asbestosstar
- Updated for FeatureCreep 12 and Minecraft 26.3.
