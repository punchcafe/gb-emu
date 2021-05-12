package dev.punchcafe.gbemu.memory;

import dev.punchcafe.gbemu.addbus.Addressable;

public class WorkRam implements Addressable {

    private final byte[] memory;

    public WorkRam(final int size){
        this.memory = new byte[size];
    }

    @Override
    public int addressSpaceSize() {
        return memory.length;
    }

    @Override
    public byte read(int address) {
        return memory[address];
    }

    @Override
    public void write(int address, byte value) {
        this.memory[address] = value;
    }
}
