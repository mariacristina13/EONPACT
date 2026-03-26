package griffith;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class GameManager {

    private Set<Integer> keysHeld = new HashSet<>();

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        System.out.println("Key released: " + KeyEvent.getKeyText(keyCode));
    }

}
