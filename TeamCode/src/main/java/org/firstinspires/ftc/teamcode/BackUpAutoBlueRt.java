package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by HP 15t-as100 on 9/25/2017.
 */
@Autonomous (name = "MiniAutoBlueRt", group = "12596")
public class BackUpAutoBlueRt extends AutoMode {

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

        while (opModeIsActive()) {
            double jewelSwiperCurrentPos = jewelSwiper.getPosition();
            //double IMUpitch = IMU.getPitch();
           /* telemetry.addData("Position", left.getCurrentPosition());
            telemetry.update();*/
            jewelSwiper.setPosition(.5);
           // if (getDecodedColumn)

            sleep(1500);

            if (isJewelRed() == true) {
                goDistanceCenter(10, -.9);
                jewelSwiper.setPosition(1);
                sleep (1000);
                goDistanceCenter(50, -1);

            } else if (isJewelRed() == false) {
                goDistanceCenter(2.5, .35);
                sleep(1000);
                jewelSwiper.setPosition(1);
                sleep(1000);
                goDistanceCenter(70, -1);
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