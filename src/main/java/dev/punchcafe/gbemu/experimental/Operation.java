package dev.punchcafe.gbemu.experimental;

public interface Operation {

    int clockCycles();
    void doOperation(final Object allHardware, final byte... args);
    /*
        How much to shift the program counter by. Operations which affect the PC will typically shift 0, otherwise this
        will be a matter of however big the operation length is
     */
    int programCounterShift();
    short opcode();
}
