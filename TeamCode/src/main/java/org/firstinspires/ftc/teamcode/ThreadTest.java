package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name="Thread Test", group="Tests")

public class ThreadTest extends LinearOpMode {

    double verticalLeft, verticalRight, horizontal = 0;

    double xCurrentBlue = 0, yCurrentBlue = 0, thetaCurrentBlue = 60;
    double xRed = 0, yRed = 0;

    @Override
    public void runOpMode() {

        Thread caseThreeThread = new CaseThree();
        waitForStart();
        caseThreeThread.start();
        while (opModeIsActive()) {
            RobotLog.d(String.format("Position, heading: %2f, %2f, %2f", xCurrentBlue, yCurrentBlue, thetaCurrentBlue));
            telemetry.addData("X:", xCurrentBlue);
            telemetry.addData("Y:", yCurrentBlue);
            telemetry.addData("Theta:", thetaCurrentBlue);
            telemetry.update();
        }
    }

    public class CaseThree extends Thread {

        private void calculateCaseThree() {
            xRed = horizontal;
            yRed = (verticalLeft + verticalRight)/2;

            xCurrentBlue = Math.sin(thetaCurrentBlue)*xRed + Math.cos(thetaCurrentBlue)*yRed;
            yCurrentBlue = Math.sin(thetaCurrentBlue)*yRed + Math.cos((thetaCurrentBlue)*xRed);
        }

        public void run() {
            while (true) {
                calculateCaseThree();
                verticalLeft =+ 1;
                verticalRight =+ 1;
                horizontal =+ 1;
            }
        }
    }
}
