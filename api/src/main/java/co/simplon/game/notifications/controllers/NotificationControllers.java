package co.simplon.game.notifications.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.notifications.dtos.NotificationDetailView;
import co.simplon.game.notifications.dtos.NotificationLightView;
import co.simplon.game.notifications.services.NotificationService;
import co.simplon.game.utils.AuthHelper;

@RestController
@RequestMapping("/notifications")
public class NotificationControllers {

    private NotificationService notificationService;
    @Autowired
    private AuthHelper authHelper;

    public NotificationControllers(
	    NotificationService notificationService) {
	this.notificationService = notificationService;
    }

    @GetMapping()
    public List<NotificationLightView> getAllForOnePlayer(
	    JwtAuthenticationToken principal) {
	Map<String, Object> user = authHelper
		.getPrincipalInfo(principal);
	Long suffix = (Long) user.get("suffix");
	return notificationService
		.getAllForOnePlayer(suffix.intValue());
    }

    @GetMapping("/unread")
    public Integer getNumberOfUnreadNotifications(
	    JwtAuthenticationToken principal) {
	Map<String, Object> user = authHelper
		.getPrincipalInfo(principal);
	Long suffix = (Long) user.get("suffix");
	return notificationService
		.getNumberOfUnreadNotifications(
			suffix.intValue());
    }

    @GetMapping("/{id}")
    public NotificationDetailView readOneById(
	    @PathVariable Long id) {
	return notificationService.readOneById(id);
    }

    @PatchMapping("/unread/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void markAsUnread(@PathVariable Long id) {
	notificationService.markAsUnread(id);
    }

}
