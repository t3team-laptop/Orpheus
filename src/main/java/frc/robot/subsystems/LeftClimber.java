// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LeftClimber extends SubsystemBase {
  /** Creates a new LeftClimber. */
   TalonFX climberPullL;
   TalonFXConfiguration climberConfig;
  public LeftClimber() {
     climberPullL = new TalonFX(Constants.Climbers.CLIMBER_MOTOR_PULL_L);
     climberPullL.setNeutralMode(NeutralModeValue.Brake);
     climberConfig = new TalonFXConfiguration();
     climberConfig.CurrentLimits.SupplyCurrentLimit = 40.0;
     climberConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
     climberConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
     climberPullL.getConfigurator().apply(climberConfig);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("R current limit", climberConfig.CurrentLimits.SupplyCurrentLimit);
  }

  public void climberMove(double speedR){
    climberPullL.set(speedR);
  }

  public void climbersStop(){
    climberPullL.stopMotor();
  }
}
