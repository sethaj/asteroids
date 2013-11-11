include Java

import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.Timer
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ActionListener
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.awt.Graphics
import java.awt.Point
import java.awt.Polygon


class Asteroid
  attr_accessor :xPos, :yPos, :clr, :width

  def initialize
    self.xPos = Random.rand(600);
    self.yPos = Random.rand(600);
    self.clr = Color.gray;
    self.width = Random.rand(15) + 5
  end

  def draw(g) 
    g.setColor(self.clr)
    g.fillOval(xPos, yPos, self.width, self.width)
  end

  def move
    self.xPos = self.xPos + Random.rand(15) - Random.rand(15)
    self.yPos = self.yPos + Random.rand(15) - Random.rand(15)
  end
end


class SpaceShip
  attr_accessor :xPos, :yPos, :clr, :shooting

  WIDTH = 40
  HEIGHT = 20
  WIN_SIZE = 5

  def initialize 
    self.xPos = Random.rand(500);
    self.yPos = Random.rand(500);
    self.clr = Color.red;
    self.shooting = false
  end

  def setShooting(s) 
    self.shooting = s
  end

  def move(p)
    self.xPos = p.getX()
    self.yPos = p.getY()
  end

  def draw(g, width) 

    g.setColor(clr);
    g.fillOval(xPos - WIDTH/2, yPos - HEIGHT/2, WIDTH, HEIGHT);

    g.setColor(Color.blue);
    g.drawLine(xPos - WIDTH/2, yPos, xPos + WIDTH/2, yPos);

    g.setColor(Color.cyan);
    g.fillRect(xPos - (WIDTH/3 - WIN_SIZE/2), yPos - 5, WIN_SIZE, WIN_SIZE);
    g.fillRect(xPos - WIN_SIZE/2, yPos - 5, WIN_SIZE, WIN_SIZE);
    g.fillRect(xPos + (WIDTH/3 - WIN_SIZE), yPos - 5, WIN_SIZE, WIN_SIZE);

    g.setColor(Color.green);
    g.drawArc(xPos - WIDTH - 5, yPos-HEIGHT, WIDTH, HEIGHT, 0, 75);
    g.drawArc(xPos + 5, yPos-HEIGHT, WIDTH, HEIGHT, 180, - 75);
    
    if self.shooting
      g.setColor(Color.pink);
      g.drawLine(xPos + WIDTH/2, yPos, width, yPos);
    end
  end
    
end


class Star
  attr_accessor :xPos, :yPos, :clr

  def initialize 
    self.xPos = Random.rand(600);
    self.yPos = Random.rand(600);
    self.clr = Color.yellow;
  end

  def draw(g) 
    g.setColor(self.clr)

    p1 = Polygon.new();
    p1.addPoint(xPos, yPos + 10)
    p1.addPoint(xPos + 5, yPos)
    p1.addPoint(xPos + 10, yPos + 10)
  
    p2 = Polygon.new();
    p2.addPoint(xPos, yPos + 5)
    p2.addPoint(xPos + 10, yPos + 5)
    p2.addPoint(xPos + 5, yPos + 10)

    g.fillPolygon(p1)
    g.fillPolygon(p2) 

  end

  def move (width)
    self.xPos = self.xPos - 3
    if self.xPos <= 0
      self.xPos = width
    end
  end
end


class Screen < JPanel
  include MouseListener, MouseMotionListener, ActionListener

  def initialize
    super

    @stars = []
    (1 .. 25).each do |n| 
      @stars.push(Star.new())
    end

    @asteroids = []
    (1 .. 10).each do |n|
      @asteroids.push(Asteroid.new())
    end

    @ship = SpaceShip.new

    timer = Timer.new 10, self
    timer.start

    self.addMouseMotionListener self
    self.addMouseListener self
  end

  def paintComponent g
    super g
    self.setBackground Color.black
    @ship.draw(g, self.getWidth())

    @stars.each do |star|
      star.draw(g)
    end
    @asteroids.each do |a|
      a.draw(g)
    end
  end

  # ActionListener
  def actionPerformed e
    @stars.each do |star|
      star.move(self.getWidth())
    end
    @asteroids.each do |a|
      a.move()
    end
    self.repaint()
  end

  # MouseMotionListener abstract methods
  def mouseMoved e
    @ship.move(e.getPoint())
    self.repaint()
  end
  def mouseDragged e
  end

  # MouseListener abstract methods
  def mousePressed e
    @ship.setShooting(true)
  end
  def mouseReleased e
    @ship.setShooting(false)
  end
  def mouseClicked e
  end
  def mouseEntered e
  end
  def mouseExited e
  end

end

frame = JFrame.new("Asteroids")
frame.setDefaultCloseOperation JFrame::EXIT_ON_CLOSE
frame.setPreferredSize Dimension.new 600, 600
panel = Screen.new
frame.add panel
frame.pack
frame.setVisible(true)
