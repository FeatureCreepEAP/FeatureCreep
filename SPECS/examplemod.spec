Name:           examplemod
Version:        12
Release:        example.fc12
Summary:        Example FeatureCreep 12 mod package
License:        JSharp4Life Licence 1
URL:            https://featurecreepmc.downwithamerica.com/
Source0:        https://featurecreepmc.downwithamerica.com/src/
BuildRequires:  java-25,maven
Requires:       featurecreep-loader=12,featurecreep-api=12,featurecreep-bootstrap=12
Main-Class:     examplemod.ExampleMod
SuperInjection: true

%description
Template spec for a FeatureCreep 12 mod.

%prep

%build
# Replace examplemod with your source directory.
buildfpm %{?sources_location}/examplemod --build

%install

%files

%changelog
* Fri Sep 25 2026 asbestosstar
- Updated template for FeatureCreep 12.
