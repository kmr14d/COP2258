import java.awt.Color;


public class greenFilter {
	
	public Color filterOperation(Color c) {
		// Get information from the original color
		int red = c.getRed();
		int green = c.getGreen();
		int blue = c.getBlue();
		
		int average = (red + green + blue) / 3;
		
		// Create the new Color and return it
		Color out = new Color(0,average,0);
		return out;
	}
	
	
}
