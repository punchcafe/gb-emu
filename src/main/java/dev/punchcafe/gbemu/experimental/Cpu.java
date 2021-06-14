package dev.punchcafe.gbemu.experimental;

import dev.punchcafe.gbemu.addbus.AddressBus;
import dev.punchcafe.gbemu.register.Registers;

public class Cpu implements ClockListeners {

    private final ExecutionContext executionContext;
    private Registers registers;
    private final AddressBus addressBus;
    private final OpcodeCache opcodeCache;

    @Override
    public void onNextTick(Clock clock) {
        if (!this.executionContext.hasCompletedCycleForOperation()) {
            this.executionContext.tickCurrentCyclesForOperation();
            return;
        }
        /**
         * Gamboy has fetch/execute overlap, so the last stage of execution fetches the next instruction
         */
        executionContext.executeInstruction();
        registers.incrementProgramCounter(executionContext.getOperation().programCounterShift());
        final var instruction = this.loadInstruction(registers.getProgramCounter());
        final var operation = opcodeCache.getOperation(instruction.getOpCode());
        executionContext.loadNextInstruction(operation, instruction);
    }

    private CpuInstruction loadInstruction(final int address) {
        final byte[] instructionBytes = new byte[]{
                addressBus.read(address),
                addressBus.read(address + 1),
                addressBus.read(address + 1)
        };
        return CpuInstruction.parse(instructionBytes);
    }

    private boolean awaitingOperationCycles() {
        return currentOperationTime >= this.executionContext.getOperation().clockCycles();
    }

}
