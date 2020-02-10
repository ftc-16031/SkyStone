package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ParkArmBot extends FourWheelsDriveBot{
    public CRServo parkArm = null;

    public ParkArmBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        parkArm = hwMap.crservo.get("parkArm");
        parkArm.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void startExtension() {
        parkArm.setPower(-1);
    }

    public void stopExtension () {
        parkArm.setPower(0);
    }

    public void unextend () {
        parkArm.setPower(1);
    }

    public void manualExtension (boolean dpadLeft, boolean dpadRight, boolean dpadDown) {
        if (dpadLeft) {
            startExtension();
        }
        if (dpadRight) {
            unextend();
        }
        if (dpadDown) {
            stopExtension();
        }
    }
}
