package bibleWords;

public class NanoTimer {

    long startTime, elapsedTime;

    public NanoTimer() {
        startTime = 0;
        elapsedTime = 0;
    }

    public void start(){
        startTime = System.nanoTime();
        elapsedTime = 0;
    }

    public void stop() {
        elapsedTime = System.nanoTime() - startTime;
        String str = String.format("%,d", elapsedTime);
        System.out.println(str + "ns");
    }

}
