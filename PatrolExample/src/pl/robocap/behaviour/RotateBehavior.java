package pl.robocap.behaviour;

import pl.robocap.sensor.PatrolGyroSensor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.subsumption.Behavior;

public class RotateBehavior implements Behavior {

	private static final int DISTANCE = 20;
	
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

		Motor.B.rotate(Integer.MAX_VALUE, true);
		Motor.C.rotate(-Integer.MAX_VALUE, true);

		while (gyroSensor.getAngle() < 360 && !suppressed) {
			Thread.yield(); // wait till turn is complete or suppressed is
							// called
		}

		Motor.B.stop();
		Motor.C.stop();

		if (gyroSensor.getAngle() >= 360) {
			Motor.B.resetTachoCount();
			Motor.C.resetTachoCount();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
