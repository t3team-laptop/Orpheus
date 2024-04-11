// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.RevBlinkin;
import frc.robot.subsystems.IntakePivot;

public class Outtake extends Command {
  private IntakeRollers intake;
  private IntakePivot pivot;
  private RevBlinkin blink;

  public Outtake(IntakeRollers intake, IntakePivot pivot, RevBlinkin blink) {
    
    this.intake = intake;
    addRequirements(intake);
    this.pivot = pivot;
    addRequirements(pivot);
    this.blink = blink;
    addRequirements(blink);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    blink.setEnabled();
    if(pivot.isAtAmpAngle()){
    intake.intake(.21); 
  }
  else{
    intake.intake(.6); //change to .6
  }
  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}