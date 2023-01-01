package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * A virtual four-bar linkage arm, mounted to a set of linear slides.
 */
public class Arm extends SubsystemBase {

    private final Servo topServo;
    private final Servo bottomServo;

    private final FtcDashboard dashboard = FtcDashboard.getInstance();
    private final Telemetry dashboardTelemetry = dashboard.getTelemetry();
    private static double position = .50;
    private final double step = .2;
    private final double[] RANGE = {72.6, 217.8}; //calculated from Onshape, may be adjusted
    private final double RADIUS = 1; // TODO: tune this, should be like 318 ish

    public Arm(final HardwareMap hMap) {
        topServo = hMap.get(Servo.class, "top");
        bottomServo = hMap.get(Servo.class, "bottom");
    }

    public void update() {
        bottomServo.setPosition(position);
        topServo.setPosition(1 - position);
        dashboardTelemetry.addData("Arm Target", position);
        dashboardTelemetry.addData("Arm Angle", getAngle());
    }

    public void setPos(double position) {
        this.position = position;
        update();
    }
    public void setAngle(double angle){ //in DEGREES
        angle = Range.clip(angle, RANGE[0], RANGE[1]); //ensure that angle is always within range
        setPos(
                (angle - RANGE[0]) / (RANGE[1] - RANGE[0]) //translates and scales angle to position
        );
    }
    public double setTargetXY(double tX, double tY){
        setAngle(Math.toDegrees(Math.acos(
            tX/RADIUS
        )));
        return tY - (RADIUS * Math.sin(Math.toRadians(getAngle())));
    }
    public void move(double step){
        position = Range.clip(position + step, 0, 1);
        update();
    }
    public void up(){
        move(step);
    }
    public void down(){
        move(-step);
    }
    public double getAngle(){
        return Range.scale(position, 0, 1, RANGE[0], RANGE[1]);
    }
    public double getRelativeX(){
        return RADIUS * Math.cos(Math.toRadians(getAngle()));
    }
    public double getRelativeY(){
        return RADIUS * Math.sin(Math.toRadians(getAngle()));
    }


}
