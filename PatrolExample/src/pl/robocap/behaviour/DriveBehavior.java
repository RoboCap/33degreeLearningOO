package pl.robocap.behaviour;

import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;

public class DriveBehavior implements Behavior {

	private boolean suppressed = false;
	
	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		
		Motor.B.forward();
		Motor.C.forward();
		
		while(!suppressed) {
			Thread.yield();
		}
		
		Motor.B.stop();
		Motor.C.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

}
