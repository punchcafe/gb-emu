package dev.punchcafe.gbemu.experimental;

public class Gameboy {

    private Clock clock;
    private long clockGapTime;

    public void run() {
        try {
            while (true) {
                clock.tick();
                Thread.sleep(clockGapTime);
            }
        } catch (InterruptedException ex) {

        }
    }
}
