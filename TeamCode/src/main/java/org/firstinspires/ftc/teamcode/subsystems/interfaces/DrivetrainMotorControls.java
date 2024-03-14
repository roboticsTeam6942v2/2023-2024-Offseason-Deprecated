package org.firstinspires.ftc.teamcode.subsystems.interfaces;

import androidx.annotation.NonNull;

public interface DrivetrainMotorControls {

    void SP(@NonNull String m, double p);

    void RTP(@NonNull String m);

    void STP(@NonNull String m, int tp);

    void SAR(@NonNull String m);

    void RWE(@NonNull String m);

    void RUE(@NonNull String m);
}
