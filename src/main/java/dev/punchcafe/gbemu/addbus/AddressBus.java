package dev.punchcafe.gbemu.addbus;

import java.util.ArrayList;
import java.util.List;

public class AddressBus {

    // an array of entire array of all memory map addresses to their relative Addressable handler
    private OffsetAddressable[] offsetAddressables;

    /**
     *
     * @param addressables an ordered list of all all the adressable blocks
     */
    public AddressBus(final List<Addressable> addressables){
        // TODO: extract into methods
        final int totalSize = addressables.stream()
                .map(Addressable::addressSpaceSize)
                .reduce(Integer::sum)
                .orElse(0);
        int offset = 0;
        final List<OffsetAddressable> offsetAddressables = new ArrayList<>();
        for(Addressable addressable : addressables){
            offsetAddressables.add(new OffsetAddressable(offset, addressable));
            offset += addressable.addressSpaceSize();
        }

        this.offsetAddressables = new OffsetAddressable[totalSize];
        for(OffsetAddressable offsetAddressable : offsetAddressables){
            for(int i = offsetAddressable.getOffset();
                i < offsetAddressable.getAddressable().addressSpaceSize() + offsetAddressable.getOffset();
                i++){
                this.offsetAddressables[i] = offsetAddressable;
            }
        }
    }

    public byte read(int address){
        final var offsetAddressable = this.offsetAddressables[address];
        return offsetAddressable.getAddressable().read(address - offsetAddressable.getOffset());
    }

    public void write(int address, byte value){
        final var offsetAddressable = this.offsetAddressables[address];
        offsetAddressable.getAddressable().write(address - offsetAddressable.getOffset(), value);
    }
}
