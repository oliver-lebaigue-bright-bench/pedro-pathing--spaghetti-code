package org.firstinspires.ftc.robotcontroller.external;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake Down", group = "Teleop")
//@Disabled
public class intake_down extends LinearOpMode {

    double INTAKE_DOWN_POS = 0.7;

    // Define class members
    Servo   servo_left;

    @Override
    public void runOpMode() {
        servo_left = hardwareMap.get(Servo.class, "left_hand");

        // Wait for the start button
        telemetry.addData(">", "Press Start to scan Servo." );
        telemetry.update();
        waitForStart();


        // Scan servo till stop pressed.
        while(opModeIsActive()){
            servo_left.setPosition(INTAKE_DOWN_POS);
        }

        // Signal done;
        telemetry.addData(">", "Done");
        telemetry.update();
    }
}
