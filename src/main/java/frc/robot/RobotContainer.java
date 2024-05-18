/*
 * Hey future T3 :) Before I leave I just wanted to thank 
 * this team for giving me the experiences it did.
 * I had no idea I was going to fall in love with making
 * things out of my own hands and watching them come to life.
 * But robotics showed me that, and I will forever be grateful
 * for it. I hope that as you guys code, build, and design
 * for this season you enjoy it as much as I always did. 
 * I know it's cheesy but please don't forget to stop 
 * and smell the roses. Remember why you are doing what you 
 * are doing. Even though things suck when stuff doesn't go
 * as planned, it's a part of the learning process and it 
 * makes you a smarter person as you adapt to it. I know we
 * can't always win and that's okay. You're learning. And that's
 * what matters. I don't think I can even imagine a high school 
 * experience without T3 in it. I devoted 4 years of my life to 
 * this crap and I have absolutely no regrets. As someone who has 
 * been programming for the team for the past few years I know
 * that it can be alot sometimes, but don't worry. Just do your
 * best with the knowledge you have. Also, I wouldn't have done
 * anything other than be the programmer for this team and I
 * loved that I did it. Thank you to all of the people that made
 * my robotics experiences so much better by just being in it.
 * Special shoutouts to: my favorite co-captains Anshu and Ryan,
 * Carrie my favorite coding partner, Anika the little sister
 * I've always wanted, and last but most certainly not least, 
 * Mr. Ware and Mr.Garren for enabling me to have such an
 * amazing educational experience with robotics. 
 * 
 * Thank you for everything,
 * Akshita Santra 
 */


package frc.robot;



import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.autos.AutoIntake;

import frc.robot.commands.Climbers.*;
import frc.robot.autos.AutoSpeakerShoot;

import frc.robot.commands.Intake.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Swerve.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    /* Controllers */
    private final CommandXboxController baseDriver = new CommandXboxController(0);
    private final CommandXboxController armDriver = new CommandXboxController(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    //private final JoystickButton zeroGyro = new JoystickButton(baseDriver, XboxController.Button.kY.value);
    //private final JoystickButton robotCentric = new JoystickButton(baseDriver, XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final LeftClimber leftClimber = new LeftClimber();
    private final RightClimber rightClimber = new RightClimber();
    private final IntakePivot intakePivot = new IntakePivot();
    private final Shooter shooter = new Shooter();
    private final IntakeRollers intakeRollers = new IntakeRollers();
    private final Vision vision = new Vision();
  


    /* Commands */
    private final LeftClimberDown leftClimberDown;
    private final LeftClimberUp leftClimberUp;
    private final RightClimberDown rightClimberDown;
    private final RightClimberUp rightClimberUp;
    private final Intake intake;
    private final IntakeDown intakeDown;
    private final IntakeUp intakeUp;
    private final AmpAngle ampAngle;
    private final Outtake outtake;
    private final ReverseShooter reverseShooter;
    private final ShootIntoSpeaker shootIntoSpeaker;
    private final SlowMode slowMode;
    private final FastMode fastMode;
    
   
    private final ManualPivotIntake manualPivotIntake;
    private final AutoIntake autoIntake;
    private final AutoSpeakerShoot autoSpeakerShoot;




    private final SendableChooser<Command> autoChooser;


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        CameraServer.startAutomaticCapture();
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -baseDriver.getRawAxis(translationAxis), 
                () -> -baseDriver.getRawAxis(strafeAxis), 
                () -> -baseDriver.getRawAxis(rotationAxis), 
                () -> baseDriver.leftBumper().getAsBoolean()
            )
        );

        /*groundIntake.setDefaultCommand(
            new ManualPivotIntake(
                groundIntake, 
               () -> armDriver.getRawAxis(translationAxis)));
*/
       
        leftClimberDown = new LeftClimberDown(leftClimber);
        leftClimberDown.addRequirements(leftClimber);
        leftClimberUp = new LeftClimberUp(leftClimber);
        leftClimberUp.addRequirements(leftClimber);
        rightClimberDown = new RightClimberDown(rightClimber);
        rightClimberDown.addRequirements(rightClimber);
        rightClimberUp = new RightClimberUp(rightClimber);
        rightClimberUp.addRequirements(rightClimber);
        intake = new Intake(intakeRollers, Robot.blink);
        intake.addRequirements(intakeRollers);
        intake.addRequirements(Robot.blink);
        intakeDown = new IntakeDown(intakePivot);
        intakeDown.addRequirements(intakePivot);
        intakeUp = new IntakeUp(intakePivot);
        intakeUp.addRequirements(intakePivot);
        ampAngle = new AmpAngle(intakePivot);
        ampAngle.addRequirements(intakePivot);
        outtake = new Outtake(intakeRollers, intakePivot, Robot.blink);
        outtake.addRequirements(intakeRollers, intakePivot);
        reverseShooter = new ReverseShooter(shooter);
        reverseShooter.addRequirements(shooter);
        shootIntoSpeaker = new ShootIntoSpeaker(shooter);
        shootIntoSpeaker.addRequirements(shooter);
        manualPivotIntake = new ManualPivotIntake(intakePivot, () -> armDriver.getRawAxis(translationAxis));
        manualPivotIntake.addRequirements(intakePivot);
        autoIntake = new AutoIntake(intakeRollers);
        autoIntake.addRequirements(intakeRollers);
        autoSpeakerShoot = new AutoSpeakerShoot(shooter, intakeRollers);
        autoSpeakerShoot.addRequirements(shooter, intakeRollers, intakePivot);
        slowMode = new SlowMode(s_Swerve);
        slowMode.addRequirements(s_Swerve);
        fastMode = new FastMode(s_Swerve);
        fastMode.addRequirements(s_Swerve);





        NamedCommands.registerCommand("shoot", shootIntoSpeaker);
        NamedCommands.registerCommand("intake down", intakeDown);
        NamedCommands.registerCommand("intake up", intakeUp);
        NamedCommands.registerCommand("intake", autoIntake);
        NamedCommands.registerCommand("outtake", outtake);
        NamedCommands.registerCommand("zero gyro", new InstantCommand(() -> s_Swerve.zeroHeading()));
        
      
       
        NamedCommands.registerCommand("SpeakerShoot", autoSpeakerShoot);



        // Configure the button bindings
        configureButtonBindings();
        
        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);
      
/* 
        SmartDashboard.putData("On-the-fly path", Commands.runOnce(() ->{
            Pose2d currentPose = s_Swerve.getPose();

            Pose2d startPos = new Pose2d(currentPose.getTranslation(), new Rotation2d());
            Pose2d endPos = new Pose2d(currentPose.getTranslation().plus(new Translation2d(2,0)), new Rotation2d());
            List<Translation2d> bezierPoints = PathPlannerPath.bezierFromPoses(startPos, endPos);

            PathPlannerPath path = new PathPlannerPath(
            bezierPoints,
             new PathConstraints(3, 3, 2*Math.PI, 4*Math.PI),
             new GoalEndState(0, Rotation2d.fromDegrees(0))
            );
            path.preventFlipping = true;

            AutoBuilder.followPath(path).schedule();
        }));
        */
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() { 
        baseDriver.y().onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
         baseDriver.b().onTrue(slowMode);
         baseDriver.b().onTrue(fastMode);
         baseDriver.a().whileTrue(new TeleopSwerve(
                s_Swerve,
                () -> -baseDriver.getRawAxis(translationAxis),
                () -> -baseDriver.getRawAxis(strafeAxis),
                () -> vision.calculateOffsetSpeaker(),
                () -> baseDriver.leftBumper().getAsBoolean()
            ));
        baseDriver.x().whileTrue(new TeleopSwerve(
                s_Swerve,
                () -> -baseDriver.getRawAxis(translationAxis),
                () -> -baseDriver.getRawAxis(strafeAxis),
                () -> vision.calculateOffsetNote(),
                () -> baseDriver.leftBumper().getAsBoolean()
            ));
         
         
        baseDriver.leftBumper().whileTrue(leftClimberUp);
        baseDriver.leftTrigger(0.25).whileTrue(leftClimberDown);
        baseDriver.rightBumper().whileTrue(rightClimberDown);
        baseDriver.rightTrigger(0.25).whileTrue(rightClimberUp);
        
        // Operator Buttons 
        armDriver.leftTrigger(0.15).whileTrue(new ShootIntoSpeaker(shooter));

        armDriver.rightTrigger(.15).whileTrue(intake);
        armDriver.rightBumper().whileTrue(outtake);
        armDriver.leftBumper().whileTrue(reverseShooter);

        armDriver.y().onTrue(intakeDown);
        armDriver.b().onTrue(ampAngle);
        armDriver.a().onTrue(intakeUp);
       
        armDriver.axisGreaterThan(translationAxis, .1).whileTrue(manualPivotIntake);
        armDriver.axisLessThan(translationAxis, -.1).whileTrue(manualPivotIntake);
      }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoChooser.getSelected().andThen(new InstantCommand(() -> s_Swerve.autoHeadingFix()));
    }

}