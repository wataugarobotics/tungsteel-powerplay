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
 * This command searches the field for AprilTags and reads the tag ID when executed, passing the
 * ID to a callback.
 */
public class ReadSignalCommand extends CommandBase {
    // lens intrinsics
    private static final double FX = 578.272; // TODO
    private static final double FY = 578.272; // TODO
    private static final double CX = 402.145; // TODO
    private static final double CY = 221.506; // TODO

    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private static final double TAG_SIZE = 0.030; // meters, TODO

    private final AprilTagDetectionPipeline pipeline = new AprilTagDetectionPipeline(TAG_SIZE, FX, FY, CX, CY);
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
        pipeline.setDecimation(3.0f);
        camera.camera.setPipeline(pipeline);
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
        while (true) {
            ArrayList<AprilTagDetection> detections = pipeline.getLatestDetections();
            if (detections.size() != 0) {
                int id = detections.get(0).id;
                whenFound.accept(id);
                break;
            }
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
