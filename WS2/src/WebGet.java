import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
/**
 * A utility class to get data from the web.  
 * Currently only supports an http get request without parameters.
 *
 * <h6>This code is released for educational purposes only and does not come with any 
 * warranty for the merchantability or fitness for any particular purpose.</h6>
 * 
 * @author Geoffery Miller geoffery.miller@gmail.com
 * @version 0.2
*/
public class WebGet	{
	/**
	 * Creates a connection to a URL provided as a String and downloads text data.  The data 
	 * is returned as a String.
	 * When this method cannot make a connection to the specified url, or there is an error in 
	 * receiving data, it will return an empty String.
	 * @param location The URL to download, this must include http:// prepended to the url.
	 * @return The data from the web resource returned as a String.
	*/
	public static String httpget(String location)	{
		String webpage = "";
		try {
			URL url = new URL(location);
			URLConnection urlConnection = url.openConnection();
			InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader buff = new BufferedReader(isr);
			String line;
			while((line = buff.readLine()) != null)	{
				webpage = webpage + line + "\n";
			}
		}
		catch(Exception e)	{
			webpage = "";
		}
		return webpage;
	}
	private WebGet() {}
}
