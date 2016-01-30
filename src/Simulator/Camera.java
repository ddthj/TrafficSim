package Simulator;

public class Camera {
	
	//This is our camera, it will cause the map to move around the window AND insure that the camera can't go out of bounds
	
	private int camX;
	private int camY;
	private int Xbound;
	private int Ybound;
	
	public Camera(int x,int y,int Xbo,int Ybo){
		camX = x;
		camY = y;	
		Xbound = Xbo;
		Ybound = Ybo;
		}
	public void changeX(int z){
		if(0<= camX+z && camX+z<= Xbound){
		camX +=z;
		}
	}
	public void changeY(int z){
		if(0<= camY+z && camY+z<= Ybound){
			camY +=z;
			}
	}
	public int X(){
		return camX;
	}
	public int Y(){
		return camY;
	}
}
