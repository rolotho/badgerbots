package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class HardwareDemo {
    // Create motors/servos in code
    public DcMotor right;
    public DcMotor left;
    public Servo clawServo;
    
    public static double maxSpeed = 1;

    private static HardwareDemo myinstance = null;
    public static  HardwareDemo getInstance() {
        if (myinstance == null) {
            myinstance = new HardwareDemo();
        }
        return myinstance;
    }
    // Intialize motors
    public void init(HardwareMap hwMap) {
        right = hwMap.get(DcMotor.class, "right");
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setPower(0);

        left = hwMap.get(DcMotor.class, "left");
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setDirection(DcMotorSimple.Direction.REVERSE);
        left.setPower(0);
        // Initialize servo
        clawServo = hwMap.get(Servo.class, "ClawServo");
    }
    // Call both motors at once
    public void setPower(double motor1, double motor2) {
        right.setPower(Range.clip(motor1, -maxSpeed, maxSpeed));
        left.setPower(Range.clip(motor2, -maxSpeed, maxSpeed));
    }
}
