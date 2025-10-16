package com.github.joschi.jadconfig.documentation;

public record ConfigurationEntryWithSection(ConfigurationEntry entry, String sectionHeading, String sectionDescription) {
    public boolean hasSection() {
        return sectionHeading != null && !sectionHeading.isBlank();
    }
}
