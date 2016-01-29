package Simulator;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;

public class Sim extends JFrame{
	public static void main(String[] Args){
		boolean running = true;
		
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
		setTitle("TrafficSim");
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 100, 100);
	}

}
