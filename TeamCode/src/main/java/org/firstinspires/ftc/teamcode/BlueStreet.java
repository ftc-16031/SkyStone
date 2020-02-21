package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="New Blue Program", group="Auto")

public class BlueStreet extends LinearOpMode {

    protected GyroBot robot = new GyroBot(this);

    int direction_forward, direction_backward;

    int[] distFirstMove = new int[]{300, 100, -100};
    int[] distFoundMove = new int[]{1900, 1700, 1500};
    int[] distBackMove = new int[]{1700, 2450, 2200};

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

        sleep(400);

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

        robot.startExtension(1);

        robot.driveStraightByGyro(direction_forward, distBackMove[skystonePostition - 1] + 200, 1, false);

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 300, 1);

        robot.dropSecondSkyStone();

        robot.originalPosition();

        robot.driveStraightByDistance(direction_forward, 225, 1);

        robot.dragFoundation();

        sleep(300);

        robot.stopExtension(1);

        robot.specialGyroDrive(robot.DIRECTION_RIGHT, 1600, 0.2, false);

        robot.goBacktoStartAnglePID(90);

        robot.originalPosition();

        robot.driveStraightByDistance(robot.DIRECTION_LEFT, 300, 1);

        robot.driveCurveByDistance(robot.DIRECTION_RIGHT, 1000, 0.3, 1);
    }


}

