// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  PhotonCamera cam1;
  PhotonCamera cam2;
  ArrayList<Integer> seenIdList;
  public VisionSubsystem() {
    cam1 = new PhotonCamera(Constants.VisionConstants.CAM1NAME);
    cam2 = new PhotonCamera(Constants.VisionConstants.CAM2NAME);
    seenIdList = new ArrayList<>();
  }

  public ArrayList<Integer> getCurrTagsSeen() {
    ArrayList<Integer> seen = new ArrayList<>();
    List<PhotonPipelineResult> pipelineResults = getAllResults();
    for (PhotonPipelineResult p : pipelineResults) {
      if (!p.hasTargets()) {
        continue;
      }
      for (PhotonTrackedTarget t : p.getTargets()) {
        int id = t.getFiducialId();
        if (!seenIdList.contains(id)) {
          seenIdList.add(id);
        }
        if (!seen.contains(id)) {
          seen.add(id);
        }
      }
    }
    return seen;
  }

  public ArrayList<Integer> seenIds() {
    return seenIdList;
  }

  public boolean currSeen(int id) {
    HashMap<List<PhotonPipelineResult>, int[]> results = getAllResultsWithIndices();
    List<PhotonPipelineResult> pipelineResults = results.keySet().iterator().next();
    int firstInd = results.get(pipelineResults)[0];
    int secondInd = results.get(pipelineResults)[1];
    boolean found = false;
    for (int i = 0; i < pipelineResults.size(); i++) {
      if (pipelineResults.get(i).hasTargets()){
        for (PhotonTrackedTarget t : pipelineResults.get(i).getTargets()) {
          int trackedId = t.getFiducialId();
          if (!seenIdList.contains(trackedId)) {
            seenIdList.add(trackedId);
          }
          if ((i == firstInd || i == secondInd) && trackedId == id) {
            found = true;
          }
        }
      }
    }
    return found;
  }

  private List<PhotonPipelineResult> getAllResults() {
    return getAllResultsWithIndices().keySet().iterator().next();
  }

  private HashMap<List<PhotonPipelineResult>, int[]> getAllResultsWithIndices() {
    List<PhotonPipelineResult> pipelineResults = cam1.getAllUnreadResults();
    int index1 = pipelineResults.size() - 1;
    pipelineResults.addAll(cam2.getAllUnreadResults());
    int index2 = pipelineResults.size() - 1;
    HashMap<List<PhotonPipelineResult>, int[]> result = new HashMap<>();
    int[] ints = {index1, index2};
    result.put(pipelineResults, ints);
    return result;
  }

  private void checkForNewIds() {
    List<PhotonPipelineResult> pipelineResults = getAllResults();
    for (PhotonPipelineResult p : pipelineResults) {
      for (PhotonTrackedTarget t : p.getTargets()) {
        int id = t.getFiducialId();
        if (!seenIdList.contains(id)) {
          seenIdList.add(id);
        }
      }
    }
  }
  @Override
  public void periodic() {
    checkForNewIds();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
