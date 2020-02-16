package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="New Red Program", group="Auto")

public class RedStreet extends LinearOpMode {

    protected GyroBot robot = new GyroBot(this);

    int direction_forward, direction_backward;

    int[] distFirstMove = new int[]{300, 100, -100};
    int[] distFoundMove = new int[]{1600, 1900, 2100};
    int[] distBackMove = new int[]{2100, 2350, 1550};

    int skystonePostition;
    protected void setDirection(){
        direction_forward = robot.DIRECTION_FORWARD;
        direction_backward = robot.DIRECTION_BACKWARD;

    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        setDirection();

        skystonePostition = robot.detectSkystone();

        robot.driveUntilDistance(22, 0.4, 1);

        robot.driveStraightByDistance(robot.DIRECTION_FORWARD, distFirstMove[skystonePostition - 1]);

        robot.pickupSkyStone();

        sleep(100);

        robot.driveStraightByGyro(direction_forward, distFoundMove[skystonePostition - 1], 1, false);

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 200);

        robot.dropSkyStone();

        robot.originalPosition();

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 200);

        robot.driveStraightByGyro(direction_backward, distBackMove[skystonePostition - 1], 1, false);

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 200, 1);

        robot.driveUntilDistance(20, 0.3, 1);

        robot.pickupSkyStone();

        sleep(100);

        robot.driveStraightByGyro(direction_forward, distBackMove[skystonePostition - 1] + 200, 1, false);

        robot.startExtension();

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 200, 1);

        robot.dropSkyStone();

        robot.originalPosition();

        robot.driveStraightByDistance(direction_forward, 200, 1);

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 100, 1);

        robot.dragFoundation();

        sleep(300);

        robot.stopExtension();

        robot.specialGyroDrive(robot.DIRECTION_RIGHT, 900, 0.1, false);

        robot.leftFront.setPower(1);

        robot.rightFront.setPower(-1);

        robot.leftRear.setPower(1);

        robot.rightRear.setPower(-1);

        robot.opMode.sleep(1200);

        robot.originalPosition();

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 300, 1);

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 400, 1);
    }


    }

