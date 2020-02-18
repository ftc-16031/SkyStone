package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="New Blue Program", group="Auto")

public class BlueStreet extends LinearOpMode {

    protected GyroBot robot = new GyroBot(this);

    int direction_forward, direction_backward;

    int[] distFirstMove = new int[]{300, 100, -100};
    int[] distFoundMove = new int[]{1700, 1500, 1300};
    int[] distBackMove = new int[]{1400, 2250, 2000};

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

        robot.driveUntilDistance(22, 0.4, 0);

        robot.driveStraightByDistance(robot.DIRECTION_FORWARD, distFirstMove[skystonePostition - 1]);

        robot.pickupSkyStone();

        sleep(100);

        robot.driveStraightByGyro(direction_forward, distFoundMove[skystonePostition - 1], 1, false);

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 200);

        robot.dropSkyStone();

        robot.originalPosition();

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 200);

        robot.driveStraightByGyro(direction_backward, distBackMove[skystonePostition - 1], 0.8, false);

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 200, 1);

        robot.driveUntilDistance(20, 0.3, 0);

        robot.pickupSkyStone();

        sleep(100);

        robot.startExtension();

        robot.driveStraightByGyro(direction_forward, distBackMove[skystonePostition - 1] + 250, 1, false);

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 250, 1);

        robot.dropSkyStone();

        robot.originalPosition();

        robot.driveStraightByDistance(direction_forward, 300, 1);

        robot.stopExtension();

        robot.dragFoundation();

        sleep(300);

        robot.specialGyroDrive(robot.DIRECTION_RIGHT, 900, 0.1, false);

        robot.goBacktoStartAnglePID(90);

        robot.originalPosition();

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 300, 1);

        robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 400, 1);
        }


    }

