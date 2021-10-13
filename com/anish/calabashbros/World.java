package com.anish.calabashbros;

import maze.*;
import java.lang.Math;

public class World {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private Tile<Thing>[][] tiles;

    public World() {

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        int dim = Math.min(WIDTH, HEIGHT);
        MazeGenerator MG = new MazeGenerator(dim);
        MG.generateMaze();
        int[][] Maze = MG.getIntMaze();
        
        for(int i = 0; i < WIDTH; i++) {
        	for(int j = 0; j < HEIGHT; j++) {
        		tiles[i][j] = new Tile<>(i, j);
        		tiles[i][j].setThing(Maze[i][j] == 1 ? new Floor(this): new Wall(this));
        	}
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }
    
    public boolean validMove(int x, int y) {
    	return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT && this.get(x, y) instanceof Floor;
    }

}
