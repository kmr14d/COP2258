import java.awt.Color;


public class OverlayFilter extends ColorFilter {
	
	public Color[][] filter(Color[][] colors, Color[][] overlay) {
		
		int width = getWidth(colors);
		int height = getHeight(colors);
		int overlayWidth = getWidth(overlay);
		int overlayHeight = getHeight(overlay);
		
		Color[][] newColors = new Color[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if ((i < overlayWidth) && (j < overlayHeight)) {
					newColors[i][j] = averageColors(colors[i][j], overlay[i][j]);
				}
				else{
					newColors[i][j] = colors[i][j];
				}
			}
		}
	
		return newColors;
	}
	
	public Color averageColors(Color c1, Color c2) {
		int red = (c1.getRed() + c2.getRed()) / 2;
		int blue = (c1.getBlue() + c2.getBlue()) / 2;
		int green = (c1.getGreen() + c2.getGreen()) / 2;
		Color newc = new Color(red,green,blue);
		return newc;
	}
	
}
