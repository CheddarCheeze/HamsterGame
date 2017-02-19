package DDR3.src;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.Color;

class View extends JPanel {
	Model model;
	Image img_background;
	Image rope;	//rope for score background
	Image celebrate;
	Receiver rec_up,rec_down,rec_left,rec_right;

	

	View(Model m) throws IOException {
		this.model = m;
		//the images that don't move
		img_background = ImageIO.read(new File("src/DDR3/background_dance.png"));
		rope = ImageIO.read(new File("src/DDR3/newrope.png")).getScaledInstance(250, 40, Image.SCALE_DEFAULT);
		celebrate = ImageIO.read(new File("src/DDR3/ballons_bunch.png"));
		this.rec_up = new Receiver(0);
		this.rec_down = new Receiver(1);
		this.rec_left = new Receiver(2);
		this.rec_right = new Receiver(3);
	}

	public void paintComponent(Graphics g) {
		if(model.isDone()) {
						
			g.drawImage(celebrate, 100, 0, null);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", Font.BOLD, 40) );
			g.drawString("You Win!", 300, 200);
			
			//INTEGRATE HERE
			return;
		}
		
		//draw background
		g.drawImage(img_background, 0, 0, null);
		
		//draw scoreboard
		g.drawImage(rope, -10, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Dialog", Font.BOLD, 40) );
		g.drawString("Score: " + model.score, 10, 35);
		
		//draw receivers
		rec_up.draw(g);
		rec_down.draw(g);
		rec_left.draw(g);
		rec_right.draw(g);
	
		//Draw sprites in reverse order
		for(int i = this.model.sprites.size()-1; i >= 0; i--)
			this.model.sprites.get(i).draw(g);
		
	}
}
