package co.simplon.game.dtos.game;

public interface GameDetailView {

    Long getId();

    String getGameName();

    String getGameDetail();

    int getMinPlayer();

    int getMaxPlayer();

    String getThumbnail();

    boolean isAvailable();

    int getVictoryPoint();

    int getLosePoint();

    int getDrawPoint();
}
