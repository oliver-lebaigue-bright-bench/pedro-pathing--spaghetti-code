# FTC Team 39068 "Spaghetti code" – TeamCode Module

This module contains all custom robot code, driver-controlled TeleOp routines, autonomous routines, computer vision processors, localizers, and tuning utilities for **FTC Team 39068 "Spaghetti code"** (Pembrokeshire College, Wales, UK).

---

## 📁 Key Components & Files

### 🕹️ TeleOp
- **`HumanDrivenOpMode.java`**: Main TeleOp routine for single-driver operation (`Gamepad 1`).
  - Tank Drive steering using left joystick Y (drive) and left joystick X (turn).
  - Roller Intake toggle (`Cross`) and Servo expansion/deployment (`Square`).
  - High-speed Flywheel Launcher with custom PIDF velocity control and presets (`D-Pad Up/Down`).

### 🤖 Autonomous
- **`BiobuzzAutonomous.java`**: Road Runner 1.0 autonomous sequence designed for our Tank Drive robot during the 2026–2027 BIOBUZZ season.
- **`AutoAlignToColorOpMode.java`**: Vision-assisted autonomous routine using OpenCV `ColorBlobLocatorProcessor` via a Logitech C270 camera.
  - Automatically turns the robot to center on yellow **Pollen** or red/blue **Nectar** game pieces.

### 🚗 Kinematics & Localizers
- **`TankDrive.java`**: Road Runner differential drive kinematics and trajectory builder for our current Tank robot.
- **`MecanumDrive.java`**: Mecanum drive kinematics prepared for future hardware upgrades.
- **`PinpointLocalizer.java`**: gobilda Pinpoint Odometry Computer hardware driver.
- **`OTOSLocalizer.java`**: SparkFun Optical Tracking Odometry Sensor driver.
- **`TwoDeadWheelLocalizer.java` / `ThreeDeadWheelLocalizer.java`**: Standard undriven odometry wheel localizers.

### ⚙️ Messages & Tuning
- **`messages/`**: Road Runner 1.0 drive commands and localizer input data structures logged for telemetry analysis.
- **`tuning/`**: OpModes for FTC Dashboard live parameter tuning, including `LocalizationTest.java`, `ManualFeedbackTuner.java`, `SplineTest.java`, and `TuningOpModes.java`.

---

## 💻 FTC Dashboard
To access live telemetry, graph PIDF velocity curves, or preview camera feeds:
1. Connect to the Control Hub Wi-Fi (`FTC-39068-...`).
2. Open `http://192.168.43.1:8080/dash` in any browser.
