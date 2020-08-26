package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

@Autonomous(name="Thread Test", group="Tests")

public class ThreadTest extends LinearOpMode {

    private DcMotor verticalEncoderLeft, verticalEncoderRight, horizontalEncoder;
    int verticalLeft, verticalRight, horizontal = 0;

    double xCurrentBlue = 0, yCurrentBlue = 0, thetaCurrentBlue = 60;
    double xRed = 0, yRed = 0;
    double verticalRightEncoderWheelPosition = 0, verticalLeftEncoderWheelPosition = 0, angleChange = 0;
    double robotOrientationRadians = 0;
    double previousVerticalRightEncoderWheelPosition = 0, previousVerticalLeftEncoderWheelPosition = 0;
    double radius = 0;

    boolean isRunning = true;

    @Override
    public void runOpMode() {

        Thread caseThreeThread = new CaseThree();
        waitForStart();
        caseThreeThread.start();
        while (opModeIsActive()) {
            RobotLog.d(String.format("Position, heading: %f, %f, %f", xCurrentBlue, yCurrentBlue, thetaCurrentBlue));
            RobotLog.d(String.format("Red values: %f, %f", xRed, yRed));
            telemetry.addData("OpMode:", String.format("%f, %f, %f", xCurrentBlue, yCurrentBlue, thetaCurrentBlue));
            telemetry.update();
        }
        isRunning = false;
    }

    public boolean foobar(int x){
        return x>0;
    }

    public class CaseThree extends Thread {

        public boolean anotherFoobar(int x){
            return x>0;
        }

        public double[] calculateCaseThree(int vL, int vR, int h, int lC, int rC, int radius) {
            verticalLeftEncoderWheelPosition = verticalEncoderLeft.getCurrentPosition();
            verticalRightEncoderWheelPosition = verticalEncoderRight.getCurrentPosition();
            angleChange = (lC - rC) / Math.PI * (radius);

            xRed = h;
            yRed = (vL + vR)/2;

            xCurrentBlue = Math.cos(Math.toRadians(thetaCurrentBlue - 90))*xRed + Math.cos(Math.toRadians(thetaCurrentBlue))*yRed;
            yCurrentBlue = Math.sin(Math.toRadians(thetaCurrentBlue))*yRed + Math.sin(Math.toRadians(thetaCurrentBlue - 90))*xRed;

            double[] position = {xCurrentBlue, yCurrentBlue};

            return position;
        }

        public void run() {
            while (isRunning) {
                calculateCaseThree(verticalLeft, verticalRight, horizontal);
                verticalLeft += 1;
                verticalRight += 1;
                horizontal += 1;
                telemetry.addData("Thread:", String.format("%f, %f, %f", verticalLeft, verticalRight, horizontal));
                telemetry.update();
                try {
                    Thread.sleep(1500);
                } catch(InterruptedException e) {
                    break;
                }
            }
        }
    }
}
