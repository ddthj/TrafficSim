package Simulator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Sim extends JPanel {
	static Camera cam = new Camera(0,0,200,2000);
	static ArrayList<Car_One> cars = new ArrayList<Car_One>();
	int x = 0;
	int y = 0;
	static long time = 0;
	static int oldtime = 0;
	static int rampspeed = 10;
	static int topspeed = 25;
	static double acceleration = 0.2;

	private void moveCars() {
		x = x + 1;
		y = y + 1;
		for(Car_One c: cars){
		c.think(cars);
		if(c.isLane() == -1){
			c.isLane(0); // right now the sim just denies any lane change requests				}
		}
		else if(c.isLane() == 1){
			c.isLane(0);
		}
		//up ahead we get the car going the speed it wants to go, within limits
		
		if(c.X() < 400){
			// if the car is not on a ramp....
			if(c.setSpeed() <= topspeed && c.setSpeed() != c.speed()){
				if(c.setSpeed() > c.speed()){
					c.speed(c.speed()+acceleration);
				}
				else{
					c.speed(c.speed()-acceleration);
				}
			}
		}
		c.move(c.X(), c.Y()+c.speed());
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.green); //setting it's color to green
		g2d.fillRect(0, 0, 600, 1500); //making a square of the set color
		
		g2d.setColor(Color.black);
		g2d.fillRect(200-(cam.X()), 0-(cam.Y()), 200, 1500);
		g2d.setColor(Color.white);
		g2d.fillRect(265-cam.X(), 0-cam.Y(), 5, 1500);
		g2d.fillRect(330-cam.X(), 0-cam.Y(), 5, 1500);
		g2d.setColor(Color.blue);
		g2d.fillRect(x-cam.X(), y-cam.Y(), 30, 30);
		for(Car_One c: cars){
			int x = c.X() - cam.X();
			int y = c.Y()-cam.Y();
			Color z = c.color();
			g2d.setColor(z);
			g2d.fillRect(x, y, 50, 100);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Sim sim = new Sim();
		Random rand = new Random();
		frame.add(sim);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        }
	        @Override
	        public void keyPressed(KeyEvent e) {
	            //System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
	            if(e.getKeyCode() == 38){
	            	cam.changeY(-20);
	            }
	            if(e.getKeyCode() == 40){
	            	cam.changeY(20);
	            }
	            if(e.getKeyCode() == 37){
	            	cam.changeX(-20);
	            }
	            if(e.getKeyCode() == 39){
	            	cam.changeX(20);
	            }
	            if(e.getKeyCode() == 27){
	            	 	            
	            }
	          }
	        @Override
	        public void keyReleased(KeyEvent e) {
	        }
	    });
		long starttime = System.nanoTime()/1000000000;
		while (true) {
			time = (System.nanoTime()/1000000000) - starttime;
			
			// the below if statement governs the creation of cars
			if(time > oldtime){
				oldtime =(int) time;
				int Num = rand.nextInt((5 - 1) + 1) + 1;
				int Spawn = rand.nextInt((3 - 1) + 1) + 1;
				Color randColor;
				
				if(Num == 1){
					randColor = Color.blue;
				}
				else if (Num == 2){
					randColor = Color.green;
				}
				else if (Num == 3){
					randColor = Color.orange;
				}
				else if (Num == 4){
					randColor = Color.red;
				}
				else{
					randColor = Color.gray;
				}
				cars.add(new Car_One(randColor, Spawn, 2));
			}
			
			//move the cars as they wish (within limits) and repaint their new locations
			sim.moveCars();
			sim.repaint();
			Thread.sleep(20);
		}
	}
}