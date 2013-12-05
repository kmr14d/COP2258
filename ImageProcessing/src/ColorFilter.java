import java.awt.Color;

public class ColorFilter {
	
	public Color[][] filter(Color[][] colors)	{
		int width = getWidth(colors);
		int height = getHeight(colors);
				
		Color[][] newColors = new Color[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				newColors[i][j] = filterOperation(colors[i][j]);
			}
		}
		
		return newColors;
	}
	
	public Color filterOperation(Color c) {
		return c;
	}
	
	public int getWidth(Color[][] c) {
		return c.length;
	}
	
	public int getHeight(Color[][] c) {
		return c[0].length;
	}

}
