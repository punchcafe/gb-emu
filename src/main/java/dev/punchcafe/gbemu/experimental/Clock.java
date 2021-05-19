package dev.punchcafe.gbemu.experimental;

import java.util.List;

/**
 * Use this class to drive everything
 */
public class Clock {
    private List<ClockListeners> listeners;
    private long time;
    public void tick(){
        time++;
        for(var listener : listeners){
            listener.onNextTick(this);
        }
    }
}
