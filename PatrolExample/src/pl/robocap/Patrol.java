package pl.robocap;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import pl.robocap.behavior.AttackBehavior;
import pl.robocap.behavior.DriveBehavior;
import pl.robocap.behavior.RotateBehavior;

public class Patrol {

	public static void main(String[] args) {
		Behavior[] patrolBehaviors = { new DriveBehavior(), new RotateBehavior(), new AttackBehavior() };
		Arbitrator arbitrator = new Arbitrator(patrolBehaviors);

		LCD.drawString("Ready for action!", 0, 1);
		Button.waitForAnyPress();

		arbitrator.go();
	}

}
