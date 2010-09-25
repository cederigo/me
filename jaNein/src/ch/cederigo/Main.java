package ch.cederigo;

import java.io.File;


public class Main {
	
	
	public static void main(String[] args) {		
		
		YesNo yesNo_tr = new YesNo(new File("sets/tr"));
		yesNo_tr.setThreshold(0.8f);
		
		System.out.print("computing classifications...");
		yesNo_tr.computeClassifications();
		System.out.println("computing classifications...done");
		System.out.println("correct: "  +yesNo_tr.getResult(true));
		System.out.println("incorrect: "+yesNo_tr.getResult(false));		
		
		
	}

}
