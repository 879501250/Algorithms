package com.qq.Structural.Facade.demo2;

import com.qq.Structural.Facade.common.CPU;
import com.qq.Structural.Facade.common.Memory;
import com.qq.Structural.Facade.common.OS;

public class Computer {
    private CPU cpu;
    private Memory memory;
    private OS os;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        os = new OS();
    }

    public void start() {
        System.out.println("Computer start begin");
        cpu.start();
        os.start();
        memory.start();
        System.out.println("Computer start end");
    }

    public void shutDown() {
        System.out.println("Computer shutDown begin");
        cpu.shutDown();
        os.shutDown();
        memory.shutDown();
        System.out.println("Computer shutDown end...");
    }
}
