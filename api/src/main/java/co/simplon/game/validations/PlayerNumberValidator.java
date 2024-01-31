package co.simplon.game.validations;

import co.simplon.game.dtos.game.UpdateGame;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

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
