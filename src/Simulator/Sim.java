package Simulator;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.image.BufferStrategy;

public class Sim{
	public static void main(String[] Args){		
		//Things we need to thing:
		//
		//50% - Make the game window
		//50% - render out a road/a test car (just a square)
		// - set up camera movements/locking
		// - set up primary sim variables (top speed, exit speed, cars joining per second, time, sim hz, camera start x and y positions, road dimensions and positions....)
		// - set up sim, create cars at random points
		// - set up sim "physics," handle movements of cars (or attempts at movements) crashes (cars intersecting) etc
		// - set up car AI's, we already have the beginnings of an outline.		
	   
		Sim s = new Sim();
		

	}
	
	public Sim(){
		int camX = 0;
		int camY = 0;
		JFrame frame = new JFrame("TrafficSim");
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(600,900));
		panel.setBounds(0,0,800,600);
		frame.setIgnoreRepaint(true);
		frame.pack();
		frame.setVisible(true);
		frame.createBufferStrategy(2);
		BufferStrategy strategy = frame.getBufferStrategy();
		boolean running = true;
		while(running){
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.green);
			g.fillRect(0, 0, 600, 1000);
			g.setColor(Color.black);
			g.fillRect(200, 0, 200-camX, 1000-camY);
			//this is where the render function will be called... IF WE CODED ONE!
			//for car in arraylist, car.render(g);
			g.dispose();
			strategy.show();
			try { Thread.sleep(10); } catch (Exception e) {}		
		}
	}
}
