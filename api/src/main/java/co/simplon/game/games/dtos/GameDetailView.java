package co.simplon.game.games.dtos;

public interface GameDetailView {

    Long getId();

    String getGameName();

    String getGameDetail();

    Integer getMinPlayer();

    Integer getMaxPlayer();

    String getThumbnail();

    boolean isAiAvailable();
}
