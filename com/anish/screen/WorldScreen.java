package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Matrix;
import com.anish.calabashbros.World;
import com.anish.calabashbros.MyColor;
import com.anish.calabashbros.RandomArray;
import com.anish.calabashbros.SelectSorter;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Matrix<Calabash> bros;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        bros = new Matrix<Calabash>(Calabash.class, 16);
        
        int s = 16;
        int n = s * s;
        MyColor c = new MyColor("resources/c256.png");
        int colors[] = c.formColorTable();
        int indexs[] = new int[n];
        for(int i = 0; i < n; i++) indexs[i] = i;
        int rIndex[] = RandomArray.randomArray(indexs);
        for(int i = 0; i < n; i++) {
        	int r = (colors[i] >> 16) & 0xff;
			int g = (colors[i] >> 8) & 0xff;
			int b = colors[i] & 0xff;
			int x = rIndex[i] / s;
			int y = rIndex[i] % s;
			bros.setT(new Calabash(new Color(r, g, b), i + 1, world), x, y);
        }
        
        for(int i = 0; i < s; i++) {
        	for(int j = 0; j < s; j++) {
        		world.put(bros.getT(i, j), 10 + 2 * j, 10 + 2 * i);
        	}
        }

        //BubbleSorter<Calabash> b = new BubbleSorter<>();
        SelectSorter<Calabash> b = new SelectSorter<>();
        b.loadMatrix(bros);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Matrix<Calabash> bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Matrix<Calabash> bros, int rank) {
    	int s = bros.getSize();
        for(int i = 0; i < s ; i++) {
        	for(int j = 0; j < s; j++) {
        		Calabash tmp = bros.getT(i, j);
        		if(tmp.getRank() == rank)
        			return tmp;
        	}
        }
        return null;
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

        if (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

}
