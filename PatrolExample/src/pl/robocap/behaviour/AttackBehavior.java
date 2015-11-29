package pl.robocap.behaviour;

import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import pl.robocap.sensor.PatrolUltrasonicSensor;

public class AttackBehavior implements Behavior {

	private static final float ATTACK_DISTANCE = 0.2f;
	private PatrolUltrasonicSensor ultrasonicSensor = new PatrolUltrasonicSensor(
			SensorPort.S3);
	private boolean suppressed = false;

	@Override
	public boolean takeControl() {
		return ultrasonicSensor.getDistance() < ATTACK_DISTANCE;
	}

	@Override
	public void action() {
		suppressed = false;
		if (ultrasonicSensor.getDistance() < ATTACK_DISTANCE) {
			Sound.playNote(Sound.FLUTE, Sound.C2, 500);
			Delay.msDelay(3000);
			while (ultrasonicSensor.getDistance() < ATTACK_DISTANCE && !suppressed) {
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
