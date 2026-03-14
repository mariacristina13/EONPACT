package griffith;

public class Riddle {
    // Class variables.
    private String question;
    private String answer;
    private String hint;
    private int countAttempts;

     // Initialise class variables.
    public Riddle(String question, String answer, String hint){
        this.question = question;
        this.answer = answer;
        this.hint = hint;
        this.countAttempts = 0;
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

    // Every time the user submits a wrong answer increase the attempts counter.
    public void incrementAttempt(){}

    // Check if the players have reached the maximum number of attempts.
    public boolean attemptsFinished(){
        return false;
    }

    // Show the players the hint after reaching a number of failed attempts.
    public String displayHint(){
        return null;
    }

    // Check if the hint is already displayed.
    public boolean isHintDisplayed(){
        return false;
    }
    
}
