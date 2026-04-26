package Sprites;

import constants.Constants;

public class Map extends Sprite{
private boolean move;
private int logDirection=0;


    public Map(String fileName,  int x, int y, int width, int height) {
        super(fileName, x, y, width, height);
        this.move=false;

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
    	if(x<0) {x=0;}//if the new position is past the left edge of the screen 
    	if(x+getWidth()>Constants.SCREEN_WIDTH) {//checks if the log goes past the right edge
    		x=Constants.SCREEN_WIDTH-getWidth()-60;//moves the log back so it fits inside the right edge		
    	}
    	setX(x);//updates the position
    }


	public void update() {
    }
}
