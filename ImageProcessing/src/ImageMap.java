import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Benjamin McLaughlin
 * @author Geoffery L. Miller
 * 
 */
public class ImageMap {

	private Color[][] colors;
       
	private int imageType;
    private int height;
    private int width;
    
    /**
     * Creates a 100x100 black ImageMap
     */
    public ImageMap() {
    	width = 100;
    	height = 100;
    	imageType = BufferedImage.TYPE_INT_RGB;
		colors = new Color[width][height];
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
				colors[i][j] = new Color(0);
			}
		}
   	}
    /**
     * Creates an ImageMap out of the provided file
     * @param filepath The image to read for this ImageMap
     */
    public ImageMap(String filepath) {
    		read(filepath);
    }
    /**
     * Creates an ImageMap given a 2D color array
     */
    public ImageMap(Color[][] c) {
    		setColors(c);
    }
    
    /**
     * Returns the 2D array of colors in this ImageMap
     * @return
     */
	public Color[][] getColors()	{
		return colors;
	}
	
	private void setColors(Color[][] c)	{
		colors = c;
		width = c.length;
		height = c[0].length;
		imageType = BufferedImage.TYPE_INT_RGB;
	}
	/**
	 * 
	 * @return The height of this ImageMap
	 */
	public int getHeight()	{
		return height;
	}
	/**
	 * 
	 * @return The width of this ImageMap
	 */
	public int getWidth()	{
		return width;
	}
	
	private void read(String filepath) {
		try{
			BufferedImage I = ImageIO.read(new File(filepath));
			imageType = I.getType();
			height = I.getHeight();
			width = I.getWidth();
			colors = new Color[width][height];  
			
			for(int h=0; h<height; h++) {
		        for(int w=0; w<width; w++) {
	                Color c = new Color(I.getRGB(w, h));
	                colors[w][h]= c;
		        }
			}
		}
		catch(IOException e) {
	        System.out.println("Error: unable to read image: " + filepath);
	        e.printStackTrace();
		}
	}
	/**
	 * Writes the colors in this ImageMap to the file specified.
	 * @param filepath The file to write to
	 */
	public void write(String filepath) {
		int dot_location = filepath.indexOf(".")+1;
		String format = filepath.substring(dot_location);
		BufferedImage I = new BufferedImage(width,height,imageType);
		Color c;
		for(int h=0; h<height; h++) {
			for(int w=0; w<width; w++) {
				c = colors[w][h];
				I.setRGB(w,h,c.getRGB());
			}
		}
		try {
			ImageIO.write(I, format, new File(filepath));
		}
		catch(IOException e) {
			System.out.println("Error: Unable to write " + format + " image file: " + filepath);
			e.printStackTrace();
		}
		catch(IllegalArgumentException e) {
			System.out.println("Error: Unable to write " + format + " file type");
			e.printStackTrace();
		}
	}
	
	/***
	 * Shows the ImageMap in a GUI frame.
	 */
	public void show()	{
		int[] pixels = new int[width*height];
		for (int x=0; x < width; x++) {
			for (int y=0; y < height; y++) {
				Color c = colors[x][y];
				pixels[x+y*this.width] = c.getRGB();
			}
		}
		final MemoryImageSource img = new MemoryImageSource(this.width, this.height, pixels, 0, width);;	
		JFrame frame = new JFrame("ImageMap");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final ImageMap that = this;
		
		JPanel panel = new JPanel()	{
			Image i = super.createImage(img);
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)(g);
				g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
				g2d.drawImage(i, 0, 0, this);
			}
		};
		panel.setPreferredSize(new Dimension(this.width, this.height));
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}

