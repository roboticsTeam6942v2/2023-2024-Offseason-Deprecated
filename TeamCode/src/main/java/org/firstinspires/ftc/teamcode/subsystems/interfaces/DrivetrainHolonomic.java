package org.firstinspires.ftc.teamcode.subsystems.interfaces;

import androidx.annotation.NonNull;

public interface DrivetrainHolonomic extends DrivetrainMotorControls {
    public void teleOpDrive(double y, double rx, double x);

    void teleOpDrive(double y, double rx, double x, double speed);

    public void drive(@NonNull String direction, double inches, double speed);
}
