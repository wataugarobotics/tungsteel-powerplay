package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ConeXYCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

import java.util.concurrent.atomic.AtomicReference;

/**
 * An op mode for 1 driver in the driver-controlled segment.
 */
@TeleOp
public class OneDriver extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx gamepad = new GamepadEx(gamepad1);
        Drivetrain drivetrain = new Drivetrain(hardwareMap);

        Claw claw = new Claw(hardwareMap);
        Lift lift = new Lift(hardwareMap);
        Arm arm = new Arm(hardwareMap);

        AtomicReference<Double> xOffset = new AtomicReference<>(arm.RADIUS);

        gamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    arm.setAngle(0);
                    xOffset.set(0.0);
                    lift.setOffset(0);
                    lift.down();
                }, lift, arm));
        gamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    arm.setAngle(0);
                    xOffset.set(0.0);
                    lift.setOffset(0);
                    lift.up();
                }, lift, arm));
        gamepad.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new InstantCommand(() -> {
                    schedule(new ConeXYCommand(arm, lift, xOffset.updateAndGet(v -> v + 10), lift.getHeight()));
                }));
        gamepad.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new InstantCommand(() -> {
                    schedule(new ConeXYCommand(arm, lift, xOffset.updateAndGet(v -> v - 10), lift.getHeight()));
                }));
        gamepad.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new InstantCommand(claw::toggle, claw));

        // The default commands will run whenever no other command requires the corresponding
        // subsystem, constantly updating the motor powers.
        drivetrain.setDefaultCommand(new DriveCommand(drivetrain, gamepad));
        lift.setDefaultCommand(new RunCommand(lift::update, lift));
        arm.setDefaultCommand(new RunCommand(arm::update, arm));

        schedule(new RunCommand(telemetry::update));
    }
}
