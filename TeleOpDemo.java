package org.firstinspires.ftc.teamcode.badgerbots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.badgerbots.HardwareDemo;
//sigma
@TeleOp (name = "TeleOp Demo")

public class TeleOpDemo extends LinearOpMode {
    HardwareDemo robot = HardwareDemo.getInstance();
    ElapsedTime runtime = new ElapsedTime();
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();

        boolean pressingX = false;
        boolean resetRuntime = false;
        waitForStart();
        while (opModeIsActive()) {
            double movement = -(gamepad1.right_trigger - gamepad1.left_trigger);
            double turning = gamepad1.left_stick_x;
//            double left = movement;
            double left = movement - turning;
            double right = movement + turning;
//            double right = movement;
            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }

            //Test this after lunch;
            if (gamepad1.dpad_left) {
                if (!resetRuntime) {
                    runtime.reset();
                    resetRuntime = true;
                }
                robot.left.setPower(left-.9*runtime.seconds()/10);
                robot.right.setPower(right-.9*runtime.seconds()/10);

            } else {
                resetRuntime = false;
            }
            telemetry.addData("Power", robot.left.getPower());
            telemetry.update();
            if (gamepad1.dpad_right) {
                // make it so max speed speeds up
                robot.left.setPower(left*1.25);
                robot.right.setPower(right*1.25);
            }
            if (!gamepad1.dpad_right || !gamepad1.dpad_left) {
                robot.left.setPower(left);
                robot.right.setPower(right);
            }
            if (gamepad1.x) {
                // move servo arm for gecko wheel
            }

            if (gamepad2.x && !pressingX) {
                pressingX = true;
                // move arm
            } else if (!gamepad2.x && pressingX) {
                pressingX = false;
            }
            if (gamepad2.right_stick_x > 0.1) {

            }
            if (gamepad2.right_trigger > 0.1) {

            }
        }
    }
}
