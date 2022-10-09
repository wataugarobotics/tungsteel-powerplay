package org.firstinspires.ftc.teamcode.subsystems;

import android.graphics.Bitmap;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

/**
 * This subsystem wraps an {@link OpenCvCamera}.
 */
public class Camera extends SubsystemBase implements CameraStreamSource {

    public OpenCvCamera camera;

    public Camera(HardwareMap hwMap) {
        int cameraMonitorViewId = hwMap
                .appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        camera = OpenCvCameraFactory
                .getInstance()
                .createWebcam(hwMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
    }

    @Override
    public void getFrameBitmap(Continuation<? extends Consumer<Bitmap>> continuation) {
        camera.getFrameBitmap(continuation);
    }
}
