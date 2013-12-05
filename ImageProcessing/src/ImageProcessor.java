//**********************************************************
// Assignment: A3: ImageProcessor
// Username: hdp12
// Author: Helen Parker
// Creation date: 10/21/2013
// Completion time: ~5
//
// Honor Code: I pledge that this program represents my own program code.
// I worked with (enter the names of others that you worked with on this assignment) 
// in designing and debugging my program.
//*********************************************************

import java.awt.Color;

public class ImageProcessor {
	public static void main(String args[]) {
		bwImage(); // Calling the bwImage method from this class
		overlayImage(); // Calling the overlayImage method from this class
	}
	
	public static void bwImage() {
		// Create a new ImageMap object named im with a file as a parameter to the constructor
		ImageMap im = new ImageMap("bird.jpg");
		// Create a new ColorFilter of type BlackWhiteFilter named cf
		ColorFilter cf = new BlackWhiteFilter();
		// Get the original colors (a 2d array) from the ImageMap im
		Color[][] original = im.getColors();
		// Get the filtered colors from the filter method in the cf object
		// providing the original colors as a parameter
		Color[][] filtered = cf.filter(original);
		// Create a new ImageMap named im2 with the filtered colors as a parameter to the constructor
		ImageMap im2 = new ImageMap(filtered);
		// Write out the contents of im2 using the write method, providing a file name parameter
		im2.write("bird_bw.jpg");
	}
	
	public static void overlayImage()	{
		ImageMap im = new ImageMap("bird.jpg");
		ImageMap overlay = new ImageMap("overlay.jpg");
		OverlayFilter io = new OverlayFilter();
		Color[][] watermarked = io.filter(im.getColors(),overlay.getColors());
		ImageMap waterMap = new ImageMap(watermarked);
		waterMap.write("bird_overlay.jpg");
	}
	
	public static void redImage() {
		ImageMap im = new ImageMap("bird.jpg");
		ColorFilter cf = new redFilter();
		Color[][] original = im.getColors();
		Color[][] filtered = cf.filter(original);
		ImageMap im2 = new ImageMap(filtered);
		im2.write("bird_red.jpg");
	}

	public static void greenImage() {
		ImageMap im = new ImageMap("bird.jpg");
		ColorFilter cf = new greenFilter();
		Color[][] original = im.getColors();
		Color[][] filtered = cf.filter(original);
		ImageMap im2 = new ImageMap(filtered);
		im2.write("bird_green.jpg");
	}
	
	public static void blueImage() {
		ImageMap im = new ImageMap("bird.jpg");
		ColorFilter cf = new blueFilter();
		Color[][] original = im.getColors();
		Color[][] filtered = cf.filter(original);
		ImageMap im2 = new ImageMap(filtered);
		im2.write("bird_blue.jpg");
	}
}
