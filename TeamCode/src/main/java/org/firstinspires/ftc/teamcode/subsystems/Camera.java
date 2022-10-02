package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class Camera extends SubsystemBase {

    public OpenCvCamera camera;

    public Camera(HardwareMap hwMap) {
        int cameraMonitorViewId = hwMap
                .appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        this.camera = OpenCvCameraFactory
                .getInstance()
                .createWebcam(hwMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

    }
}
