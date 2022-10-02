package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * A subsystem representing the robot drivetrain.
 */
public class Drivetrain extends SubsystemBase {
    /**
     * The modifier that robot velocity is multiplied by. Should be in the range [0, 1].
     */
    public double speedMod = 1.0;
    private final MecanumDrive drive;
    private RevIMU imu;

    /**
     * Constructs a Drivetrain using the "leftFront", "rightFront", "leftRear", and "rightRear"
     * motor IDs.
     * @param hwMap The robot's HardwareMap
     */
    public Drivetrain(HardwareMap hwMap) {
        Motor leftFront = new Motor(hwMap, "leftFront", Motor.GoBILDA.RPM_312);
        Motor rightFront = new Motor(hwMap, "rightFront", Motor.GoBILDA.RPM_312);
        Motor leftRear = new Motor(hwMap, "leftRear", Motor.GoBILDA.RPM_312);
        Motor rightRear = new Motor(hwMap, "rightRear", Motor.GoBILDA.RPM_312);

        this.drive = new MecanumDrive(leftFront, rightFront, leftRear, rightRear);
        drive.setMaxSpeed(speedMod);

        this.imu = new RevIMU(hwMap, "imu");
        imu.init();
    }

    /**
     * Gets the joystick positions and sets the motor power to drive the robot. The robot will
     * drive relative to the field using the onboard gyro.
     */
    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        double heading = imu.getHeading();
        drive.setMaxSpeed(speedMod);
        drive.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, heading);
    }
}
