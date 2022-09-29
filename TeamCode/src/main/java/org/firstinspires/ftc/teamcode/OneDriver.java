package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@TeleOp
public class OneDriver extends CommandOpMode {
    private Drivetrain drivetrain;
    private GamepadEx gamepad;

    @Override
    public void initialize() {
        gamepad = new GamepadEx(gamepad1);
        drivetrain = new Drivetrain(hardwareMap, gamepad);

        drivetrain.setDefaultCommand(new DriveCommand(drivetrain));
    }
}
