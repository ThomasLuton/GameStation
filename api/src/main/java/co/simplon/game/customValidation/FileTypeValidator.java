package co.simplon.game.customValidation;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypeValidator implements
	ConstraintValidator<FileType, MultipartFile> {

    private String[] types;

    @Override
    public void initialize(FileType constraintAnnotation) {
	types = constraintAnnotation.types();
    }

    @Override
    public boolean isValid(MultipartFile value,
	    ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}

	if (ArrayUtils.contains(types,
		value.getContentType())) {
	    return true;

	}

	return false;
    }

}