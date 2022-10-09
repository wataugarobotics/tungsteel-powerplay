package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.ReadSignalCommand;
import org.firstinspires.ftc.teamcode.subsystems.Camera;

@Autonomous
public class Auto extends CommandOpMode {
    @Override
    public void initialize() {
        FtcDashboard.start(hardwareMap.appContext);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry telemetry = dashboard.getTelemetry();

        Camera camera = new Camera(hardwareMap);
        dashboard.startCameraStream(camera, 30.0);

        schedule(new ReadSignalCommand(camera, (x) -> telemetry.addData("Tag ID", x)));
        schedule(new RunCommand(telemetry::update));
    }
}
