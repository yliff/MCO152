package com.company;

public class MyTimer {

    long startTime = 0;
    long elapsedTime = 0;
    //attempt 2a

    public void start() {
        if (startTime != 0) {
            throw new IllegalStateException("Timer is already active; must reset");
        }
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        if (elapsedTime == 0 && startTime != 0) {
            elapsedTime = System.currentTimeMillis();
        }
    }

    public void reset() {
        if (elapsedTime == 0) {
            startTime = System.currentTimeMillis();
        } else {
            startTime = 0;
            elapsedTime = 0;
        }
    }

    public long getAccumulatedTimer() {
        return elapsedTime == 0 ? System.currentTimeMillis() - startTime : elapsedTime - startTime;
    }

}
