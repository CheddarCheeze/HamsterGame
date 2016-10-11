package game;

import audio.MusicPlayer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;

class SunflowerSeed {
    
    int x;
    int y;
    Random randy;
    ImageIcon seed;
    int totalSeeds = 0;
    int maxx = 395;
    int maxy = 275;
    int min = 20;
    Boolean addPoint = false;
    
    SunflowerSeed(){
        x = 150;
        y = 150;
        seed = new ImageIcon("hamster/sunflower_seed.png");
        randy = new Random();
    }
    
    void update() {
        //System.out.println("Seeds!");
        x = randy.nextInt((maxx - min) + 1) + min;
        y = randy.nextInt((maxy - min) + 1) + min;
        totalSeeds++;
        addPoint = true;
    }
}

class Gary {
    
    int x;
    int y;
    int current = 0;
    int totalImages = 3;
    ImageIcon hamsterIcon;
    ImageIcon hamsterWalkRight[];
    ImageIcon hamsterWalkLeft[];
    ImageIcon hamsterWalkUp[];
    ImageIcon hamsterWalkDown[];
    ImageIcon hamsterStand[];
    //int frames_since_update;
    String hamster_direction = "DOWN";
    
    Gary() {
        x = 100;
        y = 100;
        //frames_since_update = 0;
        hamsterWalkRight = new ImageIcon[totalImages];
        hamsterWalkLeft = new ImageIcon[totalImages];
        hamsterWalkUp = new ImageIcon[totalImages+1];
        hamsterWalkDown = new ImageIcon[totalImages+1];
        hamsterStand = new ImageIcon[totalImages+1];
        try {
            hamsterIcon = new ImageIcon("hamster/hamster_icon.png");
            hamsterWalkRight[0] = new ImageIcon("hamster/hamster_walk_right/hamster_walk_right_0.png");
            hamsterWalkRight[1] = new ImageIcon("hamster/hamster_walk_right/hamster_walk_right_1.png");
            hamsterWalkRight[2] = new ImageIcon("hamster/hamster_walk_right/hamster_walk_right_2.png");
            hamsterWalkLeft[0] = new ImageIcon("hamster/hamster_walk_left/hamster_walk_left_0.png");
            hamsterWalkLeft[1] = new ImageIcon("hamster/hamster_walk_left/hamster_walk_left_1.png");
            hamsterWalkLeft[2] = new ImageIcon("hamster/hamster_walk_left/hamster_walk_left_2.png");
            hamsterWalkUp[0] = new ImageIcon("hamster/hamster_walk_up/hamster_walk_up_0.png");
            hamsterWalkUp[1] = new ImageIcon("hamster/hamster_walk_up/hamster_walk_up_1.png");
            hamsterWalkUp[2] = new ImageIcon("hamster/hamster_walk_up/hamster_walk_up_2.png");
            hamsterWalkUp[3] = new ImageIcon("hamster/hamster_walk_up/hamster_walk_up_3.png");
            hamsterWalkDown[0] = new ImageIcon("hamster/hamster_walk_down/hamster_walk_down_0.png");
            hamsterWalkDown[1] = new ImageIcon("hamster/hamster_walk_down/hamster_walk_down_1.png");
            hamsterWalkDown[2] = new ImageIcon("hamster/hamster_walk_down/hamster_walk_down_2.png");
            hamsterWalkDown[3] = new ImageIcon("hamster/hamster_walk_down/hamster_walk_down_3.png");
            hamsterStand[0] = new ImageIcon("hamster/hamster_walk_right/hamster_stand_right_0.png");
            hamsterStand[1] = new ImageIcon("hamster/hamster_walk_left/hamster_stand_left_0.png");
            hamsterStand[2] = new ImageIcon("hamster/hamster_walk_up/hamster_stand_up_0.png");
            
            /* // Doubles the size of images, very useful and may implement later. Must change collision with this.
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image newtest = toolkit.getImage("hamster/hamster_walk_down/hamster_stand_down_0.png");
            Image scaled = newtest.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            */
            
            hamsterStand[3] = new ImageIcon("hamster/hamster_walk_down/hamster_stand_down_0.png");
        }
        catch (Exception e) {
            
        }
    }
    
    void keyMovement (String direction) {
       if(direction.equals("UP"))
           y = y - 9;
       if(direction.equals("DOWN"))
           y = y + 9;
       if(direction.equals("LEFT"))
           x = x - 9;
       if(direction.equals("RIGHT")){
           x = x + 9;       
       }
       hamster_direction = direction;
    }
    
    void update() {
        //frames_since_update++;
        if(x > 395)
            x = 395;
        if(x < 20)
            x = 20;
        if(y > 275)
            y = 275;
        if(y < 20)
            y = 20;
    }
}

class Model {
    
    Gary gary;
    SunflowerSeed seed;
    MusicPlayer playsound;
    int dest_x;
    int dest_y;
    
    Model () {
        gary = new Gary();
        seed = new SunflowerSeed();
        playsound = new MusicPlayer("nom_0", "nom_1", "nom_2");
    }
    
    void setDestination (int x, int y) {
        dest_x = x;
        dest_y = y;
        //update();
    }
    
    void update()
    {
        gary.update();
        
        if(gary.x + 5 > seed.x &&
            gary.x < seed.x + 40 &&
            gary.y + 3 > seed.y &&
            gary.y < seed.y + 30)
	{
            playsound.run();            
            seed.update();
	}
   }
}

class View extends JPanel
{
	//JButton b1;
        Model model;
        Controller con;
        Image bg;
        JLabel seedsEaten;

	View(Controller c, Model m)
	{
                model = m;
                con = c;
                GridLayout experimentLayout = new GridLayout(0,1);
                setLayout(experimentLayout);
                setScoreLabel();
		//b1 = new JButton("Push me");
		//b1.addActionListener(c);
		try
		{
                    //Background
                    bg = ImageIO.read(new File("bg.jpg"));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
        
    private void setScoreLabel() {
        seedsEaten = new JLabel("Seeds: " + model.seed.totalSeeds, JLabel.LEFT);
        seedsEaten.setVerticalAlignment(SwingConstants.BOTTOM);
        add(seedsEaten);
    }
        
        @Override
	public void paintComponent(Graphics g)
	{
            super.paintComponent(g);
            
            if(model.seed.addPoint) {
                seedsEaten.setText("Seeds: " + model.seed.totalSeeds);
            }
            
            //Hamster cage background
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            
            //Draw sunflower seeds
            model.seed.seed.paintIcon(this, g, model.seed.x, model.seed.y);            
            
            //Draw hamster - checks for direction, then prints animated images in order for that direction
            if(con.right){
                model.gary.keyMovement("RIGHT");
                model.gary.hamsterWalkRight[model.gary.current].paintIcon(this, g, model.gary.x-20, model.gary.y-20);
                model.gary.current = (model.gary.current + 1) % model.gary.totalImages;
            }
            else if(con.left){
                model.gary.keyMovement("LEFT");
                model.gary.hamsterWalkLeft[model.gary.current].paintIcon(this, g, model.gary.x-20, model.gary.y-20);
                model.gary.current = (model.gary.current + 1) % model.gary.totalImages;
            }
            else if(con.up){
                model.gary.keyMovement("UP");
                model.gary.hamsterWalkUp[model.gary.current].paintIcon(this, g, model.gary.x-20, model.gary.y-20);
                model.gary.current = (model.gary.current + 1) % (model.gary.totalImages);
            }
            else if(con.down){
                model.gary.keyMovement("DOWN");
                model.gary.hamsterWalkDown[model.gary.current].paintIcon(this, g, model.gary.x-20, model.gary.y-20);
                model.gary.current = (model.gary.current + 1) % (model.gary.totalImages);
            }
            else{
                // If no key is pressed, last hamster direction is checked, and a standing picture is displayed
                if(model.gary.hamster_direction.equals("RIGHT")){
                    model.gary.hamsterStand[0].paintIcon(this, g, model.gary.x-20, model.gary.y-20);                   
                }
                else if(model.gary.hamster_direction.equals("LEFT")){
                    model.gary.hamsterStand[1].paintIcon(this, g, model.gary.x-20, model.gary.y-20);                   
                }
                else if(model.gary.hamster_direction.equals("UP")){
                    model.gary.hamsterStand[2].paintIcon(this, g, model.gary.x-20, model.gary.y-20);                   
                }
                else {
                    model.gary.hamsterStand[3].paintIcon(this, g, model.gary.x-20, model.gary.y-20);
                }
            }
	}
}


class Controller implements ActionListener, MouseListener, KeyListener
{
        Model mod;
        
        // Detects user input for direction
        Boolean up = false,down = false,left = false,right = false;

	Controller(Model m)
	{
            mod = m;
	}

        @Override
	public void actionPerformed(ActionEvent e)
	{
            //System.out.println("it works");
	}
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            //System.out.println("The user clicked the mouse");
            //mod.setDestination(e.getX(), e.getY());
            //System.out.println("CLICK!! x:" + e.getX() + " y:" + e.getY());
        }
        
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}

        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;
            }
        }

        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    up = false;
                    mod.gary.keyMovement("UP");
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    mod.gary.keyMovement("DOWN");
                    break;
                case KeyEvent.VK_LEFT:
                    left = false;
                    mod.gary.keyMovement("LEFT");
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    mod.gary.keyMovement("RIGHT");
                    break;
            }
        }
        public void keyTyped(KeyEvent e) {}
}


public class Game extends JFrame implements ActionListener
{
    public static final String TITLE = "Gary the Hamster";
    public static final int WIDTH = 400;
    public static final int HEIGHT = WIDTH / 4 * 3;
    
    Model m;
    
    public void stop(){
        System.exit(0);
    }
    
	public Game()
	{
                m = new Model();
		Controller c = new Controller(m);
		View v = new View(c,m);
                v.addMouseListener(c);
                addKeyListener(c);
                addWindowListener(new WindowAdapter() {
                    public void WindowClosing(WindowEvent e) {
                        System.err.println("Exiting Game");
                        stop();
                    }
                });
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		getContentPane().add(v);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		new Timer(90, this).start();
                
	}

	public void actionPerformed(ActionEvent evt)
	{
                m.update();
		repaint(); // Indirectly calls View.paintComponent
	}
        
	public static void main(String[] args)
	{
            
            Game game = new Game();
            
            //Background music player
            MusicPlayer player = new MusicPlayer("bg_music");
            player.run();
            
            
	}
}