package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ThreadPool;

import java.util.concurrent.ExecutorService;

@Autonomous(name="Gyro Demo", group="Demos")

public class ThreadDemo extends LinearOpMode {
    private GyroBot robot = new GyroBot(this);
    private ExecutorService updateExecutor;
    private AnotherThreadTest whatever = new AnotherThreadTest();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        updateExecutor = ThreadPool.newSingleThreadExecutor("update");
        waitForStart();
        updateExecutor.submit(whatever);
        while (opModeIsActive()) {

        }
    }
}