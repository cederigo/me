package ch.cederigo;

import java.io.File;


public class Main {
	
	
	public static void main(String[] args) {
		
		if(args.length != 2){
			System.err.println("usage: "+Main.class.getName()+" path-to-imageSet threshold");
			System.exit(1);
		}
		
		String path = args[0];
		float threshold = Float.parseFloat(args[1]);
		
		File setDir = new File(path);
		if(!setDir.exists()){
			System.err.println("could not find imageSet: "+setDir.getAbsolutePath());
			System.exit(1);			
		}
		
		System.out.println("using image-set: "+setDir.getAbsolutePath());
		System.out.println("using threshold: "+threshold);
		
		YesNo yesNo_tr = new YesNo(setDir);
		yesNo_tr.setThreshold(threshold);
		
		System.out.println("computing classifications...");
		yesNo_tr.computeClassifications();
		System.out.println("computing classifications...done");
		System.out.println("correct: "  +yesNo_tr.getResult(true));
		System.out.println("incorrect: "+yesNo_tr.getResult(false));		
		
		
	}

}
