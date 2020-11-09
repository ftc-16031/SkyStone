package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ShooterBot extends FourWheelsDriveBot {
    public DcMotor Shoot = null;
    double  position = 0.42;

    public ShooterBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        Shoot = hwMap.get(DcMotor.class, "Shoot");


    }

    public void spinshootmotor() {

           Shoot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
           Shoot.setPower(-0.5);



    }
}
