import com.github.joschi.jadconfig.converters.LongConverter;
import com.github.joschi.jadconfig.Parameter;
import com.github.joschi.jadconfig.validators.PositiveDurationValidator;

import java.time.Duration;

public class MyAnnotationValidatorTestClass {
    @Parameter(value = "my_int", converter = LongConverter.class)
    private int myIntField = 10;

    @Parameter(value = "my_duration", validators = {PositiveDurationValidator.class})
    private Duration myDurationField = Duration.ofDays(1);
}
