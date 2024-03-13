package org.firstinspires.ftc.teamcode.opmodes.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.actuators.drivetrains.Mecanum;
import org.firstinspires.ftc.teamcode.subsystems.actuators.base.Motor;

@Autonomous
public class AutonomousBasic extends LinearOpMode {
    public static Motor frontLeft, frontRight, backLeft, backRight;

    public void runOpMode() {
        // declare motors
        frontLeft = new Motor("frontLeft", hardwareMap, "reverse");
        frontRight = new Motor("frontRight", hardwareMap);
        backLeft = new Motor("backLeft", hardwareMap, "reverse");
        backRight = new Motor("backRight", hardwareMap);

        // accessing motors in drivetrain any other way but through the mecanum class after this line will lead to an error
        Mecanum robot = new Mecanum(new Motor[]{frontLeft, frontRight, backLeft, backRight});
        while (opModeIsActive()) {
            // drive forward 12 inches at a speed of 1 (full speed)
            robot.drive("f", 12, 1);
        }
    }
}
