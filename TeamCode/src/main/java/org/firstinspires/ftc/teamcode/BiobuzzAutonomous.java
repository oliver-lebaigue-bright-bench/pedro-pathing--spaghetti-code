package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Biobuzz Autonomous (Tank)", group = "Autonomous")
public class BiobuzzAutonomous extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Starting pose on the field (x, y in inches, heading in radians)
        Pose2d beginPose = new Pose2d(0, 0, 0);
        
        // Initialize TankDrive with hardwareMap and starting pose
        TankDrive drive = new TankDrive(hardwareMap, beginPose);

        // Define your Tank Drive trajectory using Road Runner 1.0 actionBuilder
        Action biobuzzTrajectory = drive.actionBuilder(beginPose)
                .lineToX(36.0)
                .turn(Math.toRadians(90))
                .lineToY(36.0)
                .turn(Math.toRadians(-90))
                .build();

        telemetry.addData("Status", "Ready for Biobuzz Autonomous");
        telemetry.update();

        // Wait for Driver Station Start button
        waitForStart();

        if (isStopRequested()) return;

        // Execute the autonomous actions
        Actions.runBlocking(
                new SequentialAction(
                        biobuzzTrajectory
                        // You can chain other custom actions here (e.g., scoring mechanisms)
                )
        );
    }
}
