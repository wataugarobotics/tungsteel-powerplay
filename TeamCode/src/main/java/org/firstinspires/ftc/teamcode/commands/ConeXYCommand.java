package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.util.LiftPosition;

public class ConeXYCommand extends InstantCommand {
    public ConeXYCommand(Arm arm, Lift lift, double x, double y) {
        super(() -> {
            double armAngle = Math.acos(x / arm.RADIUS);
            double liftHeight = y - arm.RADIUS * Math.sin(armAngle);
            arm.setAngle(Math.toDegrees(armAngle));
            lift.setHeight(new LiftPosition(liftHeight));
        }, arm, lift);
    }
}
