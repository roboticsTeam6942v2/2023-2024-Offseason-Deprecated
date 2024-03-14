package org.firstinspires.ftc.teamcode.subsystems.sensors;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.libraries.subsystem;

/**
 * Methods to streamline the usage of ColorSensors
 */
public class RGBSensor extends subsystem {
    private ColorSensor colorSensor;

    /**
     * ColorSensor Object
     * @param name Name of the ColorSensor in the code
     * @param hwMap Name of the ColorSensor in the phone
     */
    public RGBSensor(String name, @NonNull HardwareMap hwMap){
        colorSensor = hwMap.get(ColorSensor.class, name);
    }

    // may or may not need the get methods, can get rid of those if unnecessary

    /**
     * Returns red value
     * @return Red value
     */
    public double getRed(){
        return colorSensor.red();
    }

    /**
     * Returns blue value
     * @return Blue value
     */
    public double getBlue(){
        return colorSensor.blue();
    }

    /**
     * Returns green value
     * @return Green value
     */
    public double getGreen(){
        return colorSensor.green();
    }

    /**
     * Returns whether or not the red value falls within a set boundary
     * @param num1 Smaller value
     * @param num2 Larger value
     * @return Whether or not the value is in the boundary (true or false)
     */
    public boolean redBoundary(int num1, int num2){
        if (colorSensor.red() > num1 && colorSensor.red() < num2){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns whether or not the blue value falls within a set boundary
     * @param num1 Smaller value
     * @param num2 Larger value
     * @return Whether or not the value is in the boundary (true or false)
     */
    public boolean blueBoundary(int num1, int num2) {
        if (colorSensor.blue() > num1 && colorSensor.blue() < num2){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns whether or not the green value falls within a set boundary
     * @param num1 Smaller value
     * @param num2 Larger value
     * @return Whether or not the value is in the boundary (true or false)
     */
    public boolean greenBoundary(int num1, int num2){
        if (colorSensor.green() > num1 && colorSensor.green() < num2){
            return true;
        } else {
            return false;
        }
    }
}