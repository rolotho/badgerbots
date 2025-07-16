package org.firstinspires.ftc.teamcode.badgerbots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

//sigma
@TeleOp(name = "TeleOp")

public class TeleOp336 extends LinearOpMode {
    Hardware robot = Hardware.getInstance();
    ElapsedTime runtime = new ElapsedTime();
    public void geckoArmUp() {
        robot.geckoArm.setPosition(0.622);
    }
    public void geckoArmDown() {
        robot.geckoArm.setPosition(0.333);
    }

    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello, Drivers!");
        telemetry.update();

        boolean pressingX = false;
        boolean resetRuntime = false;
        waitForStart();
        runtime.startTime();
        while (opModeIsActive()) {
            double movement = -(gamepad1.right_trigger - gamepad1.left_trigger);
            double turning = gamepad1.left_stick_x;
            double armMovement = gamepad2.left_stick_y;
            boolean geckoMovement = gamepad1.square;

            if (gamepad1.circle) {
                telemetry.addData("Circle", "!");
                telemetry.update();
                movement /= 2;
                movement /= 2;
            }
            double left = movement - turning;
            double right = movement + turning;
            double max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0) {
                left /= max;
                right /= max;
            }
            robot.right.setPower(right);
            robot.left.setPower(left);

            if (gamepad2.left_stick_y >= 0.1) {
                robot.arm.setPower(0.75);
            } else if (gamepad2.left_stick_y <= -0.1) {
                robot.arm.setPower(-0.75);
            } else {
                robot.arm.setPower(0);
            }

            if (!gamepad1.cross || !gamepad1.circle) {
                robot.left.setPower(left);
                robot.right.setPower(right);
            }

            if (gamepad2.y) {
                geckoArmUp();
            }
            else if (gamepad2.x) {
                geckoArmDown();
            }
            else {
                geckoArmUp();
            }

            if (gamepad2.left_trigger >= 0.1) {
                //opens claw
                robot.clawServoLeft.setPosition(0.215);
                robot.clawServoRight.setPosition(0.697);
            } else if (gamepad2.right_trigger >= 0.1) {
                //closes claw
                robot.clawServoLeft.setPosition(0.362);
                robot.clawServoRight.setPosition(0.563);
            }
        }
    }
}
