package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.Circle;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * Simplified Autonomous OpMode to turn the robot towards detected Yellow Pollen or Red/Blue Nectar.
 */
@Autonomous(name = "Auto Align to Pollen/Nectar", group = "Autonomous")
public class AutoAlignToColorOpMode extends LinearOpMode {

    public enum TargetType { POLLEN_YELLOW, NECTAR_RED, NECTAR_BLUE, ANY_LARGEST }

    private static final int CAM_W = 320, CAM_H = 240;
    private static final double CENTER_X = CAM_W / 2.0;
    private static final double KP = 0.0035, MIN_POWER = 0.12, MAX_POWER = 0.45, DEADZONE = 10.0;

    private DcMotor[] leftMotors, rightMotors;
    private VisionPortal visionPortal;
    private ColorBlobLocatorProcessor pollenLocator, redLocator, blueLocator;
    private TargetType targetType = TargetType.ANY_LARGEST;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        initDriveHardware();
        initVision();

        // Target selection during Init
        while (opModeInInit()) {
            if (gamepad1.x || gamepad1.cross) targetType = TargetType.POLLEN_YELLOW;
            else if (gamepad1.b || gamepad1.circle) targetType = TargetType.NECTAR_RED;
            else if (gamepad1.a || gamepad1.square) targetType = TargetType.NECTAR_BLUE;
            else if (gamepad1.y || gamepad1.triangle) targetType = TargetType.ANY_LARGEST;

            telemetry.addData("Target (Cross=Yellow, Circle=Red, Square=Blue, Triangle=Any)", targetType);
            telemetry.update();
            sleep(50);
        }

        waitForStart();

        while (opModeIsActive()) {
            ColorBlobLocatorProcessor.Blob bestBlob = findBestTarget();

            if (bestBlob != null) {
                Circle circle = bestBlob.getCircle();
                double errorX = circle.getX() - CENTER_X;
                boolean aligned = Math.abs(errorX) <= DEADZONE;

                double power = 0.0;
                if (!aligned) {
                    power = Range.clip(errorX * KP, -MAX_POWER, MAX_POWER);
                    if (Math.abs(power) < MIN_POWER) power = Math.signum(power) * MIN_POWER;
                }

                setTurnPower(power);

                telemetry.addData("Status", aligned ? "ALIGNED" : "TURNING");
                telemetry.addData("Error X", "%.1f px", errorX);
                telemetry.addData("Power", "%.2f", power);
            } else {
                setTurnPower(0.0);
                telemetry.addData("Status", "NO TARGET DETECTED");
            }

            telemetry.update();
        }

        setTurnPower(0.0);
        if (visionPortal != null) visionPortal.close();
    }

    private void initDriveHardware() {
        try {
            DcMotor lf = hardwareMap.get(DcMotor.class, "leftFront");
            DcMotor lb = hardwareMap.get(DcMotor.class, "leftBack");
            DcMotor rf = hardwareMap.get(DcMotor.class, "rightFront");
            DcMotor rb = hardwareMap.get(DcMotor.class, "rightBack");
            rf.setDirection(DcMotorSimple.Direction.REVERSE);
            rb.setDirection(DcMotorSimple.Direction.REVERSE);
            leftMotors = new DcMotor[]{lf, lb};
            rightMotors = new DcMotor[]{rf, rb};
        } catch (Exception e) {
            DcMotor l = hardwareMap.get(DcMotor.class, "left");
            DcMotor r = hardwareMap.get(DcMotor.class, "right");
            r.setDirection(DcMotorSimple.Direction.REVERSE);
            leftMotors = new DcMotor[]{l};
            rightMotors = new DcMotor[]{r};
        }
    }

    private void initVision() {
        pollenLocator = createLocator(ColorRange.YELLOW, Color.YELLOW);
        redLocator = createLocator(ColorRange.RED, Color.RED);
        blueLocator = createLocator(ColorRange.BLUE, Color.BLUE);

        visionPortal = new VisionPortal.Builder()
                .addProcessor(pollenLocator)
                .addProcessor(redLocator)
                .addProcessor(blueLocator)
                .setCameraResolution(new Size(CAM_W, CAM_H))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();

        FtcDashboard.getInstance().startCameraStream(visionPortal, 30);
    }

    private ColorBlobLocatorProcessor createLocator(ColorRange colorRange, int colorRgb) {
        return new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(colorRange)
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.85, 0.85, 0.85, -0.85))
                .setDrawContours(true)
                .setBoxFitColor(colorRgb)
                .setCircleFitColor(colorRgb)
                .setBlurSize(15)
                .setDilateSize(15)
                .setErodeSize(25)
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)
                .build();
    }

    private ColorBlobLocatorProcessor.Blob findBestTarget() {
        List<ColorBlobLocatorProcessor.Blob> candidates = new ArrayList<>();

        if (targetType == TargetType.POLLEN_YELLOW || targetType == TargetType.ANY_LARGEST)
            addFilteredBlobs(candidates, pollenLocator);
        if (targetType == TargetType.NECTAR_RED || targetType == TargetType.ANY_LARGEST)
            addFilteredBlobs(candidates, redLocator);
        if (targetType == TargetType.NECTAR_BLUE || targetType == TargetType.ANY_LARGEST)
            addFilteredBlobs(candidates, blueLocator);

        ColorBlobLocatorProcessor.Blob best = null;
        double maxArea = -1;
        for (ColorBlobLocatorProcessor.Blob blob : candidates) {
            double area = blob.getContour().toArray().length;
            if (area > maxArea) {
                maxArea = area;
                best = blob;
            }
        }
        return best;
    }

    private void addFilteredBlobs(List<ColorBlobLocatorProcessor.Blob> dest, ColorBlobLocatorProcessor locator) {
        List<ColorBlobLocatorProcessor.Blob> blobs = locator.getBlobs();
        // Filter by size (contour area)
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA, 100, 25000, blobs);
        // Filter by circularity (0.60 to 1.0 ensures only round/circular shapes are detected)
        ColorBlobLocatorProcessor.Util.filterByCriteria(
                ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY, 0.60, 1.0, blobs);
        dest.addAll(blobs);
    }

    private void setTurnPower(double power) {
        for (DcMotor m : leftMotors) m.setPower(power);
        for (DcMotor m : rightMotors) m.setPower(-power);
    }
}
