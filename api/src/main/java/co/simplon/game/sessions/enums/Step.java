package co.simplon.game.sessions.enums;

public enum Step {

    DRAFT(1), STARTED(2), FINISH(3);

    private Integer number;

    private Step(Integer number) {
	this.number = number;
    }

    public Integer getNumber() {
	return this.number;
    }

}
