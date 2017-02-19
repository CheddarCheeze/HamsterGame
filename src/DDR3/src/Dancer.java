package DDR3.src;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

class Dancer extends Sprite {
	static Image img_dancer_normal = null, img_dancer_up = null, img_dancer_down = null, img_dancer_left = null, img_dancer_right = null;
	int timer;
	final int POSE_LENGTH = 40;
	final int XSCALE = 150, YSCALE = 150;
	//constructor
	Dancer() {
		this.pos_x = 40;
		this.pos_y = 220;
		this.direction = 4;
		this.width = 40;
		this.height = 40;
		this.timer = 0;


		//Load tube images the first time
		if (img_dancer_normal == null || img_dancer_up == null || img_dancer_down == null) try{
			this.img_dancer_normal 	= ImageIO.read(new File("src/DDR3/dancer_normal.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_dancer_up 		= ImageIO.read(new File("src/DDR3/dancer_up.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_dancer_down 	= ImageIO.read(new File("src/DDR3/dancer_down.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_dancer_left 	= ImageIO.read(new File("src/DDR3/dancer_left.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_dancer_right 	= ImageIO.read(new File("src/DDR3/dancer_right.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
		} catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
	}

	Dancer(Dancer oldTube) {
		this.pos_x = oldTube.pos_x;
		this.pos_y = oldTube.pos_y;
		this.direction = oldTube.direction;
		this.width = oldTube.width;
		this.height = oldTube.height;
	}

	Sprite replicate(LinkedList<Sprite> new_model_sprite) {
		return new Dancer(this);
	}


	void setPose(int pose) {
		this.direction = pose;		
	}
	
	//update method, returns true if out of valid horizontal range
	boolean update() {
		timer++;
/*		if(timer > POSE_LENGTH) {
			timer = 0;
			direction = 4;
		}
		*/
		return false;
	}


	void draw(Graphics g) {
		switch(direction) {
		case 0: g.drawImage(this.img_dancer_up, this.pos_x, this.pos_y, null);
				break;
		case 1: g.drawImage(this.img_dancer_down, this.pos_x, this.pos_y, null);
				break;
		case 2: g.drawImage(this.img_dancer_left, this.pos_x, this.pos_y, null);
				break;
		case 3: g.drawImage(this.img_dancer_right, this.pos_x, this.pos_y, null);
				break;	
		case 4: g.drawImage(this.img_dancer_normal, this.pos_x, this.pos_y, null);
				break;	
		default: System.out.println("wrong facing!");
				break;
	
		}
	}
	int state(boolean change) {
/*		if(change)
			this.retracting = true;

		if (top)
			return 1;
		else
*/			return 0;
	}

}
