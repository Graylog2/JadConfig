JadConfig
=========

[![Build Status](https://travis-ci.org/joschi/JadConfig.svg?branch=master)](https://travis-ci.org/joschi/JadConfig)
[![Coverage Status](https://img.shields.io/coveralls/joschi/JadConfig.svg)](https://coveralls.io/r/joschi/JadConfig)

JadConfig is a minimalistic annotation-driven configuration parsing framework for Java with minimal dependencies.


Example
-------

Here is a quick example:

    public class ConfigurationBean {
      @Parameter("my.stringList")
      public List<String> myList = new ArrayList<String>();

      @Parameter("my.integer")
      public int myInteger = 1;

      @Parameter(value = "my.uri", required = true)
      public URI myURI;
    }

and how you use it:

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


To use JadConfig in your project using Maven add the following lines into the dependencies section of your pom.xml:

    <dependency>
        <groupId>com.github.joschi</groupId>
        <artifactId>jadconfig</artifactId>
        <version>0.6.0</version>
    </dependency>


License
-------

JadConfig is being released under the Apache License, Version 2.0. You can download the complete license text at
http://www.apache.org/licenses/LICENSE-2.0.html
