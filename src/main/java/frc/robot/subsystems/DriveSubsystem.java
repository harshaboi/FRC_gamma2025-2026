// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  TalonFX firstMove;
  TalonFX firstRotate;
  TalonFX secondMove;
  TalonFX secondRotate;
  TalonFX thirdMove;
  TalonFX thirdRotate;
  TalonFX fourthMove;
  TalonFX fourthRotate;
  TalonFX[] motors = {firstMove, firstRotate, secondMove, secondRotate, thirdMove, thirdRotate, fourthMove, fourthRotate};
  public DriveSubsystem() {
    firstMove = new TalonFX(Constants.DriveConstants.FIRST_MOVE_ID);
    firstRotate = new TalonFX(Constants.DriveConstants.FIRST_ROTATE_ID);

    secondMove = new TalonFX(Constants.DriveConstants.SECOND_MOVE_ID);
    secondRotate = new TalonFX(Constants.DriveConstants.SECOND_ROTATE_ID);

    thirdMove = new TalonFX(Constants.DriveConstants.THIRD_MOVE_ID);
    thirdRotate = new TalonFX(Constants.DriveConstants.THIRD_ROTATE_ID);

    fourthMove = new TalonFX(Constants.DriveConstants.FOURTH_MOVE_ID);
    fourthRotate = new TalonFX(Constants.DriveConstants.FOURTH_ROTATE_ID);

  }

  

  public void stop() {
    for (TalonFX motor : motors) {
      motor.stopMotor();
    }
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
