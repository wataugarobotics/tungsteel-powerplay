package org.firstinspires.ftc.teamcode.util;

public class LiftPosition {
    private double mm;
    private double ticks;
    private static final double TICKS_PER_MM =  384.5 / 112; // Motor tics per revolution divided by circumference of pulley.

    public LiftPosition(double mm){
        setMm(mm);
    }
    public void setMm(double mm){
        this.mm = mm;
        this.ticks = mmToTicks(mm);
    }
    public void setTicks(double ticks){
        this.ticks = ticks;
        this.mm = ticksToMm(ticks);
    }
    public double mm() {
        return mm;
    }
    public double ticks() {
        return ticks;
    }

    public static double mmToTicks(double mm){
        return mm * TICKS_PER_MM;
    }
    public static double ticksToMm(double ticks){
        return ticks / TICKS_PER_MM;
    }
}

