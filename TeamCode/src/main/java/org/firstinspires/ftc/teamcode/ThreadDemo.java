package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.ThreadPool;

import java.util.concurrent.ExecutorService;

@TeleOp(name="Thread Demo", group="Demos")

public class ThreadDemo extends LinearOpMode {
    private ThreadDemo robot = new ThreadDemo(this);
    private ExecutorService updateExecutor;
    private AnotherThreadTest whatever = new AnotherThreadTest();
    DcMotor verticalLeft, verticalRight, horizontal;
    BNO055IMU imu;
    String rfName = "rf", rbName = "rb", lfName = "lf", lbName = "lb";
    String verticalLeftEncoder = rbName, verticalRightEncoder = lfName, horizontalEncoder = rfName;
    final double PIVOT_SPEED = 0.5;
    final double COUNTS_PER_INCH = 307.699557;
    ElapsedTime timer = new ElapsedTime();
    double horizontalTickOffset = 0;
@Override
        public void runOpMode() {
        robot.init(hardwareMap);
        updateExecutor = ThreadPool.newSingleThreadExecutor("update");
        waitForStart();
        updateExecutor.submit(whatever);
        while (opModeIsActive()) {
            double changeRight = robot.verticalRight.getCurrentPosition() - lastRightCount;
            double changeLeft = -robot.verticalLeft.getCurrentPosition() - lastLeftCount;
            double changeBack = robot.horizontal.getCurrentPosition() - lastBackCount;

        }
    }
}