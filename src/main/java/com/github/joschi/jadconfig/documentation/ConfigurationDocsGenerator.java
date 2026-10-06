package com.github.joschi.jadconfig.documentation;

import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.ReflectionUtils;
import com.github.joschi.jadconfig.documentation.printers.ConfigFileDocsPrinter;
import com.github.joschi.jadconfig.documentation.printers.ConfigurationSection;
import com.github.joschi.jadconfig.documentation.printers.CsvDocsPrinter;
import com.github.joschi.jadconfig.documentation.printers.DocsPrinter;
import com.github.joschi.jadconfig.info.ParameterMetadata;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.ClassUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ConfigurationDocsGenerator {

    public static void main(String[] args) throws IOException {
        final ConfigurationDocsGenerator generator = new ConfigurationDocsGenerator();
        generator.generateDocumentation(parseDocumentationFormat(args), ConfigurationBeansSPI::loadConfigurationBeans);
    }

    @Nonnull
    private static DocumentationFormat parseDocumentationFormat(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("This command needs two arguments - a format and file path. For example" + "csv ${project.build.directory}/configuration-documentation.csv");
        }
        final String format = args[0].toLowerCase(Locale.ROOT).trim();
        final String file = args[1];
        return new DocumentationFormat(format, file);
    }

    public void generateDocumentation(DocumentationFormat format, Supplier<List<Object>> configurationBeans) throws IOException {
        final List<ConfigurationSection> sections = detectConfigurationSections(configurationBeans);
        try (final DocsPrinter writer = createWriter(format)) {
            writer.write(sections);
        }
    }

    private DocsPrinter createWriter(DocumentationFormat format) throws IOException {
        final FileWriter fileWriter = new FileWriter(format.outputFile(), StandardCharsets.UTF_8);
        // TODO: if the format list expands, introduce DI and factories for printers
        return switch (format.format()) {
            case "csv" -> new CsvDocsPrinter(fileWriter);
            case "conf" -> new ConfigFileDocsPrinter(fileWriter);
            default -> throw new IllegalArgumentException("Unsupported format " + format.format());
        };
    }

    /**
     * Collects all configuration options from all available configuration beans.
     */
    private List<ConfigurationSection> detectConfigurationSections(Supplier<List<Object>> configurationBeans) {
        return configurationBeans.get()
                .stream()
                .map(ConfigurationDocsGenerator::beanToConfigSections)
                .sorted(Comparator.comparing(ConfigurationSection::hasPriority).reversed())
                .toList();
    }

    private static ConfigurationSection beanToConfigSections(Object configurationBean) {

        String sectionHeading = null;
        String sectionDescription = null;
        if (configurationBean.getClass().isAnnotationPresent(DocumentationSection.class)) {
            final DocumentationSection documentationSection = configurationBean.getClass().getAnnotation(DocumentationSection.class);
            sectionHeading = documentationSection.heading();
            sectionDescription = documentationSection.description();
        }

        final List<ConfigurationEntryWithSection> entries = parameterFields(configurationBean.getClass()).stream()
                .filter(f -> ParameterMetadata.of(f).visible())
                .map(f -> toConfigurationEntry(f, configurationBean))
                .toList();

        final List<ConfigurationEntry> entriesWithoutSection = getEntriesWithoutSection(entries);
        final List<ConfigurationSection> sortedSections = sectionsFromEntries(entries);

        return new ConfigurationSection(sectionHeading, sectionDescription, sortedSections, entriesWithoutSection);
    }

    @Nonnull
    private static List<ConfigurationSection> sectionsFromEntries(List<ConfigurationEntryWithSection> entries) {
        return entries.stream()
                .filter(ConfigurationEntryWithSection::hasSection)
                .collect(groupBySection())
                .values()
                .stream()
                .sorted(sortByPriority())
                .toList();
    }

    private static Comparator<ConfigurationSection> sortByPriority() {
        return Comparator.comparing(ConfigurationSection::hasPriority, Comparator.reverseOrder());
    }

    private static Collector<ConfigurationEntryWithSection, ?, Map<String, ConfigurationSection>> groupBySection() {
        return Collectors.groupingBy(ConfigurationEntryWithSection::sectionHeading, Collectors.collectingAndThen(Collectors.toList(), ConfigurationDocsGenerator::toConfigurationSection));
    }

    private static ConfigurationSection toConfigurationSection(List<ConfigurationEntryWithSection> list) {
        final ConfigurationEntryWithSection section = list.stream().findFirst().orElseThrow(() -> new IllegalArgumentException("No configuration section found but expected!"));
        final List<ConfigurationEntry> entries = list.stream().map(ConfigurationEntryWithSection::entry).collect(Collectors.toList());
        return new ConfigurationSection(section.sectionHeading(), section.sectionDescription(), Collections.emptyList(), entries);
    }

    @Nonnull
    private static List<ConfigurationEntry> getEntriesWithoutSection(List<ConfigurationEntryWithSection> entries) {
        return entries.stream()
                .filter(e -> !e.hasSection())
                .map(ConfigurationEntryWithSection::entry)
                .sorted(Comparator.comparing(ConfigurationEntry::hasPriority, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    /**
     * Returns all fields annotated with {@link Parameter}, including inherited ones. Fields of superclasses come first,
     * so that shared parameters are documented before the specific ones of a subclass.
     */
    private static List<Field> parameterFields(Class<?> beanClass) {
        final List<Field> fields = new ArrayList<>();
        for (Class<?> c = beanClass; c != null; c = c.getSuperclass()) {
            final List<Field> declaredFields = Arrays.stream(c.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(Parameter.class))
                    .toList();
            fields.addAll(0, declaredFields);
        }
        return fields;
    }

    private static ConfigurationEntryWithSection toConfigurationEntry(Field f, Object instance) {
        final ParameterMetadata metadata = ParameterMetadata.of(f);
        final Object defaultValue = getDefaultValue(f, instance);
        final String type = getType(f);

        final DocumentationSection documentationSection = f.getAnnotation(DocumentationSection.class);
        String sectionHeading = null;
        String sectionDescription = null;
        if (documentationSection != null) {
            sectionHeading = documentationSection.heading();
            sectionDescription = documentationSection.description();
        }
        final ConfigurationEntry entry = new ConfigurationEntry(instance.getClass(), f.getName(), type, metadata.name(), defaultValue, metadata.required(), metadata.documentation());
        return new ConfigurationEntryWithSection(entry, sectionHeading, sectionDescription);
    }

    private static Object getDefaultValue(Field f, Object instance) {
        try {
            return ReflectionUtils.getFieldValue(instance, f);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getType(Field f) {
        if (f.getType().isPrimitive()) { // unify primitive types and wrappers, e.g. int -> Integer
            return ClassUtils.primitiveToWrapper(f.getType()).getSimpleName();
        } else {
            return f.getType().getSimpleName();
        }
    }
}
