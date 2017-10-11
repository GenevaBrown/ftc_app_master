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
        //double IMUInitPitch = IMU.getPitch();

        while (opModeIsActive()) {
            double jewelSwiperCurrentPos = jewelSwiper.getPosition();
            //double IMUpitch = IMU.getPitch();
           /* telemetry.addData("Position", left.getCurrentPosition());
            telemetry.update();*/
            jewelSwiper.setPosition(1);

            sleep(1500);

            if (isJewelRed() == true) {
                goDistanceCenter(4, -.5);
                sleep(5000);
                jewelSwiper.setPosition(0);
                sleep (2000);
                goDistance(25, -.7);
                sleep(5000);
                goDistanceCenter(30, -1);
                sleep(1000);
                if (isLineBlue() == false) {
                    center.setPower(.8);
                } else if (isLineBlue() == true){
                    break;
                }

            } else if (isJewelRed() == false) {
                goDistanceCenter(4, .5);
                sleep(5000);
                jewelSwiper.setPosition(0);
                sleep(2000);
                goDistance(25, -.7);
                sleep(5000);
                goDistanceCenter(35, -1);
                sleep (1000);
                if (isLineBlue() == false) {
                    center.setPower(.8);
                } else if (isLineBlue() == true){
                    break;
                }
            }
            jewelSwiperCurrentPos = jewelSwiper.getPosition();
        }




        telemetry.addData("Encoder Position Lt ", left.getCurrentPosition());
        telemetry.addData("Encoder Postition C ", center.getCurrentPosition());
        telemetry.addData("Encoder Postition Rt ", right.getCurrentPosition());
        telemetry.update();
        //sleep(5000);
    }
}
