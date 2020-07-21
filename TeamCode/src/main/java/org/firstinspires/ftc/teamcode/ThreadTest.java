package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ThreadPool;

import java.util.concurrent.ExecutorService;

public class ThreadTest{
    public Robot(OpMode opMode) {
        ExecutorService updateExecutor;
        updateExecutor = ThreadPool.newSingleThreadExecutor("update");
    }
}

public void start() {
    updateExecutor.submit(AnotherThreadTest);
}