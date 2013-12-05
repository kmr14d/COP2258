
public class TimerTest{
	public static void main(String[] args){
 
		Timer t1 = new Timer();	//Construct the Timer object
		
		t1.start();				// Some code to time.
		
		for (int i = 0; i< 100; i = i+1) {
			String html = WebGet.httpget("http://www.google.com");
		}

		t1.stop();

		t1.printReport();		//Print timing report
	}
}
