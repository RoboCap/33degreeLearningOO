package pl.robocap.sensor;

import lejos.hardware.port.Port;
import lejos.hardware.port.UARTPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class PatrolGyroSensor extends EV3GyroSensor {

	public PatrolGyroSensor(Port port) {
		super(port);
	}

	public PatrolGyroSensor(UARTPort port) {
		super(port);
	}
	
	public float getAngle() {
		SampleProvider angleMode = getAngleMode();
		// initialise an array of floats for fetching samples
		float[] sample = new float[angleMode.sampleSize()];
		// fetch a sample
		angleMode.fetchSample(sample, 0);
		
		return sample[0];
	}
	
}
