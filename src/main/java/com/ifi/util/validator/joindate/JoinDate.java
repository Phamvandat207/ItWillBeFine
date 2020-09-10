package com.ifi.util.validator.joindate;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = JoinDateValidator.class)
@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface JoinDate {

    String message() default "Joined date must after date of birth";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
