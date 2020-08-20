package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Autonomous(name="Thread Demo", group="Demos")

public class ThreadDemo extends LinearOpMode {
    public DcMotor horizontal = null;

    String rfName = "rf", rbName = "rb", lfName = "lf", lbName = "lb";
    String verticalLeftEncoderName = rbName, verticalRightEncoderName = lfName, horizontalEncoderName = rfName;

    @Override
        public void runOpMode() {
        initDriveHardwareMap(rfName, rbName, lfName, lbName, verticalLeftEncoderName, verticalRightEncoderName, horizontalEncoderName);
        telemetry.addData("Status", "Init Complete");
        telemetry.update();
        waitForStart();
        Thread positionThread = new Thread();
        positionThread.start();
        while (opModeIsActive()) {

        telemetry.addData("horizontal encoder position", horizontal.getCurrentPosition());

        telemetry.addData("Thread Active", positionThread.isAlive());
        telemetry.update();
    }
}
    private void initDriveHardwareMap(String rfName, String rbName, String lfName, String lbName, String vl, String vr, String h){

        horizontal = hardwareMap.dcMotor.get("h");
        horizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Hardware Map Init Complete");
        telemetry.update();
    }
    }
