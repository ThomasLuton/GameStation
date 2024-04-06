package co.simplon.game.players.dtos;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.game.customValidation.FileSize;
import co.simplon.game.customValidation.FileType;

public record PlayerUpdateAvatar(
	@FileSize @FileType(types = {
		MimeTypeUtils.IMAGE_JPEG_VALUE,
		MimeTypeUtils.IMAGE_PNG_VALUE }) MultipartFile avatar){

}
