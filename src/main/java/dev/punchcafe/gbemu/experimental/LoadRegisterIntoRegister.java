package dev.punchcafe.gbemu.experimental;

import dev.punchcafe.gbemu.register.Registers;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class LoadRegisterIntoRegister {

    public enum RegisterOperations {
        A(Registers::setA, Registers::getA), B(Registers::setA, Registers::getA), C(Registers::setA, Registers::getA);
        private BiConsumer<Registers,Byte> acceptByte;
        private Function<Registers, Byte> getByte;

        RegisterOperations(/*BiConsumer<Registers, Byte> acceptByte,*/ Function<Registers, Byte> getByte){
            this.acceptByte = acceptByte;
            this.getByte = getByte;
        }
    }
}
