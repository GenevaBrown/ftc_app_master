package org.firstinspires.ftc.teamcode;

/**
 * Created by arvindv on 9/25/17.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class AutoMode extends LinearOpMode {


    DcMotor left;
    DcMotor right;
    DcMotor center;
    ColorSensor colorSensor;
    IMU IMU;
    Servo servoCollectorLt;
    Servo servoCollectorRt;
    public  boolean isJewelRed() {
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

    public void goDistance (double distanceToGo, double power) {


        int startPositionLt = 0;
        int startPositionRt = 0;

        if(distanceToGo < 0) {
            power = -power;
            distanceToGo = Math.abs(distanceToGo);
        }

        startPositionLt = left.getCurrentPosition();
        startPositionRt = right.getCurrentPosition();
        int targetDistance = ((int) ((distanceToGo / (4 * Math.PI) * 1120))/2);

        double currentPositionLt = left.getCurrentPosition();
        double currentPositionRt = right.getCurrentPosition();

        while ((Math.abs(currentPositionLt - startPositionLt) < Math.abs(targetDistance)) && opModeIsActive()) {
            left.setPower(power);
            right.setPower(power);
            telemetry.addData("Current pos L: ", currentPositionLt);
            telemetry.addData("target pos L: ", targetDistance);
            telemetry.addData("error pos: L", Math.abs(targetDistance - currentPositionLt));
            telemetry.update();
            telemetry.addData("Current pos: ", currentPositionRt);
            telemetry.addData("target pos: ", targetDistance);
            telemetry.addData("error pos: ", Math.abs(targetDistance - currentPositionRt));
            telemetry.update();
            currentPositionLt = left.getCurrentPosition();
            currentPositionRt = right.getCurrentPosition();

            if (currentPositionLt > currentPositionRt) {
                left.setPower(power * .9);
                right.setPower(power);
            } else if (currentPositionRt > currentPositionLt) {
                left.setPower(power);
                right.setPower(power * .9);
            }
            else {
                left.setPower(power);
                right.setPower(power);
            }

        }

        left.setPower(0);
        right.setPower(0);
        sleep(50);
        //again power if doesn't work
    }
    //right encoder make sure equal, downgrade power if going to fast


    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.dcMotor.get("L");
        right = hardwareMap.dcMotor.get("R");
        center = hardwareMap.dcMotor.get("C");

        servoCollectorLt = hardwareMap.servo.get("LtCollector");
        servoCollectorRt = hardwareMap.servo.get("RtCollector");

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        center.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        center = hardwareMap.dcMotor.get("C");
        colorSensor = hardwareMap.get(ColorSensor.class, "Color");
        colorSensor.setI2cAddress(I2cAddr.create7bit(0x39));
        IMU = new IMU();

        IMU.setup(hardwareMap);

        waitForStart();

        IMU.start();

        runAutoMode();
    }

    abstract void runAutoMode();

}
