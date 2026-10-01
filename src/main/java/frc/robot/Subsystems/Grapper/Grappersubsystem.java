package frc.robot.Subsystems.Grapper;


import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.lib.Subsystem;

public class Grappersubsystem extends Subsystem{
    Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
}
