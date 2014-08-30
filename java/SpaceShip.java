//************************************************************************ 
// Class Name: SpaceShip
// 
// Author: Seth Johnson
// 
// Collaborators and/or supporting material: In-class lectures, slides, code samples
// 
// Description: SpaceShip draws a space ship
// 
// Supplementary files: Requires the Screen class in order to run
// 
// Expected errors: see Screen
//
// Expected Score: see Screen
//************************************************************************* 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;



public class SpaceShip {
	
	private int xPos, yPos;
	private Color clr;
	private final int WIDTH = 40;
	private final int HEIGHT = 20;
	private boolean shooting = false;


	public SpaceShip() {
		Random r = new Random();
		xPos = r.nextInt(500);
		yPos = r.nextInt(500);
		clr = Color.red;
	}

	public void setColor(Color c) {
		this.clr = c;
	}
	
	public void setShooting(boolean s) {
		this.shooting = s;
	}
	
	public void move(Point p) {
		xPos = (int) p.getX();
		yPos = (int) p.getY();
	}
	
	public void draw(Graphics g, int width) {

		int screenWidth = width;
		
		// spaceship window dimensions
		// windows are square so this is both width and height
		final int WIN_SIZE = 5;

		// spaceship body
		g.setColor(clr);
		g.fillOval(xPos - WIDTH/2, yPos - HEIGHT/2, WIDTH, HEIGHT);

		// horizontal line in the middle of the spaceship
		g.setColor(Color.blue);
		g.drawLine(xPos - WIDTH/2, yPos, xPos + WIDTH/2, yPos);

		// 3 windows, left to right
		g.setColor(Color.cyan);
		g.fillRect(xPos - (WIDTH/3 - WIN_SIZE/2), yPos -5, WIN_SIZE, WIN_SIZE);
		g.fillRect(xPos - WIN_SIZE/2, yPos -5, WIN_SIZE, WIN_SIZE);
		g.fillRect(xPos + (WIDTH/3 - WIN_SIZE), yPos -5, WIN_SIZE, WIN_SIZE);

		// wings or horns or something
		g.setColor(Color.green);
		g.drawArc(xPos-WIDTH-5, yPos-HEIGHT, WIDTH, HEIGHT, 0, 75);
 		g.drawArc(xPos+5, yPos-HEIGHT, WIDTH, HEIGHT, 180, -75);
 		
 		// shooting lasers
 		if (this.shooting) {
 			g.setColor(Color.pink);
 			g.drawLine(xPos + WIDTH/2, yPos, screenWidth, yPos);
 		}

	}
	

	public int getXPos() {
		return this.xPos;
	}
	public int getYPos() {
		return this.yPos;
	}
}

