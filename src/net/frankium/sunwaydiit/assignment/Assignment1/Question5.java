package net.frankium.sunwaydiit.assignment.Assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

public class Question5 {
	private static SwimmingPool pool = new SwimmingPool(new double[]{30,15,10},0,0,0);
	public static JFrame constrcutGUI(){
		JFrame w = new JFrame("Swimming Pool");
		w.setSize(440,240);
		w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		w.setResizable(false);
		w.setLayout(null);
		
		//Instruction Label
		JLabel instruction = new JLabel("<html>Please select an option to begin!</html>");
		instruction.setBounds(20,10,460,40);
		instruction.setFont(new Font("Sans Serif",Font.PLAIN,12));
		w.add(instruction);
		
		//ShowInformation
		JButton showInformation = new JButton("Show Information");
		showInformation.setBounds(20,60,400,40);
		w.add(showInformation);
		//FillWater
		JButton fillWater = new JButton("Fill Water");
		fillWater.setBounds(20,110,400,40);
		w.add(fillWater);
		//DrainWater
		JButton drainWater = new JButton("Drain Water");
		drainWater.setBounds(20,160,400,40);
		w.add(drainWater);
		
		//Add OnClicks
		showInformation.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String message = MessageFormat.format("Pool Information:\n\nDimensions: {0}ft * {1}ft * {2}ft\n\nCurrent Water Amount: {3} Gallons ({4} Cubic Foot)\nCurrent Empty Spaces: {5} Gallons ({6} Cubic Foot)\n\nFill In Rate: {7} Gallon Per Min\nFill Out Rate: {8} Gallon Per Min\n\nTime to Fill until Full: {9} Min\nTime to Drain out: {10} Min",pool.getDimensions()[0],pool.getDimensions()[1],pool.getDimensions()[2],pool.getWaterAmountInGallon(),pool.getWaterAmount(),pool.getEmptySpaceInGallon(),pool.getEmptySpaceInCubicFoot(),pool.getFillingRate(),pool.getDrainingRate(),pool.timeToFill(),pool.timeToDrain());
					JOptionPane.showMessageDialog(null,message,"Pool Information",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		);
		fillWater.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						double fillRate = Double.parseDouble(JOptionPane.showInputDialog("Enter Fill Rate (Gallon Per Minutes)"));
						double time = Double.parseDouble(JOptionPane.showInputDialog("Enter Time (Minutes)"));
						pool.addWaterByGallon(time,fillRate);
						pool.setFillingRate(fillRate);
						JOptionPane.showMessageDialog(null,"Done.","Information",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception exception){
						JOptionPane.showMessageDialog(null,"Something went wrong, please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		drainWater.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						double drainRate = Double.parseDouble(JOptionPane.showInputDialog("Enter Drain Rate (Gallon Per Minutes)"));
						double time = Double.parseDouble(JOptionPane.showInputDialog("Enter Time (Minutes)"));
						pool.drainWaterByGallon(time,drainRate);
						pool.setDrainingRate(drainRate);
						JOptionPane.showMessageDialog(null,"Done.","Information",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception exception){
						JOptionPane.showMessageDialog(null,"Something went wrong, please try again.","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		return w;
	}
	public static void main(String[] args) {
		JFrame window = constrcutGUI();
		window.setVisible(true);
	}
}
