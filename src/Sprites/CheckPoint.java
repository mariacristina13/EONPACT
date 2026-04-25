package Sprites;

import riddles.Riddle;

public class CheckPoint extends Sprite {
    // Class variables.
    private Riddle riddle;
    private boolean completed;
    private boolean highlighted = false;
    private float animationTick = 0;

    // Initialise class variables.
    public CheckPoint(String fileName, int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
        this.riddle = null;
        this.completed = false;
    }

    public boolean attempt(String answer) {

        if (riddle == null)
            return false;
        if (riddle.checkAnswer(answer)) {
            completed = true;
            return true;
        } else {
            riddle.incrementAttempt(); // increase attempts
            return false;
        }
    }
    public void updateAnimation() {
        animationTick += 0.1f;
    }

    public void resetAnimation() {
         animationTick = 0;
    }

    // Setter
    public void setRiddle(Riddle riddle) {
        this.riddle = riddle;
        this.completed = false;
    }

    public void setHighlighted(boolean value) {
        this.highlighted = value;
    }

    // Getters
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

    public boolean isHighlighted() {
        return highlighted;
    }

    public int getAnimatedY() {
        return (int)(y + Math.sin(animationTick) * 5); // bob up/down
    }

    public int getAnimatedSizeOffset() {
        return (int)(Math.sin(animationTick) * 3); // pulse size
    }

}