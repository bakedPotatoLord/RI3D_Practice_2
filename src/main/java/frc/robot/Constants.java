// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }


  public static final class Swerve {
    public static final double MAX_VELOCITY = 4.0;
  }

  public static class INTAKE_CONSTANTS {
    public static final int INTAKE_4BAR_CAN_ID = 4;
    public static final int INTAKE_MANIPULATOR_CAN_ID = 5;


    public static class  FOUR_BAR {
      public static final double SETPOINT_UP= 0;
      public static final double SETPOINT_DOWN = 1.8;

      public static final SparkBaseConfig config = new SparkMaxConfig();

      static{

        config.closedLoop.pidf(0.5, 0, 0,0)
          .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
          .outputRange(-1, 1);
        config.softLimit.forwardSoftLimit(0.5)
          .reverseSoftLimit(0)
          .forwardSoftLimitEnabled(true)
          .reverseSoftLimitEnabled(true);
        config.idleMode(IdleMode.kCoast)
        .inverted(false)
        .smartCurrentLimit(20, 30);

      }
    }

    public static class MANIPULATOR{

      public static final double intakeSpeed = 0.5;
      public static final double outtakeSpeed = -0.4;

      public static final SparkBaseConfig config = new SparkMaxConfig();

      static{
        config.idleMode(IdleMode.kCoast)
        .inverted(false)
        .smartCurrentLimit(30);
        config.closedLoop.pidf(0, 0, 0, 1)
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        .outputRange(-1, 1);
      }
    }
  }
}
