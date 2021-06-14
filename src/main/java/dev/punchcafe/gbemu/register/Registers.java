package dev.punchcafe.gbemu.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Registers {
    private short programCounter;
    private short stackPointer;
    private byte accumulator;

    public byte getA(){return 0x00;}
    public byte getB(){return 0x00;}
    public byte getC(){return 0x00;}

    public void setA(){}
    public void setB(){}
    public void setC(){}

    public short incrementProgramCounter(int incrementBy){
        return this.programCounter += incrementBy;
    }
}
