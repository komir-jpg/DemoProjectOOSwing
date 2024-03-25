package Controllers;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.InsightsFrame;
import DAO.*;

public class InsightsController {
	
	InsightsFrame insightsFrame;
	Group groupSelected;
	
	public InsightsController(JFrame previousFrame,Group groupSelected) {
		this.groupSelected = groupSelected;
		setInsightsFrame(previousFrame);
		System.out.println(this.groupSelected);
	}
	
	public void setInsightsFrame(JFrame previousFrame) {
		insightsFrame = new InsightsFrame(this);
		SetFramePositionOffset(insightsFrame, GetFramePosition(previousFrame));
		SetFrameSize(insightsFrame, GetFrameSize(previousFrame));
		insightsFrame.setVisible(true);
	}
	
	private Point GetFramePosition(JFrame frame) {
		Point point;
		point = frame.getLocationOnScreen();
		return point;
	}
	private void SetFramePositionOffset(JFrame frame,Point point) {
		point.move(point.x+100, point.y+100);
		frame.setLocation(point);
	}
	
	private Dimension GetFrameSize(JFrame frame) {
		Dimension dimension;
		dimension = frame.getSize();
		return dimension;
	}
	private void SetFrameSize(JFrame frame,Dimension dimension) {
		frame.setSize(dimension);
	}
}
