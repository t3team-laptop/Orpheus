// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.RevBlinkin;

public class Intake extends Command {

  private IntakeRollers intake;
  private RevBlinkin blinkin;

  public Intake(IntakeRollers intake, RevBlinkin blinkin) {

    this.intake = intake;
    addRequirements(intake);

    this.blinkin = blinkin;
    addRequirements(blinkin);

  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    intake.intake(-.7);
    if(intake.isIntaked()){
      blinkin.isIntake();
    }
    else{
      blinkin.intakeIsRunning();
    }

  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
    if(!intake.isIntaked()){
      blinkin.setEnabled();
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}