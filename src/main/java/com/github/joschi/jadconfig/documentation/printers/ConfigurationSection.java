package com.github.joschi.jadconfig.documentation.printers;

import com.github.joschi.jadconfig.documentation.ConfigurationEntry;

import java.util.List;

public record ConfigurationSection(String heading, String description, List<ConfigurationSection> sections, List<ConfigurationEntry> entries) {
    public boolean hasPriority() {
        return entries.stream().anyMatch(ConfigurationEntry::hasPriority);
    }
}
