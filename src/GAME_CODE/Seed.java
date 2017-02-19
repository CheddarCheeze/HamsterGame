package GAME_CODE;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.util.Iterator;

class Seed extends Sprite {
	static Image img_Seed = null;
	double down_velocity;
	LinkedList<Sprite> spriteRef;

	Seed(int bird_x, int bird_y, LinkedList<Sprite> sprites) {
		this.spriteRef = sprites;
		this.pos_x = bird_x;
		this.pos_y = bird_y;
		this.width = 40;
		this.height = 28;
		this.down_velocity = -6;

		//load Seed the first time
		if (img_Seed == null) try{
			this.img_Seed = ImageIO.read(new File("src/GAME_CODE/seed.png"));
		} catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
	}

	Seed(Seed oldSeed, LinkedList<Sprite> new_model_sprites) {
		this.spriteRef = new_model_sprites;
		this.pos_x = oldSeed.pos_x;
		this.pos_y = oldSeed.pos_y;
		this.width = oldSeed.width;
		this.height = oldSeed.height;
	}

	Sprite replicate(LinkedList<Sprite> new_model_sprite) {
		return new Seed(this, new_model_sprite);
	}

	boolean update() {		//returns true if collides with tube or goes offscreen
			// apply gravity to Seed
			this.down_velocity += .52;
			this.pos_y += down_velocity;		//grows each time
			this.pos_x += 6;		//constant
			
			return (checkCollision() || !onscreen());
	}

	void draw(Graphics g) {
		// Draw the Seed
		g.drawImage(this.img_Seed, this.pos_x, this.pos_y, null);

	}

	int state(boolean change) {
		return 0;
	}

	private boolean checkCollision() {
		//search the list for tubes
		Iterator<Sprite> it = this.spriteRef.iterator();
		while(it.hasNext()) {
			Sprite s = it.next();
			if (s instanceof Tube)	{		//check collision against all tubes
				Tube t = (Tube)s;
				if (this.collision(this, t)) {
					int toss = s.state(true);
					return true;
				}
			}
		}

		return false;		//No collision detected
	}

	private boolean onscreen() {
		if (this.pos_x + this.width > 500 || this.pos_y + this.height > 500) {
			return false;
		}
		return true;
	}
}
