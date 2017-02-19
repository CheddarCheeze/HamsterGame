package GAME_CODE;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;

class Tube extends Sprite {
	static final int SPACING = 100;  	//minimum number of updates between tubes

	static Image img_tube_top = null, img_tube_bot = null;
	boolean top, retracting;
	static int numTubes = 0;
	static int timeBetweenTubes = SPACING;	//counter for spacing tubes


	//construct random tube at right side
	Tube(Random rnd_gen) {
		numTubes++;
		this.pos_x = 1000;
		this.retracting = false;
		this.top = rnd_gen.nextBoolean();
                this.width = 55;
                this.height = 400;
                
		if(top) {
			this.pos_y = -100 - rnd_gen.nextInt(200);
		}
		else {
			this.pos_y = 400 - rnd_gen.nextInt(200);
		}	

		//Load tube images the first time
		if (img_tube_top == null || img_tube_bot == null) try{
			this.img_tube_top = ImageIO.read(new File("src/GAME_CODE/bottle.png"));
			this.img_tube_bot = ImageIO.read(new File("src/GAME_CODE/cardboard.png"));
		} catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
	}

	Tube(Tube oldTube) {
		this.pos_x = oldTube.pos_x;
		this.pos_y = oldTube.pos_y;
		this.retracting = oldTube.retracting;
		this.top = oldTube.top;
		this.width = oldTube.width;
		this.height = oldTube.height;
	}

	Sprite replicate(LinkedList<Sprite> new_model_sprite) {
		return new Tube(this);
	}

	static void tryAddTube(Random rnd_gen, LinkedList<Sprite> sprites) {
		timeBetweenTubes++;			//increment spacing counter
		//add new tubes if necessary
		if (Tube.timeBetweenTubes > SPACING) {
			Tube newTube = new Tube(rnd_gen);
			sprites.add(newTube);
			timeBetweenTubes = 0;
		}
	}

	//update method, returns true if out of valid horizontal range
	boolean update() {	
		// move tube left
		this.pos_x -= 2;

		//retract if necessary
		if(retracting) {
			if(top) {
				this.pos_y -= 3;		//move top tube up
			} else {
				this.pos_y += 3;		//move bottom tube down
			}
		}

		if(pos_x < -55) {
			numTubes--;		//model removes from list if out of range
			return true;
		} else
			return false;
	}


	void draw(Graphics g) {
		if (this.top) {
			//draw top tube
			g.drawImage(this.img_tube_top, this.pos_x, this.pos_y, null);
		}
		else {
			//draw bottom tube
			g.drawImage(this.img_tube_bot, this.pos_x, this.pos_y, null);
		}
	}
	int state(boolean change) {
		if(change)
			this.retracting = true;

		if (top)
			return 1;
		else
			return 0;
	}

}
