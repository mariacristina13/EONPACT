package griffith;

public class RiddleInfo {
    // Class variables that represent the riddle information.
    private String answer;
    private String hint;

     // Initialise the class variables.
    public RiddleInfo(String answer, String hint) {
        this.answer = answer;
        this.hint = hint;
    }

    // Getters and setters.
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    
    public String getHint() {
        return hint;
    }
    public void setHint(String hint){
        this.hint = hint;
    }

}
