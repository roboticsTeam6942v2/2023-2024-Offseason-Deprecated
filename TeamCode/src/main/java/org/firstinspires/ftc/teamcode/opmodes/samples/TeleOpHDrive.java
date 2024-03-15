package org.firstinspires.ftc.teamcode.opmodes.samples;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.actuators.drivetrains.HDrive;
import org.firstinspires.ftc.teamcode.subsystems.actuators.base.Motor;

/**
 * TeleOp code to test hdrive
 */
@TeleOp
public class TeleOpMecanum extends LinearOpMode{
    public static Motor frontLeft, frontRight, backLeft, backRight, midshift;

    public void runOpMode() {
        // declare motors
        frontLeft = new Motor("frontLeft", hardwareMap, "reverse");
        frontRight = new Motor("frontRight", hardwareMap);
        backLeft = new Motor("backLeft", hardwareMap, "reverse");
        backRight = new Motor("backRight", hardwareMap);
        midshift = new Motor("midshift", hardwareMap);

        HDrive robot = new HDrive(new Motor[]{frontLeft, frontRight, backLeft, backRight}, midshift);
        while (opModeIsActive()) {
            double drive = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            teleOpDrive(drive, turn, strafe);

            /**
             * speed reduction
             */
            if(gamepad1.start){
                teleOpDrive(drive, turn, strafe, 3);
            }
        }
    }
}