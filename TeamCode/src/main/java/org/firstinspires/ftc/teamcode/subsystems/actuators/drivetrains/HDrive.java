package org.firstinspires.ftc.teamcode.subsystems.actuators.drivetrains;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.libraries.subsystem;
import org.firstinspires.ftc.teamcode.subsystems.actuators.base.Motor;
import org.firstinspires.ftc.teamcode.subsystems.interfaces.DrivetrainHolonomic;

import java.util.Arrays;

public class HDrive extends subsystem implements DrivetrainHolonomic {
    private Motor frontLeft, frontRight, backLeft, backRight, midShift;

    /**
     * Creates a HDrive drive Object by putting motors into a sorted array, and declaring the odd motor out seperate
     *
     * @param motors Four base motor Objects in an array
     * @param midshift The rotated motor Object
     */
    public HDrive(Motor[] motors, Motor midshift) {
        Arrays.sort(motors); // allows us to ensure motors are in the right order no matter what order the motor array is sent in
        this.backLeft = motors[0];
        this.backRight = motors[1];
        this.frontLeft = motors[2];
        this.frontRight = motors[3];
        this.midShift = midshift;
        midshift.close();
        // close because java is pass by value not pass by reference, if we can come up with a way to pass by referance or object we can change the function to be also able to control indiviual motors using the motor class
        for (Motor motor : motors) {
            motor.close();
        }
    }

    /**
     * Set power to motors for teleOp driving
     *
     * @param y  Driving
     * @param rx Rotation
     * @param x  Strafing
     */
    @Override
    public void teleOpDrive(double y, double rx, double x) {
        RWE("all");
        frontLeft.SP(((y + rx)));
        backLeft.SP(((y + rx)));
        frontRight.SP(((y - rx)));
        backRight.SP(((y + -rx)));
        midShift.SP(x * 1.5);
    }

    /**
     * Set power to motors for teleOp driving, allows for adjustment to speed
     *
     * @param y     Driving
     * @param rx    Rotation
     * @param x     Strafing
     * @param speed Speed reduction, higher reduction means slower speed
     */
    @Override
    public void teleOpDrive(double y, double rx, double x, double speed) {
        RWE("all");
        frontLeft.SP(((y + rx)) / speed);
        backLeft.SP(((y + rx)) / speed);
        frontRight.SP(((y - rx)) / speed);
        backRight.SP(((y + -rx)) / speed);
        midShift.SP(x * 1.5 / speed);
    }

    @Override
    public void drive(@NonNull String direction, double inches, double speed) {
        
    }

    @Override
    public boolean isBusy() {
        return frontLeft.isBusy()&&frontRight.isBusy()&&backRight.isBusy()&&backLeft.isBusy()&&midShift.isBusy();
    }

    @Override
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
            case "m":
                midShift.SP(p);
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
            case "all":
                frontLeft.SP(p);
                frontRight.SP(p);
                backLeft.SP(p);
                backRight.SP(p);
                midShift.SP(p);
                break;
        }
    }

    /**
     * Sets the mode of the motor to RUN_TO_POSITION using case switch
     *
     * @param m Motor abbreviation (fl, fr, bl, br, f, b, l, r, dt, all)
     */
    @Override
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
            case "m":
                midShift.RTP();
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
            case "all":
                frontLeft.RTP();
                frontRight.RTP();
                backLeft.RTP();
                backRight.RTP();
                midShift.RTP();
                break;
        }
    }

    /**
     * Set the target position of the motors using a case switch
     *
     * @param m  Motor abbreviation (fl, fr, bl, br, f, b, l, r, dt, all)
     * @param tp Target Position in ticks
     */
    @Override
    public void STP(@NonNull String m, int tp) {
        switch (m) {
            case "fl":
                frontLeft.STP(tp);
                break;
            case "m":
                midShift.STP(tp);
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
            case "all":
                frontLeft.STP(tp);
                frontRight.STP(tp);
                backLeft.STP(tp);
                backRight.STP(tp);
                midShift.STP(tp);
                break;
        }
    }

    @Override
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
            case "m":
                midShift.SAR();
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
            case "all":
                frontLeft.SAR();
                frontRight.SAR();
                backLeft.SAR();
                backRight.SAR();
                midShift.SAR();
                break;
        }
    }

    @Override
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
            case "m":
                midShift.RWE();
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
            case "all":
                frontLeft.RWE();
                frontRight.RWE();
                backLeft.RWE();
                backRight.RWE();
                midShift.RWE();
                break;
        }
    }

    @Override
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
            case "m":
                midShift.RUE();
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
            case "all":
                frontLeft.RUE();
                frontRight.RUE();
                backLeft.RUE();
                backRight.RUE();
                midShift.RUE();
                break;
        }
    }
}
