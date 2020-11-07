package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="Auto Test", group="Auto")

public class AutoTest extends LinearOpMode {

    protected NewCameraBot robot = new NewCameraBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        int numberOfRings = robot.detectRings();
        telemetry.addData("Number of rings:", numberOfRings);
        telemetry.update();
        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 200, 0.3);
        if (numberOfRings == 0) {
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 1000, 1);
        } else if (numberOfRings == 1) {
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 1400, 1);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 400, 1);
        } else if (numberOfRings == 4) {
            robot.driveStraightByDistance(robot.DIRECTION_FORWARD, 1800, 1);
            robot.driveStraightByDistance(robot.DIRECTION_BACKWARD, 800, 1);
        }

    }

}
