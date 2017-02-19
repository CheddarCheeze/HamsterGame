package GAME_CODE;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.util.Iterator;

class Bird extends Sprite {
	static Image bird_image = null, bird_img_flap = null, bird_img_death = null;
	LinkedList<Sprite> spriteRef;
	boolean feathers;
	
	double down_velocity;
	int frames_to_flap;

	Bird(LinkedList<Sprite> sprites) {
		this.spriteRef = sprites;
		this.pos_x = 65;
		this.pos_y = 300;
		this.width = 64;
		this.height = 57;
		this.down_velocity = -9;
		this.frames_to_flap = 0;
		this.feathers = false;

		//load bird images the first time
		if (bird_image == null || bird_img_flap == null) try{
			this.bird_image = ImageIO.read(new File("src/GAME_CODE/wingsClosed.png"));		//normal
			this.bird_img_flap = ImageIO.read(new File("src/GAME_CODE/wingsOpen.png"));	//flapping
			this.bird_img_death = ImageIO.read(new File("src/GAME_CODE/feathers.png"));	//death animation
		} catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
	}

	//copy constructor, written in class
	Bird(Bird oldBird, LinkedList<Sprite> new_model_sprites) {
		this.spriteRef = new_model_sprites;
		this.feathers = oldBird.feathers;
		this.frames_to_flap = oldBird.frames_to_flap;
		this.pos_x = oldBird.pos_x;
		this.pos_y = oldBird.pos_y;
		this.width = oldBird.width;
		this.height = oldBird.height;
		this.down_velocity = oldBird.down_velocity;
		this.frames_to_flap = oldBird.frames_to_flap;
		this.feathers = oldBird.feathers;

	}

	Sprite replicate(LinkedList<Sprite> new_model_sprite) {
		return new Bird(this, new_model_sprite);
	}

	boolean update() {
		if(this.feathers) {		//death animation
			this.pos_x += 4;		//move to right
			//if(this.pos_x >= 500)
				//System.exit(0);
			if (this.pos_y >= 500 - 46) {	//if on bottom edge, bounce
				this.down_velocity = -.6 * this.down_velocity;
				if (this.down_velocity > -1) {		//if would roll, bounce
					this.down_velocity = -6;
				}
			}
		}
		// apply gravity to bird
		this.down_velocity += .52;
		this.pos_y += down_velocity;
		this.frames_to_flap--;

		if (checkCollision())
			this.feathers = true;
		return false;
	}

	void flap() {
		if(!this.feathers) {
			// give bird a push upward
			this.down_velocity = -9;
			this.frames_to_flap = 8;
		}
	}
	
	void draw(Graphics g) {
		if (this.feathers) {
			// Draw the ball of feathers
			g.drawImage(this.bird_img_death, this.pos_x, this.pos_y, null);
		} else if (this.frames_to_flap >0) {
			// Or, Draw the bird flapping
			g.drawImage(this.bird_img_flap, this.pos_x, this.pos_y, null);
		} else {
			// Or, Draw the bird falling
			g.drawImage(this.bird_image, this.pos_x, this.pos_y, null);
		}

	}

	int state(boolean change) {
		//have we started the animation?
		if (change) {
			feathers = true;
		}

		//bird state
		if (this.feathers)
			return -1; 	//death animation
		else if (this.frames_to_flap > 0)
			return 1;	//flapping
		else
			return 0;	//not flapping
	}

	private boolean checkCollision() {
		if(this.feathers)		//ignore collisions after death
			return false;

		if(this.pos_y > 500 || this.pos_y < -(this.height))	//offscreen
			return true;

		//search the list for tubes
		Iterator<Sprite> it = this.spriteRef.iterator();
		while(it.hasNext()) {
			Sprite s = it.next();
			if (s instanceof Tube)	{		//check collision against all tubes
				Tube t = (Tube)s;
				if (this.collision(this, t))
					return true;
			}
		}
		
		return false; //no collisions detected
	}
}
