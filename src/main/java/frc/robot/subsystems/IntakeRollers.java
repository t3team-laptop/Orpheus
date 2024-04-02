// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeRollers extends SubsystemBase {
  /** Creates a new Intake. */
  private static TalonFX intakeMotor;
  DigitalInput limit = new DigitalInput(Constants.GroundIntake.INTAKE_LIMIT_SWITCH);
  TalonFXConfiguration intakeConfig;
  public IntakeRollers() {
    intakeMotor = new TalonFX(Constants.GroundIntake.INTAKE_MOTOR_ID);

    intakeConfig = new TalonFXConfiguration();
    intakeConfig.CurrentLimits.SupplyCurrentLimit = 20.0;
    intakeConfig.CurrentLimits.SupplyCurrentLimitEnable = false;
    intakeMotor.setNeutralMode(NeutralModeValue.Coast);
    intakeMotor.getConfigurator().apply(intakeConfig);
  }

  public void intake(double speed){
    intakeMotor.set(speed);
  }
  
  public void outtake(double speed){
    intakeMotor.set(speed);
  }

  public void stop(){
    intakeMotor.stopMotor();
  }

  public boolean isIntaked() {
    return !limit.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("note intaked ", !limit.get());
  }
}