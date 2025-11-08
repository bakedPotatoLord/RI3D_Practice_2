// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.ElevatorConstants.ELEVATOR_STATE;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final Intake intakeSubsystem = new Intake();
  private final Elevator elevator = new Elevator();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }


  //intake, neo for 4-bar, and neo for wheels
  
  //hopper- 1 neo

  //Elevator- 2 neos , 1 reversed

  //manipulator - 1 neo


  private void configureBindings() {


    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().onTrue(intakeSubsystem.intakeOutCommand());
    m_driverController.a().onTrue(intakeSubsystem.intakeToggleCommand());

    m_driverController.x().onTrue(elevator.zeroElevator());
    m_driverController.leftBumper().onTrue(elevator.goToSetpointCommand(ELEVATOR_STATE.BASE));
    m_driverController.rightBumper().onTrue(elevator.goToSetpointCommand(ELEVATOR_STATE.L1));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Commands.none();
  }
}
