package co.simplon.game.customValidation;

import co.simplon.game.players.dtos.PlayerUpdateNotification;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotificationEnableWithoutDaysValidator
	implements
	ConstraintValidator<NotificationEnableWithoutDays, PlayerUpdateNotification> {

    @Override
    public boolean isValid(PlayerUpdateNotification value,
	    ConstraintValidatorContext context) {
	if (value.activate()) {
	    return value.daysBefore() != null;
	}
	return true;
    }

}
