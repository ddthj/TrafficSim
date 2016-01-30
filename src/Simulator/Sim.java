package Simulator;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Sim{
	boolean running = true;
	public static void main(String[] Args){		
		
		//Things we need to thing:
		//
		//0% complete road and set up the ramps
		//50% - set up camera movements/locking
		//33% - set up primary sim variables (top speed, exit speed, cars joining per second, time, sim hz, camera start x and y positions, road dimensions and positions....)
		//25% - set up sim, create cars at random points
		// 0%- set up sim "physics," handle movements of cars (or attempts at movements) crashes (cars intersecting) etc
		//10% - set up car AI's, we already have the beginnings of an outline.		
	   
		Sim s = new Sim();
		

	}
	
	
	public Sim(){
		//setting up some variables:
		Camera cam = new Camera(0,0,600,1000);
		
		long time = 0;
		int oldtime = 0;
		//Making JFrame
		
		JFrame frame = new JFrame("TrafficSim");
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(600,900));
		panel.setBounds(0,0,800,600);
		frame.setIgnoreRepaint(true);
		frame.pack();
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Below we use a function that can execute when a keyboard key is pressed
		
		frame.addKeyListener(new KeyListener() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
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
		
		BufferStrategy strategy = frame.getBufferStrategy();
		ArrayList<Car_One> cars = new ArrayList<Car_One>();
		long starttime = System.nanoTime()/1000000000;
		//get going
		
		while(running){
			time = (System.nanoTime()/1000000000) - starttime; //this allows us to see how many seconds the sim has been running
			
			if(oldtime+10 <= (int)time){
				cars.add(new Car_One(Color.blue, 1, 2));
				cars.add(new Car_One(Color.red, 2, 2));		//this is where we will be adding cars by second, or in current case by 10 seconds
				cars.add(new Car_One(Color.yellow, 3, 2)); // car values are predetermined here, but later they will be random
			}
			
			
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics(); //making a small graphics helper
			g.setColor(Color.green); //setting it's color to green
			g.fillRect(0, 0, 600, 1000); //making a square of the set color
			g.setColor(Color.black);
			g.fillRect(200-(cam.X()), 0-(cam.Y()), 200, 1000);
			for(Car_One c: cars){
				c.drawMe(g,cam); //this will render out all the cars, we send our Graphics helper to each car
			}
			
			//this is where the render function will be called... IF WE CODED ONE!
			//for car in arraylist, car.render(g);
			
			g.dispose(); //kill the graphics helper
			strategy.show(); //update the display
			
			try { Thread.sleep(10); } catch (Exception e) {}	//limits out FPS a tad on purpose
			if (time >10){
				running = false; //currently the only way to kill the simulator is to tell it to die after 10 seconds
			}
		}
		
	}
}
