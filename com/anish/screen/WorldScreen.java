package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

import maze.*;

public class WorldScreen implements Screen {

    private World world;
    private Calabash bro;

    public WorldScreen() {
        world = new World();
        bro = new Calabash(new Color(255, 0, 0), (char)2, world);
        world.put(bro, 0, 0);
        
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
    	int k = key.getKeyCode();
    	int x = bro.getX(), y = bro.getY();
    	int tmpx = x, tmpy = y;
    	switch(k) {
    	case KeyEvent.VK_W:
    		tmpy--;
    		break;
    	case KeyEvent.VK_S:
    		tmpy++;
    		break;
    	case KeyEvent.VK_A:
    		tmpx--;
    		break;
    	case KeyEvent.VK_D:
    		tmpx++;
    		break;
    	default: break;
    	}
    	if(world.validMove(tmpx, tmpy)) {
    		bro.moveTo(tmpx, tmpy);
    		world.put(new Floor(world), x, y);
    	}
        return this;
    }

}
