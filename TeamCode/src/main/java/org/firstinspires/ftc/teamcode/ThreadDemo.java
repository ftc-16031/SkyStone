package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.RobotLog;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name="Thread Demo", group="Demos")

public class ThreadDemo extends LinearOpMode {


    public DcMotor horizontal = null;
    public DcMotor verticalLeft = null;
    public DcMotor verticalRight = null;

    String rfName = "rf", rbName = "rb", lfName = "lf", lbName = "lb";
    String verticalLeftEncoderName = rbName, verticalRightEncoderName = lfName, horizontalEncoderName = rfName;

    double xCurrentBlue = 0, yCurrentBlue = 0, thetaCurrentBlue = 60;
    double xRed = 0, yRed = 0;

    @Override
        public void runOpMode() {
        initDriveHardwareMap(rfName, rbName, lfName, lbName, verticalLeftEncoderName, verticalRightEncoderName, horizontalEncoderName);
        telemetry.addData("Status", "Init Complete");
        telemetry.update();
        Thread caseThreeThread = new CaseThree();
        waitForStart();
        caseThreeThread.start();
        while (opModeIsActive()) {
            RobotLog.d(String.format("Position, heading: %2d, %2d, %2d", xCurrentBlue, yCurrentBlue, thetaCurrentBlue));
            telemetry.addData("X:", xCurrentBlue);
            telemetry.addData("Y:", yCurrentBlue);
            telemetry.addData("Theta:", thetaCurrentBlue);
            telemetry.update();
    }
}
    private void initDriveHardwareMap(String rfName, String rbName, String lfName, String lbName, String vl, String vr, String h){

        horizontal = hardwareMap.dcMotor.get("h");
        horizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalLeft = hardwareMap.dcMotor.get("v1");
        verticalLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalRight = hardwareMap.dcMotor.get("v2");
        verticalRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Hardware Map Init Complete");
        telemetry.update();
    }



    public class CaseThree extends Thread {

        private void calculateCaseThree() {
            xRed = horizontal.getCurrentPosition();
            yRed = (verticalLeft.getCurrentPosition() + verticalRight.getCurrentPosition())/2;

            xCurrentBlue = Math.cos(Math.toRadians(thetaCurrentBlue - 90))*xRed + Math.cos(Math.toRadians(thetaCurrentBlue))*yRed;
            yCurrentBlue = Math.sin(Math.toRadians(thetaCurrentBlue))*yRed + Math.sin(Math.toRadians(thetaCurrentBlue - 90))*xRed;
        }

        public void run() {
            while (true) {
                calculateCaseThree();
            }
        }
    }
}
