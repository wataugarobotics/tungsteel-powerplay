package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * A simple 2-wheel intake.
 */
@Config
public class Intake extends SubsystemBase {
    private final MotorGroup intake;
    public static double speed = 0;
    private final FtcDashboard dashboard = FtcDashboard.getInstance();
    private final Telemetry dashboardTelemetry = dashboard.getTelemetry();

    public Intake(final HardwareMap hMap) {
        intake = new MotorGroup( // NOTE: Left is leader. Physically reverse the motor with bullet connector
                new Motor(hMap,"intakeLeft", Motor.GoBILDA.RPM_1150),
                new Motor(hMap,"intakeRight", Motor.GoBILDA.RPM_1150)
        );
    }

    public void toggle() {
        if (speed == 0) {
            speed = 0.75;
        } else {
            speed = 0;
        }
    }

    public void run(){
        intake.set(speed);
    }


}