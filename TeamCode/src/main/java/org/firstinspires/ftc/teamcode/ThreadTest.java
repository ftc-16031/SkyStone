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

    double xBlue = 0, yBlue = 0, thetaDEG = 0;
    double xRed = 0, yRed = 0;
    double verticalRight = 0, verticalLeft = 0, angleChange = 0;
    double previousVL = 0, previousVR = 0;
    final double radius = 25;
    final double wheelDiameter = 3.8;
    final double ticksPerRevolution = 8192;
    final int vLDirection = 1;
    final int vRDirection = -1;
    final int hDirection = 1;

    boolean isRunning = true;

    @Override
    public void runOpMode() {
        initDriveHardwareMap(rfName, rbName, lfName, lbName, verticalLeftEncoderName, verticalRightEncoderName, horizontalEncoderName);
        Thread caseThreeThread = new CaseThree();
        waitForStart();
        caseThreeThread.start();
        while (opModeIsActive()) {
            RobotLog.d(String.format("Position, heading: %f, %f, %f", xBlue, yBlue, thetaDEG));
            RobotLog.d(String.format("Red values: %f, %f", xRed, yRed));
            telemetry.addData("OpMode:", String.format("%f, %f, %f, %d, %d, %d", xBlue, yBlue, thetaDEG,
            verticalLeftEncoder.getCurrentPosition(), verticalRightEncoder.getCurrentPosition(), horizontalEncoder.getCurrentPosition()));
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

        public double[] calculateCaseThree(double vL, double vR, double h, double angleDEG) {
            vL = vL * vLDirection;
            vR = vR * vRDirection;
            h = h * hDirection;

            double lC = vL - previousVL;
            double rC = vR - previousVR;

            angleChange = ((lC - rC) / (Math.PI * radius * 2) * 360);

            angleDEG = angleDEG + angleChange;
            thetaDEG = angleDEG;

            System.out.println(String.format("%f, %f",angleChange, angleDEG));

            xRed = h;
            yRed = (vL + vR)/2;

            xBlue = Math.cos(Math.toRadians(angleDEG - 90))*xRed + Math.cos(Math.toRadians(angleDEG))*yRed;
            yBlue = Math.sin(Math.toRadians(angleDEG))*yRed + Math.sin(Math.toRadians(angleDEG - 90))*xRed;

            previousVL = vL;
            previousVR = vR;

            double[] position = {xBlue, yBlue};

            return position;
        }

        public void run() {
            while (isRunning) {
                calculateCaseThree((verticalLeftEncoder.getCurrentPosition() * wheelDiameter * Math.PI) / ticksPerRevolution,
            (verticalRightEncoder.getCurrentPosition() * wheelDiameter * Math.PI) / ticksPerRevolution,
             (horizontalEncoder.getCurrentPosition() * wheelDiameter * Math.PI) / ticksPerRevolution, thetaDEG);
                try {
                    Thread.sleep(50);
                } catch(InterruptedException e) {
                    break;
                }
            }
        }
    }
}
