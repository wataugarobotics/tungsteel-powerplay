package org.firstinspires.ftc.teamcode.util;
public enum Level {
    Floor(0.0),
    Short(1000.0),
    Medium(3315.0),
    Long(3000.0);

    public final double pos;

    Level(double pos) {
        this.pos = pos;
    }

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