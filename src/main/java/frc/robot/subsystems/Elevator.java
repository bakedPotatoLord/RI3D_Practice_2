package frc.robot.subsystems;


import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.ElevatorConstants.ELEVATOR_STATE;


public class Elevator extends SubsystemBase {

    SparkMax leader = new SparkMax(ElevatorConstants.ELEVATOR_CAN_ID[0], MotorType.kBrushless);
    SparkMax follower = new SparkMax(ElevatorConstants.ELEVATOR_CAN_ID[1], MotorType.kBrushless);

    SparkClosedLoopController controller = leader.getClosedLoopController();

    RelativeEncoder encoder = leader.getEncoder();

    DigitalInput limitSwitch = new DigitalInput(ElevatorConstants.LIMIT_SWITCH_DIO_PORT);

    public Elevator() {}

    double stateToSetpoint(ELEVATOR_STATE state){
        switch (state) {
            case BASE:
                return ElevatorConstants.SETPOINT_BASE;
            case L1:
                return ElevatorConstants.SETPOINT_L1;
            case L2:
                return ElevatorConstants.SETPOINT_L2;
            case L3:
                return ElevatorConstants.SETPOINT_L3;
            case L4:
                return ElevatorConstants.SETPOINT_L4;
            default:
                return ElevatorConstants.SETPOINT_BASE;
        }
    }

    void goToSetpoint(ELEVATOR_STATE state){
        controller.setReference(stateToSetpoint(state), ControlType.kPosition);
    }

    void runElevator(double speed){
        leader.set(speed);
    }

    void zeroEncoder(){
        encoder.setPosition(0);
    }

    double getEncoderPosition(){
        return encoder.getPosition();
    }

    boolean getLimitSwitch(){
        return limitSwitch.get();
    }

    Command goToSetpointCommand(ELEVATOR_STATE state){
        return runOnce(() -> goToSetpoint(state));
    }

    Command zeroElevator(){
        return Commands.run(()->runElevator(ElevatorConstants.ELEVATOR_ZERO_SPEED),this).until(this::getLimitSwitch).andThen(zeroElevator());
    }
    
}

