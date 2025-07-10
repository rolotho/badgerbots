package org.firstinspires.ftc.teamcode.badgerbots;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Auto Red")
public class AutoDemo extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    HardwareDemo robot = HardwareDemo.getInstance();
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Hello drivers!");
        telemetry.update();

        waitForStart();
        encoderMove(20.5, 0.7);
        turning(100, 0.4);
        encoderMove(20.5, 0.7);
    }
    public void  encoderMove(double distance, double speed) {
        double wheelCircumference = 3.78 * Math.PI;
        double wheelMotor = 537.7;
        double ticks = (distance * (wheelMotor / wheelCircumference));

        robot.left.setTargetPosition((int) Math.round(ticks));
        robot.right.setTargetPosition((int) Math.round(ticks));

        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(speed, speed);

        while (opModeIsActive() && (robot.left.isBusy())) {

        }
        robot.setPower(0, 0);

        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void turning(int ticks, double speed) {
        robot.left.setTargetPosition(ticks);
        robot.right.setTargetPosition(-ticks);

        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.setPower(speed, -speed);

        while (opModeIsActive() && (robot.left.isBusy())) {

        }
        robot.setPower(0, 0);

        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}