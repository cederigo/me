package ch.unibe;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("usage: java -jar JaNein.jar nameImg");
		} else {
			BWImage img = new BWImage(args[0]);
			System.out.println(img.toString());
		}
	}
	
}
