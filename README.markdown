JadConfig
=========

This is an annotation-driven configuration parsing framework for Java.

Here is a quick example:

    public class ConfigurationBean {
      @Parameter("my.stringList")
      public List<String> myList = Lists.newArrayList();

      @Parameter("my.integer")
      public int myInteger = 1;

      @Parameter("my.uri", required = true)
      public URI myURI;
    }

and how you use it:

    ConfigurationBean bean = new ConfigurationBean();
    new JadConfig(new PropertiesRepository("my.properties"), bean).process();

    Assert.assertNotNull(bean.myList);
