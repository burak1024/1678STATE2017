package frc.robot.Subsystems.grapper;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.lib.Subsystem;

public class GrapperSubsystem extends Subsystem {
    Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    private static GrapperSubsystem instance;

    public static GrapperSubsystem getInstance() {
        if (instance == null) {
            instance = new GrapperSubsystem();
        }
        return instance;
    }

    public static GrapperSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        grapping(1);

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
                    () -> grappingIMethods(),
                    () -> emptyMethod(),
                    () -> grappingEMethods()
            }
    };
public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }
    private void grappingIMethods() {
        solenoid.set(true);
    }

    private void grappingEMethods() {
        solenoid.set(false);
    }

}
