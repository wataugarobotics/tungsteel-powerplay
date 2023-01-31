package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * A virtual four-bar linkage arm, mounted to a set of linear slides.
 */
public class Arm extends SubsystemBase {

    private final ServoEx armServo;
    private final FtcDashboard dashboard = FtcDashboard.getInstance();
    private final Telemetry dashboardTelemetry = dashboard.getTelemetry();
    public final double RADIUS = 264; // Calculated from Onshape

    public Arm(final HardwareMap hMap) {
        armServo = new SimpleServo(hMap, "top", 72.6, 217.8, AngleUnit.DEGREES); //Calculated from Onshape
    }

    public void update() {
        dashboardTelemetry.addData("Arm Angle", getAngle());
    }

    public void setAngle(double angleDegrees){
        armServo.turnToAngle(angleDegrees, AngleUnit.DEGREES);
    }
    public void setTargetX(double tX){ //Sam is going to dislike how I did this
        armServo.turnToAngle(Math.acos(
            tX/RADIUS
        ), AngleUnit.DEGREES);
    }
    public double getTargetY(double tY){
        return tY - (RADIUS * Math.sin(Math.toRadians(getAngle())));
    }
    public void up(){
        armServo.rotateByAngle(30);
    }
    public void down(){
        armServo.rotateByAngle(-30);
    }
    public double getAngle(){
        return armServo.getAngle(AngleUnit.DEGREES);
    }
    public double getRelativeX(){
        return RADIUS * Math.cos(Math.toRadians(getAngle()));
    }
    public double getRelativeY(){
        return RADIUS * Math.sin(Math.toRadians(getAngle()));
    }


}
