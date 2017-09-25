package org.firstinspires.ftc.teamcode;

/**
 * Created by arvindv on 9/25/17.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

public class AutoMode extends LinearOpMode {


    DcMotor left;
    DcMotor right;
    DcMotor center;
    ColorSensor colorSensor;
    public boolean isJewelRed() {
        if (colorSensor.red() > colorSensor.blue()) {
            return true;
        }
        return false;
    }
    public int getDecodedMessage() {

    }

    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.dcMotor.get("L");
        right = hardwareMap.dcMotor.get("R");
        center = hardwareMap.dcMotor.get("C");
        colorSensor = hardwareMap.colorSensor.get("Color");
    }
}
