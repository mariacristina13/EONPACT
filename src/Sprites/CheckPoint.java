package Sprites;

import riddles.Riddle;

public class CheckPoint extends Sprite {

    private Riddle riddle;
    private boolean completed;
    private String type; // "normal", "fast", "slow"

    public CheckPoint(String fileName, int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
        this.riddle = null;
        this.completed = false;
    }


    public void setRiddle(Riddle riddle){
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
    public void setType(String type) {//add setter
        this.type = type;
    }

    public String getType() {//add getter
        return type;
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