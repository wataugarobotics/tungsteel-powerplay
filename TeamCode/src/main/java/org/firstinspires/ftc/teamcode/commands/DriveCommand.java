package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.RunCommand;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class DriveCommand extends RunCommand {
    public DriveCommand(Drivetrain drivetrain) {
        super(drivetrain::drive, drivetrain);
    }
}
