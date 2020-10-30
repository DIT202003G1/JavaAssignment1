package net.frankium.sunwaydiit.assignment.Assignment1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

public class Question4 {
	//vars
	private final static JFrame menuWindow = new JFrame("Fitness Center Registration Cost Calculator");
	private final static DecimalFormat df = new DecimalFormat("#.##");
	private final static Map<String,Double> PRICE_CONSTANTS = new HashMap<String,Double>(){{
		put("SeniorDiscount",0.3);
		put("12MonthAbove",0.15);
		put("5PersonalTrainingAbove",0.20);
	}};

	//main
	public static void main(String[] args) {
		constructMenu();
		menuWindow.setVisible(true);
	}

	//Utils
	private static void msgbox(String msg,String title){
		JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
	}
	private static String prompt(String msg){
		JPanel w = new JPanel();
		String[] options = {"ok"};
		JLabel l = new JLabel(msg);
		JTextField t = new JTextField(10);
		w.add(l);
		w.add(t);

		int selectedOption = JOptionPane.showOptionDialog(null, w, "The Title", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
		if (selectedOption == 0) return t.getText();
		else return "";
	}

	//Methods for Calculation and getting information
	private static double calculateCost(double basePricePerMonth, int numOfMonths, double personalTrainingSessionCost, int numOfPersonalTrainingSession, boolean isSenior, boolean isSixSessionOrMore, boolean isTwMonth){
		double cost = basePricePerMonth * numOfMonths;
		if (numOfMonths >= 12) cost = basePricePerMonth * numOfMonths * (1 - PRICE_CONSTANTS.get("12MonthAbove"));
		if (isSenior) cost = cost * (1 - PRICE_CONSTANTS.get("SeniorDiscount"));
		if (isSixSessionOrMore) cost = cost + personalTrainingSessionCost * numOfPersonalTrainingSession * (1 - PRICE_CONSTANTS.get("5PersonalTrainingAbove"));
		return cost;
	}
	private static void startCalculationProcess(){
		msgbox("Please enter some information to begin!\n\nPress ok to proceed.","Information");

		//cost of regular membership per month.
		double chargeOfRegularMembership;
		while (true){
			try{
				chargeOfRegularMembership = Double.parseDouble(prompt("Please enter the cost of a regular membership per month:"));
				if (chargeOfRegularMembership <= 0) throw new Exception("Inputted Negative/Zero");
				break;
			}catch(Exception e){ msgbox("Please enter a positive number (cannot be zero)","Error"); }
		}
		//cost of personal training
		double costOfPersonalTraining;
		while (true){
			try{
				costOfPersonalTraining = Double.parseDouble(prompt("Please enter the cost of one personal training session:"));
				if (costOfPersonalTraining <= 0) throw new Exception("Inputted Negative/Zero");
				break;
			}catch(Exception e){ msgbox("Please enter a positive number (cannot be zero)","Error"); }
		}
		//Senior Citizen
		boolean isSeniorCitizen = false;
		int r = JOptionPane.showConfirmDialog(null,"Are you a Senior Citizen?","Input",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (r == JOptionPane.YES_OPTION) isSeniorCitizen = true;
		//Num of personal training session
		int numOfMonth;
		while (true){
			try{
				numOfMonth = Integer.parseInt(prompt("Please enter the number of month which you're paying for:"));
				if (numOfMonth <= 0) throw new Exception("Inputted Negative/Zero");
				break;
			}catch(Exception e){ msgbox("Please enter a positive whole number","Error"); }
		}
		//Num of personal training session
		int numOfPersonalTrainingSession;
		while (true){
			try{
				numOfPersonalTrainingSession = Integer.parseInt(prompt("Please enter the number of personal training session:"));
				if (numOfPersonalTrainingSession <= 0) throw new Exception("Inputted Negative/Zero");
				break;
			}catch(Exception e){ msgbox("Please enter a positive whole number","Error"); }
		}   
		//calculation
		String message = "The membership cost is: $" + df.format(calculateCost(chargeOfRegularMembership,numOfMonth,costOfPersonalTraining,numOfPersonalTrainingSession,isSeniorCitizen,(numOfPersonalTrainingSession>5),(numOfMonth>=12)));
		msgbox(message,"Result");
	};

	//Methods for Fee Info
	private static void showFeeInfo(){
		String message = MessageFormat.format("For every senior citizen, there will be a {0}% discount.\nIf you buy membership for twelve months, and will pay today, the discount will be {1}%\nIf you buy and pay for 6 or more personal training session today, the discount for each session will be {2}%",(int)(PRICE_CONSTANTS.get("SeniorDiscount")*100),(int)(PRICE_CONSTANTS.get("12MonthAbove") * 100),(int)(PRICE_CONSTANTS.get("5PersonalTrainingAbove") * 100));
		JOptionPane.showMessageDialog(null,message,"Price Information",JOptionPane.INFORMATION_MESSAGE);
	}

	//Methods for MainMenu
	private static JButton constructMenuButton(int index, String text){
		int width = 130;
		JButton a = new JButton(text);
		a.setBounds(20 + (index * (width + 20)),65,width,30);
		return a;
	}
	private static void constructMenu(){
		//define obj
		JButton menuButtons[] = new JButton[]{
			constructMenuButton(0,"Price Info"),
			constructMenuButton(1,"Calculate"),
			constructMenuButton(2,"Exit"),
		};
		JLabel l = new JLabel("<html>Welcome to Stay Healthy and Fit center. This program determines the cost of a new membership.</html>");
		l.setBounds(20,10,460,50);

		//Event listeners
		menuButtons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showFeeInfo();
			}
		});
		menuButtons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startCalculationProcess();
			}
		});
		menuButtons[2].addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) { System.exit(0); }});

		//register obj
		for (JButton i:menuButtons) menuWindow.add(i);
		menuWindow.add(l);

		//Window Configuration
		menuWindow.setSize(480,150);
		menuWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		menuWindow.setResizable(false);
		menuWindow.setLayout(null);
	}
}


