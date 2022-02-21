// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import frc.robot.subsystems.ClimbingSubsystem;

import java.util.concurrent.DelayQueue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * Climb the middle and traversal rungs. Assumes the robot is already positioned
 * at the
 * middle rung and the upper winch is ready to be pulled.
 * 
 */
public class Climb extends SequentialCommandGroup {

  /**
   * Creates a new Command.
   *
   * @param ballCollectionSubsystem The subsystem used by this command.
   */
  public Climb(ClimbingSubsystem climbingSubsystem) {

    addCommands(
        new TelescopingArmSet(climbingSubsystem, 1),
        new WaitCommand(2),
        new RotatingArmLowerToPosition(climbingSubsystem, .9),
        new WaitCommand(2),
        new RotatingArmLowerToPosition(climbingSubsystem, 0),
        new RotatingArmLowerToPosition(climbingSubsystem, .5));
  }

}
