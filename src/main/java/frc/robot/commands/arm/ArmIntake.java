// Copyright (c) MECO Robotics
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.Constants.ElbowPosition;
import frc.robot.Constants.ShoulderPosition;

public class ArmIntake extends CommandBase {

    private final ArmSubsystem armSubsystem;
    boolean doneElbow = false;
    boolean doneShoulder = false;

    public ArmIntake(ArmSubsystem arm) {
        armSubsystem = arm;
    }

    int logger = 0;
    @Override
    public void execute() {
        if(logger++ % 1 == 0) System.out.println("ArmIntake");

        if (!doneElbow) {
            doneElbow = armSubsystem.move(ElbowPosition.middle_PickUp);
        }

        if (!doneShoulder) {
            doneShoulder = armSubsystem.move(ShoulderPosition.middle_LowNode);
        }
    }

    @Override
    public boolean isFinished() {
        return doneElbow && doneShoulder;

    }

}