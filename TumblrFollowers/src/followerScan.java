

public class followerScan {
	public static void main(String[] args) {
		// Making an array of 10 integers
		int[] x = new int[10];
		
		// This is a storage container of strings (5)
				String[] c = new String[5];
				
				String html = WebGet.httpget("http://pic.fsu.edu/");
				Page p = new Page(html);
				
				String[] links = p.getLinks();
				System.out.println(links);
				for(int i = 0; i < links.length; i++) {
					System.out.println(links[i]);
	}
}
