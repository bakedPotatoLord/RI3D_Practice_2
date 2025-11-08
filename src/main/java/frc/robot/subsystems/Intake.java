package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.INTAKE_CONSTANTS;

public class Intake extends SubsystemBase {
    

    SparkMax intake_4bar = new SparkMax(INTAKE_CONSTANTS.INTAKE_4BAR_CAN_ID,MotorType.kBrushless);
    SparkMax intake_manipulator = new SparkMax(INTAKE_CONSTANTS.INTAKE_MANIPULATOR_CAN_ID, MotorType.kBrushless);

    SparkClosedLoopController controller_4bar;
    SparkClosedLoopController controller_manipulator;


    public Intake() {

        controller_4bar = intake_4bar.getClosedLoopController();
        controller_manipulator = intake_manipulator.getClosedLoopController();
    
    }

    void intakeOut(){
        controller_4bar.setReference(INTAKE_CONSTANTS.FOUR_BAR.SETPOINT_DOWN, ControlType.kPosition);
    }

    void intakeIn(){
        controller_4bar.setReference(INTAKE_CONSTANTS.FOUR_BAR.SETPOINT_UP, ControlType.kPosition);
    }

    void intake4BarStop(){
        intake_4bar.stopMotor();
    }

    Command intakeOutCommand(){
        return runOnce(this::intakeOut);
    }

    Command intakeInCommand(){
        return runOnce(this::intakeIn);
    }

    Command intake4BarStopCommand(){
        return runOnce(this::intake4BarStop);
    }

    void intakeManipulatorRun(){
        controller_manipulator.setReference(INTAKE_CONSTANTS.MANIPULATOR.intakeSpeed, ControlType.kVelocity);
    }

    void intakeManipulatorRunReverse(){
        controller_manipulator.setReference(INTAKE_CONSTANTS.MANIPULATOR.outtakeSpeed, ControlType.kVelocity);
    }

    void intakeManipulatorStop(){
        intake_manipulator.stopMotor();
    }

    Command intakeManipulatorRunCommand(){
        return runOnce(this::intakeManipulatorRun);
    }

    Command intakeManipulatorRunReverseCommand(){
        return runOnce(this::intakeManipulatorRunReverse);
    }

    Command intakeManipulatorStopCommand(){
        return runOnce(this::intakeManipulatorStop);
    }
}
