package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Main Drive", group = "TeleOp")
public class HumanDrivenOpMode extends LinearOpMode {

    // Define motors and toggle states
    DcMotor left, right, intake, launcher;
    boolean intakeOn = false, launcherOn = false;
    boolean lastCross = false, lastCircle = false;
    boolean robotWasMoving =  false;

    @Override
    public void runOpMode() {
        // Map hardware components from configuration
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        intake = hardwareMap.get(DcMotor.class, "intake");
        launcher = hardwareMap.get(DcMotor.class, "launcher");

        // Reverse right motor so both sides drive forward together
        right.setDirection(DcMotor.Direction.REVERSE);

        // Ensure all motors are 0 power by default
        left.setPower(0);
        right.setPower(0);
        intake.setPower(0);
        launcher.setPower(0);

        // Wait for the driver station start button to be pressed
        waitForStart();

        while (opModeIsActive()) {
            // Driving controls on left joystick (stick y for forward/reverse, stick x for turn)
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.left_stick_x;

            if (Math.abs(gamepad1.left_stick_x) > 0.05 || Math.abs(gamepad1.left_stick_y) > 0.05) {
                left.setPower(Range.clip(drive + turn, -1.0, 1.0));
                right.setPower(Range.clip(drive - turn, -1.0, 1.0));
                robotWasMoving = true;
            } else if (robotWasMoving) {
                left.setPower(0);
                right.setPower(0);
                robotWasMoving = false;
            }

            // Intake toggle using cross button (edge detection)
            if (gamepad1.cross && !lastCross) {
                intakeOn = !intakeOn;
            }
            lastCross = gamepad1.cross;

            // Launcher toggle using circle button (edge detection)
            if (gamepad1.circle && !lastCircle) {
                launcherOn = !launcherOn;
            }
            lastCircle = gamepad1.circle;

            // Apply power to intake and launcher based on toggle states
            intake.setPower(intakeOn ? 1.0 : 0.0);
            launcher.setPower(launcherOn ? 1.0 : 0.0);

            telemetry.addData("status", "running...");
            telemetry.update();
        }
        
        // Stop all motors when opmode ends
        left.setPower(0);
        right.setPower(0);
        intake.setPower(0);
        launcher.setPower(0);
    }
}
