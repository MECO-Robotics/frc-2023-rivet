// Copyright (c) MECO Robotics
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.Constants;
import frc.robot.Constants.ElbowPosition;
import frc.robot.Constants.ShoulderPosition;

public class GoNodeMid extends CommandBase {

    private final ArmSubsystem armSubsystem;
    boolean doneElbow = false;
    boolean doneShoulder = false;

    public GoNodeMid(ArmSubsystem arm) {
        armSubsystem = arm;

        addRequirements(arm);
    }

    int logger = 0;
    @Override
    public void execute() {
        
        if (!doneElbow) {
            doneElbow = armSubsystem.move(ElbowPosition.middle_MiddleNode);
        }

        if (!doneShoulder) {
            doneShoulder = armSubsystem.move(ShoulderPosition.middle_MiddleNode);
        }

        if(logger++ % 10 == 0) System.out.println(String.format("GoNodeMid: %s, %s", doneElbow?"DONE":"MOVING", doneShoulder?"DONE":"MOVING"));
    }

    @Override
    public boolean isFinished() {
        return doneElbow && doneShoulder;

    }

}