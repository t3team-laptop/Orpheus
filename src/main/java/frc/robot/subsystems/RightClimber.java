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

public class RightClimber extends SubsystemBase {
  /** Creates a new RightClimber. */
  TalonFX climberPullR;
  TalonFXConfiguration climberConfig;
  public RightClimber() {
    climberPullR = new TalonFX(Constants.Climbers.CLIMBER_MOTOR_PULL_R);
    climberPullR.setNeutralMode(NeutralModeValue.Brake);
    climberConfig = new TalonFXConfiguration();
    climberConfig.CurrentLimits.SupplyCurrentLimit = 20.0;
    climberConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    climberConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    climberPullR.getConfigurator().apply(climberConfig);
  }

  
  public void climberMove(double speedR){
    climberPullR.set(speedR);
  }

  public void climbersStop(){
    climberPullR.stopMotor();
    
  }
  @Override
  public void periodic(){
    SmartDashboard.putNumber("R current limit", climberConfig.CurrentLimits.SupplyCurrentLimit);
  }
}
