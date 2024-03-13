package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;
import org.firstinspires.ftc.teamcode.libraries.subsystem;

import java.util.Arrays;


public class Mecanum extends subsystem {
    private Motor frontLeft, frontRight, backLeft, backRight;

    public Mecanum(Motor[] motors) {
        Arrays.sort(motors); // allows us to ensure motors are in the right order no matter what order the motor array is sent in
        this.backLeft = motors[0];
        this.backRight = motors[1];
        this.frontLeft = motors[2];
        this.frontRight = motors[3];
        // close because java is pass by value not pass by reference, if we can come up with a way to pass by referance or object we can change the function to be also able to control indiviual motors using the motor class
        for (int i = 0; i < motors.length; i++) {
            motors[i].close();
        }
    }

    public void SP(@NonNull String m, double p) {
        switch (m) {
            case "fl":
                frontLeft.SP(p);
                break;
            case "fr":
                frontRight.SP(p);
                break;
            case "bl":
                backLeft.SP(p);
                break;
            case "br":
                backRight.SP(p);
                break;
            case "f":
                frontLeft.SP(p);
                frontRight.SP(p);
                break;
            case "b":
                backLeft.SP(p);
                backRight.SP(p);
                break;
            case "l":
                frontLeft.SP(p);
                backLeft.SP(p);
                break;
            case "r":
                frontRight.SP(p);
                backRight.SP(p);
                break;
            case "dt":
                frontLeft.SP(p);
                frontRight.SP(p);
                backLeft.SP(p);
                backRight.SP(p);
                break;
        }
    }

    public void STP(@NonNull String m, int tp) {
        switch (m) {
            case "fl":
                frontLeft.STP(tp);
                break;
            case "fr":
                frontRight.STP(tp);
                break;
            case "bl":
                backLeft.STP(tp);
                break;
            case "br":
                backRight.STP(tp);
                break;
            case "f":
                frontLeft.STP(tp);
                frontRight.STP(tp);
                break;
            case "b":
                backLeft.STP(tp);
                backRight.STP(tp);
                break;
            case "l":
                frontLeft.STP(tp);
                backLeft.STP(tp);
                break;
            case "r":
                frontRight.STP(tp);
                backRight.STP(tp);
                break;
            case "dt":
                frontLeft.STP(tp);
                frontRight.STP(tp);
                backLeft.STP(tp);
                backRight.STP(tp);
                break;
        }
    }

    public void RTP(@NonNull String m) {
        switch (m) {
            case "fl":
                frontLeft.RTP();
                break;
            case "fr":
                frontRight.RTP();
                break;
            case "bl":
                backLeft.RTP();
                break;
            case "br":
                backRight.RTP();
                break;
            case "f":
                frontLeft.RTP();
                frontRight.RTP();
                break;
            case "b":
                backLeft.RTP();
                backRight.RTP();
                break;
            case "l":
                frontLeft.RTP();
                backLeft.RTP();
                break;
            case "r":
                frontRight.RTP();
                backRight.RTP();
                break;
            case "dt":
                frontLeft.RTP();
                frontRight.RTP();
                backLeft.RTP();
                backRight.RTP();
                break;
        }
    }

    public void SAR(@NonNull String m) {
        switch (m) {
            case "fl":
                frontLeft.SAR();
                break;
            case "fr":
                frontRight.SAR();
                break;
            case "bl":
                backLeft.SAR();
                break;
            case "br":
                backRight.SAR();
                break;
            case "f":
                frontLeft.SAR();
                frontRight.SAR();
                break;
            case "b":
                backLeft.SAR();
                backRight.SAR();
                break;
            case "l":
                frontLeft.SAR();
                backLeft.SAR();
                break;
            case "r":
                frontRight.SAR();
                backRight.SAR();
                break;
            case "dt":
                frontLeft.SAR();
                frontRight.SAR();
                backLeft.SAR();
                backRight.SAR();
                break;
        }
    }

    public void RWE(@NonNull String m) {
        switch (m) {
            case "fl":
                frontLeft.RWE();
                break;
            case "fr":
                frontRight.RWE();
                break;
            case "bl":
                backLeft.RWE();
                break;
            case "br":
                backRight.RWE();
                break;
            case "f":
                frontLeft.RWE();
                frontRight.RWE();
                break;
            case "b":
                backLeft.RWE();
                backRight.RWE();
                break;
            case "l":
                frontLeft.RWE();
                backLeft.RWE();
                break;
            case "r":
                frontRight.RWE();
                backRight.RWE();
                break;
            case "dt":
                frontLeft.RWE();
                frontRight.RWE();
                backLeft.RWE();
                backRight.RWE();
                break;
        }
    }

    public void RUE(@NonNull String m) {
        switch (m) {
            case "fl":
                frontLeft.RUE();
                break;
            case "fr":
                frontRight.RUE();
                break;
            case "bl":
                backLeft.RUE();
                break;
            case "br":
                backRight.RUE();
                break;
            case "f":
                frontLeft.RUE();
                frontRight.RUE();
                break;
            case "b":
                backLeft.RUE();
                backRight.RUE();
                break;
            case "l":
                frontLeft.RUE();
                backLeft.RUE();
                break;
            case "r":
                frontRight.RUE();
                backRight.RUE();
                break;
            case "dt":
                frontLeft.RUE();
                frontRight.RUE();
                backLeft.RUE();
                backRight.RUE();
                break;
        }
    }

    public void ST(int i) {
        frontLeft.ST(i);
        backLeft.ST(i);
        backRight.ST(i);
        frontRight.ST(i);
    }

    public boolean isBusy() {
        return frontLeft.isBusy() && backLeft.isBusy() && backRight.isBusy() && frontRight.isBusy();
    }

    public void drive(@NonNull String direction, double inches, double speed) {
        SAR("dt");
        RUE("dt");
        switch (direction) {
            case "f":
                STP("dt", EaseCommands.inTT_dt(inches));
                SP("dt", speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
                }
                SP("dt", 0);
            case "b":
                STP("dt", EaseCommands.inTT_dt(-inches));
                SP("dt", speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
                }
                SP("dt", 0);
            case "l":
                STP("fl", EaseCommands.inTT_dt(-inches));
                STP("fr", EaseCommands.inTT_dt(inches));
                STP("bl", EaseCommands.inTT_dt(inches));
                STP("br", EaseCommands.inTT_dt(-inches));
                SP("dt", speed);
                RTP("dt");
                while (isBusy()) {
                }
                SP("dt", 0);
            case "r":
                STP("fl", EaseCommands.inTT_dt(inches));
                STP("fr", EaseCommands.inTT_dt(-inches));
                STP("bl", EaseCommands.inTT_dt(-inches));
                STP("br", EaseCommands.inTT_dt(inches));
                SP("dt", speed);
                RTP("dt");
                while (isBusy()) {
                }
                SP("dt", 0);
            case "fr":
                STP("fl", EaseCommands.inTT_dt(inches));
                STP("fr", EaseCommands.inTT_dt(0));
                STP("bl", EaseCommands.inTT_dt(0));
                STP("br", EaseCommands.inTT_dt(inches));
                SP("dt", speed);
                RTP("dt");
                while (isBusy()) {
                }
                SP("dt", 0);
            case "bl":
                STP("fl", EaseCommands.inTT_dt(-inches));
                STP("fr", EaseCommands.inTT_dt(0));
                STP("bl", EaseCommands.inTT_dt(0));
                STP("br", EaseCommands.inTT_dt(-inches));
                SP("dt", speed);
                RTP("dt");
                while (isBusy()) {
                }
                SP("dt", 0);
            case "fl":
                STP("fl", EaseCommands.inTT_dt(0));
                STP("fr", EaseCommands.inTT_dt(inches));
                STP("bl", EaseCommands.inTT_dt(inches));
                STP("br", EaseCommands.inTT_dt(0));
                SP("dt", speed);
                RTP("dt");
                while (isBusy()) {
                }
                SP("dt", 0);
            case "br":
                STP("fl", EaseCommands.inTT_dt(0));
                STP("fr", EaseCommands.inTT_dt(-inches));
                STP("bl", EaseCommands.inTT_dt(-inches));
                STP("br", EaseCommands.inTT_dt(0));
                SP("dt", speed);
                RTP("dt");
                while (isBusy()) {
                }
                SP("dt", 0);
            default:
                return;
        }
    }
}
