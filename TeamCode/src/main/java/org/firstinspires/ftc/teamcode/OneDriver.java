package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * An opmode for 1 driver in the driver-controlled segment.
 */
@TeleOp
public class OneDriver extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx gamepad = new GamepadEx(gamepad1);
        Drivetrain drivetrain = new Drivetrain(hardwareMap);

        // This will run the command whenever no other command that uses the drivetrain is running,
        // constantly updating the motor powers with the gamepad information.
        drivetrain.setDefaultCommand(new DriveCommand(drivetrain, gamepad));
        schedule(new RunCommand(telemetry::update));
    }
}
