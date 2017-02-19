package GAME_CODE;

import java.util.LinkedList;
import java.util.Iterator;

class Model {
	protected enum birdAction { DO_NOTHING, FLAP, THROW_seed, FLAP_AND_THROW }

	Random rnd_gen;
	Bird bird;
	LinkedList<Sprite> sprites;
        LinkedList<Sprite> clouds;
	
	Model() {
		this.rnd_gen = new Random(System.currentTimeMillis());
		this.sprites = new LinkedList<Sprite>();
                this.clouds = new LinkedList<Sprite>();
		this.bird = new Bird(sprites);
		sprites.add(bird);		//bird takes first space in LinkedList
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

		// Copy the bird
		Sprite sFirst = sprites.getFirst();
		if(!(sFirst instanceof Bird))
			throw new IllegalArgumentException("first sprite in list is not a bird");
		bird = (Bird)sFirst;

		// Copy the other stuff
		rnd_gen = new Random(m.rnd_gen);
	}

	void update() {
		//add tubes if necessary
		Tube.tryAddTube(rnd_gen, sprites);
                Cloud.tryAddCloud(rnd_gen, clouds);

		//update sprites
		Iterator<Sprite> it = this.sprites.iterator();
		while(it.hasNext()) {
			Sprite s = it.next();
			if (s.update())	{		//update each
				it.remove();		//drop from list if out of range
			}
		}
                
                Iterator<Sprite> clouds = this.clouds.iterator();
		while(clouds.hasNext()) {
			Sprite s = clouds.next();
			if (s.update())	{		//update each
				clouds.remove();		//drop from list if out of range
			}
		}
	}
	
	void onClick() {
		bird.flap();
	}
	
	void onRightClick() {
		//add new seed if alive
		if(!bird.feathers) {
			Seed newseed = new Seed(bird.pos_x, bird.pos_y, sprites);
			sprites.add(newseed);
		}
	}

	/*int evaluateAction(birdAction action, int depth) {
		final int d = 30, k = 6;
		if (depth >= d) {
			if(bird.feathers) {
				return 0;
			} else {
				return (500 - Math.abs(bird.pos_y - 250));
			}
		} else {
			Model modelCopy = new Model(this);	//deep copy
			
			//simulate action
			switch (action) {
				case FLAP:  			modelCopy.onClick();		//left
										break;
				case THROW_seed:  		modelCopy.onRightClick();	//right
										break;
				case FLAP_AND_THROW:	modelCopy.onClick();		//left and right
										modelCopy.onRightClick();
										break;
				case DO_NOTHING:
				default:				break;
			}

			modelCopy.update();
			if (depth % k != 0) {
				return modelCopy.evaluateAction(birdAction.DO_NOTHING, depth +1);
			} else {
				int max;
				max = Math.max(modelCopy.evaluateAction(birdAction.DO_NOTHING, depth+1),
							   modelCopy.evaluateAction(birdAction.FLAP, depth+1));
				max = Math.max(max, modelCopy.evaluateAction(birdAction.THROW_seed, depth+1));
				max = Math.max(max, modelCopy.evaluateAction(birdAction.FLAP_AND_THROW, depth+1));
	
				return max;
			}
		}
	}*/	
}
