package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by HP 15t-as100 on 9/25/2017.
 */
@Autonomous (name = "TestColor", group = "12596")
public class TestColor extends AutoMode {

    @Override


 /*   void runAutoMode() {
        turnToHeading(180);
    }*/
    void runAutoMode () {
        colorSensor.enableLed(false);
        boolean isRed = isJewelRed();
        telemetry.addData("Red?",isRed);
        telemetry.update();
        sleep(10000);
    }
}
