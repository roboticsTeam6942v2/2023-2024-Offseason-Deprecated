package org.firstinspires.ftc.teamcode.subsystems.sensors;

import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.libraries.subsystem;

// creating this as a base for the future, i can't think of any methods that would need to be added
// but at least having something to work with in the future is useful

/**
 * Methods to streamline the usage of Servos
 */
public class Servo extends subsystem{
    private Servo servo;

    /**
     * Servo object
     * @param name Name of the servo in the code
     * @param hwMap Name of the servo in the phone
     */
    public Servo(String name, @NonNull HardwareMap hwMap){
        servo = hwMap.get(Servo.class, name);
    }

    /**
     * Sets the servo's position to zero
     */
    public void zero(){
        servo.setPosition(0);
    }
}