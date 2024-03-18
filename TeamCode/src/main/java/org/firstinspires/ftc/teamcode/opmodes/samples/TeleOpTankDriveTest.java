package org.firstinspires.ftc.teamcode.opmodes.samples;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.actuators.drivetrains.TankDrive;
import org.firstinspires.ftc.teamcode.subsystems.actuators.base.Motor;

/**
 * TeleOp code to test hdrive
 */
@TeleOp
public class TeleOpTankDriveTest extends LinearOpMode {
    public static Motor frontLeft, frontRight, backLeft, backRight;

    public void runOpMode() {
        // declare motors
        frontLeft = new Motor("frontLeft", hardwareMap, "reverse");
        frontRight = new Motor("frontRight", hardwareMap);
        backLeft = new Motor("backLeft", hardwareMap, "reverse");
        backRight = new Motor("backRight", hardwareMap);

        TankDrive robot = new TankDrive(new Motor[]{frontLeft, frontRight, backLeft, backRight});
        while (opModeIsActive()) {
            double left = gamepad1.left_stick_y;
            double right = gamepad1.right_stick_y;

            robot.teleOpDrive(left, right);
        }
    }
}