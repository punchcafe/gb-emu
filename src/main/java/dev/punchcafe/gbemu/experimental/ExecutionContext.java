package dev.punchcafe.gbemu.experimental;

import lombok.Getter;

@Getter
public class ExecutionContext {
    private Operation operation;
    private CpuInstruction cpuInstruction;
    private int currentCyclesOnOperation;

    public void executeInstruction(){
        operation.doOperation(null, cpuInstruction.getArg1(), cpuInstruction.getArg2());
    }

    public boolean hasCompletedCycleForOperation(){
        return this.currentCyclesOnOperation >= this.operation.clockCycles();
    }

    public void tickCurrentCyclesForOperation(){
        this.currentCyclesOnOperation++;
    }

    public void loadNextInstruction(final Operation operation, final CpuInstruction cpuInstruction){
        //TODO: should hide opcache in here?
        this.operation = operation;
        this.cpuInstruction = cpuInstruction;
        this.currentCyclesOnOperation = 0;
    }
}
