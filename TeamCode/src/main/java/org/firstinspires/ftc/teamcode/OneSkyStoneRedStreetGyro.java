package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="ONLYRUNTHISONEok", group="Exercises")

public class OneSkyStoneRedStreetGyro extends LinearOpMode {
    protected PIDBot robot = new PIDBot(this);

    int direction_forward, direction_backward;

    protected void setDirection(){
        direction_forward = robot.DIRECTION_BACKWARD;
        direction_backward = robot.DIRECTION_FORWARD;
    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        setDirection();

        robot.resetAngle();
//        robot.driveStraightbyDistancePID( robot.DIRECTION_LEFT, 400, 0.7);
        robot.driveUntilDistance(60, 0.3);

        while (opModeIsActive())

            robot.checkDirection();     //make sure that it continuously checks for double "correction" for moving straight

        robot.opMode.sleep(3000);
        if (robot.isSkystoneDetected()) {
            robot.driveStraightbyDistancePID(robot.DIRECTION_FORWARD, 150, 0.6);
//          robot.driveStraightbyDistancePID(robot.DIRECTION_LEFT, 270, 0.5);
            robot.driveUntilDistance(21, 0.3);

            robot.pickupSkyStone();
//            robot.driveStraightbyDistancePID(robot.DIRECTION_RIGHT, 100, 0.5);
            robot.driveUntilDistance(35, 0.3);

            robot.driveStraightbyDistancePID(direction_backward, 1600, 0.8);
            robot.driveStraightbyDistancePID(robot.DIRECTION_LEFT, 250, 0.5);
            robot.dropSkyStone();
            robot.originalPosition();
            robot.driveStraightbyDistancePID(robot.DIRECTION_RIGHT, 150, 0.5);
            robot.driveStraightbyDistancePID(direction_forward, 850, 1);
        } else {
            robot.driveStraightbyDistancePID(direction_forward, 200, 0.6);
            robot.opMode.sleep(3000);
            if (robot.isSkystoneDetected()) {
                robot.driveStraightbyDistancePID(robot.DIRECTION_FORWARD, 150, 0.6);
//                robot.driveStraightbyDistancePID(robot.DIRECTION_LEFT, 270, 0.5);
                robot.driveUntilDistance(21, 0.3);

                robot.pickupSkyStone();
//                robot.driveStraightbyDistancePID(robot.DIRECTION_RIGHT, 100, 0.5);
                robot.driveUntilDistance(35, 0.3);

                robot.driveStraightbyDistancePID(direction_backward, 1800, 0.8);
                robot.driveStraightbyDistancePID(robot.DIRECTION_LEFT, 250, 0.5);
                robot.dropSkyStone();
                robot.originalPosition();
                robot.driveStraightbyDistancePID(robot.DIRECTION_RIGHT, 150, 0.5);
                robot.driveStraightbyDistancePID(direction_forward, 850, 1);
            } else {

                robot.driveStraightbyDistancePID(direction_forward, 200, 0.6);
                robot.driveStraightbyDistancePID(robot.DIRECTION_FORWARD, 150, 0.6);
//                robot.driveStraightbyDistancePID(robot.DIRECTION_LEFT, 270, 0.5);
                robot.driveUntilDistance(21, 0.3);

                robot.pickupSkyStone();
//                robot.driveStraightbyDistancePID(robot.DIRECTION_RIGHT, 100, 0.5);
                robot.driveUntilDistance(35, 0.3);

                robot.driveStraightbyDistancePID(direction_backward, 2000, 0.8);
                robot.driveStraightbyDistancePID(robot.DIRECTION_LEFT, 250, 0.5);
                robot.dropSkyStone();
                robot.originalPosition();
                robot.driveStraightbyDistancePID(robot.DIRECTION_RIGHT, 150, 0.5);
                robot.driveStraightbyDistancePID(direction_forward, 850, 1);
            }
        }


    }
}
