package Simulator;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Sim extends JPanel{
	boolean running = true;
	static Camera cam = new Camera(0,0,500,1500);
	
	
	//@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.green); //setting it's color to green
		g2d.fillRect(0, 0, 600, 1500); //making a square of the set color
		g2d.setColor(Color.black);
		g2d.fillRect(200-(cam.X()), 0-(cam.Y()), 200, 1500);
		g2d.setColor(Color.white);
		g2d.fillRect(265-cam.X(), 0-cam.Y(), 5, 1500);
		g2d.fillRect(330-cam.X(), 0-cam.Y(), 5, 1500);
	}
	
	
	public static void main(String[] Args){		
		
		boolean running = true;
		
		//Things we need to thing:
		//
		//0% complete road and set up the ramps
		//50% - set up camera movements/locking
		//33% - set up primary sim variables (top speed, exit speed, cars joining per second, time, sim hz, camera start x and y positions, road dimensions and positions....)
		//25% - set up sim, create cars at random points
		// 0%- set up sim "physics," handle movements of cars (or attempts at movements) crashes (cars intersecting) etc
		//10% - set up car AI's, we already have the beginnings of an outline.		
	   
		long time = 0;
		int oldtime = 0;
		int rampspeed = 10;
		int topspeed = 20;
		int acceleration = 1;
		
		
		//Making JFrame
		
		JFrame frame = new JFrame("TrafficSim");
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Below we use a function that can execute when a keyboard key is pressed
		
		frame.addKeyListener(new KeyListener() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            //System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
	            if(e.getKeyCode() == 38){
	            	cam.changeY(-10);
	            }
	            if(e.getKeyCode() == 40){
	            	cam.changeY(10);
	            }
	            if(e.getKeyCode() == 37){
	            	cam.changeX(-10);
	            }
	            if(e.getKeyCode() == 39){
	            	cam.changeX(10);
	            }
	            if(e.getKeyCode() == 27){
	            	running = false;
	            
	            }
	          }
	    
	        @Override
	        public void keyReleased(KeyEvent e) {
	        }
	    });
		//some final bits before we get going
		
		ArrayList<Car_One> cars = new ArrayList<Car_One>();
		long starttime = System.nanoTime()/1000000000;
		//get going
		
		while(running){
			repaint();
			
			time = (System.nanoTime()/1000000000) - starttime; //this allows us to see how many seconds the sim has been running
			
			if(oldtime+2 <= (int)time){
				cars.add(new Car_One(Color.blue, 1, 2));
				cars.add(new Car_One(Color.red, 2, 2));		//this is where we will be adding cars by second, or in current case by 10 seconds
				cars.add(new Car_One(Color.yellow, 3, 2)); // car values are predetermined here, but later they will be random
			}
			
			
			
			//this is where the sim moves and renders all the cars
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
				else{//if the car is on a ramp...
					if(c.setSpeed() <= rampspeed && c.setSpeed() != c.speed()){
						if(c.setSpeed() > c.speed()){
							c.speed(c.speed()+acceleration);
						}
						else{
							c.speed(c.speed()-acceleration);
						}
					}
					
				}
				c.move(c.X(), c.Y()+c.speed());
				//c.paintComponent(g,cam); //this will render out all the cars, we send our Graphics helper to each car
			}
			
			//this is where the render function will be called... IF WE CODED ONE!
			//for car in arraylist, car.render(g);
			try { Thread.sleep(10); } catch (Exception e) {}	//limits out FPS a tad on purpose
		}
	}
}
