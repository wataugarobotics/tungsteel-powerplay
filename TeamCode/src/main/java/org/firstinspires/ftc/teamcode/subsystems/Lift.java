package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Level;

import java.util.List;

@Config
public class Lift extends SubsystemBase {
    public static double KP = .01;
    public static double KI = 0.0;
    public static double KD = 0.0;
    public static double KF = 0.0;

    private Level level = Level.Floor;
    private final MotorGroup motor;
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();
    private final PIDFController pidf;
    private boolean emergencyStop = false;

    /**
     * Constructs a Lift with a HardwareMap. This uses the id "lift" to get the motor.
     * @param hwMap the HardwareMap
     */
    public Lift(HardwareMap hwMap) {
        motor = new MotorGroup(
                new MotorEx(hwMap, "lift0", Motor.GoBILDA.RPM_435),
                new MotorEx(hwMap, "lift1", Motor.GoBILDA.RPM_435)
        );
        motor.setInverted(true);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor.setRunMode(Motor.RunMode.PositionControl);
        motor.resetEncoder();
        pidf = new PIDFController(KP, KI, KD, KF);

    }

    /**
     * Returns the average of a List of doubles
     * Applicable to any List of doubles, but primarily for MotorGroup.getPositions()
     * @param doubleList
     * @return double
     */
    private double avg(@NonNull List<Double> doubleList){
        double total = 0;
        for (double d :doubleList) {
            total += d;
        }
        return total / doubleList.size();
    }



    /* **** Commands: **** */

    /**
     * Should be called in a loop to set the motor power.
     */
    public void update() {
        if(!emergencyStop) motor.set(pidf.calculate(avg(motor.getPositions())));
        else motor.set(0);
    }
    /**
     * Moves the lift up 1 level. This is saturating, meaning if the lift is at the top level it
     * will stay at the top level.
     */
    public void up() {
        setLevel(level.up());
    }

    /**
     * Moves the lift down 1 level. This is saturating, meaning if the lift is at the bottom level
     * it will stay at the bottom level.
     */
    public void down() {
        setLevel(level.down());
    }

    /**
     * Gets the target level.
     * @return the current target level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * An "emergency stop" if tuning makes the lift go crazy
     * Toggle forces the lift to 0 power
     */
    public void toggleStop(){
        emergencyStop = !emergencyStop;
    }
    /**
     * Sets the target level of the lift.
     * @param level the new level to set
     */
    public void setLevel(Level level) {
        this.level = level;
        pidf.setPIDF(KP,KI,KD,KF);
        //
        pidf.setSetPoint(level.pos);
    }

    /**
     * Sends telemetry data for tuning/debugging purposes. Can be graphed with FTC Dashboard
     * which is pretty nifty
     * The FTC Dashboard address is 192.168.43.1:8080/dash
     */
    public void sendTelemetry(){
        dashboardTelemetry.addData("Lift Position", avg(motor.getPositions()));
        dashboardTelemetry.addData("Lift Target", level.pos);
        dashboardTelemetry.addData("Lift Error", avg(motor.getPositions())- level.pos);
        dashboardTelemetry.addData("Lift Velocity", motor.getVelocity());
        dashboardTelemetry.update();
    }
}
