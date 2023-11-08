package org.firstinspires.ftc.teamcode.teleOP;

import java.lang.Math;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp
public class Test extends LinearOpMode {

    final static int DT = (int)(3.61*2.89*28); // input gear ratio math
    public static DcMotorEx frontLeft, frontRight,backLeft,backRight,arm,leftChain,rightChain,rotator;
    public static Servo endEffector;
    private Orientation lastAngles = new Orientation();
    double globalAngle;
    IMU imu;

    final float CHAIN_SENSITIVITY = 5; // subject ot change
    final float ARM_SENSITIVITY = 5; // also subject to change

    public void runOpMode() {

        ElapsedTime t = new ElapsedTime();

        arm = hardwareMap.get(DcMotorEx.class, "arm");
        arm.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        SAR("arm");
        RUE("arm");
        STP("arm",0);
        RTP("arm");
        leftChain = hardwareMap.get(DcMotorEx.class, "leftChain");
        rightChain = hardwareMap.get(DcMotorEx.class, "rightChain");
        leftChain.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightChain.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightChain.setDirection(DcMotorEx.Direction.REVERSE);
        SAR("chain");
        RUE("chain");
        STP("chain",0);
        RTP("chain");
        endEffector = hardwareMap.get(Servo.class, "endEffector");
        rotator = hardwareMap.get(DcMotorEx.class, "rotator");
        rotator.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);
        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        SAR("dt");
        RUE("dt");
        STP("dt", 0);
        RTP("dt");
        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");

        telemetry.addData("Press Start When Ready", "");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x*1.1; // increase if when put diagnal it goes more foward/backward, if strafe more then decrease
            double rx = gamepad1.right_stick_x;

            // maintain ratio in case of range clip
            double denominator = Math.max(Math.abs(y)+Math.abs(x)+Math.abs(rx),1);
            frontLeft.setPower((y+x+rx)/denominator);
            backLeft.setPower((y-x+rx)/denominator);
            frontRight.setPower((y-x-rx)/denominator);
            backRight.setPower((y+x-rx)/denominator);

//            STP("arm",(int)(arm.getCurrentPosition()+(-gamepad2.right_stick_y*CHAIN_SENSITIVITY)));
            STP("chain",(int)(getChainPos()+(gamepad2.right_trigger*CHAIN_SENSITIVITY)-(gamepad2.left_trigger*CHAIN_SENSITIVITY)));
            if (leftChain.getTargetPosition()>getChainPos()) {
                SP("chain",.7);
            } else if (leftChain.getTargetPosition()<=getChainPos()) {
                SP("chain",.5);
            }
            if (leftChain.getTargetPosition()<1000) { // limits unknown and need to be set
                STP("chain",1000);
            } else if (leftChain.getTargetPosition()<0) {
                STP("chain",0);
            }
            RTP("chain");

            STP("arm",(int)(arm.getCurrentPosition()+(-gamepad2.left_stick_y*ARM_SENSITIVITY)));
            if (arm.getTargetPosition()>arm.getCurrentPosition()) {
                SP("arm",.8);
            } else if (arm.getTargetPosition()<=arm.getCurrentPosition()) {
                SP("arm",.6);
            }
            if (arm.getTargetPosition()<800) { // limits unknown and need to be set
                STP("arm",800);
            } else if (arm.getTargetPosition()<0) {
                STP("arm",0);
            }
            RTP("arm");


        }
    }
    public static void SP (@NonNull String m, double p) {
        switch(m){
            case"fl":frontLeft.setPower(p);break;
            case"fr":frontRight.setPower(p);break;
            case"bl":backLeft.setPower(p);break;
            case"br":backRight.setPower(p);break;
            case"f":frontLeft.setPower(p);frontRight.setPower(p);break;
            case"b":backLeft.setPower(p);backRight.setPower(p);break;
            case"l":frontLeft.setPower(p);backLeft.setPower(p);break;
            case"r":frontRight.setPower(p);backRight.setPower(p);break;
            case"dt":frontLeft.setPower(p);frontRight.setPower(p);backLeft.setPower(p);backRight.setPower(p);break;
            case"chain":leftChain.setPower(p);leftChain.setPower(p);break;
            case"arm":arm.setPower(p);break;
        }
    }
    public static void STP (@NonNull String m, int tp) {
        switch(m){
            case"fl":frontLeft.setTargetPosition(tp);break;
            case"fr":frontRight.setTargetPosition(tp);break;
            case"bl":backLeft.setTargetPosition(tp);break;
            case"br":backRight.setTargetPosition(tp);break;
            case"f":frontLeft.setTargetPosition(tp);frontRight.setTargetPosition(tp);break;
            case"b":backLeft.setTargetPosition(tp);backRight.setTargetPosition(tp);break;
            case"l":frontLeft.setTargetPosition(tp);backLeft.setTargetPosition(tp);break;
            case"r":frontRight.setTargetPosition(tp);backRight.setTargetPosition(tp);break;
            case"dt":frontLeft.setTargetPosition(tp);frontRight.setTargetPosition(tp);backLeft.setTargetPosition(tp);backRight.setTargetPosition(tp);break;
            case"chain":leftChain.setTargetPosition(tp);rightChain.setTargetPosition(tp);break;
            case"arm":arm.setTargetPosition(tp);break;
        }
    }
    public static void RTP (@NonNull String m) {
        switch(m){
            case"fl":frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"fr":frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"bl":backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"br":backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"f":frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"b":backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"l":frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"r":frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"dt":frontLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);frontRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);backLeft.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);backRight.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"chain":leftChain.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);rightChain.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
            case"arm":arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);break;
        }
    }
    public static void SAR (@NonNull String m) {
        switch(m){
            case"fl":frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"fr":frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"bl":backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"br":backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"f":frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"b":backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"l":frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"r":frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"dt":frontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);frontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);backLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);backRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"chain":leftChain.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);rightChain.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
            case"arm":arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);break;
        }
    }
    public static void RWE (@NonNull String m) {
        switch(m){
            case"fl":frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"fr":frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"bl":backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"br":backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"f":frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"b":backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"l":frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"r":frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"dt":frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"chain":leftChain.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);rightChain.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
            case"arm":arm.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);break;
        }
    }
    public static void RUE (@NonNull String m) {
        switch(m){
            case"fl":frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"fr":frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"bl":backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"br":backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"f":frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"b":backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"l":frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"r":frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"dt":frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"chain":leftChain.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);rightChain.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
            case"arm":arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);break;
        }
    }
    public static void ST(int i) {
        frontLeft.setTargetPositionTolerance(i);
        backLeft.setTargetPositionTolerance(i);
        backRight.setTargetPositionTolerance(i);
        frontRight.setTargetPositionTolerance(i);
    }
    public static int getChainPos () {
        return Math.round((leftChain.getCurrentPosition()+rightChain.getCurrentPosition())/2);
    }

    public void drive (@NonNull String direction, double inches, double speed){
        SAR("dt");
        RUE("dt");
        switch(direction){
            case"f":
                STP("dt",inTT_dt(inches));
                SP("dt",speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){}
                SP("dt",0);
            case"b":
                STP("dt",inTT_dt(-inches));
                SP("dt",speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){}
                SP("dt",0);
            case"l":
                STP("fl",inTT_dt(-inches));
                STP("fr",inTT_dt(inches));
                STP("bl",inTT_dt(inches));
                STP("br",inTT_dt(-inches));
                SP("dt",speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){}
                SP("dt",0);
            case"r":
                STP("fl",inTT_dt(inches));
                STP("fr",inTT_dt(-inches));
                STP("bl",inTT_dt(-inches));
                STP("br",inTT_dt(inches));
                SP("dt",speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){}
                SP("dt",0);
            case"fr":
                STP("fl",inTT_dt(inches));
                STP("fr",inTT_dt(0));
                STP("bl",inTT_dt(0));
                STP("br",inTT_dt(inches));
                SP("dt",speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){}
                SP("dt",0);
            case"bl":
                STP("fl",inTT_dt(-inches));
                STP("fr",inTT_dt(0));
                STP("bl",inTT_dt(0));
                STP("br",inTT_dt(-inches));
                SP("dt",speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){}
                SP("dt",0);
            case"fl":
                STP("fl",inTT_dt(0));
                STP("fr",inTT_dt(inches));
                STP("bl",inTT_dt(inches));
                STP("br",inTT_dt(0));
                SP("dt",speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){}
                SP("dt",0);
            case"br":
                STP("fl",inTT_dt(0));
                STP("fr",inTT_dt(-inches));
                STP("bl",inTT_dt(-inches));
                STP("br",inTT_dt(0));
                SP("dt",speed);
                RTP("dt");
                while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){}
                SP("dt",0);
        }
    }
    public static int inTT_dt(double inches){ // inches to ticks for DT
        return(int)(DT*inches);
    }

    public void resetAngle() {
        lastAngles = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        globalAngle = 0;
    }
    public double getAngle() {
        // imu works in eulear angles so we have to detect when it rolls across the backwards 180 threshold
        Orientation angles = imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double change = angles.firstAngle - lastAngles.firstAngle;

        if (change < -180) {
            change += 360;
        } else if (change > 180) {
            change -= 360;
        }
        globalAngle += change;
        lastAngles = angles;
        return globalAngle;
    }
}
