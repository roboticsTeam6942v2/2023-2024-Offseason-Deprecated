package org.firstinspires.ftc.teamcode.subsystems;

import static java.lang.Math.*;

/**
 * Constants Object, used to keep anything that wont change over the season, or will change and gets called from many classes
 */
public class Constants {

    // drivetrain info
    /**
     * Diameter of wheel, used to do math for the autonomous drivetrains
     */
    final double diameter_dt=4; // wheel diameter
    /**
     * Gear ratio of motor, used to do math for the autonomous drivetrains
     */
    final int motor_ratio_dt=(int)(3.16*2.89); // ratio on motor
    /**
     * Gear ratio of motor, used to do math for the autonomous drivetrains
     */
    final double gear_ratio_up_dt=1; // gears on driveshaft, direct drive = 1
    final double gear_ratio_down_dt=1; // gears on driveshaft, direct drive = 1

    // to calculate distances
    // assuming its a hall effect encoder then *28 accounts for the rises and falls for the channels
    final double conversion_factor_dt = getConversionFactorDT();
//    final double conversion_factor_linear_slide = getConversionFactorLinearSlide();

    /**
     * Returns the conversion factor from inches to ticks of our current drivetrain
     * @return Conversion of inches to ticks
     */
    public double getConversionFactorDT() {
        try {
            return ((motor_ratio_dt*gear_ratio_up_dt*28)/gear_ratio_down_dt)/(diameter_dt*PI);
        }
        catch (ArithmeticException e) {
            return (motor_ratio_dt*28)/(diameter_dt*PI);
        }
    }

    /*
    public double getConversionFactorLinearSlide() {
        double stringperrotation=spool_diameter!=0?spool_diameter:toIN(spool_displacement, displacement_units);
        try {
            return ((motor_ratio_linear_slide*gear_ratio_up_linear_slide*28)/gear_ratio_down_linear_slide)/(stringperrotation*PI);
        }
        catch (ArithmeticException e) {
            return (motor_ratio_linear_slide*28)/(stringperrotation*PI);
        }
    } */
}
