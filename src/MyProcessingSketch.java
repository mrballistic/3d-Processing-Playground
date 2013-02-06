import processing.core.*;
import processing.opengl.*;

public class MyProcessingSketch extends PApplet {
	
	Dot[] dots = new Dot[450];
	PShader blur;
	Boolean move;
	PMatrix3D cam;
	final static int R = 1000;

	public void setup() {
		
		sphereDetail(20);

		size(1280,720,P3D);
		blur = loadShader("blur.glsl");
		move = true;
		
		
		for (int i = 0; i < dots.length; i++) {
		      dots[i] = new Dot(this);
		}
		
		cam = new PMatrix3D();

	}
	
	public void draw() {
	
		background(0);
		
		cam.rotateX((float) -(mouseY - height / 2.0) / height / 20);
		cam.rotateY((float) -(mouseX - width  / 2.0) / width  / 20);
		PVector x = cam.mult(new PVector(1, 0, 0), new PVector(0, 0, 0));
		PVector y = cam.mult(new PVector(0, 1, 0), new PVector(0, 0, 0));
		PVector d = x.cross(y); d.normalize(); d.mult(R);
	//	camera(0, 0, 0, d.x, d.y, d.z, y.x, y.y, y.z);
		
		//spotLight(51, 102, 126, 80, 20, 40, -1, 0, 0, PI/2, 2);
		//directionalLight(5, 5, 5, 0, -1, -1);
		directionalLight(225, 225, 225, 0, -1, 0);
		//pointLight(51, 51, 126, 35, 40, 36);
		ambientLight(145, 145, 145);
		
		blendMode(REPLACE);
		
		//blendMode(ADD);
		//filter(blur);

		//lights();
		
	    for (int i = 0; i < dots.length; i++) {
	    	
	    	if(move){
	    		dots[i].target(0,0,0);
	    		
	    	} else {
	    		dots[i].target(mouseX,mouseY, 0);
	    	}
	    	
	    	//stroke(126);
	    	
	    	if(i>0){
	    	//	line(dots[i].x, dots[i].y, dots[i-1].x, dots[i-1].y);
	    	}
	    	
	        dots[i].move();
	        dots[i].display();
	      }
	}
	
	public void mousePressed() {

		 move = false;

	}

	public void mouseReleased() {
		  move = true;
	}

	
	
	 public static void main(String args[]) {
		    PApplet.main(new String[] { "--present", "MyProcessingSketch" });
	 }
	
	
}
