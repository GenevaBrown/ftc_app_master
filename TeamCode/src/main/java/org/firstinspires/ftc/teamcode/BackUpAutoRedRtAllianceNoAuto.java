package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by HP 15t-as100 on 9/25/2017.
 */
@Autonomous (name = "AutoRedRtAllianceNoAuto", group = "12596")
public class BackUpAutoRedRtAllianceNoAuto extends AutoMode {

    @Override


 /*   void runAutoMode() {
        turnToHeading(180);
    }*/
    void runAutoMode () {
        double initJewelSwiperPos = jewelSwiper.getPosition();
        int glyphPosition = -1;
        telemetry.addData("Encoder Position L ", left.getCurrentPosition());
        telemetry.addData("Encoder Postition C ", center.getCurrentPosition());
        telemetry.addData("Encoder Position Rt ", right.getCurrentPosition());
        telemetry.update();
        //int i = 0;
        jewelSwiper.setPosition(0);
        //double IMUInitPitch = IMU.getPitch();
        lHDrive.setPosition(0.5);
        rHDrive.setPosition(0.5);
        colorSensor.enableLed(false);

            double jewelSwiperCurrentPos = jewelSwiper.getPosition();
            //double IMUpitch = IMU.getPitch();
           /* telemetry.addData("Position", left.getCurrentPosition());
            telemetry.update();*/
            jewelSwiper.setPosition(.53);
           // if (getDecodedColumn)

            sleep(1500);

            if (isJewelRed() == true) {
                goDistanceCenter(45, .95);
                jewelSwiper.setPosition(1);
                sleep(1000);
                goDistance(20, .9, true);
                goDistanceCenter(40, .7);
                stop();

            } else {
                goDistanceCenter(20, -.85);
                jewelSwiper.setPosition(1);
                sleep(1000);
                goDistance(20, .9, true);
                goDistanceCenter(7, -.7);
                goDistance(15, -.7, true);
                stop();

            jewelSwiperCurrentPos = jewelSwiper.getPosition();
        }




        telemetry.addData("Encoder Position Lt ", left.getCurrentPosition());
        telemetry.addData("Encoder Postition C ", center.getCurrentPosition());
        telemetry.addData("Encoder Postition Rt ", right.getCurrentPosition());
        telemetry.update();
        //sleep(5000);
    }
}
