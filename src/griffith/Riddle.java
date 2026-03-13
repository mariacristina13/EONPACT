package griffith;

public abstract class Riddle {
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
}
