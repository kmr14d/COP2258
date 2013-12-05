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
 * @version 0.3
 */
public class ImageMap {

	private Color[][] colors;
       
	private int imageType;
    private int height;
    private int width;
    
    private int[] pixels;
    
    private MemoryImageSource img;
    /***
     * Creates a black ImageMap of size width by height.  
     * @param width
     * @param height
     */
    public ImageMap(int width, int height) {
   
    	this.width = width;
    	this.height = height;
    	this.pixels = new int[width*height];
    	img = new MemoryImageSource(this.width, this.height, pixels, 0, width);
	this.img.setFullBufferUpdates(true);
    	
    	imageType = BufferedImage.TYPE_INT_RGB;
		colors = new Color[width][height];
		this.fill(new Color(0));
		this.update();
   	}
    /***
     * Creates a 200 by 200 black ImageMap
     */
    public ImageMap()	{
    		this(200,200);
    }
    /***
     * Creates an ImageMap from a file
     * @param filepath
     */
    public ImageMap(String filepath) {
    		read(filepath);
    }
    
    /***
     * 
     * @return The 2d array of Color objects in this ImageMap
     */
	public Color[][] getColors()	{
		return colors;
	}
	public Color getColor(int x, int y) {
		return colors[x][y];
	}
	public void fill(Color c) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.setColor(i,j,c);
			}
		}
	}
	/***
	 * Sets a color at position x,y on the ImageMap
	 * @param x
	 * @param y
	 * @param c
	 */
	public void setColor(int x, int y, Color c) {
		colors[x][y] = c;
		pixels[x+y*this.width] = c.getRGB();
	}
	private void setColors(Color[][] c)	{
		colors = c;
		width = c.length;
		height = c[0].length;
		imageType = BufferedImage.TYPE_INT_RGB;
	}
	/***
	 * 
	 * @return The height of the ImageMap
	 */
	public int getHeight()	{
		return height;
	}
	/***
	 * 
	 * @return The width of the ImageMap
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

	/***
	 * Writes the ImageMap to an image file.
	 * @param filepath The name of the file to write
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
	public void update() {
		img.newPixels();
	}
	/***
	 * Shows the ImageMap in a GUI frame.
	 */
	public void show()	{
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
		update();
	}
	
	
}
