package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;

public class PinchArmTwoBot extends FourWheelsDriveBot {
    static final double INCREMENT   = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   500;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position
    static final double REAR_ARM_INIT = 0;
    static final double REAR_PINCH_INIT = 0;
    static final double FRONT_ARM_INIT = 0;
    static final double FRONT_PINCH_INIT = 0;

    public Servo rearArm = null;
    public Servo rearPinch = null;
    public Servo frontArm = null;
    public Servo frontPinch = null;

    public PinchArmTwoBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        // Connect to servo (Assume PushBot Left Hand)
        // Change the text in quotes to match any servo name on your robot.
        rearArm = hwMap.servo.get("rearArm");
        rearPinch = hwMap.servo.get("rearPinch");
        frontArm = hwMap.servo.get("frontArm");
        frontPinch = hwMap.servo.get("frontPinch");

        rearArm.setPosition(REAR_ARM_INIT);
        rearPinch.setPosition(REAR_PINCH_INIT);
        frontArm.setPosition(FRONT_ARM_INIT);
        frontPinch.setPosition(FRONT_PINCH_INIT);
        RobotLog.d(String.format("After Init : REAR ARM : %f, REAR PINCH : %f, FRONT ARM : %f, FRONT PINCH : %f",
                rearArm.getPosition(), rearPinch.getPosition(),
                frontArm.getPosition(), frontPinch.getPosition()));
    }

    public void pickupSkyStone() {
        
    }


}