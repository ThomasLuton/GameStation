package co.simplon.game.validations;

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
@Constraint(validatedBy = GamePointValidator.class)
public @interface GamePoint {

    String message() default "Game point incoherent";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
