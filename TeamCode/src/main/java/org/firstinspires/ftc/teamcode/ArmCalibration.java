package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

/**
 * An opmode for 1 driver in the driver-controlled segment.
 */
@TeleOp
public class ArmCalibration extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx gamepad = new GamepadEx(gamepad1);
        Arm arm = new Arm(hardwareMap);

        gamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(arm::down, arm));
        gamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(arm::up, arm));

        // The default commands will run whenever no other command requires the corresponding
        // subsystem, constantly updating the motor powers.
        arm.setDefaultCommand(new RunCommand(arm::setPos, arm));

        schedule(new RunCommand(telemetry::update));
    }
}
