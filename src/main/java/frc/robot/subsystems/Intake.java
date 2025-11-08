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

    boolean setPointDown = false;

    public Intake() {

        controller_4bar = intake_4bar.getClosedLoopController();
        controller_manipulator = intake_manipulator.getClosedLoopController();
    
    }

    void intakeOut(){
        setPointDown = true;
        controller_4bar.setReference(INTAKE_CONSTANTS.FOUR_BAR.SETPOINT_DOWN, ControlType.kPosition);
    }

    void intakeIn(){
        setPointDown = false;
        controller_4bar.setReference(INTAKE_CONSTANTS.FOUR_BAR.SETPOINT_UP, ControlType.kPosition);
    }

    void intake4BarStop(){
        intake_4bar.stopMotor();
    }
    
    void intakeToggle(){
        setPointDown = !setPointDown;

        controller_4bar.setReference(
            setPointDown ?  INTAKE_CONSTANTS.FOUR_BAR.SETPOINT_DOWN : INTAKE_CONSTANTS.FOUR_BAR.SETPOINT_UP,
            ControlType.kPosition
        );
    }

    public Command intakeOutCommand(){
        return runOnce(this::intakeOut);
    }

    public Command intakeInCommand(){
        return runOnce(this::intakeIn);
    }

    public Command intake4BarStopCommand(){
        return runOnce(this::intake4BarStop);
    }

    public Command intakeToggleCommand(){
        return runOnce(this::intakeToggle);
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

    public Command intakeManipulatorRunCommand(){
        return runOnce(this::intakeManipulatorRun);
    }

    public Command intakeManipulatorRunReverseCommand(){
        return runOnce(this::intakeManipulatorRunReverse);
    }

    public Command intakeManipulatorStopCommand(){
        return runOnce(this::intakeManipulatorStop);
    }
}
