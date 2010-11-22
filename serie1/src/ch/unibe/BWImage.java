package ch.unibe;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.media.jai.JAI;

public class BWImage {

	private int width;
	private int height;
	private int[][] img;
	private String filename;
	
	public static final int BINARIZE_THRESHOLD = 200;
		
	
	public BWImage(File imageFile) {
		if(imageFile == null) return;
		filename = imageFile.getName();
		BufferedImage bImg = JAI.create("fileload", imageFile.getAbsolutePath()).getAsBufferedImage();
		width = bImg.getWidth();
		height = bImg.getHeight();
		img = new int[width][height];
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				int[] rgb = new int[3];
				rgb = bImg.getRaster().getPixel(x, y, rgb);
				if (rgb[0] < BINARIZE_THRESHOLD) {
					img[x][y] = 1;
				} else {
					img[x][y] = 0;
				}
			}
		}
	}
	
	public String getName(){
		return filename;
	}
	
	public int[][] getPixels(){
		return img;
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

	public String toString() {
		String strImg = "";
		for (int y=0; y < height; y++) {
			String strLine = "";
			for (int x=0; x < width; x++) {
				strLine += Integer.toString(img[x][y]);
			}
			strImg += strLine + "\n";
		}
		return strImg;
	}
	
}
