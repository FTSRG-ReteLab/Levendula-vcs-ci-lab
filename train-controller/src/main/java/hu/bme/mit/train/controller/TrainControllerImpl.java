package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private Thread thread;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	public TrainControllerImpl() {
		thread = new Thread() {
			public void run() {
				thread.run();
				try {
					followSpeed();
					thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}

	@Override
	public void followSpeed() {
		referenceSpeed += step;
		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) referenceSpeed = speedLimit;
		if (referenceSpeed < 0) referenceSpeed = 0;
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
	}
	public void emergencyStop(){
		this.step = -1000;//I got you. 
	}

}
