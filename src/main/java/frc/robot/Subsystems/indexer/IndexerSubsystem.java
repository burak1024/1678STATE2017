package frc.robot.Subsystems.indexer;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class IndexerSubsystem extends Subsystem {
    private final TalonFX indexerMotor1 = new TalonFX(IndexerConstants.INDEX_MOTOR_ID1);
    private final TalonFX indexerMotor2 = new TalonFX(IndexerConstants.INDEX_MOTOR_ID2);
    private final TalonFX indexerMotor3 = new TalonFX(IndexerConstants.INDEX_MOTOR_ID3);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private static IndexerSubsystem instance;

    public static IndexerSubsystem getInstance() {
        if (instance == null) {
            instance = new IndexerSubsystem();
        }
        return instance;
    }

    public static IndexerSubsystem subsystem() {
        return getInstance();
    }
    public IndexerSubsystem(){
        indexerMotor1.getConfigurator().apply(IndexerConfig.config());
        indexerMotor2.getConfigurator().apply(IndexerConfig.config2());
        indexerMotor3.getConfigurator().apply(IndexerConfig.config2());

    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        indexing(1);

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
                    () -> indexingIMethods(),
                    () -> emptyMethod(),
                    () -> indexingEMethods()
            }
    };
    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }

    private void indexingIMethods() {
        indexerMotor1.setControl(Voltage.withOutput(8.0));
        indexerMotor2.setControl(Voltage.withOutput(8.0));
        indexerMotor3.setControl(Voltage.withOutput(8.0));
    }

    private void indexingEMethods() {
        indexerMotor1.setControl(Voltage.withOutput(0));
        indexerMotor2.setControl(Voltage.withOutput(0));
        indexerMotor3.setControl(Voltage.withOutput(0));
    }

}
