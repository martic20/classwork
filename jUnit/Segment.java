package jUnit;

public class Segment {

	private int x1,x2,y1,y2;
	
	public Segment (Punt a, Punt b){
		this.x1=a.getX();
		this.x2=b.getX();
		this.y1=a.getY();
		this.y2=b.getY();		
	}
	
	public double longitud(){
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
}
 