package pl.robocap.behaviour;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.subsumption.Behavior;
import pl.robocap.sensor.PatrolGyroSensor;

public class RotateBehavior implements Behavior {

	private static final int ROTATION_LEVEL = 170;
	private static final int DISTANCE = 1500;
	
	private boolean suppressed = false;
	private PatrolGyroSensor gyroSensor = new PatrolGyroSensor(SensorPort.S4);

	@Override
	public boolean takeControl() {
		return Motor.B.getTachoCount() > DISTANCE || Motor.C.getTachoCount() > DISTANCE;
	}

	@Override
	public void action() {
		suppressed = false;

		if (Motor.B.getTachoCount() != 0) {
			gyroSensor.reset();
		}

		Motor.B.backward();
		Motor.C.forward();
		
		while (gyroSensor.getAngle() < ROTATION_LEVEL && !suppressed) {
			Thread.yield(); // wait till turn is complete or suppressed is
							// called
		}

		Motor.B.stop();
		Motor.C.stop();

		if (gyroSensor.getAngle() >= ROTATION_LEVEL) {
			Motor.B.resetTachoCount();
			Motor.C.resetTachoCount();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
	
}
