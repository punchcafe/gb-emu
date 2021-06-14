package dev.punchcafe.gbemu.experimental;

import java.util.List;

public class OpcodeCache {

    private final Operation[] operations;

    private OpcodeCache(final Operation[] operations){
        this.operations = operations;
    }

    public Operation getOperation(final int opcode){
        return operations[opcode];
    }

    public static OpcodeCache buildOpcodeCache(final List<Operation> allOperations) {
        final var maxOpcode = allOperations.stream()
                .mapToInt(Operation::opcode)
                .max()
                .orElseThrow();
        final var operations = new Operation[maxOpcode];
        for(final var operation : allOperations){
            operations[operation.opcode()] = operation;
        }
        return new OpcodeCache(operations);
    }
}
