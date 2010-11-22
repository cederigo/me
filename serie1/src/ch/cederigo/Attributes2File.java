package ch.cederigo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.unibe.BWImage;

public class Attributes2File {

	public static void main(String[] args){
		
		if(args.length != 3){
			System.err.println("usage: "+Attributes2File.class.getName()+" path-to-imageSet filterRegex out-file");
			System.exit(1);
		}
		
		String srcpath 		= args[0];
		String filterRegex 	= args[1]; 
		String dstpath 		= args[2];
		
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
		
		final Pattern p = Pattern.compile(filterRegex);
		
		FilenameFilter filter = new FilenameFilter() {			
			public boolean accept(File dir, String name) {
				
				Matcher matcher = p.matcher(name);
				return matcher.find();
			}
		};
		
		
		for(File f : setDir.listFiles(filter)){
			
			
			
			BWImage image = new BWImage(f);
			if(image == null) continue;
			
			
			ImageAttribute attExtractor = new ImageAttribute(image);
			System.out.println("processing file: "+image.getName()+" h="+attExtractor.getHorizontalRatio()+" v="+attExtractor.getVerticalRatio());
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
