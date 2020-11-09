package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Shootertest", group="Tests")

public class Shootertest extends LinearOpMode {
    private ShooterBot robot = new ShooterBot(this);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

       robot.spinshootmotor();
        while (opModeIsActive()) {

            sleep(100);

        }

    }
}