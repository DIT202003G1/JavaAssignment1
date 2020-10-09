package net.frankium.sunwaydiit.assignment.Assignment1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.MessageFormat;
import java.text.DecimalFormat;

//Milk Carton Question, 20 Marks
public class Question1 {
	public static void main(String[] args) {
		//Define the variables
		final double CARTON_UNIT = 3.78;
		final double COST_PER_LITER = 0.38;
		final double PROFIT_PER_CARTON = 0.27;

		//Create GUI elements' instances
		final JFrame[] MainWindow = {new JFrame("Production Calculator")};
		JButton calculateButton = new JButton("Calculate");
		JTextField literInputField = new JTextField();
		JLabel inputLabel = new JLabel("Milk Production:");
		JLabel instructionLabel = new JLabel("<html>Enter the milk production in Liters to Calculate:<br/>Cartons Needed, Cost and Profit</html>");

		//Add event listeners to GUI elements
		calculateButton.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						double inputLiters = Double.parseDouble(literInputField.getText());
						if (inputLiters < 0) throw new Exception("Negative Liter Number");
						double cartons = inputLiters/CARTON_UNIT; //Calculate Cartons Needed
						double cost = inputLiters * COST_PER_LITER; //Calculate Price
						double profit = cartons * PROFIT_PER_CARTON; //Calculate Profit
						JOptionPane.showMessageDialog(null,MessageFormat.format("The calculation result of {0} Liters.\n\nCartons: {1}\nCost: ${2}\nProfit: ${3}",inputLiters,cartons,cost,profit),"Calculation Result",JOptionPane.INFORMATION_MESSAGE);
					} catch(Exception exception) {
						JOptionPane.showMessageDialog(null,"Something went wrong! Please re-enter the numbers!\n\nThe number has be POSITIVE without any other words (such as english letters)","Message",JOptionPane.ERROR_MESSAGE);
						literInputField.setText("");
					}
				}
			}
		);

		//Set element Size and Position
		MainWindow[0].setSize(400,200);
		calculateButton.setBounds(280,110,100, 40);
		instructionLabel.setBounds(20,10,360,43);
		literInputField.setBounds(150, 60,230,35);
		inputLabel.setBounds(20,60,150,35);

		//Add GUI Elements to Window
		MainWindow[0].add(calculateButton);
		MainWindow[0].add(instructionLabel);
		MainWindow[0].add(literInputField);
		MainWindow[0].add(inputLabel);

		//Some Configurations and Display Window
		MainWindow[0].setLayout(null);
		MainWindow[0].setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Sets Close Button to EXIT instead of hide window!!
		MainWindow[0].setVisible(true);
    }
}