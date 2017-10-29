package org.firstinspires.ftc.teamcode;

/**
 * Created by arvindv on 9/25/17.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static android.R.attr.key;

public abstract class AutoMode extends LinearOpMode {


    DcMotor left;
    DcMotor right;
    DcMotor center;
    ColorSensor colorSensor;
    ColorSensor blueLineSensor;
    IMU IMU;
    Servo servoCollectorLt;
    Servo servoCollectorRt;
    Servo jewelSwiper;
    Servo lHDrive;
    Servo rHDrive;

    double dropHeight = 0.43;


    public Decoder vuforia;

    public  boolean isJewelRed() {
        if (colorSensor.red() > colorSensor.blue()) {
            return true;
        }
        return false;
    }

    public  boolean isLineBlue() {
        if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green() && colorSensor.blue() > colorSensor.alpha()) {
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

    public void turnToPitch (double targetPitch) {
        double currentPitch = IMU.getPitch();
        double error = getTurnError(targetPitch, currentPitch);

        while(Math.abs(error) > .5 && opModeIsActive()) {
            currentPitch = IMU.getHeading();
            error = -getTurnError(targetPitch, currentPitch);
            left.setPower(error * 0.001);
            right.setPower(error * 0.001);
            telemetry.addData("Degree: ", currentPitch);
            telemetry.addData("Error: ", error);
            telemetry.update();
        }
        left.setPower(.7);
        right.setPower(-.7);
        sleep(2000);
        left.setPower(0);
        right.setPower(0);
    }

    public void goDistance (double distanceToGo, double power, boolean liftCWheel) {


        int startPositionLt = 0;
        int startPositionRt = 0;

        if(liftCWheel == true) {
            lHDrive.setPosition(0.5);
            rHDrive.setPosition(0.5);
            sleep (500);
        }

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
                left.setPower(-power * .9);
                right.setPower(power);
            } else if (currentPositionRt > currentPositionLt) {
                left.setPower(-power);
                right.setPower(power * .9);
            }
            else {
                left.setPower(-power);
                right.setPower(power);
            }

        }

        left.setPower(0);
        right.setPower(0);
        sleep(50);
        //again power if doesn't work
    }
    public void goDistanceCenter (double distanceToGoC, double power) {
        int startPositionC = 0;
        rHDrive.setPosition(0.5 + dropHeight);
        lHDrive.setPosition(0.5 - dropHeight);
        if (distanceToGoC < 0) {
            power = -power;
            distanceToGoC = Math.abs(distanceToGoC);
        }
        startPositionC = center.getCurrentPosition();
        int targetDistance = ((int) ((distanceToGoC / (4 * Math.PI) * 1120))/2);

        double currentPositionC = center.getCurrentPosition();

        while ((Math.abs(currentPositionC - startPositionC) < Math.abs(targetDistance)) && opModeIsActive()) {
            center.setPower(power);
            telemetry.addData("Current pos C: ", currentPositionC);
            telemetry.addData("target pos C: ", targetDistance);
            telemetry.addData("error pos: C", Math.abs(targetDistance - currentPositionC));
            telemetry.update();

            currentPositionC = center.getCurrentPosition();
        }
            center.setPower(0);
            sleep(50);
        }
    public int Vuforia () {
        vuforia = new Decoder(hardwareMap, "AYx3Kw3/////AAAAGQreNEJhLkdWqUbBsQ06dnWIksoccLxh/R9WNkXB8hvuonWmFXUWJ2tYqM+8VqYCWXkHfanXzG/G1un7ZvwgGkkO6u0ktevZDb8AFWF2/Y4wVH1BWGQ2psV5QkHAKZ7Z6ThZI01HPZqixiQowyeUstv7W/QU8jJ48NrqGBLVYdE6eFfzNDzVY/1IvrBJaRwqKR8vo+3a2zmeFEnEhFTqMI7anU2WSPy8RP7tR61CdfidjL2biMe0RiSOBIbqOe4rs9NGaDvp1Crtz17uyY71GyMkp+Kmjbejyfj8LgZ/dZQoEsuVuQyo0dbd4KBxsEJlQj/uAEst22QoEwZe0Af4DnFtwn6/IEe02L3DT3/Np+ZX");
        RelicRecoveryVuMark vuMark = vuforia.getMark();
        vuforia.start();
        return vuforia.getDecodedColumn();
    }

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
        lHDrive = hardwareMap.servo.get("LH");
        rHDrive = hardwareMap.servo.get("RH");
        colorSensor = hardwareMap.get(ColorSensor.class, "Color");
        colorSensor.setI2cAddress(I2cAddr.create7bit(0x39));
        blueLineSensor = hardwareMap.get(ColorSensor.class, "blueLineSensor");
        blueLineSensor.setI2cAddress(I2cAddr.create7bit(0x24));
        IMU = new IMU();
        jewelSwiper = hardwareMap.servo.get("jewelSwiper");

        IMU.setup(hardwareMap);

        waitForStart();

        IMU.start();

        runAutoMode();
    }


    abstract void runAutoMode();

}
