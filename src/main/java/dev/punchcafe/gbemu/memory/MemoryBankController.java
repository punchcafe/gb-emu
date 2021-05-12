package dev.punchcafe.gbemu.memory;

import dev.punchcafe.gbemu.addbus.Addressable;

public class MemoryBankController implements Addressable {

    private int currentBankIndex = 0;
    private final byte[][] banks;

    public MemoryBankController(final int bankSize, final int numberOfBanks){
        this.banks = new byte[numberOfBanks][bankSize];
    }

    public void switchBank(int bankNumber){
        this.currentBankIndex = bankNumber - 1;
    }

    public int currentBank(){
        return currentBankIndex + 1;
    }

    @Override
    public int addressSpaceSize() {
        return banks[currentBankIndex].length;
    }

    @Override
    public byte read(int address) {
        return banks[currentBankIndex][address];
    }

    @Override
    public void write(int address, byte value) {
        banks[currentBankIndex][address] = value;
    }
}
