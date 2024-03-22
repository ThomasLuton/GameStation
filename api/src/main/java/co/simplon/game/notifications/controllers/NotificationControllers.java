package co.simplon.game.notifications.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.game.notifications.dtos.NotificationDetailView;
import co.simplon.game.notifications.dtos.NotificationLightView;
import co.simplon.game.notifications.services.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationControllers {

    private NotificationService notificationService;

    public NotificationControllers(
	    NotificationService notificationService) {
	this.notificationService = notificationService;
    }

    @GetMapping("/players/{playerId}")
    public List<NotificationLightView> getAllForOnePlayer(
	    @PathVariable Long playerId) {
	return notificationService
		.getAllForOnePlayer(playerId);
    }

    @GetMapping("/{id}")
    public NotificationDetailView getOneById(
	    @PathVariable Long id) {
	return notificationService.getOneById(id);
    }

}
