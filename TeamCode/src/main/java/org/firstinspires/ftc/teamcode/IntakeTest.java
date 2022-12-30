package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

/**
 * An opmode for 1 driver in the driver-controlled segment.
 */
@TeleOp
public class IntakeTest extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx gamepad = new GamepadEx(gamepad1);
        Intake intake = new Intake(hardwareMap);

        gamepad.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new InstantCommand(intake::toggle, intake));

        // The default commands will run whenever no other command requires the corresponding
        // subsystem, constantly updating the motor powers.
        intake.setDefaultCommand(new RunCommand(intake::run, intake));

        schedule(new RunCommand(telemetry::update));
    }
}
