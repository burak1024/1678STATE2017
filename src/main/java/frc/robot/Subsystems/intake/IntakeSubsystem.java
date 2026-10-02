package frc.robot.Subsystems.intake;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class IntakeSubsystem extends Subsystem {
    private final TalonFX intakeMotor = new TalonFX(0);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private static IntakeSubsystem instance;

    public static IntakeSubsystem getInstance() {
        if (instance == null) {
            instance = new IntakeSubsystem();
        }
        return instance;
    }

    public static IntakeSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        intaking(1);

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
                    () -> intakingIMethods(),
                    () -> emptyMethod(),
                    () -> intakingEMethods()
            }
    };

    private void intakingIMethods() {
        intakeMotor.setControl(Voltage.withOutput(8.0));
    }

    private void intakingEMethods() {
        intakeMotor.setControl(Voltage.withOutput(0));
    }
}
