package GAME_CODE;

import java.awt.Graphics;
import java.util.LinkedList;

abstract class Sprite {
	int pos_x;
	int pos_y;
	int width;
	int height;

	abstract Sprite replicate(LinkedList<Sprite> new_model_sprite);	//copy constructor

	abstract boolean update();
	abstract void draw(Graphics g);
	abstract int state(boolean change);		//use true parameter to shift state, false otherwise
			//tubes return 1 if top, 0 if bottom tube
			//birds return frames of flap remaining

	boolean collision(Sprite a, Sprite b) {
		int aR = a.pos_x + a.width;	//A right
		int aL = a.pos_x;			//A left
		int aB = a.pos_y + a.height;//A bottom
		int aT = a.pos_y;			//A top

		int bR = b.pos_x + b.width;	//B right
		int bL = b.pos_x;			//B left
		int bB = b.pos_y + b.height;//B bottom
		int bT = b.pos_y;			//B top

		if(aR < bL)
			return false;
		if(aL > bR)
			return false;
		if(aB < bT) 		// larger values are lower onscreen
			return false;
		if(aT > bB) 		// larger values are lower onscreen
			return false;
		return true;		//otherwise, must be overlapping
	}
}
