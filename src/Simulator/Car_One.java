package Simulator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Car_One{
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
	private double speed = 0;
	
	public Car_One(Color co,int spawn,int dest){
		color = co;
		spawnpoint = spawn;
		destination = dest;
		posX = 410;
		posY = 50;
		//eventually here we will set the x and y coordinates of the car to wherever the spawnpoint is
	}
	
	//Below are a ton of small functions so that the sim may simulate the car properly
	
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
	public double speed(){
		return speed;
	}
	public void speed(double z){
		speed =  z;
	}
	public int X(){
		return posX;
	}
	public int Y(){
		return posY;
	}
	public Color color(){
		return color;
	}
	public int Dest(){
		return destination;
	}
	public void move(double x, double y){
		posX = (int)x; //only used for lane changes
		posY = (int)y;
	}
	public void think(ArrayList<Car_One> cars){
		//this is where the car will make all of it's decisions based on surrounding traffic and distance until destination
		if(posX>400){
			setSpeed = 5;
		}
		else{
			setSpeed = 10;
		}
		if(posX >210){
			setLane = -1;
		}
		
	}
	

}
