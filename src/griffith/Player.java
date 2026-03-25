package griffith;

public abstract class Player {//abstract class represents a player in the game

	public int x = 0;
    public int y = 0;
    public String name;

    public Player(String name) {//constructor

        this.name = name;

    }

    public void move(int dx, int dy) {//update the player position by using x and y changes

        x += dx;
        y += dy;

    }
}
