package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Gyro Demo", group="Demos")

public class GyroDemo extends LinearOpMode {
    private GyroBot robot = new GyroBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.resetAngle();
        waitForStart();
        while (opModeIsActive()) {
            robot.goBacktoStartAnglePID(0);
        }
    }
}