package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * This command sets drivetrain powers based on gamepad control stick input.
 */
public class DriveCommand extends RunCommand {
    public DriveCommand(Drivetrain drivetrain, GamepadEx gamepad) {
        super(() -> drivetrain.driveFieldCentric(
                gamepad.getLeftY(),
                gamepad.getLeftX(),
                -gamepad.getRightX()
        ), drivetrain);
    }
}
