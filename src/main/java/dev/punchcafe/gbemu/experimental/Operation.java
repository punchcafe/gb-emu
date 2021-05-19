package dev.punchcafe.gbemu.experimental;

public interface Operation {

    int clockCycles();
    void doOperation(final byte args, final Object allHardware);
}
