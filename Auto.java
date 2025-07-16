package org.firstinspires.ftc.teamcode.badgerbots;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Auto Red")
public class Auto extends LinearOpMode {
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
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.left.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.right.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.arm.setTargetPosition(0);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addData("Status", "Hello drivers!");
        telemetry.setAutoClear(false);
        telemetry.update();

        // [0-1] 0.5
        waitForStart();
//        robot.geckoArm.setPower(0.6);
        while (opModeIsActive()) {
            autoScript();
            telemetry.addData("Moving to step", step);
            telemetry.addData("arm pos", robot.arm.getCurrentPosition());
            telemetry.update();
        }
    }
    private void autoScript() {
        switch (step) {
            case (0):
                closeClaw();
                sleep(750);
                encoderMove(20, 0.7);
                step = 3;
                break;
//            case (1):
//                robot.geckoArm.setPower(-0.5);
//                step = 2;
//                break;
//            case (2):
//                robot.geckoArm.setPower(-0.05);
//                step = 3;
//                break;
            case (3):
                encoderMove(18, 0.7);
                step = 4;
                break;
            case (4):
                robot.geckoArm.setPower(0.25);
                sleep(750);
                turning(-835, 0.5);
                step = 5;
                break;
            case (5):
                encoderMove(21, 0.7);
                step = 6;
                break;
            case (6):
                robot.arm.setPower(1);
                robot.arm.setTargetPosition(900);
                while (opModeIsActive() && robot.arm.isBusy()) {
                }
                robot.arm.setPower(0);
                sleep(750);
                openClaw();
                step = 7;
                break;
        }
    }
    public void  encoderMove(double distance, double speed) {
        double wheelCircumference = 3.78 * Math.PI;
        double wheelMotor = 537.7;
        double ticks = (distance * (wheelMotor / wheelCircumference));

        robot.left.setTargetPosition((int) Math.round(ticks) + robot.left.getTargetPosition());
        robot.right.setTargetPosition((int) Math.round(ticks) + robot.right.getTargetPosition());



        robot.setPower(speed, speed);

        while (opModeIsActive() && (robot.left.isBusy()) && (robot.right.isBusy())) {
        }
        robot.setPower(0, 0);

    }
    /** pos = right **/
    public void turning(int ticks, double speed) {
        robot.left.setTargetPosition(ticks + robot.left.getTargetPosition());
        robot.right.setTargetPosition(-ticks + robot.right.getTargetPosition());

        robot.setPower(speed, -speed);

        while (opModeIsActive() && (robot.left.isBusy()) && (robot.right.isBusy())) {
        }
        robot.setPower(0, 0);

    }
}