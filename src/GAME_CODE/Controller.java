package GAME_CODE;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class Controller implements MouseListener {
	Model model;

	Controller(Model m) {
		this.model = m;
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 3) {
			this.model.onRightClick();
		}
		else {
			this.model.onClick();
		}
	}
			
	/*public void update() {
		int val_DO_NOTHING = model.evaluateAction(Model.birdAction.DO_NOTHING, 0);
		int val_FLAP = model.evaluateAction(Model.birdAction.FLAP, 0);
		int val_THROW_seed = model.evaluateAction(Model.birdAction.THROW_seed, 0);
		int val_FLAP_AND_THROW = model.evaluateAction(Model.birdAction.FLAP_AND_THROW, 0);

		//calculate max then compare
		int max = Math.max(Math.max(Math.max(val_DO_NOTHING, val_FLAP), val_THROW_seed), val_FLAP_AND_THROW);
		

		//actions in priority, highest to lowest
		if (max == val_DO_NOTHING) {		//do nothing
		} else if (max == val_FLAP) {		//flap
			this.model.onClick();
		} else if (max == val_THROW_seed) {	//throw
			this.model.onRightClick();
		} else if (max == val_FLAP_AND_THROW) {	//flap and throw
			this.model.onClick();
			this.model.onRightClick();
		}
		return;
	}*/

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

}
