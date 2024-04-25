package co.simplon.game.customValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = FileTypeValidator.class)
public @interface FileType {

    // Permet de préciser un message d'erreur quand on gère les messages coté back
    String message() default "File type is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] types();

}