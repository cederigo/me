package ch.cederigo;

import ch.unibe.BWImage;

public class ImageAttribute {

	private BWImage img;
	private boolean done = false;
	private double horizRatio;
	private double vertRatio;
	
	public ImageAttribute(BWImage inImage){
		this.img = inImage;
	}
	
	private void doWork(){
		if(done) return;
		
		int l = 0;//left
		int r = 0;//right
		int t = 0;//top
		int b = 0;//bottom
		int[][] pixels = img.getPixels();
		int width  = img.getWidth();
		int height = img.getHeight();		
		
		for (int y=0; y < height; y++) {			
			for (int x=0; x < width; x++) {				
				int pixel = pixels[x][y];
				
				if(pixel == 1){
					//left?
					if(x < width/2){ l++ ; }else{ r++ ; }
					//top?
					if(y < height/2){ t++; }else{ b++; }
				}				
			}			
		}
		if(r != 0)
			horizRatio = (float)l/(float)r;
		else
			horizRatio = l;
		if(b != 0)
			vertRatio = (float)t/(float)b;
		else
			vertRatio = b;
		
		
		done = true;	
		
	}
	
	
	public double getHorizontalRatio(){
		doWork();
		return horizRatio;
		
	}
	
	
	public double getVerticalRatio(){
		doWork();
		return vertRatio;
		
	}
	
	
	
	
}
