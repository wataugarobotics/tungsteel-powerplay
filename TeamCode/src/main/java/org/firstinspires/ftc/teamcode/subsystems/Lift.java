package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This subsystem controls the robot's lift.
 */
public class Lift extends SubsystemBase {
    /**
     * The level that the lift is at.
     */
    public enum Level {
        /** The lowest setting */
        Floor(0.0),
        /** The short pole junctions */
        Short(1.0),
        /** The medium-length pole junctions */
        Medium(2.0),
        /** The long pole junctions */
        Long(3.0);

        /** The position of the lift at a given setting in meters */
        public final double pos;

        Level(double pos) {
            this.pos = pos;
        }

        /**
         * Increments the level without wrapping.
         */
        public Level up() {
            switch (this) {
                case Floor:
                    return Short;
                case Short:
                    return Medium;
                case Medium:
                case Long:
                    return Long;
                default: // unreachable
                    return Floor;
            }
        }

        /**
         * Decrements the level without wrapping.
         */
        public Level down() {
            switch (this) {
                case Floor:
                case Short:
                    return Floor;
                case Medium:
                    return Short;
                case Long:
                    return Medium;
                default: // unreachable
                    return Long;
            }
        }
    }

    private static final double KS = 0.0; // TODO
    private static final double KV = 0.0; // TODO
    private static final double KA = 0.0; // TODO
    private static final double KP = 0.0; // TODO
    private static final double DIST_PER_TICK = 1.0; // TODO

    private Level level = Level.Floor;
    private final MotorEx motor;

    /**
     * The multiplier used for the motor power. Should be in the range [0, 1].
     */
    public double speedMod = 1.0;

    /**
     * Constructs a Lift with a HardwareMap. This uses the id "lift" to get the motor.
     * @param hwMap the HardwareMap
     */
    public Lift(HardwareMap hwMap) {
        motor = new MotorEx(hwMap, "lift");
        motor.setDistancePerPulse(DIST_PER_TICK);
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor.setRunMode(Motor.RunMode.PositionControl);
        motor.setPositionCoefficient(KP);
        motor.setFeedforwardCoefficients(KS, KV, KA);
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
     * Sets the target level of the lift.
     * @param level the new level to set
     */
    public void setLevel(Level level) {
        this.level = level;
        motor.setTargetDistance(level.pos);
    }

    /**
     * Should be called in a loop to set the motor power.
     */
    public void update() {
        motor.set(speedMod);
    }
}
