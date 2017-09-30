package org.firstinspires.ftc.teamcode;

/**
 * Created by arvindv on 9/25/17.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
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
    public double getTurnError(double target, double current) {
        double o = target - current;
        return (((o + 180)) % 360) - 180;
    }

    public void turnToHeading(double targetHeading) {
        double currentHeading = IMU.getHeading();
        double error = getTurnError(targetHeading, currentHeading);

        while(Math.abs(error) > .5 && opModeIsActive()) {
            currentHeading = IMU.getHeading();
            error = -getTurnError(targetHeading, currentHeading);
            left.setPower(error * 0.008);
            right.setPower(error * 0.008);
            telemetry.addData("Degree: ", currentHeading);
            telemetry.addData("Error: ", error);
            telemetry.update();
        }
        left.setPower(.7);
        right.setPower(-.7);
        sleep(2000);
        left.setPower(0);
        right.setPower(0);
    }

    public void goDistance (double targetDistance) {
        targetDistance = (targetDistance/(4*Math.PI) * 1120) / 2;
        double startPositionLt = left.getCurrentPosition();
        double startPositionC = center.getCurrentPosition();

        double currentPositionLt = Math.abs(left.getCurrentPosition());
        double currentPositionC = center.getCurrentPosition();
        double error = targetDistance - currentPositionLt - startPositionLt;

        while(Math.abs(error) > .5 && opModeIsActive()) {
            currentPositionLt = Math.abs(left.getCurrentPosition());
            error = targetDistance - currentPositionLt - startPositionLt;
            left.setPower(error * 0.008);
            right.setPower(error * -0.008);
            telemetry.addData("Position: ", currentPositionLt);
            telemetry.addData("Error: ", error);
            telemetry.update();
        }
        /*left.setPower(.7);
        right.setPower(-.7);
        sleep(2000);
        left.setPower(0);
        right.setPower(0);*/
    }



    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.dcMotor.get("L");
        right = hardwareMap.dcMotor.get("R");
        center = hardwareMap.dcMotor.get("C");

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        center.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
