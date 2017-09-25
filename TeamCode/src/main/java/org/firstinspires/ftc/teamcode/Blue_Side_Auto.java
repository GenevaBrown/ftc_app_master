package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

/**
 * Created by HP 15t-as100 on 9/25/2017.
 */

@Autonomous (name="Blue Side Auto", group="12596")

public class Blue_Side_Auto {

    while(opModeIsActive) {
        public boolean isBlue() { //checks to see if the jewel is blue
            NormalizedRGBA colors = colorSensor.getNormalizedColors()
            if (colors.blue > colors.red) {
                return true;
            }
            return false;
        }
    }

}
