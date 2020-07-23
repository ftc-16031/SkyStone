package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Safe Blue Program", group="Auto")

public class BlueStreetOld extends LinearOpMode {

    protected GyroBot robot = new GyroBot(this);

    int direction_forward, direction_backward;

    int[] distFirstMove = new int[]{300, 100, -100};
    int[] distFoundMove = new int[]{1900, 1700, 1500};
    int[] distBackMove = new int[]{1600, 2450, 2200};

    int skystonePostition;
    protected void setDirection(){
        direction_backward = robot.DIRECTION_FORWARD;
        direction_forward = robot.DIRECTION_BACKWARD;
    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        setDirection();

        skystonePostition = robot.detectSkystone();

        robot.driveUntilDistance(19, 0.3, 0);

        robot.driveStraightByGyro(robot.DIRECTION_FORWARD, distFirstMove[skystonePostition - 1], 0.5, false);

        robot.pickupSkyStone();

        sleep(500);

        robot.driveStraightByGyro(direction_forward, distFoundMove[skystonePostition - 1], 1, false);

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 200, 0.5);

        robot.dropSkyStone();

        robot.originalPosition();

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 200, 0.5);

        robot.driveStraightByGyro(direction_backward, distBackMove[skystonePostition - 1], 0.8, false);

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 200, 0.5);

        robot.driveUntilDistance(19, 0.3, 0);

        robot.pickupSkyStone();

        sleep(500);

        robot.driveStraightByGyro(direction_forward, distBackMove[skystonePostition - 1] + 300, 1, false);

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 200, 0.5);

        robot.dropSkyStone();

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 100, 0.5);

        robot.driveStraightByDistance(direction_backward, 1400, 1);
    }


}