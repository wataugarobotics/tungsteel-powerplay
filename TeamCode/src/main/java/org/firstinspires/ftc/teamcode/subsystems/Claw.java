package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Claw extends SubsystemBase {
    private SimpleServo clawServo;

    private boolean isOpen = false;

    private static final double MIN_ANGLE = 0.0;
    private static final double MAX_ANGLE = 100.0;

    public Claw(HardwareMap hwMap) {
        clawServo = new SimpleServo(hwMap, "claw", MIN_ANGLE, MAX_ANGLE);
    }
    public void open() {
        clawServo.turnToAngle(MIN_ANGLE);
        isOpen = true;
    }
    public void close() {
        clawServo.turnToAngle(MAX_ANGLE);
        isOpen = false;
    }

    public void toggle() {
        if(isOpen) {
            close();
        } else {
            open();
        }
    }
}
