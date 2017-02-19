package DDR3.src;

import java.util.LinkedList;
import java.util.Iterator;

class Model {
	static final int WIN_SCORE = 20;
	
	Random rnd_gen;
	LinkedList<Sprite> sprites;
	int score;
	Dancer dancer;
	
	boolean done;
	
	boolean isDone() {
		
		return done;
	}
	
	
	Model() {
		this.rnd_gen = new Random(System.currentTimeMillis());
		this.sprites = new LinkedList<Sprite>();
		this.score = 0;
		this.dancer = new Dancer();
		sprites.add(dancer);
	}

	//model copy constructor, written in class
	Model(Model m) {
		// Copy the sprites
		sprites = new LinkedList<Sprite>();
		Iterator<Sprite> it = m.sprites.iterator();
		while(it.hasNext()) {
			Sprite s = it.next();
			sprites.add(s.replicate(this.sprites));	//replicates s. sprites is needed by bird copy constructor
		}

		// Copy the other stuff
		rnd_gen = new Random(m.rnd_gen);
		score = m.score;
	}

	void update() {
		//add tubes if necessary
		Arrow.tryAddArrow(rnd_gen, sprites);

		//update sprites
		Iterator<Sprite> it = this.sprites.iterator();
		while(it.hasNext()) {
			Sprite s = it.next();
			if (s.update())	{		//update each
				it.remove();		//drop from list if out of range
			}
		}
		
		//win condition
		if(score >= WIN_SCORE) {
			done = true;
		}
	}
	
	//reset pose
	void onRelease() {
		dancer.setPose(4);
	}
	
	//arrows pressed
	void onPressUp() {
		Iterator<Sprite> it = this.sprites.iterator();
		if(it.hasNext()) {
			Sprite s = it.next();
			s = it.next();
			if(s.direction==0 && (Math.abs(s.pos_x - Arrow.danceLine) < 40)) {
				System.out.println(++score);
				it.remove();
			}
			
		}
		dancer.setPose(0);
	}
	
	void onPressDown() {
		Iterator<Sprite> it = this.sprites.iterator();
		if(it.hasNext()) {
			Sprite s = it.next();
			s = it.next();

			if(s.direction==1 && (Math.abs(s.pos_x - Arrow.danceLine) < 40)) {
				System.out.println(++score);
				it.remove();
			}
			
		}
		dancer.setPose(1);
	}
	
	void onPressLeft() {
		Iterator<Sprite> it = this.sprites.iterator();
		if(it.hasNext()) {
			Sprite s = it.next();
			s = it.next();

			if(s.direction==2 && (Math.abs(s.pos_x - Arrow.danceLine) < 40)) {
				System.out.println(++score);
				it.remove();
			}
			
		}
		dancer.setPose(2);
	}
	
	void onPressRight() {
		Iterator<Sprite> it = this.sprites.iterator();
		if(it.hasNext()) {
			Sprite s = it.next();
			s = it.next();

			if(s.direction==3 && (Math.abs(s.pos_x - Arrow.danceLine) < 40)) {
				score++;
				it.remove();
			}
			
		}
		dancer.setPose(3);
	}
}
