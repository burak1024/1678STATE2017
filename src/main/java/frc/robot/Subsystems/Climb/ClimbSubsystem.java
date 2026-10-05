package frc.robot.Subsystems.Climb;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class ClimbSubsystem extends Subsystem {
    private final TalonFX ClimbMotor = new TalonFX(1);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private static ClimbSubsystem instance;

    public static ClimbSubsystem getInstance() {
        if (instance == null)
            instance = new ClimbSubsystem();
        return instance;
    }

    public static ClimbSubsystem subsystem() {
        return getInstance();
    }
    public ClimbSubsystem(){
        ClimbMotor.getConfigurator().apply(ClimbConfig.config());
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        climbing(1);

        public int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    public Runnable[][] methods = {
            {
                    () -> emptyMethod(),
                    () -> emptyMethod(),
                    () -> emptyMethod()
            },
            {
                    () -> climbingIMethods(),
                    () -> emptyMethod(),
                    () -> climbingEMethods()
            }
    };
    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }
    public void climbingIMethods(){
        ClimbMotor.setControl(Voltage.withOutput(8.0));
    }
    public void climbingEMethods(){
        ClimbMotor.setControl(Voltage.withOutput(0));
    }
}
