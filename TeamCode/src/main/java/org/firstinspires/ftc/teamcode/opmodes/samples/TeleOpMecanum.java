package org.firstinspires.ftc.teamcode.opmodes.samples;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.subsystems.actuators.drivetrains.Mecanum;
import org.firstinspires.ftc.teamcode.subsystems.actuators.base.Motor;
import org.firstinspires.ftc.teamcode.subsystems.sensors.Imu;

/**
 * TeleOp code to test mecanum drive
 */
@TeleOp
public class TeleOpMecanum extends LinearOpMode {
    private static Motor frontLeft, frontRight, backLeft, backRight;
    private Imu imu;
    private boolean robotOriented = true, fast = true;

    public void runOpMode() {
        // declare motors
        frontLeft = new Motor("frontLeft", hardwareMap, "reverse");
        frontRight = new Motor("frontRight", hardwareMap);
        backLeft = new Motor("backLeft", hardwareMap, "reverse");
        backRight = new Motor("backRight", hardwareMap);

        imu = new Imu("imu", hardwareMap, RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

        Mecanum robot = new Mecanum(new Motor[]{frontLeft, frontRight, backLeft, backRight});
        while (opModeIsActive()) {
            // can change these if needed
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            if (robotOriented) {
                double botHeading = imu.getAngle(AngleUnit.RADIANS);
                double rotX = strafe * Math.cos(-botHeading) - drive * Math.sin(-botHeading);
                double rotY = strafe * Math.sin(-botHeading) + drive * Math.cos(-botHeading);

                rotX = rotX * 1.1;

                if (fast) {
                    robot.teleOpDrive(rotY, turn, rotX);
                } else {
                    robot.teleOpDrive(rotY, turn, rotX, 3);
                }
            } else if (fast) {
                robot.teleOpDrive(drive, turn, strafe);
            } else {
                robot.teleOpDrive(drive, turn, strafe, 3);
            }

            if (gamepad1.start && fast) {
                fast = false;
            } else if (gamepad1.start && !fast) {
                fast = true;
            }

            if (gamepad1.back && robotOriented) {
                robotOriented = false;
            } else if (gamepad1.back && !robotOriented) {
                robotOriented = true;
            }
        }
    }
}