package ch.cederigo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import ch.unibe.BWImage;

public class Attributes2File {

	public static void main(String[] args){
		
		if(args.length != 2){
			System.err.println("usage: "+Attributes2File.class.getName()+" path-to-imageSet out-file");
			System.exit(1);
		}
		
		String srcpath = args[0];
		String dstpath = args[1];
		
		File setDir = new File(srcpath);
		if(!setDir.exists()){
			System.err.println("could not find imageSet: "+setDir.getAbsolutePath());
			System.exit(1);			
		}
		
		File outFile = new File(dstpath);
		BufferedWriter writer = null;
		try {
			 writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		
		for(File f : setDir.listFiles()){
			
			BWImage image = new BWImage(f);
			if(image == null) continue;
			
			ImageAttribute attExtractor = new ImageAttribute(image);
			try {
				writer.write(attExtractor.getHorizontalRatio()+" "+attExtractor.getVerticalRatio()+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
