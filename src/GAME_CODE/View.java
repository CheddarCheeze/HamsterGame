package GAME_CODE;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class View extends JPanel {
	Model model;
        BufferedImage bg;
            
	View(Model m) throws IOException {
		this.model = m;
                try { 
                    bg = ImageIO.read(new File("src/GAME_CODE/background.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
	}

	public void paintComponent(Graphics g) {
		//Draw sprites in reverse order
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                
                for(int i = this.model.clouds.size()-1; i >= 0; i--)
			this.model.clouds.get(i).draw(g);
		for(int i = this.model.sprites.size()-1; i >= 0; i--)
			this.model.sprites.get(i).draw(g);
	}
}
