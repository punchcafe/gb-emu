package dev.punchcafe.gbemu.memory;

import dev.punchcafe.gbemu.addbus.Addressable;
import jdk.jfr.Experimental;

import java.util.List;

/**
 * Potential solution for flags in memory mapping which have impacts on other components
 */
@Experimental
public class MagicMemory implements Addressable {

    private interface Listener{
        void notifyChange(final MagicMemory magicMemory);
    }

    private List<Listener> listeners;

    @Override
    public int addressSpaceSize() {
        return 0;
    }

    @Override
    public byte read(int address) {
        return 0;
    }

    @Override
    public void write(int address, byte value) {
        // do write
        for(final var listener : this.listeners){
            listener.notifyChange(this);
        }
    }
}
