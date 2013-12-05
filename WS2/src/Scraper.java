import javax.swing.JOptionPane;

/** 
 * The Scraper class implements a main method to make use of the WebGet and Page classes to 
 * scrape links from a webpage and print them out.
*/
public class Scraper {

	public static void main(String args[])	{
	    // Store "http://pic.fsu.edu/" in a String called location
	    String location = "api.tumblr.com/v2/blog/letsraisehel/followers";
	    
	    // Call the httpget method from WebGet, providing location as a parameter
	    // and store the return String into a String called html
	    String html = WebGet.httpget(location);

	    System.out.println(html);
	    // Printing the links
	    Page p = new Page(html);
	    printLinks(p);
	}
	
	public static void printLinks(Page p)	{
	    if(p.numLinks() == 0) {
	        System.out.println("I can't find any links on the page!");
	    }
	    else {
	        for(int x = 0; x < p.numLinks(); x = x + 1) {
	            System.out.println(x + ": " + p.getLink(x));
	        }
	    }
	}
}
