package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.libraries.subsystem;

import java.io.Closeable;


public class Motor extends subsystem implements Closeable, Comparable<Motor> {
    private DcMotorEx motor;
    private String name;
    private int globalTicks;
    private boolean closed;
    public MotorControlAlgorithm algorithm;

    public Motor(@NonNull String name, @NonNull HardwareMap hwMap) {
        this(name, hwMap, "forward");
    }

    public Motor(@NonNull String name, @NonNull HardwareMap hwMap, @NonNull String direction) {
        motor = hwMap.get(DcMotorEx.class, name);
        motor.setDirection(direction.toLowerCase().charAt(0) == 'r' ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        this.name = name;
        closed = false;
    }

    public String getName() {
        return name;
    }

    public void SP(double power) {
        ensureOpen();
        motor.setPower(power);
    }

    public void STP(int tp) {
        ensureOpen();
        motor.setTargetPosition(+globalTicks);
    }

    public void RTP() {
        ensureOpen();
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void SAR() {
        ensureOpen();
        globalTicks = motor.getCurrentPosition();
    }

    public void RWE() {
        ensureOpen();
        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void RUE() {
        ensureOpen();
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    public void ST(int i) {
        ensureOpen();
        motor.setTargetPositionTolerance(i);
    }

    // basic rules for tuning
    // p means youre going fast the further you are from your target
    // i means if you hit a rough patch or arent getting to speed we increase power over time to get there
    // d means depending on how big of a spike from the rate of change, we slow down to prevent overshoot
    // the larger the spike the more it dampens, so if youve been slow on a bump and i gets high enough to pass the bump
    // then all of a sudden in one loop youve moved 4x the degrees you normally do, d will spike up to slow you down so you dont overshoot

    // input adjustment value
    public void changePIDF(double p, double i, double d, double f) {
        ensureOpen();
        PIDFCoefficients oldPIDF = motor.getPIDFCoefficients(DcMotorEx.RunMode.RUN_TO_POSITION);
        PIDFCoefficients newPIDF = new PIDFCoefficients(p + oldPIDF.p, i + oldPIDF.i, d + oldPIDF.p, f + oldPIDF.f, MotorControlAlgorithm.PIDF);
        motor.setPIDFCoefficients(DcMotorEx.RunMode.RUN_TO_POSITION, new PIDFCoefficients());
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    public double GCP() {
        return motor.getCurrentPosition();
    }

    public double GTP() {
        return motor.getTargetPosition();
    }

    @Override
    public void close() {
        if (closed)
            return;
        closed = true;
    }

    private void ensureOpen() {
        if (closed)
            throw new IllegalStateException("Motor closed");
    }

    @Override
    public int compareTo(Motor otherMotor) {
        return this.name.compareTo(otherMotor.getName());
    }
}
