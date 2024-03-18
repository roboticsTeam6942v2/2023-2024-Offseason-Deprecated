package org.firstinspires.ftc.teamcode.opmodes.samples;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.actuators.drivetrains.Mecanum;
import org.firstinspires.ftc.teamcode.subsystems.actuators.base.Motor;

/**
 * TeleOp code to test mecanum drive
 */
@TeleOp
public class TeleOpMecanum extends LinearOpMode{
    public static Motor frontLeft, frontRight, backLeft, backRight;

    public void runOpMode() {
        // declare motors
        frontLeft = new Motor("frontLeft", hardwareMap, "reverse");
        frontRight = new Motor("frontRight", hardwareMap);
        backLeft = new Motor("backLeft", hardwareMap, "reverse");
        backRight = new Motor("backRight", hardwareMap);

        Mecanum robot = new Mecanum(new Motor[]{frontLeft, frontRight, backLeft, backRight});
        while (opModeIsActive()) {
            // can change these if needed
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            robot.teleOpDrive(drive, turn, strafe);

            /**
             * speed reduction
             */
            if(gamepad1.start){
                robot.teleOpDrive(drive, turn, strafe, 3);
            }
        }
    }
}