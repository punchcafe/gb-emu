package dev.punchcafe.gbemu.addbus;

import dev.punchcafe.gbemu.memory.WorkRam;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddressBusTest {

    @Test
    void shouldReadAndWriteToSeparateBlocks(){
        final var ramBlockOne = new WorkRam(10);
        final var ramBlockTwo = new WorkRam(10);
        final List<Addressable> addressables = List.of(ramBlockOne, ramBlockTwo);
        final var addressBus = new AddressBus(addressables);
        assertNotEquals(ramBlockOne.read(0), 0xaa);
        assertNotEquals(ramBlockTwo.read(6), 0xa1);
        addressBus.write(0, (byte) 0xaa);
        addressBus.write(16, (byte) 0xa1);
        assertEquals(ramBlockOne.read(0), (byte) 0xaa);
        assertEquals(ramBlockTwo.read(6), (byte) 0xa1);
    }
}
