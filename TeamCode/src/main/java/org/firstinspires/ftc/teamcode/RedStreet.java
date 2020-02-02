package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Autonomous(name="New Red Program", group="New Auto")

public class RedStreet extends LinearOpMode {

    protected GyroBot robot = new GyroBot(this);

    int direction_forward, direction_backward;

    int[] distFirstMove = new int[]{300, 100, -100};
    int[] distFoundMove = new int[]{1300, 1500, 1700};
    int[] distBackMove = new int[]{1850, 2050, 1250};

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

//            robot.driveUntilDistance(35, 0.3, 1);

//            robot.driveStraightByDistance(direction_forward, distFoundMove[skystonePostition - 1], 0.8);

            robot.driveStraightByGyro(direction_forward, distFoundMove[skystonePostition - 1], 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 150);

            robot.dropSkyStone();
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 150);


            robot.originalPosition();

//            robot.driveStraightByDistance(direction_backward, distBackMove[skystonePostition - 1], 0.8);

            robot.driveStraightByGyro(direction_backward, distBackMove[skystonePostition - 1], 1, false);
            robot.driveStraightByDistance(robot.DIRECTION_RIGHT, 200, 1);

            robot.driveUntilDistance(20, 0.3, 1);

            robot.pickupSkyStone();
            sleep(100);

//            robot.driveUntilDistance(35, 0.3, 1);
//            robot.driveStraightByDistance(direction_forward, distBackMove[skystonePostition - 1], 0.8);

            robot.driveStraightByGyro(direction_forward, distBackMove[skystonePostition - 1], 1, false);

            robot.dropSkyStone();

            robot.driveStraightByDistance(direction_forward, 300, 1);
            robot.driveStraightByDistance(robot.DIRECTION_LEFT, 200, 1);
            robot.dragFoundation();
            robot.driveStraightByGyro(robot.DIRECTION_RIGHT, 300, 0.3, false);
            robot.driveStraightByGyro(robot.DIRECTION_RIGHT, 700, 1, false);


    }


    }

