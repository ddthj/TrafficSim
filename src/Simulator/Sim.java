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
	static Camera cam = new Camera(0,0,200,15000);
	static ArrayList<Car_One> cars = new ArrayList<Car_One>();
	int x = 0;
	int y = 0;
	static long time = 0;
	static long oldtime = 0;
	static int rampspeed = 5;
	static int topspeed = 10;
	static double acceleration = 0.2;

	private void moveCars() {
		for(Car_One c: cars){
		c.think(cars);
		x = c.X();
		y = c.Y();
		boolean change = true;
		if(c.isLane() == -1){
			for(Car_One b: cars){
				if(x > b.X() && y >= b.Y() && y<= b.Y() +100){
					change = false;
				}
			}
		}
		else if(c.isLane() == 1){
			for(Car_One b: cars){
				if(x < b.X() && y >= b.Y() && y<= b.Y() +100){
					change = false;
				}
			}
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
			else{
				c.speed(c.speed()-acceleration);
			}
		}
		else{
			if(c.setSpeed() <= rampspeed && c.setSpeed() != c.speed()){
				if(c.setSpeed() > c.speed()){
					c.speed(c.speed()+acceleration);
				}
				else{
					c.speed(c.speed()-acceleration);
				}
			}
			else{
				c.speed(c.speed()-acceleration);
			}
			
		}
		if(change){
			c.move(c.X()+(c.isLane()*3), c.Y()+c.speed());
		}
		else{
			c.move(c.X(), c.Y()+c.speed());
		}
		c.isLane(0);
		
		
		}
		for(int i = cars.size()-1; i>=0; i--){
			if(cars.get(i).Y() > 14950){
				cars.remove(i);
			}
			if(cars.get(i).X() >400 && cars.get(i).Y()>1050 && cars.get(i).Y() <14000 ){
				cars.remove(i);
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.green); //setting it's color to green
		g2d.fillRect(0, 0, 1000, 15000); //making a square of the set color
		g2d.setColor(Color.black);
		g2d.fillRect(200-(cam.X()), 0-(cam.Y()), 200, 15000);
		g2d.fillRect(405-(cam.X()), 50-(cam.Y()), 70, 1000);
		g2d.fillRect(405-(cam.X()), 14000-(cam.Y()), 70, 1000);
		g2d.setColor(Color.white);
		g2d.fillRect(265-cam.X(), 0-cam.Y(), 5, 15000);
		g2d.fillRect(330-cam.X(), 0-cam.Y(), 5, 15000);
		g2d.setColor(Color.blue);
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
	            	cam.changeY(-30);
	            }
	            if(e.getKeyCode() == 40){
	            	cam.changeY(30);
	            }
	            if(e.getKeyCode() == 37){
	            	cam.changeX(-30);
	            }
	            if(e.getKeyCode() == 39){
	            	cam.changeX(30);
	            }
	            if(e.getKeyCode() == 27){
	            	 	            
	            }
	          }
	        @Override
	        public void keyReleased(KeyEvent e) {
	        }
	    });
		long starttime = System.nanoTime()/100000000;
		while (true) {
			time = (System.nanoTime()/100000000) - starttime;
			// the below if statement governs the creation of cars
			if(time > oldtime+2){
				oldtime = time;
				int Num = rand.nextInt((5 - 1) + 1) + 1;
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
				cars.add(new Car_One(randColor, 50, 15000));
				
				Num = rand.nextInt((5 - 1) + 1) + 1;
				
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
				cars.add(new Car_One(randColor, 50, 15000));
			}
			
			//move the cars as they wish (within limits) and repaint their new locations
			sim.moveCars();
			sim.repaint();
			Thread.sleep(10);
		}
	}
}