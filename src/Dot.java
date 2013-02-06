import processing.core.*;
import processing.opengl.*;

public class Dot {
	PApplet parent; // The parent PApplet that we will render ourselves onto
	
	float x;
	float y;
	float z;
	float speed;
	float w;
	boolean mouse;
	int color;
	float alpha;
	
	float initY;
	float initZ;
	float targetX;
	float targetY;
	float targetZ;
	
	double easing;
	
	Dot(PApplet p) {
		
		parent = p;
		
		x = 0;
		y = parent.random(-700,(float)(2*parent.height));
		z = parent.random(-1500,1500);
		
		initY = y;
		initZ = z;
		
		speed = parent.random(10,38);
		
		easing = parent.random(2, 30);
		easing = .01 * easing;
		
		w = parent.random(5,85);
		
		mouse = false;
		
		color = parent.color(parent.random(50,175), parent.random(50,75), parent.random(128,256));
		alpha = 100;
		
		//alpha = parent.random(90,100);
	}
	
	void display(){
		
		parent.pushMatrix();
		
		parent.fill(color,alpha);
		parent.noStroke();

		//parent.noFill();
		//parent.stroke(color);
		
		parent.translate(x, y, z);
		parent.sphere(w);
		
		parent.popMatrix();
		
		//(x,y,w,w);		
	}
	
	void target(int _x,int _y, int _z){
		
		targetX = _x;
		targetY = _y;
		targetZ = _z;
		
		if((targetX == 0)&&(targetY == 0)){
			targetY = initY;
			targetZ = initZ;
		}
	}
	
	void move() {
		
		parent.pushMatrix();
		
		if(targetX == 0){
			x += speed;
		} else {
		    float dx = targetX - x;
		    if(parent.abs(dx) > 1) {
		        x += dx * easing;
		    }
		}

		float dy = targetY - y;
		if(parent.abs(dy) > 1) {
		     y += dy * easing;
		}
		
		float dz = targetZ - z;
		if(parent.abs(dz) > 1) {
			z += dz * easing;
		}
		
		
		if(x>parent.width+1320) x = -1520; // reset to beginning
		
		parent.translate(x,y,z);
		
		parent.popMatrix();
		
	}

}
