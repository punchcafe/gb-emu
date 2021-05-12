package dev.punchcafe.gbemu.memory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryBankControllerTest {

    @Test
    void canSwitchBetweenMemoryBanks(){
        final var address = 5;
        final var dataInBankOne = (byte) 0xa;
        final var dataInBankTwo = (byte) 0xb;
        final var dataInBankThree = (byte) 0xc;
        final var bankController = new MemoryBankController(10, 3);

        bankController.write(address, dataInBankOne);
        bankController.switchBank(2);
        bankController.write(address, dataInBankTwo);
        bankController.switchBank(3);
        bankController.write(address, dataInBankThree);

        bankController.switchBank(1);
        assertEquals(bankController.read(address), dataInBankOne);
        bankController.switchBank(2);
        assertEquals(bankController.read(address), dataInBankTwo);
        bankController.switchBank(3);
        assertEquals(bankController.read(address), dataInBankThree);
    }
}
