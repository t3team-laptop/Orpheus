// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  TalonFX ShooterMotorLeft;
  TalonFX ShooterMotorRight;
  TalonFXConfiguration shooterConfig;
  
    public Shooter() {

        ShooterMotorLeft = new TalonFX(Constants.Shooter.ShooterMotorLeftID);
        ShooterMotorRight = new TalonFX(Constants.Shooter.ShooterMotorRightID);
        
        shooterConfig  = new TalonFXConfiguration();
        shooterConfig.CurrentLimits.SupplyCurrentLimit = 30.0;
        shooterConfig.CurrentLimits.SupplyCurrentLimitEnable = true;

         ShooterMotorLeft.setNeutralMode(NeutralModeValue.Coast);
        ShooterMotorRight.setNeutralMode(NeutralModeValue.Coast);

        ShooterMotorLeft.getConfigurator().apply(shooterConfig);
        ShooterMotorRight.getConfigurator().apply(shooterConfig);

    }
    public void setShooterLSpeed(Double Speed) {
      ShooterMotorLeft.set(Speed);
    }
    public void setShooterRSpeed(Double Speed) {
      ShooterMotorRight.set(Speed);
    }
    public void stopL() {
      ShooterMotorLeft.stopMotor();
    }
    public void stopR(){
      ShooterMotorRight.stopMotor();
    }
  @Override
  public void periodic() {
  }
}