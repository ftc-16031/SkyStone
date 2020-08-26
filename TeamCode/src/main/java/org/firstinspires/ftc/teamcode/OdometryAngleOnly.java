package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Odometry Angle Only", group="Demos")
public class OdometryAngleOnly implements Runnable {
    private DcMotor verticalEncoderLeft, verticalEncoderRight, horizontalEncoder;

    private boolean isRunning = true;

    double verticalRightEncoderWheelPosition = 0, verticalLeftEncoderWheelPosition = 0, changeInRobotOrientation = 0;
    double verticalRightEncoderWheelPosition = 0, verticalLeftEncoderWheelPosition = 0, angleChange = 0;
    private double robotOrientationRadians = 0;
    private double previousVerticalRightEncoderWheelPosition = 0, previousVerticalLeftEncoderWheelPosition = 0;

    //how to find this
    private double radius;
    public OdometryAngleOnly(DcMotor verticalEncoderLeft, DcMotor verticalEncoderRight, DcMotor horizontalEncoder) {
        this.verticalEncoderLeft = verticalEncoderLeft;
        this.verticalEncoderRight = verticalEncoderRight;
        this.horizontalEncoder = horizontalEncoder;
    }
    private void globalCoordinatePositionUpdate(){
        verticalLeftEncoderWheelPosition = verticalEncoderLeft.getCurrentPosition();
        verticalRightEncoderWheelPosition = verticalEncoderRight.getCurrentPosition();
        double leftChange = verticalLeftEncoderWheelPosition - previousVerticalLeftEncoderWheelPosition;
        double rightChange = verticalRightEncoderWheelPosition - previousVerticalRightEncoderWheelPosition;
        angleChange = (leftChange - rightChange) / Math.PI * (radius);
        robotOrientationRadians = ((angleChange + robotOrientationRadians));
    }
    @Override
    public void run() {
        while (isRunning) {
            globalCoordinatePositionUpdate();
            }
        }

}
