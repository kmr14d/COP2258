	
public class Timer {

	private long startTime,
	endTime,
	elapsedTimeMS,
	elapsedTimeSec;
	
	double milliSec = 1000.0;
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	public void stop(){
		endTime = System.currentTimeMillis();
		
	}
	public long getTimeMS(){
		elapsedTimeMS = (endTime - startTime);
		return elapsedTimeMS;
		
	}
	public double getTimeSec(){
		elapsedTimeSec = (long) (elapsedTimeMS / milliSec);
		return elapsedTimeSec;
	}
	public void printReport(){
		if (getTimeMS() >= milliSec){
		System.out.println(getTimeSec() + " seconds");
		}
		
		else {
			System.out.println(getTimeMS() + " milliseconds");
		}
		
	}
	
}
