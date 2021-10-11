package com.anish.calabashbros;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class MyColor {

	private BufferedImage image;
	
	public MyColor(String name){
		try {
			File f = new File(name);
			image = ImageIO.read(f);
		}catch(IOException e) {
			System.out.println("Error");
		}
	}
	
	public int[] formColorTable() {
		int res[] = new int[256];
		int h = image.getHeight();
		int w = image.getWidth();
		int index = 0;
		for(int i = 0; i < h; i += 27) {
			for(int j = 0; j < w; j += 36) {
				int tmp = image.getRGB(j, i);
				res[index++] = tmp;
			}
		}
		return res;
	}
	
}
