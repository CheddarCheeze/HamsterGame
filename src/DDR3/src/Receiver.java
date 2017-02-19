package DDR3.src;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

class Receiver extends Sprite {
	static Image img_rec_up = null, img_rec_down = null, img_rec_left = null, img_rec_right = null;

	final int XSCALE = 30, YSCALE = 30;
	
	//constructor
	Receiver(int setDirection) {
		
		this.direction = setDirection;
		this.width = 40;
		this.height = 40;
		this.pos_x = Arrow.danceLine+width/2;
		
		switch(direction) {
			case 0: this.pos_y = 50;
					break;
			case 1: this.pos_y = 125;
					break;
			case 2: this.pos_y = 200;
					break;
			case 3: this.pos_y = 275;
					break;	
			default: System.out.println("wrong facing!");
					break;
		}
		//adjust height for width
		this.pos_y += height/2;


		//Load tube images the first time
		if (img_rec_up == null || img_rec_down == null) try{
			this.img_rec_up 		= ImageIO.read(new File("src/DDR3/up_outline.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_rec_down 	= ImageIO.read(new File("src/DDR3/down_outline.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_rec_left 	= ImageIO.read(new File("src/DDR3/left_outline.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_rec_right 	= ImageIO.read(new File("src/DDR3/right_outline.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
		} catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
	}

	Receiver(Receiver oldTube) {
		this.pos_x = oldTube.pos_x;
		this.pos_y = oldTube.pos_y;
		this.direction = oldTube.direction;
		this.width = oldTube.width;
		this.height = oldTube.height;
	}

	Sprite replicate(LinkedList<Sprite> new_model_sprite) {
		return new Receiver(this);
	}


	void setPose(int pose) {
		this.direction = pose;		
	}
	
	//update method, returns true if out of valid horizontal range
	boolean update() {
		return false;
	}


	void draw(Graphics g) {
		switch(direction) {
		case 0: g.drawImage(this.img_rec_up, this.pos_x, this.pos_y, null);
				break;
		case 1: g.drawImage(this.img_rec_down, this.pos_x, this.pos_y, null);
				break;
		case 2: g.drawImage(this.img_rec_left, this.pos_x, this.pos_y, null);
				break;
		case 3: g.drawImage(this.img_rec_right, this.pos_x, this.pos_y, null);
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
