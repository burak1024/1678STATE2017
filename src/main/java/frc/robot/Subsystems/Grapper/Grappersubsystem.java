package frc.robot.Subsystems.Grapper;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.lib.Subsystem;

public class Grappersubsystem extends Subsystem {
    Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    private static Grappersubsystem instance;

    public static Grappersubsystem getInstance() {
        if (instance == null) {
            instance = new Grappersubsystem();
        }
        return instance;
    }

    public static Grappersubsystem subsystem() {
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

    private void grappingIMethods() {
        solenoid.set(true);
    }

    private void grappingEMethods() {
        solenoid.set(false);
    }

}
