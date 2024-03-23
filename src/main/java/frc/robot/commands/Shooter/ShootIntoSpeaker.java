package frc.robot.commands.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakePivot;
import frc.robot.subsystems.Shooter;
public class ShootIntoSpeaker extends Command {
  /** Creates a new ShootIntoSpeaker. */
  private Shooter shooter;
  private IntakePivot pivot;
  public ShootIntoSpeaker(Shooter shooter, IntakePivot pivot) {
    this.shooter = shooter;
    this.pivot = pivot;
    addRequirements(shooter);
    addRequirements(pivot);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pivot.shoot();
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.setShooterLSpeed(-.75);
    shooter.setShooterRSpeed(-.75);

    while(!pivot.pivotIsFinished()){
      pivot.shoot();
    }

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopL();
    shooter.stopR();
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}