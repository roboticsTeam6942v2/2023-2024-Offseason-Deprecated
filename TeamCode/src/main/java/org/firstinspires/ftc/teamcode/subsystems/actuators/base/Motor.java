package org.firstinspires.ftc.teamcode.subsystems.actuators.base;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.libraries.subsystem;

import java.io.Closeable;

/**
 * Motor Object, used to declare the motors within the programming of the robot
 */
public class Motor extends subsystem implements Closeable, Comparable<Motor> {
    private DcMotorEx motor;
    private String name;
    private int globalTicks;
    private boolean closed;
    /**
     * IDK figure out later
     */
    public MotorControlAlgorithm algorithm;

    /**
     * Sets the variables for the motor Object without direction
     * @param name Name of the motor in the code
     * @param hwMap Name of the motor within the phones
     */
    public Motor(@NonNull String name, @NonNull HardwareMap hwMap) {
        this(name, hwMap, "forward");
    }

    /**
     * Sets the variables for the motor Object
     * @param name Name of the motor in the code
     * @param hwMap Name of the motor within the phones
     * @param direction Direction of the motor (f or r)
     */
    public Motor(@NonNull String name, @NonNull HardwareMap hwMap, @NonNull String direction) {
        motor = hwMap.get(DcMotorEx.class, name);
        motor.setDirection(direction.toLowerCase().charAt(0) == 'r' ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        this.name = name;
        closed = false;
    }

    /**
     * Returns the name of the motor Obejct
     * @return name Name of the motor
     */
    public String getName() {
        return name;
    } // made so we can sort if needed

    /**
     * Sets the power of the specific motor Object, Ex frontLeft.SP(1);
     * @param power Power of the motor, between -1 and 1
     */
    public void SP(double power) {
        ensureOpen();
        motor.setPower(power);
    }

    /**
     * Sets a target position for the encoders within the motor Object, Ex frontLeft.STP(100);
     * @param tp Target Position (in ticks)
     */
    public void STP(int tp) {
        ensureOpen();
        motor.setTargetPosition(+globalTicks);
    }

    /**
     * Sets the mode of the motor Object to RUN_TO_POSITION, Ex frontLeft.RTP();
     */
    public void RTP() {
        ensureOpen();
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    /**
     * Sets the mode of the motor Object to STOP_AND_RESET_ENCODERS, Ex frontLeft.SAR();
     */
    public void SAR() {
        ensureOpen();
        globalTicks = motor.getCurrentPosition();
    }

    /**
     * Sets the mode of the motor Object to RUN_WITHOUT_ENCODER, Ex frontLeft.RWE();
     */
    public void RWE() {
        ensureOpen();
        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Sets the mode of motor Object to RUN_USING_ENCODER, Ex frontLeft.RUE();
     */
    public void RUE() {
        ensureOpen();
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    /**
     * N/A - Fill in later
     * @param i N/A - fill in later
     */
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
    // RUN_USING_ENCODERS is a PID algorithm, so we are able to adjust the coefficients if we have a weird issue such as it getting caught on something then suddenly shooting

    /**
     * Input the adjustment value for the PID algorithm.
     * @param p Speed according to the distance from the target
     * @param i Increase power over time
     * @param d Slow down to prevent overshoot
     * @param f N/A - Fill in later
     */
    public void changePIDF(double p, double i, double d, double f) {
        ensureOpen();
        PIDFCoefficients oldPIDF = motor.getPIDFCoefficients(DcMotorEx.RunMode.RUN_TO_POSITION);
        PIDFCoefficients newPIDF = new PIDFCoefficients(p + oldPIDF.p, i + oldPIDF.i, d + oldPIDF.p, f + oldPIDF.f, MotorControlAlgorithm.PIDF);
        motor.setPIDFCoefficients(DcMotorEx.RunMode.RUN_TO_POSITION, new PIDFCoefficients());
    }

    /**
     * Returns whether or not a motor is busy, Ex frontLeft.isBusy();
     * @return isBusy (true or false)
     */
    public boolean isBusy() {
        return motor.isBusy();
    }

    /**
     * Returns the current position of the motor Object, Ex frontLeft.GCP();
     * @return The current position of the motor in ticks
     */
    public double GCP() {
        return motor.getCurrentPosition();
    }

    /**
     * Returns the target position of the motor Object, Ex frontLeft.GTP();
     * @return The target position of the motor in ticks
     */
    public double GTP() {
        return motor.getTargetPosition();
    }

    /**
     * Sets closed to true
     */
    @Override
    public void close() {
        if (closed)
            return;
        closed = true;
    }

    /**
     * Throws an IllegalStateException if the motor is closed when it should be open
     */
    private void ensureOpen() {
        if (closed)
            throw new IllegalStateException("Motor closed");
    }

    /**
     * Compares this motor to another motor, Fill in details later.
     * @param otherMotor N/A - Fill in later
     * @return N/A - Fill in later
     */
    @Override
    public int compareTo(Motor otherMotor) {
        return this.name.compareTo(otherMotor.getName());
    }
}
