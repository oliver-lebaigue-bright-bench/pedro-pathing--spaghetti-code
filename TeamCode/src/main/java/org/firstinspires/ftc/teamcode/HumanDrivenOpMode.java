package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Main Drive", group = "TeleOp")
public class HumanDrivenOpMode extends LinearOpMode {

    // Launcher PEDF (PIDF) Velocity Tuning Constants (Defined directly in script)
    private static final double LAUNCHER_P = 0.0025; // Increased for snappier low-to-high speed acceleration
    private static final double LAUNCHER_I = 0.0001;
    private static final double LAUNCHER_D = 0.00005;
    private static final double LAUNCHER_F = 0.00040; // Feedforward factor

    private static final double LAUNCHER_SPEED_LOW = 1500.0;  // Ticks per second
    private static final double LAUNCHER_SPEED_HIGH = 2800.0; // Ticks per second

    // Define motors and toggle states
    DcMotor left, right, intake;
    DcMotorEx launcher;
    Servo intake_servo;

    boolean intakeOn = false, intake_servoBoolean;
    boolean lastCross = false, lastSquare = false, lastDpadUp = false, lastDpadDown = false;
    boolean robotWasMoving = false;
    
    // Launcher speed state
    double launcherTargetVelocity = 0.0;
    double integralError = 0.0;
    double lastError = 0.0;
    ElapsedTime pidTimer = new ElapsedTime();

    @Override
    public void runOpMode() {
        // Map hardware components from configuration
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        intake = hardwareMap.get(DcMotor.class, "intake");
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        intake_servo = hardwareMap.get(Servo.class, "left_hand");

        // Reverse right motor so both sides drive forward together
        right.setDirection(DcMotor.Direction.REVERSE);

        // Configure launcher direction and active braking
        launcher.setDirection(DcMotor.Direction.REVERSE); // Fixes spinning the wrong way (change to FORWARD if needed)
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // Stops instantly when power is 0 (no coasting)

        // Run without encoder mode for custom software PIDF velocity control loop
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Ensure all motors are 0 power by default
        left.setPower(0);
        right.setPower(0);
        intake.setPower(0);
        launcher.setPower(0);
        intake_servo.setPosition(0.1);

        // Wait for the driver station start button to be pressed
        waitForStart();
        pidTimer.reset();

        while (opModeIsActive()) {
            // Driving controls on left joystick (stick y for forward/reverse, stick x for turn)
            double drive = -gamepad1.left_stick_y;
            double turn = -gamepad1.left_stick_x;

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

            // Intake servo toggle using square button (edge detection)
            if (gamepad1.square && !lastSquare) {
                intake_servoBoolean = !intake_servoBoolean;
            }
            lastSquare = gamepad1.square;

            // Launcher speed control using dpad_up (increase) and dpad_down (decrease)
            if (gamepad1.dpad_up && !lastDpadUp) {
                if (launcherTargetVelocity == 0.0) {
                    launcherTargetVelocity = LAUNCHER_SPEED_LOW;
                } else if (launcherTargetVelocity == LAUNCHER_SPEED_LOW) {
                    launcherTargetVelocity = LAUNCHER_SPEED_HIGH;
                }
            }

            if (gamepad1.dpad_down && !lastDpadDown) {
                if (launcherTargetVelocity == LAUNCHER_SPEED_HIGH) {
                    launcherTargetVelocity = LAUNCHER_SPEED_LOW;
                } else if (launcherTargetVelocity == LAUNCHER_SPEED_LOW) {
                    launcherTargetVelocity = 0.0;
                }
            }

            lastDpadUp = gamepad1.dpad_up;
            lastDpadDown = gamepad1.dpad_down;

            // Apply power to intake
            intake.setPower(intakeOn ? -0.55 : 0);

            // Compute delta time for PIDF
            double elapsedSeconds = pidTimer.seconds();
            pidTimer.reset();
            double dt = (elapsedSeconds <= 0.0 || elapsedSeconds > 0.1) ? 0.02 : elapsedSeconds;

            // Software PEDF (PIDF) Velocity Controller
            double currentVelocity = launcher.getVelocity();
            double error = launcherTargetVelocity - currentVelocity;

            integralError += error * dt;
            integralError = Range.clip(integralError, -500.0, 500.0);

            double derivativeError = (error - lastError) / dt;
            lastError = error;

            double feedforward = launcherTargetVelocity * LAUNCHER_F;
            double proportional = error * LAUNCHER_P;
            double integral = integralError * LAUNCHER_I;
            double derivative = derivativeError * LAUNCHER_D;

            double rawPower = feedforward + proportional + integral + derivative;
            double launcherPower;

            // If target velocity is 0, stop motor completely using BRAKE mode
            if (launcherTargetVelocity == 0.0) {
                launcherPower = 0.0;
                integralError = 0.0;
                lastError = 0.0;
            } else {
                launcherPower = Range.clip(rawPower, -1.0, 1.0);
            }

            launcher.setPower(launcherPower);

            // increase second value to go further down
            intake_servo.setPosition(intake_servoBoolean ? 0.1 : 0.65);

            telemetry.addData("Status", "Running...");
            telemetry.addData("Launcher Target Velocity", launcherTargetVelocity);
            telemetry.addData("Launcher Actual Velocity", currentVelocity);
            telemetry.addData("Launcher Motor Power", launcherPower);
            telemetry.addData("Intake On", intakeOn);
            telemetry.update();
        }
        
        // Stop all motors when opmode ends
        left.setPower(0);
        right.setPower(0);
        intake.setPower(0);
        launcher.setPower(0);
    }
}
