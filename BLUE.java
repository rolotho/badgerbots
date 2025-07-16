package org.firstinspires.ftc.teamcode.badgerbots;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Auto Blue")
public class BLUE extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = Hardware.getInstance();
    int step = 0;
    public void openClaw() {
        robot.clawServoLeft.setPosition(0.215);
        robot.clawServoRight.setPosition(0.697);
    }
    public void closeClaw() {
        robot.clawServoLeft.setPosition(0.362);
        robot.clawServoRight.setPosition(0.563);
    }
    public void geckoArmUp() {
        robot.geckoArm.setPosition(0.622);
    }
    public void geckoArmDown() {
        robot.geckoArm.setPosition(0.333);
    }

    public void runOpMode() {
        robot.init(hardwareMap);
        robot.left.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.right.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.arm.setTargetPosition(0);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.left.setTargetPosition(0);
        robot.right.setTargetPosition(0);
        robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addData("Status", "Hello drivers!");
        telemetry.setAutoClear(true);
        telemetry.update();

        // [0-1] 0.5
        waitForStart();
//        robot.geckoArm.setPower(0.6);
        while (opModeIsActive()) {
            autoScript();
            telemetry.addData("Moving to step", step);
            telemetry.update();
        }
    }
    // the actual script for Autonomous mode, made in switch case to make debugging easier
    private void autoScript() {
        switch (step) {
            case (0):
                closeClaw();
                sleep(750);
                geckoArmUp();
                step = 1;
                break;
            case (1):
                encoderMove(24, 0.6);
                sleep(750);
                geckoArmDown();
                sleep(750);
                step = 2;
                break;
            case (2):
                encoderMove(100, 0.8);
                sleep(750);
                geckoArmUp();
                step = 3;
                break;
            case (3):
                turning(725, 0.4);
                sleep(750);
                step = 4;
                break;
            case (4):
                encoderMove(88, 0.8);
                step = 5;
                break;
            case (5):
                robot.arm.setPower(1);
                robot.arm.setTargetPosition(1100);
                while (opModeIsActive() && robot.arm.isBusy()) {
                }
                robot.arm.setPower(0);
                sleep(750);
                openClaw();
                sleep(750);
                robot.arm.setPower(1);
                robot.arm.setTargetPosition(-500);
                while (opModeIsActive() && robot.arm.isBusy()) {
                }
                robot.arm.setPower(0);
                sleep(250);
                step = 7;
                break;
//            case (6):
//                turning(1750, 0.4);
//                sleep(750);
//                step = 7;
//                break;
            case (7):
                encoderMove(-76, 0.8);
                step = 8;
                break;
            case (8):
                turning(850, 0.4);
                sleep(750);
                step = 9;
                break;
            case (9):
                encoderMove(40, 0.6);
                step = 10;
                break;
            case (10):
                geckoArmDown();
                sleep (750);
                encoderMove(6, 0.4);
                step = 11;
                break;
            case (11):
                encoderMove(6, 0.2);
                step = 12;
                break;

        }
    }
    // defines distance for the robot
    public void  encoderMove(double distance, double speed) {
        double wheelCircumference = 3.77953 * Math.PI;
        double wheelMotor = 537.7;
        double ticks = (distance * (wheelMotor / wheelCircumference));

        robot.left.setTargetPosition((int) Math.round(ticks) + robot.left.getCurrentPosition());
        robot.right.setTargetPosition((int) Math.round(ticks) + robot.right.getCurrentPosition());

        robot.setPower(speed, speed);

        while (opModeIsActive() && robot.left.isBusy()) {
            telemetry.addData("target ", robot.left.getTargetPosition());
            telemetry.addData("current", robot.left.getCurrentPosition());
            telemetry.update();
        }
        robot.setPower(0, 0);

    }
    /** pos = right **/
    public void turning(int ticks, double speed) {
        robot.left.setTargetPosition(ticks + robot.left.getCurrentPosition());
        robot.right.setTargetPosition(-ticks + robot.right.getCurrentPosition());

        robot.setPower(speed, -speed);

        while (opModeIsActive() && (robot.left.isBusy()) && (robot.right.isBusy())) {
        }
        robot.setPower(0, 0);

    }
}