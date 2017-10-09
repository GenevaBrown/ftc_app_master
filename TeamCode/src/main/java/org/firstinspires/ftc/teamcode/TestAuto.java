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
        double initJewelSwiperPos = jewelSwiper.getPosition();
        telemetry.addData("Encoder Position L ", left.getCurrentPosition());
        telemetry.addData("Encoder Postition C ", center.getCurrentPosition());
        telemetry.addData("Encoder Position Rt ", right.getCurrentPosition());
        telemetry.update();
        //int i = 0;
        jewelSwiper.setPosition(0);


        while (opModeIsActive()) {
            double jewelSwiperCurrentPos = jewelSwiper.getPosition();
           /* telemetry.addData("Position", left.getCurrentPosition());
            telemetry.update();*/
            jewelSwiper.setPosition(1);

            sleep(2000);

            if (isJewelRed() == true) {
                goDistanceCenter(3, -.5);
                break;
            } else if (isJewelRed() == false) {
                goDistanceCenter(3, .5);
                break;
            }
            jewelSwiper.setPosition(0);
            jewelSwiperCurrentPos = jewelSwiper.getPosition();
        }




        telemetry.addData("Encoder Position Lt ", left.getCurrentPosition());
        telemetry.addData("Encoder Postition C ", center.getCurrentPosition());
        telemetry.addData("Encoder Postition Rt ", right.getCurrentPosition());
        telemetry.update();
        //sleep(5000);
    }
}
