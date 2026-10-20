package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.Circle;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

import java.util.List;

@TeleOp(name = "auto_mode", group = "Concept")
public class auto_mode extends LinearOpMode {

    // variables
    DcMotor launcher;

    @Override
    public void runOpMode() {

        // motors
        launcher = hardwareMap.get(DcMotor.class, "launcher");
        launcher.setPower(0);

        // pollen locater
        ColorBlobLocatorProcessor colour_locator_pollen = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.YELLOW)

                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.75, 0.75, 0.75, -0.75))
                .setDrawContours(true)
                .setBoxFitColor(0)
                .setCircleFitColor(Color.rgb(255, 255, 0))
                .setBlurSize(15)

                .setDilateSize(15)
                .setErodeSize(25)
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)

                .build();

        // nectar locater
        ColorBlobLocatorProcessor colour_locator_nectar = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.RED)

                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.75, 0.75, 0.75, -0.75))
                .setDrawContours(true)
                .setBoxFitColor(0)
                .setCircleFitColor(Color.rgb(255, 255, 0))
                .setBlurSize(15)

                .setDilateSize(15)
                .setErodeSize(25)
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)

                .build();

        VisionPortal portal = new VisionPortal.Builder()
                .addProcessor(colour_locator_pollen)
                .addProcessor(colour_locator_nectar)
                .setCameraResolution(new Size(320, 240))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();

        waitForStart();
        while (opModeIsActive()) {
            // pollen
            for (ColorBlobLocatorProcessor.Blob b : pollen_blobs) {
                Circle circleFit = b.getCircle();
                telemetry.addLine(String.format("%5.3f      %3d     (%3d,%3d)",
                        b.getCircularity(), (int) circleFit.getRadius(), (int) circleFit.getX(), (int) circleFit.getY()));
            }
        }

        /*telemetry.setMsTransmissionInterval(100);
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        while (opModeIsActive() || opModeInInit()) {
            telemetry.addData("preview on/off", "... Camera Stream\n");

            // Read the current list
            List<ColorBlobLocatorProcessor.Blob> pollen_blobs = colour_locator_pollen.getBlobs();
            List<ColorBlobLocatorProcessor.Blob> nectar_blobs = colour_locator_nectar.getBlobs();


            // pollen blobs
            ColorBlobLocatorProcessor.Util.filterByCriteria(
                    ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                    50, 20000, pollen_blobs);
            ColorBlobLocatorProcessor.Util.filterByCriteria(
                    ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                    0.6, 1, pollen_blobs);

            // nectar blobs
            ColorBlobLocatorProcessor.Util.filterByCriteria(
                    ColorBlobLocatorProcessor.BlobCriteria.BY_CONTOUR_AREA,
                    50, 20000, nectar_blobs);
            ColorBlobLocatorProcessor.Util.filterByCriteria(
                    ColorBlobLocatorProcessor.BlobCriteria.BY_CIRCULARITY,
                    0.6, 1, nectar_blobs);


            telemetry.addLine("Circularity Radius Center");

            // pollen
            for (ColorBlobLocatorProcessor.Blob b : pollen_blobs) {
                Circle circleFit = b.getCircle();
                telemetry.addLine(String.format("%5.3f      %3d     (%3d,%3d)",
                        b.getCircularity(), (int) circleFit.getRadius(), (int) circleFit.getX(), (int) circleFit.getY()));
            }
            // nectar
            for (ColorBlobLocatorProcessor.Blob b : nectar_blobs) {
                Circle circleFit = b.getCircle();
                telemetry.addLine(String.format("%5.3f      %3d     (%3d,%3d)",
                        b.getCircularity(), (int) circleFit.getRadius(), (int) circleFit.getX(), (int) circleFit.getY()));
            }

            telemetry.update();
            sleep(100);
        }*/
    }
}
