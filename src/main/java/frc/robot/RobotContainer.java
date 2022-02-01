// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Shift;
import frc.robot.commands.MoveOctagon;
import frc.robot.commands.Outtake;
import frc.robot.commands.PlusSign;
import frc.robot.commands.RaiseBallCollectionArm;
import frc.robot.commands.AutoCollect2;
import frc.robot.commands.AutoShoot3;
import frc.robot.commands.AutoShootCollect;
import frc.robot.commands.AutoShootCollectRightShoot;
import frc.robot.commands.Intake;
import frc.robot.commands.LowerBallCollectionArm;
import frc.robot.commands.Stop;
import frc.robot.commands.TeleopBallCollection;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.BallCollectionSubsystem;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.ControllerSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final BallCollectionSubsystem ballCollectionSubsystem = new BallCollectionSubsystem();
  private final ClimbingSubsystem climbingArmSubsystem = new ClimbingSubsystem();
  private final ControllerSubsystem controllerSubsystem = new ControllerSubsystem();

  private final TeleopBallCollection teleopBallCollection = new TeleopBallCollection(ballCollectionSubsystem, controllerSubsystem);
  
  private final Map<String, Command> autoCommands = new HashMap<>();
  private final SendableChooser<String> autoCommandChoice = new SendableChooser<String>();

  private final SendableChooser<DriveMode> driveMode = new SendableChooser<DriveMode>();

  public enum DriveMode {
    SplitArcade,
    Tank,
    Joystick
  }

  /** The container for the robot. Contains subsystems, I/O devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
    autoCommands.put("MoveOctagon", new MoveOctagon(driveSubsystem));
    autoCommands.put("PlusSign", new PlusSign(driveSubsystem));
    autoCommands.put("AutoShootCollectRightShoot", new AutoShootCollectRightShoot(driveSubsystem));
    autoCommands.put("AutoCollect2", new AutoCollect2(driveSubsystem));
    autoCommands.put("AutoShoot3", new AutoShoot3(driveSubsystem));
    autoCommands.put("AutoShootCollect", new AutoShootCollect(driveSubsystem));

    for(String choiceName : autoCommands.keySet()) {
      autoCommandChoice.addOption(choiceName, choiceName);
    }
    autoCommandChoice.setDefaultOption("AutoShootCollectRightShoot", "AutoShootCollectRightShoot");
    SmartDashboard.putData("Autonomous Mode", autoCommandChoice);
    
    driveMode.setDefaultOption("Split Arcade", DriveMode.SplitArcade);
    driveMode.addOption("Tank", DriveMode.Tank);
    driveMode.addOption("Joystick", DriveMode.Joystick);
    SmartDashboard.putData("Drive mode", driveMode);

    SmartDashboard.putData("ARM UP", new RaiseBallCollectionArm(ballCollectionSubsystem));
    SmartDashboard.putData("ARM DOWN", new LowerBallCollectionArm(ballCollectionSubsystem));
    SmartDashboard.putData("SHIFT DOWN", new Shift(driveSubsystem, false));
    SmartDashboard.putData("SHIFT UP", new Shift(driveSubsystem, true));
    SmartDashboard.putData("INTAKE", new Intake(ballCollectionSubsystem));
    SmartDashboard.putData("OUTTAKE", new Outtake(ballCollectionSubsystem));
    // Set default commands
    driveSubsystem.setDefaultCommand(new Stop(driveSubsystem));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // This is an example of button bindings using the new approach. The rest
    // of the button bindings are actually done in the ControllerSubsystem class.
/*
    JoystickButton shiftDown = new JoystickButton(
      controllerSubsystem.getPilotController(), 
      XboxController.Button.kLeftBumper);

    shiftDown.whenPressed(new Shift(driveSubsystem, false), false);

    JoystickButton shiftUp = new JoystickButton(
      controllerSubsystem.getPilotController(), 
      XboxController.Button.kBumperRight.value);

    shiftUp.whenPressed(new Shift(driveSubsystem, true), false);
    */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    Command command = null;

    if(autoCommandChoice != null && autoCommandChoice.getSelected() != null) {
      command = autoCommands.get(autoCommandChoice.getSelected());
    }

    return command;
  }

  public Command getTeleopDriveCommand() {
    return new TeleopDrive(driveSubsystem, controllerSubsystem, driveMode.getSelected());
  }

  public Command getTeleopBallCollection() {
    return teleopBallCollection;
  }

  public DriveSubsystem getDriveSubsystem() {
    return driveSubsystem;
  }
}
