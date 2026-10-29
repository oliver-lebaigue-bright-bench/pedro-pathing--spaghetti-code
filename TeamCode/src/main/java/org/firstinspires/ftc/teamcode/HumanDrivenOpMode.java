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

    // Launcher PIDF constants & speed presets
    private static final double LAUNCHER_P = 0.0025;
    private static final double LAUNCHER_I = 0.0001;
    private static final double LAUNCHER_D = 0.00005;
    private static final double LAUNCHER_F = 0.00040;

    private static final double SPEED_LOW = 1500.0;
    private static final double SPEED_HIGH = 3000.0;

    // Servo positions
    private static final double SERVO_UP = 0.1;
    private static final double SERVO_DOWN = 0.65;

    // Hardware components
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intake;
    private DcMotorEx launcher;
    private Servo intakeServo;

    // Toggle states
    private boolean intakeOn = false;
    private boolean intakeServoUp = false;
    private double targetVelocity = 0.0;

    // Edge detection for buttons
    private boolean lastCross = false;
    private boolean lastSquare = false;
    private boolean lastDpadUp = false;
    private boolean lastDpadDown = false;

    // PID controller state
    private double integralError = 0.0;
    private double lastError = 0.0;
    private final ElapsedTime pidTimer = new ElapsedTime();

    @Override
    public void runOpMode() {
        initHardware();

        waitForStart();
        pidTimer.reset();

        while (opModeIsActive()) {
            handleDriving();
            handleIntake();
            handleLauncherSpeed();
            updateLauncherPID();
            updateTelemetry();
        }

        stopRobot();
    }

    private void initHardware() {
        leftDrive = hardwareMap.get(DcMotor.class, "left");
        rightDrive = hardwareMap.get(DcMotor.class, "right");
        intake = hardwareMap.get(DcMotor.class, "intake");
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        intakeServo = hardwareMap.get(Servo.class, "left_hand");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        launcher.setDirection(DcMotor.Direction.REVERSE);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftDrive.setPower(0);
        rightDrive.setPower(0);
        intake.setPower(0);
        launcher.setPower(0);
        intakeServo.setPosition(SERVO_UP);
    }

    private void handleDriving() {
        double drive = -gamepad1.left_stick_y;
        double turn = -gamepad1.left_stick_x;
        leftDrive.setPower(drive + turn);
        rightDrive.setPower(drive - turn);
    }

    private void handleIntake() {
        if (gamepad1.cross && !lastCross) {
            intakeOn = !intakeOn;
        }
        lastCross = gamepad1.cross;

        if (gamepad1.square && !lastSquare) {
            intakeServoUp = !intakeServoUp;
        }
        lastSquare = gamepad1.square;

        intake.setPower(intakeOn ? -0.55 : 0.0);
        intakeServo.setPosition(intakeServoUp ? SERVO_UP : SERVO_DOWN);
    }

    private void handleLauncherSpeed() {
        if (gamepad1.dpad_up && !lastDpadUp) {
            if (targetVelocity == 0.0) {
                targetVelocity = SPEED_LOW;
            } else if (targetVelocity == SPEED_LOW) {
                targetVelocity = SPEED_HIGH;
            }
        }
        lastDpadUp = gamepad1.dpad_up;

        if (gamepad1.dpad_down && !lastDpadDown) {
            if (targetVelocity == SPEED_HIGH) {
                targetVelocity = SPEED_LOW;
            } else if (targetVelocity == SPEED_LOW) {
                targetVelocity = 0.0;
            }
        }
        lastDpadDown = gamepad1.dpad_down;
    }

    private void updateLauncherPID() {
        if (targetVelocity == 0.0) {
            launcher.setPower(0.0);
            integralError = 0.0;
            lastError = 0.0;
            pidTimer.reset();
            return;
        }

        double elapsedSeconds = pidTimer.seconds();
        pidTimer.reset();
        double dt = (elapsedSeconds <= 0.0 || elapsedSeconds > 0.1) ? 0.02 : elapsedSeconds;

        double currentVelocity = launcher.getVelocity();
        double error = targetVelocity - currentVelocity;

        integralError = Range.clip(integralError + error * dt, -500.0, 500.0);
        double derivative = (error - lastError) / dt;
        lastError = error;

        double power = (targetVelocity * LAUNCHER_F)
                + (error * LAUNCHER_P)
                + (integralError * LAUNCHER_I)
                + (derivative * LAUNCHER_D);

        launcher.setPower(Range.clip(power, -1.0, 1.0));
    }

    private void updateTelemetry() {
        telemetry.addData("Status", "Running...");
        telemetry.addData("Launcher Target Velocity", targetVelocity);
        telemetry.addData("Launcher Actual Velocity", launcher.getVelocity());
        telemetry.addData("Launcher Power", launcher.getPower());
        telemetry.addData("Intake Active", intakeOn);
        telemetry.update();
    }

    private void stopRobot() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        intake.setPower(0);
        launcher.setPower(0);
    }
}
