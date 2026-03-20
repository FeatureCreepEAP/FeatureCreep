package featurecreep.api.bg.resource_packs;

import org.jboss.dmr.ModelNode;
import java.util.ArrayList;
import java.util.List;

public class FCPackMCMeta {

    private int pack_version;
    private String description;
    private List<String> enabledFeatures;
    private List<String> supportedFormats; // Can hold a single format or a range
    private List<String> blockFilters; // Patterns for the filter block
    private List<Overlay> overlays; // List of overlays
    private List<AdditionalLanguage> additionalLanguages; // List of additional languages

    public FCPackMCMeta(int pack_version, String description) {
        this.pack_version = pack_version;
        this.description = description;
        this.enabledFeatures = new ArrayList<>();
        this.supportedFormats = new ArrayList<>();
        this.blockFilters = new ArrayList<>();
        this.overlays = new ArrayList<>();
        this.additionalLanguages = new ArrayList<>(); // Initialize as empty list
    }

    // Additional constructor for all fields
    public FCPackMCMeta(int pack_version, String description, List<String> enabledFeatures,
                        List<String> supportedFormats, List<String> blockFilters,
                        List<Overlay> overlays, List<AdditionalLanguage> additionalLanguages) {
        this.pack_version = pack_version;
        this.description = description;
        this.enabledFeatures = enabledFeatures != null ? enabledFeatures : new ArrayList<>();
        this.supportedFormats = supportedFormats != null ? supportedFormats : new ArrayList<>();
        this.blockFilters = blockFilters != null ? blockFilters : new ArrayList<>();
        this.overlays = overlays != null ? overlays : new ArrayList<>();
        this.additionalLanguages = additionalLanguages != null ? additionalLanguages : new ArrayList<>();
    }

    // Getters and Setters
    public int getPackVersion() {
        return pack_version;
    }

    public void setPackVersion(int pack_version) {
        this.pack_version = pack_version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEnabledFeatures() {
        return enabledFeatures;
    }

    public void setEnabledFeatures(List<String> enabledFeatures) {
        this.enabledFeatures = enabledFeatures;
    }

    public List<String> getSupportedFormats() {
        return supportedFormats;
    }

    public void setSupportedFormats(List<String> supportedFormats) {
        this.supportedFormats = supportedFormats;
    }

    public List<String> getBlockFilters() {
        return blockFilters;
    }

    public void setBlockFilters(List<String> blockFilters) {
        this.blockFilters = blockFilters;
    }

    public List<Overlay> getOverlays() {
        return overlays;
    }

    public void setOverlays(List<Overlay> overlays) {
        this.overlays = overlays;
    }

    public List<AdditionalLanguage> getAdditionalLanguages() {
        return additionalLanguages;
    }

    public void setAdditionalLanguages(List<AdditionalLanguage> additionalLanguages) {
        this.additionalLanguages = additionalLanguages;
    }

    public String asJSON() {
        ModelNode node = new ModelNode();
        
        node.get("pack").get("pack_format").set(pack_version);
        node.get("pack").get("description").set(description);
        
        // Handle supported_formats
        if (!supportedFormats.isEmpty()) {
            ModelNode formatsNode = node.get("pack").get("supported_formats");
            for (String format : supportedFormats) {
                formatsNode.add(format);
            }
        }

        // Handle features
        if (!enabledFeatures.isEmpty()) {
            ModelNode featuresNode = node.get("features").get("enabled");
            for (String feature : enabledFeatures) {
                featuresNode.add(feature);
            }
        }

        // Handle filters
        if (!blockFilters.isEmpty()) {
            ModelNode filterNode = node.get("filter").get("block");
            for (String pattern : blockFilters) {
                filterNode.add(pattern);
            }
        }

        // Handle overlays
        if (!overlays.isEmpty()) {
            ModelNode overlaysNode = node.get("overlays").get("entries");
            for (Overlay overlay : overlays) {
                ModelNode overlayNode = overlaysNode.add();
                overlayNode.get("formats").set(overlay.getFormats());
                overlayNode.get("directory").set(overlay.getDirectory());
            }
        }

        // Handle additional languages
        if (!additionalLanguages.isEmpty()) {
            ModelNode langNode = node.get("language");
            for (AdditionalLanguage lang : additionalLanguages) {
                ModelNode languageNode = langNode.get(lang.getCode());
                languageNode.get("name").set(lang.getName());
                languageNode.get("region").set(lang.getRegion());
                languageNode.get("bidirectional").set(lang.isBidirectional());
            }
        }

        return node.toJSONString(false);
    }

    public static FCPackMCMeta fromJSON(String json) {
        ModelNode node = ModelNode.fromJSONString(json);
        return fromModelNode(node);
    }
    
    public static FCPackMCMeta fromModelNode(ModelNode node) {
        
        int packFormat = node.get("pack").get("pack_format").asInt();
        String description = node.get("pack").get("description").asString();
        
        List<String> enabledFeatures = new ArrayList<>();
        if (node.has("features")) {
            for (ModelNode feature : node.get("features").get("enabled").asList()) {
                enabledFeatures.add(feature.asString());
            }
        }
        
        List<String> supportedFormats = new ArrayList<>();
        if (node.has("pack") && node.get("pack").has("supported_formats")) {
            for (ModelNode format : node.get("pack").get("supported_formats").asList()) {
                supportedFormats.add(format.asString());
            }
        }
        
        List<String> blockFilters = new ArrayList<>();
        if (node.has("filter")) {
            for (ModelNode pattern : node.get("filter").get("block").asList()) {
                blockFilters.add(pattern.asString());
            }
        }
        
        List<Overlay> overlays = new ArrayList<>();
        if (node.has("overlays")) {
            for (ModelNode overlayNode : node.get("overlays").get("entries").asList()) {
                int formats = overlayNode.get("formats").asInt();
                String directory = overlayNode.get("directory").asString();
                overlays.add(new Overlay(formats, directory));
            }
        }
        
        List<AdditionalLanguage> additionalLanguages = new ArrayList<>();
        if (node.has("language")) {
            ModelNode langNode = node.get("language");
            for (String code : langNode.keys()) {
                String name = langNode.get(code).get("name").asString();
                String region = langNode.get(code).get("region").asString();
                boolean bidirectional = langNode.get(code).get("bidirectional").asBoolean();
                additionalLanguages.add(new AdditionalLanguage(code, name, region, bidirectional));
            }
        }
        
        return new FCPackMCMeta(packFormat, description, enabledFeatures, supportedFormats, blockFilters, overlays, additionalLanguages);
    }

    // Inner class for Overlay
    public static class Overlay {
        private int formats;
        private String directory;

        public Overlay(int formats, String directory) {
            this.formats = formats;
            this.directory = directory;
        }

        public int getFormats() {
            return formats;
        }

        public void setFormats(int formats) {
            this.formats = formats;
        }

        public String getDirectory() {
            return directory;
        }

        public void setDirectory(String directory) {
            this.directory = directory;
        }
    }

    // Inner class for AdditionalLanguage
    public static class AdditionalLanguage {
        private String code;
        private String name;
        private String region;
        private boolean bidirectional;

        public AdditionalLanguage(String code, String name, String region, boolean bidirectional) {
            this.code = code;
            this.name = name;
            this.region = region;
            this.bidirectional = bidirectional;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public boolean isBidirectional() {
            return bidirectional;
        }

        public void setBidirectional(boolean bidirectional) {
            this.bidirectional = bidirectional;
        }
    }
}
