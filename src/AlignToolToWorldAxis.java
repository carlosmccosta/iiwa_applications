package iiwa_applications;


import static com.kuka.roboticsAPI.motionModel.BasicMotions.lin;

import javax.inject.Inject;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Frame;

public class AlignToolToWorldAxis extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;


	@Override
	public void initialize() {
	}


	@Override
	public void run() {
		Frame frame = lbr.getCurrentCartesianPosition(lbr.getFlange(), getApplicationData().getFrame("/base"));
		frame.setAlphaRad(getClosestRectAngle(frame.getAlphaRad()));
		frame.setBetaRad(getClosestRectAngle(frame.getBetaRad()));
		frame.setGammaRad(getClosestRectAngle(frame.getGammaRad()));
		lbr.move(lin(frame));
	}
	
	public double getClosestRectAngle(double angle) {
		if (angle < Math.toRadians(-135.0))
			return Math.toRadians(-180.0);
		if (angle < Math.toRadians(-45.0))
			return Math.toRadians(-90.0);
		if (angle < Math.toRadians(45.0))
			return 0.0;
		if (angle < Math.toRadians(135.0))
			return Math.toRadians(90.0);
		else
			return Math.toRadians(180.0);
	}
}