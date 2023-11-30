package co.simplon.game.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import co.simplon.game.dtos.game.UpdateGame;

public class PlayerNumberValidator implements
	ConstraintValidator<PlayerNumber, UpdateGame> {

    @Override
    public boolean isValid(UpdateGame value,
	    ConstraintValidatorContext context) {
	if (value.getMinPlayer() <= value.getMaxPlayer()) {
	    return true;
	}
	return false;
    }

}
