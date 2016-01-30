package Simulator;

import java.awt.Color;
import java.awt.Graphics;

public class Car_One {
	private int setSpeed; //This is the speed the car wants to go
	private int setLane; // -1 = left, 0= no change, 1 = right
	private Color color; //This is the color of the car
	private int posX;     //X and Y coordinates of the car
	private int posY;	  //
	private int spawnpoint; //ramp the car spawns at
	private int destination; // ramp the car must exit at
	private int ticksAlive = 0; //how many game ticks have passed since creation
	private boolean blinker = false;
	private boolean atDest = false; //we will set this to true for the sim to delete the car
	
	public Car_One(Color co,int spawn,int dest){
		color = co;
		spawnpoint = spawn;
		destination = dest;
		if(spawnpoint == 1){
			posX = 210;
			posY = 100;
		}
		else if (spawnpoint == 2){
			posX = 340;
			posY = 100;
		}
		else if (spawnpoint == 3){
			posX = 275;
			posY = 100;
		}
		//eventually here we will set the x and y coordinates of the car to wherever the spawnpoint is
	}
	public void drawMe(Graphics g,Camera cam){
		g.setColor(color);
		g.fillRect(posX - cam.X(), posY-cam.Y(), 50, 100);
	}
	public boolean isBlink(){	
		return blinker;
	}
	public void think(){
		
	}
	

}
