package visual;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import processing.core.PApplet;

public class ViewStream extends PApplet{

	private static int viewWidth = 1000;
	private static int viewHeight = 1000;
	
	private static double fMin = 20;
	private static double fMax = 1000;
	
	private static double duration = 40;
	
	private static String filename;
	private static String imagename;
	
	public static void main(String[] args) {
		if (args.length > 0) {
			filename = args[0];
			imagename = args[1];
		}
		PApplet.main("visual.ViewStream");
	}

	public void settings() {
		size(viewWidth, viewHeight);
	}

	public void setup() {
		noLoop();
	}

	public void draw() {
		background(255);
		loadPixels();
		try {
			Scanner in = new Scanner(new File(filename));
			in.nextLine();
			String line = in.nextLine();
			while (line.startsWith("i")) {
				String[] grain = line.split(" ");
				int x = (int) (Double.valueOf(grain[1]) / duration * viewWidth);
				int y = viewHeight - 1 - (int) ((Double.valueOf(grain[4]) - fMin) / (fMax - fMin) * viewHeight);
				int i = x + (y * viewWidth);
				pixels[i] = color(0);
				line = in.nextLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updatePixels();
		save(imagename);
	}
}
