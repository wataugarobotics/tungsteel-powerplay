package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import java.util.function.DoubleSupplier;

public class Drivetrain extends SubsystemBase {
    public double speedMod = 1.0;
    private final DoubleSupplier leftX;
    private final DoubleSupplier leftY;
    private final DoubleSupplier rightX;
    private final MecanumDrive drive;

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

    public void drive() {
        double x = leftX.getAsDouble();
        double y = leftY.getAsDouble();
        double r = rightX.getAsDouble();

        double leftFrontPower = y + x + r;
        double rightFrontPower = y - x - r;
        double leftRearPower = y - x + r;
        double rightRearPower = y + x - r;

        drive.setMaxSpeed(speedMod);
        drive.driveWithMotorPowers(
                Range.clip(leftFrontPower, -1.0, 1.0),
                Range.clip(rightFrontPower, -1.0, 1.0),
                Range.clip(leftRearPower, -1.0, 1.0),
                Range.clip(rightRearPower, -1.0, 1.0)
        );
    }
}
