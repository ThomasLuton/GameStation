package co.simplon.game.validations;

import co.simplon.game.dtos.game.UpdateGame;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GamePointValidator implements
	ConstraintValidator<GamePoint, UpdateGame> {

    @Override
    public boolean isValid(UpdateGame value,
	    ConstraintValidatorContext context) {
	int victory = value.getVictoryPoint();
	int draw = value.getDrawPoint();
	int lose = value.getLosePoint();

	if ((victory > draw) && (draw > lose)) {
	    return true;
	}

	return false;
    }

}
