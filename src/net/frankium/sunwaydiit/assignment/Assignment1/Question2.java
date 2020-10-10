package net.frankium.sunwaydiit.assignment.Assignment1;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.MessageFormat;

public class Question2 {
	public static void main(String[] args) {

		//Define GUI Elements
		final JFrame MainWindow = new JFrame("Number Sorter");
		JButton sortButton = new JButton("Sort!");
		JTextField num1Field = new JTextField();
		JTextField num2Field = new JTextField();
		JTextField num3Field = new JTextField();
		JLabel inputLabel = new JLabel("Type in Numbers:");
		JLabel instructionLabel = new JLabel("<html>Enter some numbers and press sort to sort in<br/>ascending order.</html>");
		MainWindow.setSize(400,200);
		sortButton.setBounds(280,110,100, 40);
		instructionLabel.setBounds(20,10,360,43);
		num1Field.setBounds(160, 60,50,35);
		num2Field.setBounds(220, 60,50,35);
		num3Field.setBounds(280, 60,50,35);
		inputLabel.setBounds(20,60,150,35);
		MainWindow.add(sortButton);
		MainWindow.add(instructionLabel);
		MainWindow.add(num1Field);
		MainWindow.add(num2Field);
		MainWindow.add(num3Field);
		MainWindow.add(inputLabel);
		MainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		MainWindow.setResizable(false);
		MainWindow.setLayout(null);
		MainWindow.setVisible(true);

		sortButton.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						double nums[] = new double[]{Double.parseDouble(num1Field.getText()),Double.parseDouble(num2Field.getText()),Double.parseDouble(num3Field.getText())};
						double temp;
						if (nums[0]>nums[1]){
							temp = nums[0];
							nums[0] = nums[1];
							nums[1] = temp;
						} if (nums[1]>nums[2]){
							temp = nums[1];
							nums[1] = nums[2];
							nums[2] = temp;
						} if (nums[0]>nums[1]){
							temp = nums[0];
							nums[0] = nums[1];
							nums[1] = temp;
						}
						JOptionPane.showMessageDialog(null,MessageFormat.format("Sorting Result: {0}, {1}, {2}",nums[0],nums[1],nums[2]),"Result",JOptionPane.INFORMATION_MESSAGE);
					} catch(Exception exception) {
						JOptionPane.showMessageDialog(null,"Something went wrong! Please enter only numbers","Message",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
	}
}
