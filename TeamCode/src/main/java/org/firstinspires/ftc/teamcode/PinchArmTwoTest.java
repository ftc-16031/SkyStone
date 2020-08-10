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
        robot.rearPickupSkyStone();
        sleep(5000);
        robot.rearDropSkyStone();
        sleep(3000);
        robot.frontPickupSkyStone();
        sleep(5000);
        robot.frontDropSkyStone();
    }
}
