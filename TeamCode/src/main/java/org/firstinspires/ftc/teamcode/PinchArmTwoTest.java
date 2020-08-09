package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="PinchArmTwo Test", group="Exercises")

public class PinchArmTwoTest extends LinearOpMode {
    private PinchArmTwoBot robot = new PinchArmTwoBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        sleep(10000);
    }
}
