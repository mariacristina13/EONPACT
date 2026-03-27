package Sprites;

import riddles.Riddle;

public class CheckPoint extends Sprite {

    private Riddle riddle;
    private boolean completed;

    public CheckPoint(String fileName, int x, int y, int w, int h, Riddle riddle) {
        super(fileName, x, y, w, h);
        this.riddle = riddle;
        this.completed = false;
    }

    public boolean attempt(String answer) {
        if (riddle.checkAnswer(answer)) {
            completed = true;
            return true;
        } else {
            riddle.incrementAttempt();
            return false;
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isFailed() {
        return riddle.attemptsFinished();
    }

    public String getHint() {
        return riddle.displayHint();
    }

    public Riddle getRiddle() {
        return riddle;
    }
}