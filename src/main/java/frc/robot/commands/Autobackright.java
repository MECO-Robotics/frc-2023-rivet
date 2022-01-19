// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** Drives the robot autonomously in a plus sign pattern. */
public class Autobackright extends SequentialCommandGroup {

  /**
   * Create a new command.
   *
   * @param driveSubsystem The subsystem used by this command.
   */
  public Autobackright(DriveSubsystem driveSubsystem) {

    // Don't need to add the DriveSubsystem as a required subsystem because the
    // commands
    // scheduled will do that.

    addCommands(
        new DriveForward(driveSubsystem, 20),
        // intake ball
        new SpinRightDistance(driveSubsystem, -20),
        new DriveForward(driveSubsystem, 20),
        new SpinRightDistance(driveSubsystem, -20),
        // shoot 2 balls 1 pre loaded and 1 intaked
        new SpinRightDistance(driveSubsystem, 20),
        new DriveForward(driveSubsystem, 20)

    );

  }
}
