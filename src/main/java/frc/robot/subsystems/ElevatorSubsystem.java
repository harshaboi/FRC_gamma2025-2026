// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix6.hardware.TalonFX;
public class ElevatorSubsystem extends SubsystemBase {
  private TalonFX motor1;
  private TalonFX motor2;
  private boolean isUp = false;
  private boolean isDown = true;
  /** Creates a new ExampleSubsystem. */
  public ElevatorSubsystem() {
    motor1 = new TalonFX(Constants.ElevatorConstants.MOTOR1_ID);
    motor2 = new TalonFX(Constants.ElevatorConstants.MOTOR2_ID);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  // public Command exampleMethodCommand() {
  //   // Inline construction of command goes here.
  //   // Subsystem::RunOnce implicitly requires `this` subsystem.
  //   return runOnce(
  //       () -> {
  //         /* one-time action goes here */
  //       });
  // }

  /**
   * Sets the speed of the motors given a speed
   *
   * @param speed the speed to be set to
   */
  private void setSpeed(double speed) {
    if (speed < -1) {
      speed = -1;
    } else if (speed > 1) {
      speed = 1;  
    }
    motor1.set(speed);
    motor2.set(-speed);
  }

  /**
   * Stops the motors from running
   */
  public void stop() {
    setSpeed(0);
  }

  private double getMotorPos() {
    return motor1.getPosition().getValueAsDouble();
  }

  public void checkIsUp() {
    if (getMotorPos() >= Constants.ElevatorConstants.MAX) {
      isUp = true;
    } else {
      isUp = false;
    }
  }

  public void checkIsDown() {
    if (getMotorPos() <= Constants.ElevatorConstants.MIN) {
      isDown = true;
    } else {
      isDown = false;
    }
  }

  /**
   * Runs the elevator up
   */
  public void runUp() {
    if (!isUp) {
      setSpeed(1);
    }
  }

  /**
   * Runs the elevator down
   */
  public void runDown() {
    if (!isDown) {
      setSpeed(-1);
    }
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
