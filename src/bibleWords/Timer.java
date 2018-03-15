package bibleWords;

import java.util.concurrent.TimeUnit;

public class Timer {
	
	private long startTime, elapsedTime;
	
	public Timer() {
		startTime = 0;
		elapsedTime = 0;
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
		elapsedTime = 0;
	}
	
	public void stop() {
		elapsedTime = System.currentTimeMillis() - startTime;
	}
	
	public String getTime() {
		 if(elapsedTime > 10000) {
			 long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
			 elapsedTime -= TimeUnit.MINUTES.toMillis(minutes);
		     long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
		     
		     return minutes + ":" + seconds;
		 } else {
			 return elapsedTime + "ms";
		 }
	}

}
