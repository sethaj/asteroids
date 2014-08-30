//************************************************************************ 
// Class Name: Asteroid
// 
// Author: Seth Johnson
// 
// Collaborators and/or supporting material: in class lectures and code
// 
// Description of the class: Creates asteroids that move around the screen.
// 
// Supplementary files: Requires the Screen class in order to function
// 
// List of errors that can be expected: see Screen
// 
// Expected score: see Screen
//************************************************************************* 


import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Asteroid {
	
	private int xPos, yPos;
	Random r = new Random();
	private int asteroidWidth;
	private Color asteroidColor = Color.gray;
	static final int DIM = 600;
	
	public Asteroid() {

		this.xPos = r.nextInt(DIM);
		this.yPos = r.nextInt(DIM);
		//System.out.println("x: "+ xPos + " y: " + yPos);
		//Set the value of width to an integer between 5..20.
		this.asteroidWidth = r.nextInt(15) + 6;
	}
	
	public void draw (Graphics g) {
		g.setColor(asteroidColor);
		g.fillOval(xPos, yPos, asteroidWidth, asteroidWidth);
	}
	
	public void move() {
		// modify the xPos AND the yPos by a small random amount, anywhere 
		// from -15 to +15 pixels.
		xPos = xPos + ( r.nextInt(16) - r.nextInt(16) );
		yPos = yPos + ( r.nextInt(16) - r.nextInt(16) );
	}
	
}
