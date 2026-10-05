package frc.robot.Subsystems.Shooter;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class ShooterSubsystem extends Subsystem {
    private final TalonFX shooterMotor1 = new TalonFX(0);
    private final TalonFX shooterMotor2 = new TalonFX(0);
    private final TalonFX shooterMotor3 = new TalonFX(0);
    private final TalonFX shooterMotor4 = new TalonFX(0);
    private final TalonFX shooterMotor5 = new TalonFX(0);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private static ShooterSubsystem instance;

    public static ShooterSubsystem getInstance() {
        if (instance == null) {
            instance = new ShooterSubsystem();
        }
        return instance;
    }

    public static ShooterSubsystem subsystem() {
        return getInstance();
    }
    public ShooterSubsystem(){
        shooterMotor1.getConfigurator().apply(ShooterConfig.config());
        shooterMotor2.getConfigurator().apply(ShooterConfig.config());
        shooterMotor3.getConfigurator().apply(ShooterConfig.config());
        shooterMotor4.getConfigurator().apply(ShooterConfig.config());
        shooterMotor5.getConfigurator().apply(ShooterConfig.config());

    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        shooting(1);

        public int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }

    }
    public Runnable [][]methods = {
        {
            ()->emptyMethod(),
            ()->emptyMethod(),
            ()->emptyMethod(),
        },
        {
            ()->shootingIMethods(),
            ()->emptyMethod(),
            ()->shootingEMethods()
        }
    };
    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }
    private void shootingIMethods(){
        shooterMotor1.setControl(Voltage.withOutput(8.0));
        shooterMotor2.setControl(Voltage.withOutput(8.0));
        shooterMotor3.setControl(Voltage.withOutput(8.0));
        shooterMotor4.setControl(Voltage.withOutput(8.0));
        shooterMotor5.setControl(Voltage.withOutput(8.0));
    }
    private void shootingEMethods(){
        shooterMotor1.setControl(Voltage.withOutput(0));
        shooterMotor2.setControl(Voltage.withOutput(0));
        shooterMotor3.setControl(Voltage.withOutput(0));
        shooterMotor4.setControl(Voltage.withOutput(0));
        shooterMotor5.setControl(Voltage.withOutput(0));
    }

}
