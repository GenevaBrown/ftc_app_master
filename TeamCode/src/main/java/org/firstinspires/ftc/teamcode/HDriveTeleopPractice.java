package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by HP 15t-as100 on 9/22/2017.
 */
@TeleOp(name="H-Drive Teleop", group="12596")

public class HDriveTeleopPractice extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor centerMotor;



    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        leftMotor = hardwareMap.dcMotor.get("L");
        rightMotor = hardwareMap.dcMotor.get("R");
        centerMotor = hardwareMap.dcMotor.get("C");

        telemetry.addData("leftStickPosition ", gamepad1.left_stick_y);
        telemetry.addData("rightStickPosition ", gamepad1.right_stick_y);
        telemetry.addData("leftTriggerPosition ", gamepad1.left_trigger);
        while (opModeIsActive()) {

            if (Math.abs(gamepad1.left_stick_y) > .01) {
                leftMotor.setPower(-gamepad1.left_stick_y);
            }
            else {
                leftMotor.setPower(0);
            }
            if (Math.abs(gamepad1.right_stick_y) > .01) {
                rightMotor.setPower(-gamepad1.right_stick_y);
            }
            else {
                rightMotor.setPower(0);
            }
            if (gamepad1.left_trigger > .05) {
                centerMotor.setPower(gamepad1.left_trigger);
            }
            else if (gamepad1.right_trigger > .05) {
                centerMotor.setPower(-gamepad1.right_trigger);
            }
            else {
                centerMotor.setPower(0);
            }



        }

    }
}
