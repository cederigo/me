package ch.cederigo;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ch.unibe.BWImage;

public class YesNo {

	
	
	enum IMAGE_CLASS {
		Y,
		N
	};
	
	
	private BWImage[] imageSet;
	private int correctClassifications = 0;
	private float threshold = 0.8f;
	
	
	public YesNo(BWImage[] imageSet){
		this.imageSet = imageSet;
	}
	
	public YesNo(File setDir){
		this.imageSet = loadImageSet(setDir);
	}
	
	public void computeClassifications(){
		
		correctClassifications = 0;
		
		for(BWImage image : imageSet){
			IMAGE_CLASS imageClass = classifyImage(image);
			boolean test = verifyImageClass(image, imageClass); 			
			System.out.println("image: "+image.getName()+"\tclass(guessed): "+imageClass+"\tcorrect:"+test);
			
			if(test)
				correctClassifications++;			
		}
	}
	
	public int getResult(boolean correct){
		if(correct)
			return correctClassifications;
		else
			return imageSet.length - correctClassifications;
	}
	
	public void setThreshold(float value){
		threshold = value;
	}
	
	
	private boolean verifyImageClass(BWImage img,IMAGE_CLASS iClass){		
		
		String yesNo = img.getName().substring(0, 1);		
		
		switch(iClass){
		case Y:
			return yesNo.equals("J");
		case N:
			return yesNo.equals("N");		
		default:
			return false;
			
		}
		
		
	}	
	
	
	private IMAGE_CLASS classifyImage(BWImage img){
		if(img == null) return null;
		
		//compute h = l/r
		float h = 0;		
		int l = 0;
		int r = 0;
		int[][] pixels = img.getPixels();
		int width  = img.getWidth();
		int height = img.getHeight();
		
		
		for (int y=0; y < height; y++) {			
			for (int x=0; x < width; x++) {				
				int pixel = pixels[x][y];				
				if(pixel == 1){
					if(x <= width/2)
						l++;
					else
						r++;					
				}				
			}			
		}
		h = (float)l/(float)r;		
		if(h >= threshold)
			return IMAGE_CLASS.N;
		else
			return IMAGE_CLASS.Y;
		
	}
	
	private BWImage[] loadImageSet(File setDir){
				
		List<BWImage> imageList = new LinkedList<BWImage>();
		
		
		for(File imageFile : setDir.listFiles()){
			BWImage bwImage = new BWImage(imageFile);
			if(bwImage != null)
				imageList.add(bwImage);
		}
		
		return imageList.toArray(new BWImage[]{});
		
	}	
	
	
	
}
