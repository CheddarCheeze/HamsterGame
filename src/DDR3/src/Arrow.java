package DDR3.src;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

class Arrow extends Sprite {
	static int SPACING = 80;  	//minimum number of updates between tubes
	static int speed = 2;
	static Image img_arrow_up = null, img_arrow_down = null, img_arrow_left = null, img_arrow_right = null;
	static int numArrows = 0;
	static int timeBetweenArrows = SPACING;	//counter for spacing tubes

	public static final int danceLine = 200;
	
	final int XSCALE = 70, YSCALE = 70;


	//construct random tube at right side
	Arrow(Random rnd_gen) {
		numArrows++;
		this.pos_x = 900;
		this.direction = rnd_gen.nextInt(4);
		this.width = 40;
		this.height = 40;
		
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

		//Load tube images the first time
		if (img_arrow_up == null || img_arrow_down == null) try{
			this.img_arrow_up = ImageIO.read(new File("src/DDR3/arrow_up_yellow.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_arrow_down = ImageIO.read(new File("src/DDR3/arrow_down_green.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_arrow_left = ImageIO.read(new File("src/DDR3/arrow_left_red.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
			this.img_arrow_right = ImageIO.read(new File("src/DDR3/arrow_right_blue.png")).getScaledInstance(XSCALE, YSCALE, Image.SCALE_DEFAULT);
		} catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
	}

	Arrow(Arrow oldTube) {
		this.pos_x = oldTube.pos_x;
		this.pos_y = oldTube.pos_y;
		this.direction = oldTube.direction;
		this.width = oldTube.width;
		this.height = oldTube.height;
	}

	Sprite replicate(LinkedList<Sprite> new_model_sprite) {
		return new Arrow(this);
	}

	static void tryAddArrow(Random rnd_gen, LinkedList<Sprite> sprites) {
		timeBetweenArrows++;			//increment spacing counter
		//add new arrow if necessary
		if (Arrow.timeBetweenArrows > SPACING) {
			Arrow newArrow = new Arrow(rnd_gen);
			sprites.add(newArrow);
			timeBetweenArrows = 0;
			
			
			SPACING -= rnd_gen.nextInt(15);
			if(SPACING < 30){
				SPACING = 30;
			}
			
			if(speed < 10) {
				int addition = rnd_gen.nextInt(5) -3;
				if(addition > 0) {
					speed += addition;
				}
			}
		}
	}

	//update method, returns true if out of valid horizontal range
	boolean update() {	
		// move tube left
		this.pos_x -= speed;

		//stop at the end
		if(pos_x < danceLine-width/2) {
			numArrows--;		//model removes from list if out of range
			return true;
		} else
			return false;
	}


	void draw(Graphics g) {
		switch(direction) {
		case 0: g.drawImage(this.img_arrow_up, this.pos_x, this.pos_y, null);
				break;
		case 1: g.drawImage(this.img_arrow_down, this.pos_x, this.pos_y, null);
				break;
		case 2: g.drawImage(this.img_arrow_left, this.pos_x, this.pos_y, null);
				break;
		case 3: g.drawImage(this.img_arrow_right, this.pos_x, this.pos_y, null);
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
