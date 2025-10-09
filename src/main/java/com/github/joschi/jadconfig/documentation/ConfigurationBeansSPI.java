package com.github.joschi.jadconfig.documentation;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

public class ConfigurationBeansSPI {
    public static List<Object> loadConfigurationBeans() {
        return ServiceLoader.load(DocumentedBeansService.class).stream()
                .map(ServiceLoader.Provider::get)
                .map(DocumentedBeansService::getDocumentedConfigurationBeans)
                .flatMap(Collection::stream)
                .distinct()
                .toList();
    }
}
