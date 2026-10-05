package frc.robot.Subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Climb.ClimbSubsystem;
import frc.robot.Subsystems.Grapper.GrapperSubsystem;
import frc.robot.Subsystems.Shooter.ShooterSubsystem;
import frc.robot.Subsystems.indexer.IndexerSubsystem;
import frc.robot.Subsystems.intake.IntakeSubsystem;

public class SuperStructure extends SubsystemBase {
    private static SuperStructure instance;
    public static Translation2d targetPos;

    public static SuperStructure getInstance() {
        if (instance == null) {
            instance = new SuperStructure();
        }
        return instance;
    }

    private final Runnable[] methods = {
            () -> idleIMethods(),
            () -> intakingIMethods(),
            () -> shootingIMethods(),
            () -> climbingIMethods(),
            () -> grappingIMethods()
    };

    private void idleIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, GrapperSubsystem.state.idle, ShooterSubsystem.state.idle,
                ClimbSubsystem.state.idle, IndexerSubsystem.state.idle);
    }

    private void intakingIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.intaking, GrapperSubsystem.state.idle, ShooterSubsystem.state.idle,
                ClimbSubsystem.state.idle, IndexerSubsystem.state.idle);
    }

    private void shootingIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, GrapperSubsystem.state.idle,
                ShooterSubsystem.state.shooting, ClimbSubsystem.state.idle, IndexerSubsystem.state.indexing);
    }

    private void climbingIMethods() {
        changeSubsystemStates(IntakeSubsystem.state.idle, GrapperSubsystem.state.idle, ShooterSubsystem.state.idle,
                ClimbSubsystem.state.climbing, IndexerSubsystem.state.idle);
    }
    private void grappingIMethods(){
        changeSubsystemStates(IntakeSubsystem.state.idle, GrapperSubsystem.state.grapping, ShooterSubsystem.state.idle, ClimbSubsystem.state.idle, IndexerSubsystem.state.idle);
    }

    private void changeSubsystemStates(IntakeSubsystem.state intakeState, GrapperSubsystem.state GrapperState,
            ShooterSubsystem.state shooterState, ClimbSubsystem.state climbState, IndexerSubsystem.state IndexState) {
        IntakeSubsystem.subsystem().changeState(intakeState);
        GrapperSubsystem.subsystem().changeState(GrapperState);
        ShooterSubsystem.getInstance().changeState(shooterState);
        ClimbSubsystem.subsystem().changeState(climbState);
        IndexerSubsystem.subsystem().changeState(IndexState);
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        intaking(1),
        shooting(2),
        climbing(3),
        grapping(4);

        public final int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    @Override
    public void periodic() {

        SmartDashboard.putString("SuperStructure/State", currentState.toString());

    }

    public SuperStructure() {
        IntakeSubsystem.subsystem();
        GrapperSubsystem.subsystem();
        ShooterSubsystem.subsystem();
        ClimbSubsystem.subsystem();
        IndexerSubsystem.subsystem();
    }

    public void changeState(state newState) {
        if (this.currentState == newState)
            return;
        currentState = newState;
        methods[currentState.stateNum].run();
        SmartDashboard.putNumber("ActiveState", currentState.stateNum);
    }

}
