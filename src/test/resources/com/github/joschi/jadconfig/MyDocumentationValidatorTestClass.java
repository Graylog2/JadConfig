import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.documentation.Documentation;

import java.time.Duration;

public class MyDocumentationValidatorTestClass {

    @Documentation(visible = false)
    @Parameter(value = "my_hidden_property")
    private String hiddenProperty;

    @Documentation("configure some int value")
    @Parameter(value = "my_int", required = true)
    private int myIntField = 10;

    // here's no @Documentation annotation, should lead to an error
    @Parameter(value = "my_duration")
    private Duration myDurationField = Duration.ofDays(1);

}
