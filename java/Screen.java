//************************************************************************ 
// Class Name: Screen
// 
// Author: Seth Johnson
// 
// Collaborators and/or supporting material: in-class lectures and code
// 
// Description of the class: This class draws a spaceship that can move
// around space following the mouse. Then the mouse button is pressed,
// a laser is fired. Stars in the background move in left to right, asteroids
// move around the screen.
// 
// Supplementary files: In order for Screen to run it needs access to the 
// SpaceShip, Star and Asteroid classes.
// 
// List of errors that can be expected: I don't expect any errors
// 
// Expected score: 94/100
//************************************************************************* 


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Screen extends JPanel implements ActionListener {

	private final int SCREEN_SIZE = 600;
	private SpaceShip ship;
	
	private final int NUM_STARS = 25;
	private ArrayList<Star> starList = new ArrayList<Star>();
	private Timer starTimer;
	private int STAR_DELAY = 10;
	
	private final int NUM_ASTEROIDS = 10;
	private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	private Timer asteroidTimer;
	private int ASTEROID_DELAY = 50;
	

	public Screen() {

		setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
		
		starTimer = new Timer(STAR_DELAY, this);
		asteroidTimer = new Timer(ASTEROID_DELAY, this);
		
		ship = new SpaceShip();
		
		for (int i=0; i < NUM_STARS; i++) {
			starList.add(new Star());
		}
		for (int i=0; i < NUM_ASTEROIDS; i++) {
			asteroidList.add(new Asteroid());
		}

		addMouseMotionListener(new ScreenMouseMotionListener());
		addMouseListener(new ScreenMouseListener());
		
		starTimer.start();
		asteroidTimer.start();
	}

	public void paintComponent(Graphics page) {

		super.paintComponent(page);
		setBackground(Color.black);

		for (Star star: starList) {
			star.drawStar(page);
		}
		for (Asteroid asteroid: asteroidList) {
			asteroid.draw(page);
		}
		ship.draw(page, getWidth());
	}
	
	public void actionPerformed (ActionEvent event) {
		
		if (event.getSource().equals(starTimer)) {
			for (Star star: starList) {
				star.move(getWidth());
			}
		}
		if (event.getSource().equals(asteroidTimer)) {
			for (Asteroid asteroid: asteroidList) {
				asteroid.move();
			}
		}
		repaint();
	}

	private class ScreenMouseMotionListener extends MouseMotionAdapter {

		public void mouseMoved (MouseEvent event) {
			ship.move(event.getPoint());
			repaint();
		}
		public void mouseDragged (MouseEvent event) {
			ship.move(event.getPoint());
			repaint();
		}
	}

	private class ScreenMouseListener extends MouseAdapter {

		public void mousePressed (MouseEvent event) {
			ship.setShooting(true);
			repaint();
		}

		public void mouseReleased (MouseEvent event) {
			ship.setShooting(false);
			repaint();
		}
	}


	public static void main(String[] args) {

		JFrame frame = new JFrame ("Asteroids");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new Screen());

		frame.pack();
		frame.setVisible(true);
	}
}
