package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.function.DoubleSupplier;

/**
 * A subsystem representing the robot drivetrain.
 */
public class Drivetrain extends SubsystemBase {
    /**
     * The modifier that robot velocity is multiplied by. Should be in the range [0, 1].
     */
    public double speedMod = 1.0;
    private final DoubleSupplier leftX;
    private final DoubleSupplier leftY;
    private final DoubleSupplier rightX;
    private final MecanumDrive drive;

    /**
     * Constructs a Drivetrain using the "leftFront", "rightFront", "leftRear", and "rightRear"
     * motor IDs.
     * @param hwMap The robot's HardwareMap
     * @param gamepad The gamepad used to control the motor
     */
    public Drivetrain(HardwareMap hwMap, GamepadEx gamepad) {
        Motor leftFront = new Motor(hwMap, "leftFront", Motor.GoBILDA.RPM_312);
        Motor rightFront = new Motor(hwMap, "rightFront", Motor.GoBILDA.RPM_312);
        Motor leftRear = new Motor(hwMap, "leftRear", Motor.GoBILDA.RPM_312);
        Motor rightRear = new Motor(hwMap, "rightRear", Motor.GoBILDA.RPM_312);

        this.drive = new MecanumDrive(leftFront, rightFront, leftRear, rightRear);
        drive.setMaxSpeed(speedMod);

        this.leftX = gamepad::getLeftX;
        this.leftY = gamepad::getLeftY;
        this.rightX = gamepad::getRightX;
    }

    /**
     * Gets the joystick positions and sets the motor power to drive the robot.
     */
    public void drive() {
        double strafeSpeed = leftX.getAsDouble();
        double forwardSpeed = leftY.getAsDouble();
        double turnSpeed = rightX.getAsDouble();

        drive.setMaxSpeed(speedMod);
        drive.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
    }
}
