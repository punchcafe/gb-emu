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
}
