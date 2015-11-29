package pl.robocap.sensor;

import lejos.hardware.port.Port;
import lejos.hardware.port.UARTPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class PatrolUltrasonicSensor extends EV3UltrasonicSensor {

	public PatrolUltrasonicSensor(Port port) {
		super(port);
	}
	
	public PatrolUltrasonicSensor(UARTPort port) {
		super(port);
	}

	public float getDistance() {
		SampleProvider distanceMode = getDistanceMode();
		// initialise an array of floats for fetching samples
		float[] sample = new float[distanceMode.sampleSize()];
		// fetch a sample
		distanceMode.fetchSample(sample, 0);
		
		return sample[0];
	}
}
