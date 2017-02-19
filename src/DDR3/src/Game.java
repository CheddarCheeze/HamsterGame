package DDR3.src;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.io.IOException;
import java.awt.Robot;

public class Game extends JFrame implements ActionListener {
	Model model;
	Robot mouseDroid;
	Controller controller;
	View view;
	MusicPlayer music;

	public Game() throws IOException {
		this.model = new Model();
//		this.music = new MusicPlayer("src/DDR3/resources/audio/hamsterdance");
//		this.music.run();	//run the song TODO: ability to change songs?
		try{
			this.mouseDroid = new Robot();		//fixes java jumpiness
		} catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
		this.controller = new Controller(this.model);
		this.view = new View(this.model);
		addKeyListener(controller);
		this.setTitle("Hamster DDR Game");
		this.setSize(813, 402);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		new Timer(20, this).start(); // Indirectly calls actionPerformed at regular intervals
		
	}

	public void actionPerformed(ActionEvent evt) {
		this.model.update();
		mouseDroid.mouseWheel(0);		//fix jumpiness
		repaint(); // Indirectly calls View.paintComponent
	}

	public static void main(String[] args) throws Exception {
		Game runningGame = new Game();
	}
}
