package com.company;


interface ElapsedTimeClockInterface {
    long currentTimeMillis();
}
class ElapsedTimeClock implements ElapsedTimeClockInterface {
    public long currentTimeMillis(){
        return System.currentTimeMillis();
    }
}
class ElapsedTimeClock2 implements ElapsedTimeClockInterface {
    public long currentTimeMillis(){
        return System.nanoTime() / 1_000_000;
    }
}
class Timer {

    public Timer(ElapsedTimeClockInterface etc){
        clock = etc;
    }
    private long start;
    private long stop;
    private boolean isStopped;
    private ElapsedTimeClockInterface clock;

    public void start() {

        isStopped = true;
        start = clock.currentTimeMillis();
    }

    public void stop() {
        isStopped = true;
        stop = clock.currentTimeMillis();
    }

    public void reset() {
        start = clock.currentTimeMillis();
    }

    public long getElapsedTime() {
        return (isStopped ? stop : clock.currentTimeMillis()) - start;
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Good job!");
    }

}

