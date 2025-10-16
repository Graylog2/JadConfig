package com.github.joschi.jadconfig.guice;

import com.github.joschi.jadconfig.JadConfig;
import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.Repository;
import com.github.joschi.jadconfig.RepositoryException;
import com.github.joschi.jadconfig.ValidationException;
import com.github.joschi.jadconfig.repositories.PropertiesRepository;
import com.github.joschi.jadconfig.testbeans.EmptyBean;
import com.github.joschi.jadconfig.testbeans.SimpleConfigurationBean;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class NamedConfigParametersModuleTest {
    private static final String PROPERTIES_FILE = PropertiesRepository.class.getResource("/testConfiguration.properties").getFile();

    private Repository repository;

    @BeforeEach
    public void setUp() {
        repository = new PropertiesRepository(PROPERTIES_FILE);
    }

    @Test
    public void testEmptyBean() throws RepositoryException, ValidationException {
        final EmptyBean emptyBean = new EmptyBean();
        final Injector injector = Guice.createInjector(new NamedConfigParametersModule(Collections.singleton(emptyBean)));

        assertSame(emptyBean, injector.getInstance(EmptyBean.class));
    }

    @Test
    public void testDoNotRegisterBeans() throws RepositoryException, ValidationException {
        final EmptyBean emptyBean = new EmptyBean();
        final Injector injector = Guice.createInjector(new NamedConfigParametersModule(Collections.singleton(emptyBean), false));

        assertNotSame(emptyBean, injector.getInstance(EmptyBean.class));
    }

    @Test
    public void testNamedParameters() throws RepositoryException, ValidationException {
        SimpleConfigurationBean bean = new SimpleConfigurationBean();
        new JadConfig(repository, bean).process();

        final Injector injector = Guice.createInjector(new NamedConfigParametersModule(Collections.singleton(bean)),
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(NamedParametersBean.class);
                    }
                });

        final NamedParametersBean namedParametersBean = injector.getInstance(NamedParametersBean.class);

        assertEquals(bean.getMyString(), namedParametersBean.myString);
    }

    @Test
    public void testNullValuedParameters() throws ValidationException, RepositoryException {
        NullValueBean bean = new NullValueBean();
        new JadConfig(repository, bean).process();

        final Injector injector = Guice.createInjector(new NamedConfigParametersModule(Collections.singleton(bean)),
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(NullTargetBean.class);
                    }
                });

        final NullTargetBean nullTargetBean = injector.getInstance(NullTargetBean.class);

        assertNull(nullTargetBean.target);
    }

    public static final class NullValueBean {
        @Parameter("null-test")
        public Object source = null;
    }

    public static final class NullTargetBean {
        @Inject
        @Named("null-test")
        @Nullable
        public Object target = new Object();
    }

    public static final class NamedParametersBean {
        @Inject
        @Named("test.string")
        private String myString;

        @Inject
        @Named("test.byte")
        private byte myByte;

        @Inject
        @Named("test.short")
        private short myShort;

        @Inject
        @Named("test.integer")
        private int myInt;

        @Inject
        @Named("test.long")
        private long myLong;

        @Inject
        @Named("test.float")
        private float myFloat;

        @Inject
        @Named("test.double")
        private double myDouble;

        @Inject
        @Named("test.boolean")
        private boolean myBoolean;

        @Inject
        @Named("test.list")
        private List<String> stringList;

        @Inject
        @Named("test.uri")
        private URI uri;

        @Inject
        @Named("test.file")
        private File file;
    }

}