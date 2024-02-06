package com.qq.Structural.Facade.demo1;

import com.qq.Structural.Facade.common.CPU;
import com.qq.Structural.Facade.common.Memory;
import com.qq.Structural.Facade.common.OS;

public class Test {
    public static void main(String[] args) {
        CPU cpu = new CPU();
        cpu.start();
        Memory memory = new Memory();
        memory.start();
        OS os = new OS();
        os.start();
    }
}
