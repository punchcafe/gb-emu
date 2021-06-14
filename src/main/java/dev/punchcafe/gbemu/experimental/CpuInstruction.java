package dev.punchcafe.gbemu.experimental;

import lombok.Getter;

@Getter
public class CpuInstruction {
    private byte opCode;
    private byte arg1;
    private byte arg2;

    /**
     * Parses CISC, (instruction of variable length)
     * @param bytes
     * @return
     */
    public static CpuInstruction parse(final byte[] bytes){
        final var instruction = new CpuInstruction();
        instruction.opCode = bytes[0];
        instruction.arg1 = bytes[1];
        instruction.arg2 = bytes[1];
        return instruction;
    }
}
