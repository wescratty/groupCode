import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;

import java.lang.Math;

public class Week3Gui extends JPanel {

	private JLabel problemlb, clocklb;
	private JButton Start;
	private JPanel buttonPanel;
	private Timer timer;

	private double hypontinuse = 0;
	private double clockIt = 0;
	
	private int yval1 = 0;
	private int yval2 = 0;
	private int yval3 = 0;
	private int op = 0;
	private int op1 = 0;
	private int op2 = 0;
	private int MAX = 10;
	private int ans = 0;
	private int answer1 = 1;
	private int answer2 = 2;
	private int answer3 = 3;
	private int score = 0;
	private int tolerance = 50;
	private int speed = 10;
	private int step1 = 0;
	private int step2 = 0;
	private int step3 = 0;
	private int top = 3;
	
	private JComboBox operands;// 
	private String[] operators = { "+", "-", "/", "*" }; //
	private String problem = "a + b";
	

	Random myrand = new Random();

	public Week3Gui() {
		
		
		// 電電電電電電電電電電電電電 Components 電電電電電電電電電電電電電�

		problemlb = new JLabel(" Score:" + score + "			" + "Problem is: "
				+ problem);
		clocklb = new JLabel("Clock :" + clockIt);
		Start = new JButton("Start");
		operands = new JComboBox(operators);
		buttonPanel = new JPanel();

		// 電電電電電電電電電電電電電 Panel set up 電電電電電電電電電電電電電�
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLoweredBevelBorder());
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(1000, 750));
		add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.add(problemlb);
		buttonPanel.add(Start);
		buttonPanel.add(operands);
		buttonPanel.add(clocklb);

		addMouseListener(new clickListener());
		timer = new Timer(speed, new buttonListener());
		Start.addActionListener(new buttonListener());

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawString("" + answer1, 200, yval1);
		g.drawString("" + answer2, 400, yval2);
		g.drawString("" + answer3, 600, yval3);

	}

	private class clickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			Point point = (e.getPoint());
			if (timer.isRunning())
			{
			if (pythagoreanTheorem(point.x, 200, point.y, yval1) < tolerance) {
				if (answer1 == ans) {
					score++;
					updatelabel();
					reset();

				}
			}else

			if (pythagoreanTheorem(point.x, 400, point.y, yval2) < tolerance) {
				if (answer2 == ans) {
					score++;
					updatelabel();
					reset();

				}
			}else
			if (pythagoreanTheorem(point.x, 600, point.y, yval3) < tolerance) {
				if (answer3 == ans) {
					score++;
					updatelabel();
					reset();

				}
			}else
			{
				score--;
				reset();
			}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}
	}

	//
	// end constructor
	
	
	// 電電電電電電電電電電電� Methods  電電電電電電電電電電電電電電�
	
	public int pythagoreanTheorem(double x1, double x2, double y1, double y2) {
		x1 = Math.abs(x1 - x2);
		y1 = Math.abs(y1 - y2);
		x1 = Math.pow(x1, 2);
		y1 = Math.pow(y1, 2);

		x1 = x1 + y1;
		hypontinuse = Math.sqrt(x1);
		return (int) (hypontinuse);

	}
	//電電電電電電電電電電電電電電電電電電電

	public void Questions(int choice) {

		op1 = myrand.nextInt(MAX) + 1;
		op2 = myrand.nextInt(MAX) + 1;

		switch (choice) {
		case 0: // addition
			ans = op1 + op2;
			// operators[op] = "+";
			break;
		case 1: // subtraction
			ans = op1 - op2;
			// operator = "-";
			break;
		case 2: // division
			op2 = op1;
			op1 *= myrand.nextInt(MAX) + 1;
			ans = op1 / op2;
			// operator = "/";
			break;
		case 3: // multiplication
			ans = op1 * op2;
			// operator = "*";
			break;
		}

	}

	//電電電電電電電電電電電電電電電電電電電

	public String toString() {
		return "What is " + op1 + " " + operators[op] + " " + op2 + "?";
	}
	//電電電電電電電電電電電電電電電電電電電

	public void reset() {
		if (score > 10) {
			top++;
		}
		op = (operands.getSelectedIndex());
		Questions(op);
		yval1 = 0;
		yval2 = 0;
		yval3 = 0;
		step1 = myrand.nextInt(top) + 1;
		step2 = myrand.nextInt(top) + 1;
		step3 = myrand.nextInt(top) + 1;
		switch (myrand.nextInt(3)) {
		case 0:
			answer1 = ans;
			answer2 = myrand.nextInt(10);
			answer3 = myrand.nextInt(10);
			break;
		case 1:
			answer1 = myrand.nextInt(10);
			answer2 = ans;
			answer3 = myrand.nextInt(10);
			break;
		case 2:
			answer1 = myrand.nextInt(10);
			answer2 = myrand.nextInt(10);
			answer3 = ans;
			break;
		}
		updatelabel();

	}

	//電電電電電電電電電電電電電電電電電電電

	public void updatelabel() {
		problemlb.setText(" Score:" + score + "			" + "Problem is: "
				+ toString());
	}
	
	//電電電電電電電電 Buttons 電電電電電電電電電電電

	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == timer) {//
				clockIt += .010;
				clocklb.setText("Clock: " + (int) clockIt);
				if (clockIt > 30) {
					timer.stop();
				}
				yval1 += step1;
				yval2 += step2;
				yval3 += step3;
				if (yval1 >= 760 || yval2 >= 760 || yval3 >= 760) {
					reset();
				}
				repaint();

				
			} else if (event.getSource() == Start) {
				timer.start();
				reset();

//			} else if (event.getSource() == timer2) {
//				clockIt += .10;
//				reset();
//
			}
		}
	}
}
