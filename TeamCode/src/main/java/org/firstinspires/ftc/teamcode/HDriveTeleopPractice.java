package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by HP 15t-as100 on 9/22/2017.
 */
@TeleOp(name="H-Drive Teleop", group="12596")

public class HDriveTeleopPractice extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor centerMotor;
    Servo servoCollectorLt;
    Servo servoCollectorRt;
    Servo jewelSwiper;
    Servo lHDrive;
    Servo rHDrive;
    CRServo omnipulatorRt;
    CRServo omnipulatorLt;
    Servo omniPusher;
    DcMotor omnipulatorLift;
    DigitalChannel liftStop;
    double dropHeight = 0.43;

    @Override
    public void runOpMode() throws InterruptedException {

        lHDrive = hardwareMap.servo.get("LH");
        rHDrive = hardwareMap.servo.get("RH");
        leftMotor = hardwareMap.dcMotor.get("L");
        rightMotor = hardwareMap.dcMotor.get("R");
        centerMotor = hardwareMap.dcMotor.get("C");
        servoCollectorLt = hardwareMap.servo.get("LtCollector");
        servoCollectorRt = hardwareMap.servo.get("RtCollector");
        jewelSwiper = hardwareMap.servo.get("jewelSwiper");
        omnipulatorRt = hardwareMap.crservo.get("omnipulatorRt");
        omnipulatorLt = hardwareMap.crservo.get("omnipulatorLt");
        omniPusher = hardwareMap.servo.get("pusher");
        omnipulatorLift = hardwareMap.dcMotor.get("Lift");
        liftStop = hardwareMap.digitalChannel.get("liftStop");
        liftStop.setMode(DigitalChannel.Mode.INPUT);
        centerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        telemetry.addData("leftStickPosition ", gamepad1.left_stick_y);
        telemetry.addData("rightStickPosition ", gamepad1.right_stick_y);
        telemetry.addData("leftTriggerPosition ", gamepad1.left_trigger);

        double servoInitPositionLt = servoCollectorLt.getPosition();
        double servoInitPositionRt = servoCollectorRt.getPosition();

        lHDrive.setPosition(0.5);
        rHDrive.setPosition(0.5);
        jewelSwiper.setPosition(1);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Jewel Swiper Pos: ", jewelSwiper.getPosition());
            telemetry.addData("liftStopPos: ", liftStop.getState());
            telemetry.update();
            double jewelSwiperCurrentPos = jewelSwiper.getPosition();
            double omnipulatorLtCurrentPos = omnipulatorLt.getPower();
            double omnipulatorRtCurrentPos = omnipulatorRt.getPower();
            double pusherCurrentPos = omniPusher.getPosition();
            //double servoCurrentPosLt = servoCollectorLt.getPosition();
            //double servoCurrentPosRt = servoCollectorRt.getPosition();
            if (Math.abs(gamepad1.left_stick_y) > .01) {
                lHDrive.setPosition(0.5);
                rHDrive.setPosition(0.5);
                leftMotor.setPower(gamepad1.left_stick_y);
            }
            /*if (Math.abs(gamepad1.left_stick_y) > .01 && Math.abs(gamepad1.left_stick_y) < .5) {
                leftMotor.setPower(gamepad1.left_stick_y / 2);
            }
            if (Math.abs(gamepad1.left_stick_y) >= .5 && Math.abs(gamepad1.left_stick_y) < .7) {
                leftMotor.setPower(gamepad1.left_stick_y / 1.25);
            }
            if (Math.abs(gamepad1.left_stick_y) >= .7) {
                leftMotor.setPower(gamepad1.left_stick_y);
            }*/
            else {
                leftMotor.setPower(0);
            }
            /*if (Math.abs(gamepad1.right_stick_y) > .01 && Math.abs(gamepad1.right_stick_y) < .5) {
                rightMotor.setPower(-gamepad1.right_stick_y / 2);
            }
            if (Math.abs(gamepad1.right_stick_y) >= .5 && Math.abs(gamepad1.right_stick_y) < .7) {
                rightMotor.setPower(-gamepad1.right_stick_y / 1.25);
            }
            if (Math.abs(gamepad1.right_stick_y) >= .7) {
                rightMotor.setPower(-gamepad1.right_stick_y);
            }*/
            if (Math.abs(gamepad1.right_stick_y) > .01) {
                lHDrive.setPosition(0.5);
                rHDrive.setPosition(0.5);
                rightMotor.setPower(-gamepad1.right_stick_y);
            }
            else {
                rightMotor.setPower(0);
            }
            if (gamepad1.left_trigger > .05) {
                rHDrive.setPosition(0.5 + dropHeight);
                lHDrive.setPosition(0.5 - dropHeight);
                centerMotor.setPower(-gamepad1.left_trigger);
            }
            else if (gamepad1.right_trigger > .05) {
                rHDrive.setPosition(0.5 + dropHeight);
                lHDrive.setPosition(0.5 - dropHeight);
                centerMotor.setPower(gamepad1.right_trigger);
            }
            else {
                centerMotor.setPower(0);
                //lHDrive.setPosition(0.5);
                //rHDrive.setPosition(0.5);

            }
            if (gamepad2.right_trigger > .05) {
                omnipulatorLt.setPower(-.7);
                omnipulatorRt.setPower(.7);
            }
            else if (gamepad2.left_trigger > .05) {
                omnipulatorLt.setPower(.7);
                omnipulatorRt.setPower(-.7);
            }
            else {
                omnipulatorLt.setPower(0);
                omnipulatorRt.setPower(0);
            }
            if (gamepad2.y) {
                omniPusher.setPosition(1);
            }
            else {
                omniPusher.setPosition(0);
            }
            if (gamepad2.dpad_up) {
                omnipulatorLift.setPower(.7);
            }
            else if (gamepad2.dpad_down && liftStop.getState()) {
                omnipulatorLift.setPower(-.7);
            }
            else {
                omnipulatorLift.setPower(0);
            }
            if (gamepad2.right_bumper) {
                servoCollectorLt.setPosition(servoInitPositionLt + .5);
                servoCollectorRt.setPosition(servoInitPositionRt - .5);
            }
            else if (gamepad2.left_bumper) {
                servoCollectorLt.setPosition(servoInitPositionLt - .5);
                servoCollectorRt.setPosition(servoInitPositionRt + .5);
            } else {
                servoCollectorLt.setPosition(0.5);
                servoCollectorRt.setPosition(0.5);
            }

            if (gamepad1.dpad_up) {
                jewelSwiper.setPosition(1);
            }
            else if (gamepad1.dpad_down) {
                jewelSwiper.setPosition(.55);
            }
            else{
                jewelSwiper.setPosition(jewelSwiperCurrentPos);
            }
            jewelSwiperCurrentPos = jewelSwiper.getPosition();



        }

    }
}
