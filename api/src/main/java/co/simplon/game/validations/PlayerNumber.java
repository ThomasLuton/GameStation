package co.simplon.game.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Constraint(validatedBy = PlayerNumberValidator.class)
public @interface PlayerNumber {

    String message() default "Number of player incoherent";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
