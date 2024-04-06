package co.simplon.game.customValidation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements
	ConstraintValidator<FileSize, MultipartFile> {

    private final long MAXSIZE = (long) 1e+7;

    @Override
    public boolean isValid(MultipartFile value,
	    ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}
	return (value.getSize() < MAXSIZE);
    }

}
