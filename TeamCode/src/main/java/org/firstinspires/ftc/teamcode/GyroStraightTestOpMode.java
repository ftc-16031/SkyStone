package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="PID STRAIGHT TEST lLL", group="Exercises")

public class GyroStraightTestOpMode extends LinearOpMode {
    protected PIDBot robot = new PIDBot(this);

    int direction_forward, direction_backward;

    protected void setDirection() {
        direction_forward = robot.DIRECTION_BACKWARD;
        direction_backward = robot.DIRECTION_FORWARD;
    }

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();

        setDirection();

        robot.resetAngle();

        while (opModeIsActive())

            robot.checkDirection();


        robot.driveStraightbyDistancePID(robot.DIRECTION_FORWARD, 1000, 0.3 );

        robot.rotate(90, 0.3);

        robot.driveStraightbyDistancePID(robot.DIRECTION_FORWARD, 1000, 0.3 );



    }

}