package org.firstinspires.ftc.teamcode;

/**
 * Created by arvindv on 9/25/17.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public abstract class AutoMode extends LinearOpMode {


    DcMotor left;
    DcMotor right;
    DcMotor center;
    ColorSensor colorSensor;
    IMU IMU;
    public boolean isJewelRed() {
        if (colorSensor.red() > colorSensor.blue()) {
            return true;
        }
        return false;
    }
    public int getDecodedMessage() {
        return 0;
    }

    public void turnToHeading(double targetHeading) {
        double currentHeading = IMU.getHeading();
        double error = (((targetHeading - currentHeading) / 180)%360) - 180;
        while(Math.abs(error) > 2 && opModeIsActive()) {
            currentHeading = IMU.getHeading();
            error = (((targetHeading - currentHeading) / 180)%360) - 180;
            left.setPower(error * 0.01);
            right.setPower(error * 0.01);
            telemetry.addData("Degree: ", currentHeading);
            telemetry.addData("Error: ", error);
            telemetry.update();

        }
    }
    public void driveDistance() {

    }


    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.dcMotor.get("L");
        right = hardwareMap.dcMotor.get("R");
        center = hardwareMap.dcMotor.get("C");
        colorSensor = hardwareMap.colorSensor.get("Color");
        IMU = new IMU();

        IMU.setup(hardwareMap);

        waitForStart();

        IMU.start();

        runAutoMode();
    }

    abstract void runAutoMode();

}
