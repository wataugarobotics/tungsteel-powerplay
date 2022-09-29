package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.RunCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * A basic command to drive the robot with the gamepad. This should be set as the default command
 * for the drivetrain.
 */
public class DriveCommand extends RunCommand {
    public DriveCommand(Drivetrain drivetrain) {
        // add drivetrain as a requirement and use drivetrain.drive as the run command
        super(drivetrain::drive, drivetrain);
    }
}
