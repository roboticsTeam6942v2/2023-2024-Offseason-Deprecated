package org.firstinspires.ftc.teamcode.subsystems.interfaces;

import androidx.annotation.NonNull;

public interface DrivetrainHolonomic extends DrivetrainMotorControls {

    /**
     * Set power to motors for teleOp driving
     * @param y Driving
     * @param rx Rotation
     * @param x Strafing
     */
    public void teleOpDrive(double y, double rx, double x);

    /**
     * Set power to motors for teleOp driving, allows for adjustment to speed
     *
     * @param y     Driving
     * @param rx    Rotation
     * @param x     Strafing
     * @param speed Speed reduction, higher reduction means slower speed
     */
    void teleOpDrive(double y, double rx, double x, double speed);

    /**
     * Driving method used for autonomous using case switch, distance, and power
     * @param direction Direction to drive
     * @param inches Distance using inches
     * @param speed Power (between -1 and 1)
     */
    public void drive(@NonNull String direction, double inches, double speed);
}
