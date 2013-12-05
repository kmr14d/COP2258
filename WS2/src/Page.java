import java.util.ArrayList;
import java.util.Arrays;
/** This class is set up with an HTML page and provides methods which 
 * allow you to scrape words and links from the page.
 *
 * <h6>This code is released for educational purposes only and does not come with any
 * warranty for the merchantability or fitness for any particular purpose.</h6>
 *
 * @author Geoffery Miller geoffery.miller@gmail.com
 * @version 0.3
*/
public class Page	{

	private String html;
	private ArrayList<String> links;
	private ArrayList<String> words;

	/** Creates a page object and automatically calls the setHTML method to 
	 * process the links on the page for the provided html String.
	 * @param html The HTML page as a String
	*/
	public Page(String html)	{
		setHTML(html);
	}
	
	/** A setter for providing a String html page.  If you do not set any HTML page, the 
	 * getter methods will return blank strings
	 * @param html The HTML page as a String.
	*/
	private void setHTML(String html)	{
		this.html = html;
		processLinks();
		processWords();
	}
	
	/** Provides the number of links found on the page. */
	public int numLinks()	{
		return links.size();
	}
	
	/** Gets a link at a specified index.  Will throw exceptions if you do not specify a valid index 
	 * @param index The index to get from the list of links on the page.
	 * @return The link as a String
	*/
	public String getLink(int index)	{
		return links.get(index);
	}

	/** A simple way to get all of the links on the page as a String array.
	 * @return A string array of all of the links found on the page */
	public String[] getLinks()	{
		return links.toArray(new String[links.size()]);
	}

	/** Returns all of the words on the page as a String array.  
	 * These words are not checked by a dictionary, in the case the word 
	 * is a proper noun or acronym.  This function will attempt to remove HTML code.
	 * @return A String array of all of the words on the page.
	*/
	public String[] getWords()	{
		return words.toArray(new String[words.size()]);
	}
	
	private void processWords() {
		String data = this.html;
		// Attempt to remove HTML tags
		String noHTML = data.replaceAll("\\<.*?\\>", "");
		// Get all of the distinct phrases left, make them all lowercase
		String[] tempWords = noHTML.split(" ");
		for (String t : tempWords) {
			t = t.toLowerCase();
		}
	
		words = new ArrayList<String>(Arrays.asList(tempWords));
	
	}
	
	private void processLinks()	{
		String data = this.html;
		String link = "a href";
		ArrayList<String> links = new ArrayList<String>();
		int find;
		int lastFind = 0;
		try {
			while((find = data.indexOf(link,lastFind)) != -1)	{
				int startFind = data.indexOf("\"", find);
				int endFind = data.indexOf("\"",startFind+1);
				String theLink = data.substring(startFind+1,endFind);
				links.add(theLink);
				lastFind = endFind;
			}
		}
		catch (Exception e)	{

		}
		this.links = links;
	}

}
