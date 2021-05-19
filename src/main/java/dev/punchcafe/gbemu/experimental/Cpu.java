package dev.punchcafe.gbemu.experimental;

import dev.punchcafe.gbemu.register.Registers;

public class Cpu implements ClockListeners {

    private Operation currentOperation;
    private int currentOperationTime;
    private Registers registers;

    @Override
    public void onNextTick(Clock clock) {
        currentOperationTime++;
        if(currentOperation.clockCycles() <= currentOperationTime){
            setNextOperation();
            return;
        }
    }

    private void setNextOperation(){
        // get next in pc, parse bytes from ram to find opcode
        this.currentOperationTime = 0;
    }

}
