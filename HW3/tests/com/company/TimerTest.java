package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// imitation clock...pretends to behave like a clock
class FakeClock implements ElapsedTimeClockInterface {

    FakeClock(long ... vals){
        returnValues = vals;
    }
    private int counter = 0;
    private long returnValues[];

    @Override
    public long currentTimeMillis() {
        return returnValues[counter++];
    }
}

public class TimerTest {

    @Test
    void getElapsedTimeWhenStartStopShouldBe1003() {
        ElapsedTimeClockInterface clock = new FakeClock(0, 52);
        Timer t = new Timer( clock );

        t.start();
        t.stop();

        assertEquals(52, t.getElapsedTime());
    }

}
