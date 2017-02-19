package GAME_CODE;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.io.IOException;
import java.awt.Robot;

public class Game extends JFrame implements ActionListener {
	Model model;
	Controller controller;
	View view;

	public Game() throws IOException {
		this.model = new Model();
		this.controller = new Controller(this.model);
		this.view = new View(this.model);
		view.addMouseListener(controller);
		this.setTitle("Flappy Hamster");
		this.setSize(1000, 500);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
                this.setResizable(false);
		new Timer(18, this).start(); // Indirectly calls actionPerformed at regular intervals
	}

	public void actionPerformed(ActionEvent evt) {
		//this.controller.update();
		this.model.update();
		repaint(); // Indirectly calls View.paintComponent
	}

	public static void main(String[] args) throws Exception {
		new Game();
	}
}
