package org.firstinspires.ftc.teamcode.libraries;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class subsystem {
    HardwareMap hwMap;
    // hardware mapping must be done in run op mode so this allows us to easier control our objects
    public subsystem(){
        hwMap = null;
    }
    public subsystem(HardwareMap hardwareMap){
        hwMap = hardwareMap;
    }
}
