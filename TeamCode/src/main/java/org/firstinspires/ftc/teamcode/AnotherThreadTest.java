package org.firstinspires.ftc.teamcode;

public class AnotherThreadTest implements Runnable{
    public void run(){
        System.out.println("HELOEL>/?!??!>,!");
    }
    public AnotherThreadTest() {
        Thread t1 = new Thread (new AnotherThreadTest());
        t1.start();
    }
}
