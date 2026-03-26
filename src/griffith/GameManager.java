package griffith;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class GameManager {

<<<<<<< HEAD

    
}
=======
    private Set<Integer> keysHeld = new HashSet<>();

    public void keyPressed(int keyCode) {
        keysHeld.add(keyCode);
        System.out.println(KeyEvent.getKeyText(keyCode));
    }

    public void keyReleased(int keyCode) {
        keysHeld.remove(keyCode);
        System.out.println(KeyEvent.getKeyText(keyCode));
    }

    public boolean isKeyHeld(int keyCode) {
        return keysHeld.contains(keyCode);
    }

    public void update() {
        if (isKeyHeld(Constants.LEFTKEY))  System.out.println("Left");
        if (isKeyHeld(Constants.RIGHTKEY)) System.out.println("Right");
    }

}
>>>>>>> e5f78a098c75b97552561c19ecf47bf15dcadac9
