JadConfig
=========

JadConfig is a small annotation-driven configuration parsing framework for Java with minimal dependencies.

http://joschi.github.com/JadConfig/


Example
-------

Here is a quick example:

    public class ConfigurationBean {
      @Parameter("my.stringList")
      public List<String> myList = Lists.newArrayList();

      @Parameter("my.integer")
      public int myInteger = 1;

      @Parameter(value = "my.uri", required = true)
      public URI myURI;
    }

and how you use it:

    ConfigurationBean bean = new ConfigurationBean();
    new JadConfig(new PropertiesRepository("my.properties"), bean).process();

    Assert.assertNotNull(bean.myList);


To use JadConfig in your project using Maven add the following lines into the dependencies section of your pom.xml:

    <dependency>
        <groupId>com.github.joschi</groupId>
        <artifactId>jadconfig</artifactId>
        <version>0.3</version>
    </dependency>


License
-------
JadConfig is being released under the Apache License, Version 2.0. You can download the complete license text at
http://www.apache.org/licenses/LICENSE-2.0.html
