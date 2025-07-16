package org.firstinspires.ftc.teamcode.badgerbots;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Hardware {
    // Create motors/servos in code
    public DcMotor right;
    public DcMotor left;
    public DcMotor arm;
    public Servo clawServoLeft;
    public Servo clawServoRight;
    public CRServo geckoArm;
    
    public static double maxSpeed = 1;

    private static Hardware myinstance = null;
    public static  Hardware getInstance() {
        if (myinstance == null) {
            myinstance = new Hardware();
        }
        return myinstance;
    }
    // Intialize motors
    public void init(HardwareMap hwMap) {
        right = hwMap.get(DcMotor.class, "right");
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        right.setPower(0);

        left = hwMap.get(DcMotor.class, "left");
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setPower(0);

        arm = hwMap.get(DcMotor.class, "arm");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setPower(0);
        // Initialize servo
        clawServoLeft = hwMap.get(Servo.class, "clawServoLeft");
        clawServoRight = hwMap.get(Servo.class, "clawServoRight");
        geckoArm = hwMap.get(CRServo.class, "geckoArm");

    }
    // Call both motors at once
    public void setPower(double motor1, double motor2) {
        right.setPower(Range.clip(motor1, -maxSpeed, maxSpeed));
        left.setPower(Range.clip(motor2, -maxSpeed, maxSpeed));
    }
}
