package Sprites;

import constants.Constants;

public class Map extends Sprite{
private boolean move;
private int logDirection=0;
    //private BufferedImage tile2;
    //private BufferedImage tile3;
    //private BufferedImage tile4;


    public Map(String fileName,  int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
        this.move=false;
        /* try {
            tile2 = ImageIO.read(new File("images/" + tile2FileName));
        } catch (IOException e) {
            e.printStackTrace();
            tile2 = null;
        }

        try {
            tile3 = ImageIO.read(new File("images/" + tile3FileName));
        } catch (IOException e) {
            e.printStackTrace();
            tile3 = null;
        }

        try {
            tile4 = ImageIO.read(new File("images/" + tile4FileName));
        } catch (IOException e) {
            e.printStackTrace();
            tile3 = null;
        } */
    }
   
    
  

//Ability to move method
	public boolean mobility() {
    return move;
    }
	
 //Getter and setter   
	public int getLogDirection() {
		return logDirection;
	}
	public void setLogDirection(int logDirection) {
		this.logDirection = logDirection;
	}
	  public boolean isMove() {
			return move;
		}
		public void setMove(boolean move) {
			this.move = move;
		}

//move log method
	public void moveLog(int direction) {
    	if(!mobility()) {//checks if movement is not allowed
    		return;
    		}
    	this.logDirection=direction;
    	int x=getX()+direction*Constants.PLAYER_SPEED;
    	if(x<0) {x=0;}//if the new position is past the left edge of the screen reset position
    	if(x+getWidth()>Constants.SCREEN_WIDTH) {//checks if the log goes past the right edge
    		x=Constants.SCREEN_WIDTH-getWidth();//moves the log back so it fits inside the right edge		
    	}
    	setX(x);//updates the position
    }
    /*  
    public void draw(Graphics g, int panelWidth, int panelHeight) {
    
        int x = 50;   
        int x2 = x + Constants.TILE_WIDTH + 10;
        int x3 = x2 + Constants.TILE_WIDTH + 10;
        int x4 =x3 + 50;        
        int y = 100; 
    
        if (getImage() != null) {
            g.drawImage(getImage(), x, y, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }

        if (tile2 != null) {
            g.drawImage(tile2, x2, y+50, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }

        if (tile3 != null) {
            g.drawImage(tile3, x3, y-30, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }

        if (tile4 != null) {
            g.drawImage(tile4, x4, y+60, Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        }
    } */
    

    


	public void update() {
    }
}
