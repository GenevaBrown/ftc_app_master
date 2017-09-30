package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by HP 15t-as100 on 9/25/2017.
 */
@Autonomous (name = "TestAuto", group = "12596")
public class TestAuto extends AutoMode {

    @Override


 /*   void runAutoMode() {
        turnToHeading(180);
    }*/
    void runAutoMode () {
        telemetry.addData("Encoder Position ", left.getCurrentPosition());
        telemetry.addData("Encoder Postition ", right.getCurrentPosition());
        telemetry.addData("Encoder Postition ", center.getCurrentPosition());
        telemetry.update();
        goDistance(10);

        sleep(1000);
        telemetry.addData("Encoder Position ", left.getCurrentPosition());
        telemetry.addData("Encoder Postition ", right.getCurrentPosition());
        telemetry.addData("Encoder Postition ", center.getCurrentPosition());
        telemetry.update();
        sleep(5000);
    }
}