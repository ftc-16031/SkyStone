package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Odometry Angle Only", group="Demos")
public class OdometryAngleOnly implements Runnable {
    //Odometry wheels
    private DcMotor verticalEncoderLeft, verticalEncoderRight, horizontalEncoder;

    //Thead run condition
    private boolean isRunning = true;

    //Position variables for storage
    double verticalRightEncoderWheelPosition = 0, verticalLeftEncoderWheelPosition = 0, changeInRobotOrientation = 0;
    private double robotOrientationRadians = 0;
    private double previousVerticalRightEncoderWheelPosition = 0, previousVerticalLeftEncoderWheelPosition = 0;

    //this needs to be calculated
    private double robotEncoderWheelDistance;

    private void globalCoordinatePositionUpdate(){
        //getting current positions
        verticalLeftEncoderWheelPosition = verticalEncoderLeft.getCurrentPosition();
        verticalRightEncoderWheelPosition = verticalEncoderRight.getCurrentPosition();
        double leftChange = verticalLeftEncoderWheelPosition - previousVerticalLeftEncoderWheelPosition;
        double rightChange = verticalRightEncoderWheelPosition - previousVerticalRightEncoderWheelPosition;
        //calculating the angle
        changeInRobotOrientation = (leftChange - rightChange) / Math.PI * (robotEncoderWheelDistance);
        robotOrientationRadians = ((robotOrientationRadians + changeInRobotOrientation));
    }
    @Override
    public void run() {
        while (isRunning) {
            globalCoordinatePositionUpdate();
            }
        }

}
