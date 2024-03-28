package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.ASUtil;
import frc.robot.Constants;

import java.util.List;
import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;

public class Vision extends SubsystemBase {
  private PhotonCamera cam;
  private PhotonCamera camBack;

  private int speakerTag;
  private int ampTag;

  PIDController rotationController = new PIDController(.015, 0.015, 0);


  /** Creates a new Vision. */
  public Vision() {
    cam = new PhotonCamera(Constants.Vision.CAM_NAME);
    camBack = new PhotonCamera(Constants.Vision.CAM_NAME_BACK);
    cam.setDriverMode(false);
    camBack.setDriverMode(false);

    Optional<Alliance> ally = DriverStation.getAlliance();
    if (ally.isPresent()) {
      if (ally.get() == Alliance.Red) {
          speakerTag = 4;
          ampTag = 5;
      }
      if (ally.get() == Alliance.Blue) {
          speakerTag = 7;
          ampTag = 6;
      }
    }
  }


  public double calculateOffsetSpeaker() {
    var result = cam.getLatestResult();
    double roatationSpeed;
    int index = 0;

    if (result.hasTargets()) {
      for(int i = 0; i < result.getTargets().size(); i++){
        if(result.getTargets().get(i).getFiducialId() == speakerTag){
          index = i;
        }
      }
      double yaw = (result.getTargets().get(index).getYaw());
      roatationSpeed = rotationController.calculate(yaw, 0);
    } 
   
    else{roatationSpeed = 0;}
    return roatationSpeed;
  }

  public double calculateOffsetAmp() {
    var result = camBack.getLatestResult();
    double roatationSpeed;

    if (result.hasTargets() && result.getTargets().get(ampTag) != null) {
      double yaw = (result.getTargets().get(0).getYaw());
      roatationSpeed = -rotationController.calculate(yaw, 0);
    } 
    else{roatationSpeed = 0;}
    return roatationSpeed;
  }


  @Override
  public void periodic() {
    
  }
}