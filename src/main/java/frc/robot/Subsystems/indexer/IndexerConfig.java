package frc.robot.Subsystems.indexer;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;

public class IndexerConfig {
    public static TalonFXConfiguration config() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Slot0.kS = IndexerConstants.kS;
        config.Slot0.kV = IndexerConstants.kV;
        config.Slot0.kA = IndexerConstants.kA;
        config.Slot0.kP = IndexerConstants.kP;
        config.Slot0.kI = IndexerConstants.kI;
        config.Slot0.kD = IndexerConstants.kD;

        return config;
    }
    public static TalonFXConfiguration config2(){
        TalonFXConfiguration config = config();
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        return config;
    }
}
