# JadConfig
[![Build Status](https://travis-ci.org/joschi/JadConfig.svg?branch=master)](https://travis-ci.org/joschi/JadConfig)
[![Coverage Status](https://img.shields.io/coveralls/joschi/JadConfig.svg)](https://coveralls.io/r/joschi/JadConfig)

JadConfig is a minimalistic annotation-driven configuration parsing framework for Java with minimal dependencies.


## Example

Here is a quick example of a Java class used as configuration bean:

    public class ConfigurationBean {
      @Parameter("my.stringList")
      public List<String> myList = new ArrayList<String>();

      @Parameter("my.integer")
      public int myInteger = 1;

      @Parameter(value = "my.uri", required = true)
      public URI myURI;
    }

and how you initialize it with JadConfig:

    ConfigurationBean bean = new ConfigurationBean();
    new JadConfig(new PropertiesRepository("my.properties"), bean).process();

    Assert.assertNotNull(bean.myList);


You can also use multiple repositories as source for your configuration (first match wins):

    ConfigurationBean bean = new ConfigurationBean();
    new JadConfig(
            Arrays.asList(
                new EnvironmentRepository(),
                new PropertiesRepository("my.properties")
            ),
            bean)
        .process();

    Assert.assertNotNull(bean.myList);

### Joda-Time

JadConfig optionally supports [Joda-Time](http://www.joda.org/joda-time/). In order to use it just add the Joda-Time
dependency to your `pom.xml`:

    <dependency>
        <groupId>joda-time</groupId>
        <artifactId>joda-time</artifactId>
        <version>2.8</version>
    </dependency>


And register `JodaTimeConverterFactory` with the JadConfig instance:

    JadConfig jadConfig = new JadConfig(repository, configurationBean);
    jadConfig.addConverterFactory(new JodaTimeConverterFactory());
    jadConfig.process();


### Guava

JadConfig optionally supports some data types from [Google Guava](https://github.com/google/guava/). In order to use
it just add the Google Guava dependency to your `pom.xml`:

    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>18.0</version>
    </dependency>


And register `GuavaConverterFactory` with the JadConfig instance:

    JadConfig jadConfig = new JadConfig(repository, configurationBean);
    jadConfig.addConverterFactory(new GuavaConverterFactory());
    jadConfig.process();


Currently the following data types are being supported:

  * `CacheBuilderSpec`
  * `HashCode`
  * `HostAndPort`
  * `HostSpecifier`
  * `InternetDomainName`
  * `MediaType`
  * `UnsignedInteger`
  * `UnsignedLong`


### Guice

JadConfig optionally supports registering named bindings in [Google Guice](https://github.com/google/guice/). In order
to use it just add the Google Guice dependency to your `pom.xml`:

    <dependency>
        <groupId>com.google.inject</groupId>
        <artifactId>guice</artifactId>
        <version>4.0</version>
    </dependency>


And register `NamedConfigParametersModule` with the Guice Injector:

    Injector injector = Guice.createInjector(new NamedConfigParametersModule(Collections.singleton(configurationBean)));


The name of the bindings are identical to the `@Parameter` name.

Example:

    public class MyConfigBean {
        @Parameter("my.custom.config")
        public String customConfig;
    }

    // Create injector and register NamedConfigParametersModule.
    // [...]

    public class MyClass {
        @Inject
        public MyClass(@Named("my.custom.config") String customConfig) {
            // ...
        }
    }

    // MyClass will be instantiated with the value of customConfig from the MyConfigBean instance.
    MyClass myClass = injector.getInstance(MyClass.class);

Please note that nullable properties which should be injected by Guice have to be annotated with `@Nullable`,
see [UseNullable](https://github.com/google/guice/wiki/UseNullable) in the Guice wiki for details.


## Maven

To use JadConfig in your project using Maven add the following lines into the dependencies section of your `pom.xml`:

    <dependency>
        <groupId>com.github.joschi</groupId>
        <artifactId>jadconfig</artifactId>
        <version>0.11.0</version>
    </dependency>


## Support

Please file bug reports and feature requests in [GitHub issues](https://github.com/joschi/JadConfig/issues).


## License

JadConfig is being released under the Apache License, Version 2.0. You can download the complete license text at
http://www.apache.org/licenses/LICENSE-2.0.html
