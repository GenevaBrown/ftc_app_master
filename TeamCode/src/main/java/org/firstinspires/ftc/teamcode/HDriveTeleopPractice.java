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

        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        centerMotor = hardwareMap.dcMotor.get("centerMotor");

        telemetry.addData("leftStickPosition ", gamepad1.left_stick_y);
        telemetry.addData("rightStickPosition ", gamepad1.right_stick_y);
        telemetry.addData("leftTriggerPosition ", gamepad1.left_trigger);
        while (opModeIsActive()) {

            if (gamepad1.left_stick_y > .05) {
                leftMotor.setPower(-gamepad1.left_stick_y);
            }
            if (gamepad1.right_stick_y > .05) {
                rightMotor.setPower(gamepad1.right_stick_y);
            }


        }

    }
}
