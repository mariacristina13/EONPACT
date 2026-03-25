package griffith;

public class Map {
private char[][] map;

public void map(char[][] map) {
	this.map=map;
}
//Create Map
public void createMap() {
	for(int i=0;i<map.length;i++) {//In this case i represents the row
		for(int j=0;j<map.length;j++) {//j represents column
			map[i][j]='.';
		}
	}
}
}
