package co.simplon.game.customValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy = NotificationEnableWithoutDaysValidator.class)
public @interface NotificationEnableWithoutDays {

    String message() default "When notification is enable, you should provide a number of days";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
