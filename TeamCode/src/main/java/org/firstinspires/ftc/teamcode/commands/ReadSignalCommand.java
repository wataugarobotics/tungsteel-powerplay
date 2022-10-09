package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Camera;
import org.firstinspires.ftc.teamcode.util.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
import java.util.function.IntConsumer;

/**
 * This command searches the field for apriltags and reads the ID when executed, passing the
 * ID to a callback.
 */
public class ReadSignalCommand extends CommandBase {
    // lens intrinsics
    private static final double FX = 578.272; // TODO
    private static final double FY = 578.272; // TODO
    private static final double CX = 402.145; // TODO
    private static final double CY = 221.506; // TODO

    private static final int WIDTH = 800; // TODO
    private static final int HEIGHT = 448; // TODO

    private static final double TAG_SIZE = 0.166; // meters, TODO

    private final AprilTagDetectionPipeline aprilTagDetectionPipeline = new AprilTagDetectionPipeline(TAG_SIZE, FX, FY, CX, CY);
    private final Camera camera;
    private final IntConsumer whenFound;

    /**
     * @param camera The camera subsystem to use.
     * @param whenFound An IntConsumer to run with a tag ID when a tag is found.
     */
    public ReadSignalCommand(Camera camera, IntConsumer whenFound) {
        this.camera = camera;
        addRequirements(camera);
        this.whenFound = whenFound;
    }

    @Override
    public void initialize() {
        aprilTagDetectionPipeline.setDecimation(3.0f);
        camera.camera.setPipeline(aprilTagDetectionPipeline);
        camera.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.camera.startStreaming(WIDTH, HEIGHT, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    @Override
    public void execute() {
        int framesWithoutDetection = 0;

        while (true) {
            ArrayList<AprilTagDetection> detections = aprilTagDetectionPipeline.getLatestDetections();

            if (detections.size() == 0) {
                framesWithoutDetection += 1;
            } else {
                int id = detections.get(0).id;
                whenFound.accept(id);
                break;
            }
            if (framesWithoutDetection >= 6) {
                aprilTagDetectionPipeline.setDecimation(2.0f);
            }
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
