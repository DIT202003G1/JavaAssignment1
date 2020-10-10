package net.frankium.sunwaydiit.assignment.Assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

public class Question3 {
	//GUI Elements
	private static JFrame MainWindow = new JFrame("Calculator for Cost of Picture Framing Service");
	private static Map<String,JButton> buttons = new HashMap<String, JButton>();
	private static Map<String,JLabel> labels = new HashMap<String, JLabel>();
	private static Map<String,JTextField> inputFields = new HashMap<String, JTextField>();
	private static Map<String,JRadioButton> radioButtons = new HashMap<String, JRadioButton>();

	//Constants
	private final static int FRAME_WIDTH = 1;
	private final static Map<String,Double> PRESET_PRICES = new HashMap<String,Double>(){{
		put("coloring",0.1);
		put("regularFramePerInch",0.15);
		put("fancyFramePerInch",0.25);
		put("cardboardPerSqInch",0.02);
		put("glassPerSqInch",0.07);
		put("crown",0.35);
	}};

	//Utils
	private static DecimalFormat df = new DecimalFormat("#.##");

	public static void main(String[] args) {
		//Instruction Label
		labels.put("mainInstruction",new JLabel("<html>Please enter some information and press calculate to calculate <br/>the cost!</html>"));
		labels.get("mainInstruction").setBounds(20,10,460,40);
		labels.get("mainInstruction").setFont(new Font("Sans Serif",Font.PLAIN,12));

		//Dimension Inputs
		labels.put("dimensionLabel",new JLabel("Picture Dimensions (In Inch)"));
		labels.get("dimensionLabel").setBounds(20,50,460,40);

		labels.put("widthLabel",new JLabel("Width"));
		labels.get("widthLabel").setBounds(20,90,460,40);
		labels.get("widthLabel").setFont(new Font("Sans Serif",Font.PLAIN,12));

		labels.put("lengthLabel",new JLabel("Length"));
		labels.get("lengthLabel").setBounds(220,90,460,40);
		labels.get("lengthLabel").setFont(new Font("Sans Serif",Font.PLAIN,12));

		inputFields.put("widthField",new JTextField());
		inputFields.get("widthField").setBounds(75,90+10,100,30);

		inputFields.put("lengthField",new JTextField());
		inputFields.get("lengthField").setBounds(275,90+10,100,30);

		//Type of Frame
		labels.put("frameTypeLabel",new JLabel("Frame Type"));
		labels.get("frameTypeLabel").setBounds(20,150,460,40);
		labels.get("frameTypeLabel").setFont(new Font("Sans Serif",Font.BOLD,12));

		radioButtons.put("normalFrame",new JRadioButton("Normal Frame"));
		radioButtons.get("normalFrame").setBounds(20,190,150,40);
		radioButtons.get("normalFrame").setSelected(true);

		radioButtons.put("fancyFrame",new JRadioButton("Fancy Frame"));
		radioButtons.get("fancyFrame").setBounds(220,190,150,40);

		radioButtons.get("normalFrame").addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					radioButtons.get("fancyFrame").setSelected(false);
					radioButtons.get("normalFrame").setSelected(true);
				}
			}
		);
		radioButtons.get("fancyFrame").addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					radioButtons.get("fancyFrame").setSelected(true);
					radioButtons.get("normalFrame").setSelected(false);
				}
			}
		);


		//ColorSelection
		labels.put("colorLabel",new JLabel("Select Color"));
		labels.get("colorLabel").setBounds(20,250,460,40);
		labels.get("colorLabel").setFont(new Font("Sans Serif",Font.BOLD,12));

		radioButtons.put("whiteColor",new JRadioButton("White"));
		radioButtons.get("whiteColor").setBounds(20,290,150,40);
		radioButtons.get("whiteColor").setSelected(true);

		radioButtons.put("customColor",new JRadioButton("Custom Color"));
		radioButtons.get("customColor").setBounds(220,290,150,40);

		inputFields.put("customColor",new JTextField());
		inputFields.get("customColor").setBounds(220,330,150,30);
		inputFields.get("customColor").setEnabled(false);

		radioButtons.get("whiteColor").addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					radioButtons.get("whiteColor").setSelected(true);
					radioButtons.get("customColor").setSelected(false);
					inputFields.get("customColor").setEnabled(false);
				}
			}
		);
		radioButtons.get("customColor").addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					radioButtons.get("whiteColor").setSelected(false);
					radioButtons.get("customColor").setSelected(true);
					inputFields.get("customColor").setEnabled(true);
				}
			}
		);

		//Crown
		labels.put("crownLabel",new JLabel("Add Crowns"));
		labels.get("crownLabel").setBounds(20,370,460,40);
		labels.get("crownLabel").setFont(new Font("Sans Serif",Font.BOLD,12));

		labels.put("crownNumber",new JLabel("Number of Crowns"));
		labels.get("crownNumber").setBounds(20,410,460,40);
		labels.get("crownNumber").setFont(new Font("Sans Serif",Font.PLAIN,12));

		inputFields.put("crownField",new JTextField("0"));
		inputFields.get("crownField").setBounds(175,410+10,50,30);

		//Calculate Button
		buttons.put("resetButton",new JButton("Reset"));
		buttons.get("resetButton").setBounds(190,500,150,40);

		buttons.put("calculateButton",new JButton("Calculate"));
		buttons.get("calculateButton").setBounds(20,500,150,40);

		buttons.get("resetButton").addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					inputFields.get("widthField").setText("");
					inputFields.get("lengthField").setText("");
					inputFields.get("customColor").setText("");
					inputFields.get("crownField").setText("0");
					inputFields.get("customColor").setEnabled(false);
					radioButtons.get("customColor").setSelected(false);
					radioButtons.get("whiteColor").setSelected(true);
					radioButtons.get("fancyFrame").setSelected(false);
					radioButtons.get("normalFrame").setSelected(true);
				}
			}
		);
		buttons.get("calculateButton").addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						validateInformation();
						String message = "The Calculation Result is as following:\n\n";
						double dimension[] = {Double.parseDouble(inputFields.get("lengthField").getText()),Double.parseDouble(inputFields.get("widthField").getText())};
						double dimensionWithFrame[] = {dimension[0]+2,dimension[1]+2};
						double area = dimension[0] * dimension[1];

						//Frame Costs
						double frameCost;
						if (radioButtons.get("normalFrame").isSelected()) {
							frameCost = (PRESET_PRICES.get("regularFramePerInch") * (dimensionWithFrame[0]*2 + dimensionWithFrame[1] * 2));
						} else{
							frameCost = (PRESET_PRICES.get("fancyFramePerInch") * (dimensionWithFrame[0]*2 + dimensionWithFrame[1] * 2));
						}
						message += "Frame Cost = $"+ df.format(frameCost) + "\n";

						//Color Cost
						double colorCost = 0;
						if (radioButtons.get("customColor").isSelected()) {
							colorCost = PRESET_PRICES.get("coloring");
							message += "Coloring Cost = $"+ df.format(colorCost) + "\n";
						};

						//Crown Cost
						double crownCost;
						crownCost = PRESET_PRICES.get("crown") * Integer.parseInt(inputFields.get("crownField").getText());
						if (crownCost != 0){
							message += "Crown Cost = $"+ df.format(crownCost) + "\n";
						}

						//Glass Cost
						double glassCost;
						glassCost = PRESET_PRICES.get("glassPerSqInch") * dimension[0] * dimension[1];
						message += "Glass Cost = $"+ df.format(glassCost) + "\n";

						//Cardboard Cost
						double cardboardCost;
						cardboardCost = PRESET_PRICES.get("cardboardPerSqInch") * dimension[0] * dimension[1];
						message += "Cardboard Cost = $"+ df.format(cardboardCost) + "\n\n";

						message += "TOTAL COST = $"+ df.format(frameCost + colorCost + crownCost + glassCost + cardboardCost);
						JOptionPane.showMessageDialog(null,message,"Result",JOptionPane.INFORMATION_MESSAGE);
					} catch(Exception exception){
						JOptionPane.showMessageDialog(null,exception.getMessage(),"Message",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);

		for (Map.Entry<String,JButton> i : buttons.entrySet()) MainWindow.add(i.getValue());
		for (Map.Entry<String,JLabel> i : labels.entrySet()) MainWindow.add(i.getValue());
		for (Map.Entry<String,JTextField> i : inputFields.entrySet()) MainWindow.add(i.getValue());
		for (Map.Entry<String,JRadioButton> i : radioButtons.entrySet()) MainWindow.add(i.getValue());
		MainWindow.setSize(450,600);
		MainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		MainWindow.setResizable(false);
		MainWindow.setLayout(null);
		MainWindow.setVisible(true);
	}
	private static void validateInformation() throws Exception{
		boolean isError = false;
		String ErrMessage = "";
		try{
			if ((Double.parseDouble(inputFields.get("widthField").getText()) <= 0)||(Double.parseDouble(inputFields.get("lengthField").getText()) <= 0)) throw new Exception("Negative Dimensions");
		}catch(Exception e){
			ErrMessage += "Width and Length has to be Positive Number (None Zero)\n\n";
			isError = true;
		}
		try{
			if (Integer.parseInt(inputFields.get("crownField").getText()) < 0) throw new Exception("Negative Crown Number");
		} catch (Exception e){
			ErrMessage += "Number of Crowns has to be Positive Whole Number\n\n";
			isError = true;
		}
		if (radioButtons.get("customColor").isSelected() && inputFields.get("customColor").getText().isBlank()){
			ErrMessage += "Color cannot be Blank\n\n";
			isError = true;
		}
		if (isError) throw new Exception(ErrMessage);
	}
}