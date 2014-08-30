//************************************************************************ 
// Class Name: Star
// 
// Author: Dr. Colleen van Lent (move() method by Seth Johnson)
// 
// Collaborators and/or supporting material: For move(): In-class lectures, slides, code samples
// 
// Description: Draws Stars that move in space
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


public class Star {
	private int xPos;
	private int yPos;
	Color starColor;
	static final int DIM = 600;
	
	public Star(){
		Random r = new Random();
		xPos = r.nextInt(DIM);
		yPos = r.nextInt(DIM);
		starColor = Color.yellow;
	}
	
	public void drawStar(Graphics g){
		int[] xArr1 = {xPos, xPos+5, xPos+10};
		int[] yArr1 = {yPos + 10, yPos, yPos+10};
		int[] xArr2 = {xPos, xPos+10, xPos+5};
		int[] yArr2 = {yPos +5, yPos+5, yPos+10};
		
		g.setColor(starColor);
		g.fillPolygon(xArr1, yArr1, 3);
		g.fillPolygon(xArr2, yArr2, 3);	
	}
	
	public void move(int w) {
		int panelWidth = w;
		this.xPos = this.xPos - 3;
		if (this.xPos <= 0) {
			this.xPos = panelWidth;
		}
	}
}