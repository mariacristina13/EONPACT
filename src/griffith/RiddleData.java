package griffith;

import java.util.LinkedHashMap;


public class RiddleData {
    // Linked Hash Map to store the riddles and the information about them (answer and hint).
    private LinkedHashMap<String, RiddleInfo> riddleData = new LinkedHashMap<String, RiddleInfo>();

    // Initialise the Linked Hash Map and add the riddles to it.
    public RiddleData() {
        riddleData = new LinkedHashMap<String, RiddleInfo>();
    }

    // Method to add the riddles to the Linked Hash Map.
    private void addRiddles(String riddle, String answer, String hint) {
        riddleData.put(riddle, new RiddleInfo(answer, hint));
    }

}
