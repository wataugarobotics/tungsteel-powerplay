package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift extends SubsystemBase {
    public enum Position {
        Floor(0.0),
        Short(1.0),
        Medium(2.0),
        Long(3.0);

        public final double pos;

        private Position(double pos) {
            this.pos = pos;
        }

        public Position up() {
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

        public Position down() {
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

    private Position position = Position.Floor;
    private MotorEx motor;

    public Lift(HardwareMap hwMap) {
        this.motor = new MotorEx(hwMap, "lift");
    }

    public void up() {
        this.setPosition(position.up());
    }
    public void down() {
        this.setPosition(position.down());
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pos) {
        // TODO
        this.position = pos;
    }
}
