package dev.punchcafe.gbemu.addbus;

class OffsetAddressable {
    private final int offset;
    private final Addressable addressable;

    OffsetAddressable(final int offset, final Addressable addressable){
        this.offset = offset;
        this.addressable = addressable;
    }

    public int getOffset(){
        return this.offset;
    }

    public Addressable getAddressable(){
        return this.addressable;
    }

    /**
     * Write to the {@link Addressable}, adjusting from memory-map address space to relative address space.
     *
     * @param address the Gameboy memory-map address
     * @param value the value at that address
     */
    public void write(final int address, final byte value){
        addressable.write(adjustAddressToOffset(address), value);
    }

    /**
     * Read from the {@link Addressable}, adjusting from memory-map address space to relative address space.
     *
     * @param address the Gameboy memory-map address
     * @return the value at the address
     */
    public byte read(final int address){
        return addressable.read(adjustAddressToOffset(address));
    }

    private int adjustAddressToOffset(final int address){
        return address - offset;
    }
}
