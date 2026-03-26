package griffith;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class GameManager {

    private Set<Integer> keysHeld = new HashSet<>();

    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);
        System.out.println(KeyEvent.getKeyText(keyCode));
    }

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        System.out.println(KeyEvent.getKeyText(keyCode));
    }

}
