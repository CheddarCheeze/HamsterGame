package DDR3.src;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements KeyListener {
	Model model;

	Controller(Model m) {
		this.model = m;
	}


	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			this.model.onPressUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.model.onPressDown();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.model.onPressLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.model.onPressRight();
		} else {
			System.out.println("other");
			//this.model.onClick();
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:	
			case KeyEvent.VK_DOWN:	
			case KeyEvent.VK_LEFT:	
			case KeyEvent.VK_RIGHT:	
					this.model.onRelease();
			default:
					break;
		}
	}
			
	
}
