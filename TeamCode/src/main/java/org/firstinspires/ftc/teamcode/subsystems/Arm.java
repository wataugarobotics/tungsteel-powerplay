package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * A gripper mechanism that grabs a stone from the quarry.
 * Centered around the Skystone game for FTC that was done in the 2019
 * to 2020 season.
 */
public class Arm extends SubsystemBase {

    private final DcMotor armMotor;
    private final ArmFeedforward feedforward;
    public static double KS;
    public static double K_COS;
    public static double KV;
    public static double KA;
    public Arm(final HardwareMap hMap, final String name) {
        armMotor = hMap.get(DcMotor.class, name);
        feedforward = new ArmFeedforward(KS, K_COS, KV, KA);
    }

    public void spin(double power) {
        armMotor.setPower(power);
    }
    public void setPos(double angle){
        double degPerSec = 90;
        armMotor.setPower(feedforward.calculate(Math.toRadians(angle), Math.toRadians(degPerSec)));
    }


}