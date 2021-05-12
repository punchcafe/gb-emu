package dev.punchcafe.gbemu.addbus;

public interface Addressable {
    int addressSpaceSize();

    byte read(int address);

    void write(int address, byte value);
}
