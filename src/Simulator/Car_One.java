package Simulator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
	private int speed = 0;
	
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
	
	//Below are a ton of small functions so that the sim may simulate the car properly
	
	public void drawMe(Graphics g,Camera cam){
		g.setColor(color);
		g.fillRect(posX - cam.X(), posY-cam.Y(), 50, 100);
	}
	public boolean isBlink(){	
		return blinker;
	}
	public int isLane(){
		return setLane;
	}
	public void isLane(int z){
		setLane = z;
	}
	public int setSpeed(){
		return setSpeed;
	}
	public int speed(){
		return speed;
	}
	public void speed(int z){
		speed = z;
	}
	public int X(){
		return posX;
	}
	public int Y(){
		return posY;
	}
	public void move(int x, int y){
		posX = x; //only used for lane changes
		posY = y;
	}
	public void think(ArrayList<Car_One> cars){
		//this is where the car will make all of it's decisions based on surrounding traffic and distance until destination
		setSpeed = 10;
	}
	

}
