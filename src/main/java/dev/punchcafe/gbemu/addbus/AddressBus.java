package dev.punchcafe.gbemu.addbus;

import java.util.ArrayList;
import java.util.List;

public class AddressBus {

    private OffsetAddressable[] offsetAddressableByAddress;

    public AddressBus(final List<Addressable> addressables){
        final int totalAdressSpaceSize = getTotalAdressSpaceSize(addressables);
        final List<OffsetAddressable> offsetAddressables = buildOffsetAddressablesFromAddressables(addressables);
        this.offsetAddressableByAddress = buildRandomAccessAddressableLookup(offsetAddressables, totalAdressSpaceSize);
    }

    public byte read(int address){
        return this.offsetAddressableByAddress[address].read(address);
    }

    public void write(int address, byte value){
        this.offsetAddressableByAddress[address].write(address, value);
    }

    private int getTotalAdressSpaceSize(final List<Addressable> addressables){
        return addressables.stream()
                .map(Addressable::addressSpaceSize)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private List<OffsetAddressable> buildOffsetAddressablesFromAddressables(final List<Addressable> addressables){
        int offset = 0;
        final List<OffsetAddressable> offsetAddressables = new ArrayList<>();
        for(Addressable addressable : addressables){
            offsetAddressables.add(new OffsetAddressable(offset, addressable));
            offset += addressable.addressSpaceSize();
        }
        return offsetAddressables;
    }

    private OffsetAddressable[] buildRandomAccessAddressableLookup(final List<OffsetAddressable> offsetAddressables,
                                                                   final int totalAdressSpaceSize){
        final OffsetAddressable[] lookupArray = new OffsetAddressable[totalAdressSpaceSize];
        for(OffsetAddressable offsetAddressable : offsetAddressables){
            for(int i = offsetAddressable.getOffset();
                i < offsetAddressable.getAddressable().addressSpaceSize() + offsetAddressable.getOffset();
                i++){
                lookupArray[i] = offsetAddressable;
            }
        }
        return lookupArray;
    }
}
