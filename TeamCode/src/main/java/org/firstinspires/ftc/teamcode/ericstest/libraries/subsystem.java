package org.firstinspires.ftc.teamcode.ericstest.libraries;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class subsystem {
    HardwareMap hwMap;
    public subsystem(){
        hwMap = null;
    }
    public subsystem(HardwareMap hardwareMap){
        hwMap = hardwareMap;
    }
}
