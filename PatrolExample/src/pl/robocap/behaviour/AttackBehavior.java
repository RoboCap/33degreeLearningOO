package pl.robocap.behaviour;

import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import pl.robocap.sensor.PatrolUltrasonicSensor;

public class AttackBehavior implements Behavior {

	private PatrolUltrasonicSensor ultrasonicSensor = new PatrolUltrasonicSensor(
			SensorPort.S4);
	private boolean suppressed = false;

	@Override
	public boolean takeControl() {
		return ultrasonicSensor.getDistance() < 0.15f;
	}

	@Override
	public void action() {
		suppressed = false;
		if (ultrasonicSensor.getDistance() < 0.15f) {
			Sound.playNote(Sound.FLUTE, Sound.C2, 500);
			Delay.msDelay(3000);
			while (ultrasonicSensor.getDistance() < 0.15f && !suppressed) {
				Sound.beepSequenceUp();
				Delay.msDelay(2000);
			}
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
