<div align="center">

<!-- Header Banner & Typing Intro -->
<img src="https://capsule-render.vercel.app/api?type=waving&color=FFD700&height=180&section=header&text=SPAGHETTI%20CODE&fontSize=55&fontColor=1a1a1a&animation=fadeIn&fontAlignY=38" width="100%" alt="Team 39068 Header Banner" />

<br/>

<img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&weight=500&size=25&pause=1000&color=FFD700&center=true&vCenter=true&width=650&lines=FTC+Team+39068+%22Spaghetti+code%22;Pembrokeshire+College+%C2%B7+Wales%2C+UK;Road+Runner+1.0+%2B+OpenCV+Vision;2026-2027+BIOBUZZ+Rookie+Season" alt="Typing SVG" />

<br/><br/>

<img src="logo.png" width="140" alt="Spaghetti Code Logo">

<br/><br/>

<!-- Action & Status Badges -->
<a href="https://github.com/oliver-lebaigue-bright-bench/pedro-pathing--spaghetti-code"><img src="https://img.shields.io/badge/FTC%20Season-2026--2027%20BIOBUZZ-FFD700?style=for-the-badge&logo=first&logoColor=black" alt="FTC Season" height="38" /></a>
<a href="mailto:oliverlebaigue@gmail.com"><img src="https://img.shields.io/badge/Contact-Email_Us-blue?style=for-the-badge&logo=gmail&logoColor=white" alt="Contact Email" height="38" /></a>
<a href="#sponsors-and-support-us"><img src="https://img.shields.io/badge/Status-Rookie_Team-orange?style=for-the-badge" alt="Status" height="38" /></a>

<br/><br/>

<!-- Tech Stack Badges -->
[![Java](https://img.shields.io/badge/Java-17-FFD700?style=flat-square&logo=java&logoColor=black&labelColor=1a1a1a)](https://www.oracle.com/java/)
[![FTC SDK](https://img.shields.io/badge/FTC_SDK-v9.0+-blue?style=flat-square&logo=android&logoColor=white&labelColor=1a1a1a)](https://github.com/FIRST-Tech-Challenge/FTCRobotController)
[![OpenCV](https://img.shields.io/badge/OpenCV-VisionPortal-green?style=flat-square&logo=opencv&logoColor=white&labelColor=1a1a1a)](https://opencv.org/)
[![Road Runner](https://img.shields.io/badge/Road_Runner-1.0-purple?style=flat-square&labelColor=1a1a1a)](https://rr.brott.dev/)
[![FTC Dashboard](https://img.shields.io/badge/FTC_Dashboard-Live_Telemetry-red?style=flat-square&labelColor=1a1a1a)](https://acmerobotics.github.io/ftc-dashboard/)
[![Location](https://img.shields.io/badge/Location-Pembrokeshire_College_%C2%B7_Wales_UK-00E676?style=flat-square&labelColor=1a1a1a)](#about-team-39068)

</div>

---

<!-- SECTION HEADER: ABOUT -->
<img src="https://capsule-render.vercel.app/api?type=rect&color=1a1a1a&height=45&text=ABOUT%20TEAM%2039068&fontSize=18&fontColor=FFD700&stroke=FFD700&strokeWidth=1" width="100%" alt="About Section" />

<br/>

### 🍝 Who We Are

Founded in 2026, **Team 39068 "Spaghetti code"** is a student-led robotics team based at **Pembrokeshire College** in Wales, UK. As a team composed heavily of passionate student programmers, engineers, and makers, our name pays homage to software development culture with a touch of humor!

2026–2027 marks our **Rookie Year** competing in the FIRST Tech Challenge.

> **💡 Rookie Journey:** As a rookie team built from the ground up by students, we are actively developing our custom software stack—integrating motion profiling, computer vision, closed-loop motor control, and live telemetry streaming.

---

<!-- SECTION HEADER: HARDWARE -->
<img src="https://capsule-render.vercel.app/api?type=rect&color=1a1a1a&height=45&text=ROBOT%20HARDWARE%20AND%20SUBSYSTEMS&fontSize=18&fontColor=FFD700&stroke=FFD700&strokeWidth=1" width="100%" alt="Hardware Section" />

<br/>

### 🤖 Robot Hardware Architecture

| Subsystem | Hardware Specs & Implementation |
| :--- | :--- |
| **Drivetrain** | Differential **Tank Drive** layout. *(Future goal: Upgrading to a Mecanum Drive once kit funding is secured!)* |
| **Intake** | Active Roller Intake with a servo-driven deployment mechanism that starts folded to pass the 18" sizing inspection and deploys in-game. |
| **Launcher** | Flywheel Launcher powered by a high-speed `DcMotorEx` using closed-loop PIDF velocity control for consistent shot trajectory. |
| **Sensors** | Motor-integrated wheel encoders + **Logitech C270 Webcam** for computer vision. |
| **Control System** | REV Control Hub + REV Driver Hub. |

> **⚠️ Expansion Rule Compliance:** The roller intake mechanism starts folded vertically to fit inside the standard 18" $\times$ 18" $\times$ 18" FTC sizing cube. Upon match start, a servo releases/deploys the intake outward for game element acquisition.

---

<!-- SECTION HEADER: SOFTWARE -->
<img src="https://capsule-render.vercel.app/api?type=rect&color=1a1a1a&height=45&text=SOFTWARE%20AND%20AUTONOMOUS&fontSize=18&fontColor=FFD700&stroke=FFD700&strokeWidth=1" width="100%" alt="Software Section" />

<br/>

### 💻 Core Software Stack

Our codebase is built on Java using the official FTC SDK and integrates modern robotics control software:

* **Road Runner 1.0 (`BiobuzzAutonomous`)**: Trajectory generation and motion planning tailored for our Tank Drive setup.
* **OpenCV Color Blob Detection (`AutoAlignToColorOpMode`)**: Vision portal processing using `ColorBlobLocatorProcessor` to automatically detect and align with yellow **Pollen** and red/blue **Nectar** targets.
* **Closed-Loop Velocity PIDF (`HumanDrivenOpMode`)**: Custom PIDF velocity algorithm with feedforward (`F`) and anti-windup bounds for flywheel launcher precision.
* **FTC Dashboard Integration**: Real-time telemetry streaming, live webcam feed (30 FPS), and on-the-fly parameter tuning accessible at `http://192.168.43.1:8080/dash`.
* **Pedro Pathing Readiness**: Software infrastructure ready for **Pedro Pathing** and custom localizers (Pinpoint, OTOS, Dead Wheels) as soon as the Mecanum hardware upgrade is completed.

---

<!-- SECTION HEADER: TELEOP CONTROLS -->
<img src="https://capsule-render.vercel.app/api?type=rect&color=1a1a1a&height=45&text=TELEOP%20DRIVER%20CONTROLS&fontSize=18&fontColor=FFD700&stroke=FFD700&strokeWidth=1" width="100%" alt="TeleOp Section" />

<br/>

### 🎮 Driver Mappings (`Gamepad 1`)

TeleOp is mapped for single-driver operation on **Gamepad 1** (`HumanDrivenOpMode`):

| Input | Subsystem | Action / Behavior |
| :--- | :--- | :--- |
| `Left Joystick Y` | Drivetrain | Forward / Backward power |
| `Left Joystick X` | Drivetrain | Steering / Turning power |
| `Cross (X)` | Intake | Toggle Roller Intake ON / OFF |
| `Square ([])` | Intake Servo | Toggle Deployment Servo (Up / Down) |
| `D-Pad Up` | Launcher | Increase Flywheel Speed (`Off` $\rightarrow$ `Low (1500 RPM)` $\rightarrow$ `High (3000 RPM)`) |
| `D-Pad Down` | Launcher | Decrease Flywheel Speed (`High` $\rightarrow$ `Low` $\rightarrow$ `Off`) |

---

<!-- 🔥 FUTURE ROADMAP SECTION 🔥 -->
<div align="center">

<img src="https://capsule-render.vercel.app/api?type=rect&color=1a1a1a&height=70&text=ROBOT%20AND%20SOFTWARE%20ROADMAP&fontSize=24&fontColor=FFD700&stroke=FFD700&strokeWidth=1" width="100%" alt="Future Roadmap Header" />

<p align="center"><b>Our development pipeline and upcoming hardware/software upgrades:</b></p>

</div>

<br/>

<table>
  <tr>
    <td width="50%" valign="top">
      <h3 align="center">🏎️ Mecanum Drivetrain Upgrade</h3>
      <div align="center">
        <img src="https://img.shields.io/badge/Status-Seeking_Sponsors-orange?style=flat-square&labelColor=1a1a1a" alt="Status" />
      </div>
      <br/>
      Transitioning from differential Tank Drive to a 4-wheel Mecanum drive system to unlock omnidirectional holonomic movement on the field.
    </td>
    <td width="50%" valign="top">
      <h3 align="center">📍 Pedro Pathing Integration</h3>
      <div align="center">
        <img src="https://img.shields.io/badge/Status-In_Infrastructure_Dev-FFD700?style=flat-square&labelColor=1a1a1a" alt="Status" />
      </div>
      <br/>
      Migrating autonomous pathing to <b>Pedro Pathing</b> for high-speed, bezier curve trajectory generation and precise pose convergence.
    </td>
  </tr>
  <tr>
    <td width="50%" valign="top">
      <h3 align="center">🎯 Dedicated Odometry Sensors</h3>
      <div align="center">
        <img src="https://img.shields.io/badge/Status-Hardware_Planned-blue?style=flat-square&labelColor=1a1a1a" alt="Status" />
      </div>
      <br/>
      Integrating gobilda Pinpoint Odometry Computer or SparkFun OTOS sensors for millimeter-level positional tracking during autonomous mode.
    </td>
    <td width="50%" valign="top">
      <h3 align="center">👁️ Advanced Multi-Blob Vision</h3>
      <div align="center">
        <img src="https://img.shields.io/badge/Status-Testing-00E676?style=flat-square&labelColor=1a1a1a" alt="Status" />
      </div>
      <br/>
      Expanding OpenCV <code>ColorBlobLocatorProcessor</code> pipeline to dynamically track, filter, and queue multiple game elements simultaneously.
    </td>
  </tr>
</table>

<br/>

---

<!-- SECTION HEADER: GETTING STARTED -->
<img src="https://capsule-render.vercel.app/api?type=rect&color=1a1a1a&height=45&text=GETTING%20STARTED%20AND%20DEPLOYMENT&fontSize=18&fontColor=FFD700&stroke=FFD700&strokeWidth=1" width="100%" alt="Getting Started Section" />

<br/>

### 📲 Deployment Steps

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/oliver-lebaigue-bright-bench/pedro-pathing--spaghetti-code.git
   ```
2. **Open in Android Studio**: Launch Android Studio Narwhal (2023.3.1+) and open the project root directory.
3. **Connect Control Hub**: Connect your laptop to the Control Hub via USB-C or micro-USB cable.
4. **Deploy**: Select the `:TeamCode` target configuration and hit **Run** (`Shift + F10`).

---

<!-- SECTION HEADER: SPONSORS -->
<img src="https://capsule-render.vercel.app/api?type=rect&color=1a1a1a&height=45&text=SPONSORS%20AND%20SUPPORTERS&fontSize=18&fontColor=FFD700&stroke=FFD700&strokeWidth=1" width="100%" alt="Sponsors Section" />

<br/>

As a **rookie team with limited funds**, we are immensely grateful to our founding sponsors:

<!-- * 🛠️ **[iFixit](https://www.ifixit.com/)** – Tooling, repair equipment, and hardware support. -->
* 🎓 **[Pembrokeshire College](https://www.pembrokeshire.ac.uk/)** – Educational backing, workspace, and sponsorship.

> **📢 Support Team 39068!** We are actively seeking sponsors and community partners to help us fund hardware upgrades. If you would like to support our rookie team, please get in touch at [oliverlebaigue@gmail.com](mailto:oliverlebaigue@gmail.com)!

---

<!-- SECTION HEADER: ROSTER -->
<img src="https://capsule-render.vercel.app/api?type=rect&color=1a1a1a&height=45&text=TEAM%20ROSTER%20AND%20CREDITS&fontSize=18&fontColor=FFD700&stroke=FFD700&strokeWidth=1" width="100%" alt="Roster Section" />

<br/>

### Team Leads
* 👩‍🏫 **Alison Whitehurst** – Team Lead
* 👨‍🏫 **Eben Sky** – Team Lead

### Student Roster
| Student | Role(s) |
| :--- | :--- |
| **Oliver** | Team Manager • Build Team • Software Engineer |
| **Eli** | Team Manager • Software Engineer |
| **Charlie** | Lead Driver |
| **Oakley** | Build Team • Electrics |
| **Tien Le** | Strategy Lead |
| **Iestyn** | Finance Lead |
| **Sam** | Social Media |
| **Osian** | Digital Media (Team Logo & Graphics) |
| **Dawid** | Documentation (Makerspace) |

---

<!-- SECTION HEADER: CONTACT -->
<div align="center">

<br/>

### ✉️ Contact & Inquiries

For sponsorship inquiries, outreach, or technical questions:

* 📧 **Email**: [oliverlebaigue@gmail.com](mailto:oliverlebaigue@gmail.com)
* 🏫 **Location**: Pembrokeshire College, Wales, United Kingdom
* 🤖 **FIRST Team Number**: FTC 39068

<br/>

<sub>Built with care by Team 39068 "Spaghetti code". © 2026</sub>

</div>
