package swing;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.text.NumberFormat;
import javax.swing.border.*;

public class Calculator extends JFrame {

	JButton buttonCalculate;
	JLabel labelNumber1, labelNumber2, labelHowManyTimes;
	JTextField textFieldNumber1, textFieldNumber2;
	JCheckBox dollarSign, commaSeparator;
	JRadioButton addNums, subtractNums, multNums, divideNums;
	JSlider howManyTimes;

	double number1, number2, totalCalc;

	public static void main(String[] args) {
		new Calculator();
	}

	public Calculator() {
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calculator");
		JPanel thePanel = new JPanel();

		buttonCalculate = new JButton("Calculate");
		ListenForButton lForButton = new ListenForButton();
		buttonCalculate.addActionListener(lForButton);
		thePanel.add(buttonCalculate);

		labelNumber1 = new JLabel("Number 1");
		thePanel.add(labelNumber1);
		textFieldNumber1 = new JTextField("", 5);
		thePanel.add(textFieldNumber1);

		labelNumber2 = new JLabel("Number 2");
		thePanel.add(labelNumber2);
		textFieldNumber2 = new JTextField("", 5);
		thePanel.add(textFieldNumber2);

		dollarSign = new JCheckBox("Dollars");
		commaSeparator = new JCheckBox("Commas");
		thePanel.add(dollarSign);
		thePanel.add(commaSeparator, true);

		addNums = new JRadioButton("Add");
		subtractNums = new JRadioButton("Subtract");
		multNums = new JRadioButton("Multiply");
		divideNums = new JRadioButton("Divide");
		ButtonGroup operation = new ButtonGroup();
		operation.add(addNums);
		operation.add(subtractNums);
		operation.add(multNums);
		operation.add(divideNums);
		JPanel operPanel = new JPanel();
		Border operBorder = BorderFactory.createTitledBorder("Operation");
		operPanel.setBorder(operBorder);
		operPanel.add(addNums);
		operPanel.add(subtractNums);
		operPanel.add(multNums);
		operPanel.add(divideNums);
		addNums.setSelected(true);
		thePanel.add(operPanel);

		labelHowManyTimes = new JLabel("Perform how many times");
		thePanel.add(labelHowManyTimes);
		howManyTimes = new JSlider(0, 99, 1);
		howManyTimes.setMinorTickSpacing(1);
		howManyTimes.setMajorTickSpacing(10);
		howManyTimes.setPaintTicks(true);
		howManyTimes.setPaintLabels(true);
		ListenForSlider lForSlider = new ListenForSlider();
		howManyTimes.addChangeListener(lForSlider);
		thePanel.add(howManyTimes);
		this.add(thePanel);
		this.setVisible(true);

		textFieldNumber1.requestFocus();

	}

	private class ListenForButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttonCalculate) {
				try {
					number1 = Double.parseDouble(textFieldNumber1.getText());
					number2 = Double.parseDouble(textFieldNumber2.getText());

				} catch (NumberFormatException exep) {
					JOptionPane.showMessageDialog(Calculator.this, "Please enter a valid number", "Error",
							JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}

				if (addNums.isSelected()) {
					totalCalc = addNumbers(number1, number2, howManyTimes.getValue());
				} else if (subtractNums.isSelected()) {

					totalCalc = subtractNumbers(number1, number2, howManyTimes.getValue());
				} else if (multNums.isSelected()) {

					totalCalc = multiplyNumbers(number1, number2, howManyTimes.getValue());
				} else {

					totalCalc = divideNumbers(number1, number2, howManyTimes.getValue());
				}

				if (dollarSign.isSelected()) {
					NumberFormat numFormat = NumberFormat.getCurrencyInstance();
					JOptionPane.showMessageDialog(Calculator.this, numFormat.format(totalCalc), "Solution",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (commaSeparator.isSelected()) {
					NumberFormat numFormat = NumberFormat.getNumberInstance();
					JOptionPane.showMessageDialog(Calculator.this, numFormat.format(totalCalc), "Solution",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(Calculator.this, totalCalc, "Solution",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		}

	}

	private class ListenForSlider implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == howManyTimes) {
				labelHowManyTimes.setText("Perform how many times? " + howManyTimes.getValue());
			}
		}
	}

	public static double addNumbers(double number1, double number2, int howManyTimes) {

		double total = 0;
		int i = 1;

		while (i <= howManyTimes) {
			total = total + (number1 + number2);
			i++;
		}

		return total;
	}

	public static double subtractNumbers(double number1, double number2, int howManyTimes) {

		double total = 0;
		int i = 1;

		while (i <= howManyTimes) {
			total = total + (number1 - number2);
			i++;
		}

		return total;
	}
	
	public static double multiplyNumbers(double number1, double number2, int howManyTimes) {

		double total = 0;
		int i = 1;

		while (i <= howManyTimes) {
			total = total + (number1 * number2);
			i++;
		}

		return total;
	}
	
	public static double divideNumbers(double number1, double number2, int howManyTimes) {

		double total = 0;
		int i = 1;

		while (i <= howManyTimes) {
			total = total + (number1 / number2);
			i++;
		}

		return total;
	}

}
