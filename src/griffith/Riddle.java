package griffith;

public class Riddle {
    // Class variables.
    private String question;
    private String answer;
    private String hint;
    private int countAttempts;
    // Variable to check if hint has been displayed;
    private boolean displayed;

     // Initialise class variables.
    public Riddle(String question, String answer, String hint){
        this.question = question;
        this.answer = answer;
        this.hint = hint;
        this.countAttempts = 0;
        this.displayed = false;
    }

    
    // Getters
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getHint() {
        return hint;
    }

    public int getCountAttempts() {
        return countAttempts;
    }

    // Check if the answer given by the players is correct.
    public boolean checkAnswer(String input){
        // If the input provided by the players matches the answer of the riddle then return true, otherwise return false.
        if (input.trim().equalsIgnoreCase(answer)){
            return true;
        }
        return false;
    }

    // This method will help increase the attempts counter every time the player submits a wrong answer.
    public void incrementAttempt(){
        countAttempts ++;
    }

    // Check if the players have reached the maximum number of attempts.
    public boolean attemptsFinished(){
        if (countAttempts >= Constants.MAX_ATTEMPTS){
            return true;
        }

        return false;
    }

    // Show the players the hint after reaching a number of failed attempts.
    public String displayHint(){
        if(countAttempts >= Constants.SHOW_HINT_AFTER_ATTEMPTS){
            // Change the displayed variable to true if the hint is shown to the player.
            displayed = true;
            return hint;
        }
        return "";
    }

    // Check if the hint is already displayed.
    public boolean isHintDisplayed(){
        return false;
    }
    
}
