package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

@Autonomous(name="Thread Test", group="Tests")

public class ThreadTest extends LinearOpMode {

    public DcMotor verticalLeftEncoder, verticalRightEncoder, horizontalEncoder = null;

    String rfName = "rf", rbName = "rb", lfName = "lf", lbName = "lb";
    String verticalLeftEncoderName = rbName, verticalRightEncoderName = lfName, horizontalEncoderName = rfName;

    double xCurrentBlue = 0, yCurrentBlue = 0, thetaDEG = 0;
    double xRed = 0, yRed = 0;
    double verticalRightPosition = 0, verticalLeftPosition = 0, angleChange = 0;
    double previousVL = 0, previousVR = 0;
    final double radius = 10;

    boolean isRunning = true;

    @Override
    public void runOpMode() {
        initDriveHardwareMap(rfName, rbName, lfName, lbName, verticalLeftEncoderName, verticalRightEncoderName, horizontalEncoderName);
        Thread caseThreeThread = new CaseThree();
        waitForStart();
        caseThreeThread.start();
        while (opModeIsActive()) {
            RobotLog.d(String.format("Position, heading: %f, %f, %f", xCurrentBlue, yCurrentBlue, thetaDEG));
            RobotLog.d(String.format("Red values: %f, %f", xRed, yRed));
            telemetry.addData("OpMode:", String.format("%f, %f, %f", xCurrentBlue, yCurrentBlue, thetaDEG));
            telemetry.update();
        }
        isRunning = false;
    }

    private void initDriveHardwareMap(String rfName, String rbName, String lfName, String lbName, String vl, String vr, String h){

        horizontalEncoder = hardwareMap.dcMotor.get("h");
        horizontalEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalLeftEncoder = hardwareMap.dcMotor.get("v1");
        verticalLeftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalLeftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalRightEncoder = hardwareMap.dcMotor.get("v2");
        verticalRightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalRightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Hardware Map Init Complete");
        telemetry.update();
    }

    public boolean foobar(int x){
        return x>0;
    }

    public class CaseThree extends Thread {

        public boolean anotherFoobar(int x){
            return x>0;
        }

        public double[] calculateCaseThree(int vL, int vR, int h, double thetaDEG) {
            verticalLeftPosition = vL;
            verticalRightPosition = vR;

            double lC = vL - previousVL;
            double rC = vR - previousVR;

            angleChange = ((lC - rC) / (Math.PI * radius * 2) * 360);

            thetaDEG = thetaDEG + angleChange;

            xRed = h;
            yRed = (vL + vR)/2;

            xCurrentBlue = Math.cos(Math.toRadians(thetaDEG - 90))*xRed + Math.cos(Math.toRadians(thetaDEG))*yRed;
            yCurrentBlue = Math.sin(Math.toRadians(thetaDEG))*yRed + Math.sin(Math.toRadians(thetaDEG - 90))*xRed;

            previousVL = vL;
            previousVR = vR;

            double[] position = {xCurrentBlue, yCurrentBlue};

            return position;
        }

        public void run() {
            while (isRunning) {
                calculateCaseThree(verticalLeftEncoder.getCurrentPosition(), verticalRightEncoder.getCurrentPosition(),
                        horizontalEncoder.getCurrentPosition(), thetaDEG);
                try {
                    Thread.sleep(1500);
                } catch(InterruptedException e) {
                    break;
                }
            }
        }
    }
}
