 package game;

import audio.MusicPlayer;
import galaga.*;
import java.awt.BorderLayout;

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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

 class WorldMap {
     Area[] areas;
     
     public WorldMap() {
         areas = new Area[16];
     }
 }
 
 class Area {
     Tile[][] tiles;
     
     public Area() {
         tiles = new Tile[8][8];
     }
     
     void setCollisions(int[] i) {
         for(int j = 0; j < 8; j++) {
             for(int k = 0; k < 8; k++) {
                 
             }
         }
     }
 }
 
 class Tile {
    
	private BufferedImage image;
	private int type;
	
	// tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() { return image; }
	public int getType() { return type; }
}
 
class SunflowerSeed {
    
    int x;
    int y;
    int[] path;
    int isize;
    int screenSize;
    Random randy;
    ImageIcon seed;
    int totalSeeds = 0;
    int maxx; //395
    int maxy; //275
    int min = 20;
    Boolean addPoint = false;
    
    SunflowerSeed(){
        x = 150;
        y = 100;
        maxx = 500;
        maxy = 500;
        seed = new ImageIcon("hamster/sunflower_seed.png");
        randy = new Random();
    }
    
    void resetSeed() {
        x = randy.nextInt((maxx - min) + 1) + min;
        y = randy.nextInt((maxy - min) + 1) + min;
        while(path[((int)(x/isize))+((int)(y/isize)*screenSize)] != 0) {
            x = randy.nextInt((maxx - min) + 1) + min;
            y = randy.nextInt((maxy - min) + 1) + min;
        }
    }
    
    void update() {
        //System.out.println("Seeds!");
        resetSeed();
        totalSeeds++;
        addPoint = true;
    }
}

class Gary {
    
    int x;
    int y;
    int current = 0;
    int totalImages = 3;
    int[] path;
    int isize;
    int screenSize;
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
        if(x > 635) //395 (-5)
            x = 635;
        if(x < 20)
            x = 20;
        if(y > 615) //275 (-25)
            y = 615;
        if(y < 20)
            y = 20;
        //  g.drawImage(sprites[path[i+screenSize*j]], i*isize, j*isize, isize, isize, this);
        if(path[((int)(x/isize))+((int)(y/isize)*screenSize)] != 0) {
            
            if(hamster_direction.equals("UP"))
                y = y + 9;
            if(hamster_direction.equals("DOWN"))
                y = y - 9;
            if(hamster_direction.equals("LEFT"))
                x = x + 9;
            if(hamster_direction.equals("RIGHT")){
                x = x - 9;       
            }
        }
    }
}

class NPC {
    
    int x,y;
    String name;
    ImageIcon pic;
    ImageIcon pic2;
    int currentArea;
    int current;
    boolean valid;
    
    NPC(String _name, int _x, int _y, String _pic, String _pic2, int ca) {
        name = _name;
        x = _x;
        y = _y;
        pic = new ImageIcon(_pic);
        pic2 = new ImageIcon(_pic2);
        currentArea = ca;
        current = 0;
        valid = false;
    }
    
    void setCharacter(String _name, int _x, int _y, String _pic, String _pic2, int ca){
        name = _name;
        x = _x;
        y = _y;
        pic = new ImageIcon(_pic);
    }
    
    void startDialog() {
        infoBox("Do you want to play a game?","Hammy");
    }
    void startDialog2() {
        infoBox("Do you want to play a game?","Hammy");
    }
    void startDialog3() {
        infoBox("Do you want to play a game?","Hammy");
    }
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        ImageIcon icon = new ImageIcon("Jigsaw.png");
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE,icon);
        TTT.main(new String[0]);
    }
    public static void infoBox2(String infoMessage, String titleBar)
    {
        ImageIcon icon = new ImageIcon("Jigsaw.png");
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE,icon);
        try {
            galaga.Game.main(new String[0]);
        }
        catch(Exception e ) {
            System.out.println("Something wrong with Galaga launch from game.Game.java?");
        }
    }
    public static void infoBox3(String infoMessage, String titleBar)
    {
        ImageIcon icon = new ImageIcon("Jigsaw.png");
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE,icon);
//        TTT.main(new String[0]);
//        try {
//            galaga.Game.main(new String[0]);
//        }
//        catch(Exception e ) {
//            System.out.println("Something wrong with Galaga launch from game.Game.java?");
//        }
    }
    
}

class Model {
    
    Gary gary;
    SunflowerSeed seed;
    NPC npc;
    NPC npc2;
    NPC npc3;
    MusicPlayer playsound;
    int dest_x;
    int dest_y;
    
    
    Model () {
        gary = new Gary();
        seed = new SunflowerSeed();
        playsound = new MusicPlayer("nom_0", "nom_1", "nom_2");
        npc = new NPC("Hammy", 500, 70, "hamster/npc/bluefron.png","hamster/npc/blue_front_nose_up.png",2);
        npc2 = new NPC("Girl", 470, 330, "hamster/npc/girlfront.png","hamster/npc/girl_move.png",2);
        npc3 = new NPC("Green", 210, 530, "hamster/npc/green_man_front.png","hamster/npc/green_man_step.png",1);
    }
    
    
    void setDestination (int x, int y) {
        dest_x = x;
        dest_y = y;
        //update();
    }
    
    void nearNPC() {
        if(npc.valid) {
            if(gary.x + 5 > npc.x &&
                gary.x < npc.x + 40 &&
                gary.y + 3 > npc.y &&
                gary.y < npc.y + 30)
            {
                npc.startDialog();
            }
        }
        if(npc2.valid) {
            if(gary.x + 5 > npc2.x &&
                gary.x < npc2.x + 40 &&
                gary.y + 3 > npc2.y &&
                gary.y < npc2.y + 30)
            {
                npc2.startDialog2();
            }
        }
        if(npc3.valid) {
            if(gary.x + 5 > npc3.x &&
                gary.x < npc3.x + 40 &&
                gary.y + 3 > npc3.y &&
                gary.y < npc3.y + 30)
            {
                npc3.startDialog3();
            }
        }
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
        BufferedImage bg;
        BufferedImage tileset;
        BufferedImage[] sprites;
        WorldMap worldMap;
        JLabel seedsEaten;
        int isize;
        int screenSize;
        int[][] path;
        int currentArea;
        
        static boolean game1;
        static boolean game2;
        static boolean game3;
        

	View(Controller c, Model m) {
            model = m;
            con = c;
            game1 = false;
            game2 = false;
            game3 = false;
//            GridLayout experimentLayout = new GridLayout(4,4);
//            setLayout(experimentLayout);
            setScoreLabel();
            //b1 = new JButton("Push me");
            //b1.addActionListener(c);
            try {
                //Background
                isize = 64;
                screenSize = 10;
                currentArea = 0;
                bg = ImageIO.read(new File("bg.jpg"));
                tileset = ImageIO.read(new File("tiles.png"));
                sprites = new BufferedImage[5];
                for(int i = 0; i < 5; i++) {
                    sprites[i] = tileset.getSubimage(i*isize, 0, isize, isize);
                }
                sprites[0] = ImageIO.read(new File("bg.jpg"));
                sprites[1] = tileset.getSubimage(0*isize, 0, isize, isize);
//                worldMap = new WorldMap(); //
                path = new int[][]{new int[]{
                    1,1,1,1,1,1,1,1,1,1,
                    1,0,0,0,0,1,1,1,1,0,
                    1,1,1,1,0,1,1,1,0,0,
                    1,1,0,0,0,1,1,0,0,1,
                    1,1,0,1,1,1,1,0,1,1,
                    1,1,0,0,0,1,1,0,1,1,
                    1,1,1,1,0,0,0,0,1,1,
                    1,1,1,0,0,1,1,0,1,1,
                    1,1,0,0,1,1,1,0,0,0,
                    0,0,0,1,1,1,1,1,0,0
                }, new int[] {
                    1,0,0,1,1,1,1,1,0,0,
                    1,1,0,1,1,1,1,0,0,1,
                    1,1,0,0,1,1,0,0,1,1,
                    1,1,1,0,0,0,0,1,1,1,
                    1,1,1,0,1,1,0,1,1,1,
                    1,1,0,0,0,0,0,1,1,1,
                    1,1,0,1,1,1,1,1,1,1,
                    1,1,0,0,1,1,1,1,1,1,
                    1,1,1,0,1,1,1,1,1,1,
                    1,1,1,1,1,1,1,1,1,1
                }, new int[] {
                    1,1,1,1,1,1,1,1,1,1,
                    0,0,0,1,1,0,1,0,0,1,
                    0,1,0,0,0,0,0,0,1,1,
                    1,1,1,1,1,1,1,1,1,1,
                    1,1,1,1,1,1,1,1,1,1,
                    1,1,1,0,1,1,1,0,1,1,
                    1,1,1,0,1,3,0,0,1,1,
                    1,1,0,0,0,2,0,1,1,1,
                    0,0,0,0,1,1,1,1,0,1,
                    0,1,1,1,1,1,1,0,0,1
                }, new int[] {
                    0,1,1,1,1,1,1,0,1,1,
                    0,0,1,1,1,1,0,0,1,1,
                    1,0,1,1,1,0,0,1,1,1,
                    1,0,0,0,1,0,1,1,1,1,
                    1,1,1,0,1,0,1,1,1,1,
                    1,1,0,0,1,0,0,0,1,1,
                    1,0,0,1,1,1,1,0,0,1,
                    1,0,1,1,1,1,1,1,0,1,
                    1,0,0,0,0,0,0,0,0,1,
                    1,1,1,1,1,1,1,1,1,1
                }};
                model.gary.path = path[currentArea];
                model.gary.isize = isize;
                model.gary.screenSize = screenSize;
                model.seed.path = path[currentArea];
                model.seed.isize = isize;
                model.seed.screenSize = screenSize;
            }
            catch(Exception e) {
                e.printStackTrace(System.err);
                System.exit(0);
            }
	}
        
        
        public static void game1Clear() {
            game1 = true;
        }
        public static void game2Clear() {
            game2 = true;
        }
        public static void game3Clear() {
            game3 = true;
        }
        
        public void game1Change() {
            path[2][65] = 0;
            path[2][75] = 0;
        }
        
    private void setScoreLabel() {
        seedsEaten = new JLabel("Seeds: " + model.seed.totalSeeds, JLabel.LEFT);
        seedsEaten.setVerticalAlignment(SwingConstants.BOTTOM);
        add(seedsEaten);
    }
    
    void refreshMap(int current) {
        model.gary.path = path[current];
        model.seed.path = path[current];
        model.seed.resetSeed();
    }
        
        @Override
	public void paintComponent(Graphics g)
	{
            super.paintComponent(g);
            
            if(model.seed.addPoint) {
                seedsEaten.setText("Seeds: " + model.seed.totalSeeds);
            }
            
            //Hamster cage background
            //g.drawImage(bg, 0, 0, 240, 240, this);
            if(currentArea == 0 && model.gary.y > screenSize*isize-40 && model.gary.hamster_direction.equals("DOWN")) {
                currentArea = 1;
                model.npc3.valid = true;
                model.gary.y = 15;
                refreshMap(currentArea);
            }
            else if(currentArea == 1 && model.gary.y < 30 && model.gary.hamster_direction.equals("UP")) {
                currentArea = 0;
                model.npc3.valid = false;
                model.gary.y = screenSize*isize-40;
                refreshMap(currentArea);
            }
            else if(currentArea == 0 && model.gary.x > screenSize*isize-30 && model.gary.hamster_direction.equals("RIGHT")) {
                currentArea = 2;
                model.npc.valid = true;
                model.npc2.valid = true;
                model.gary.x = 15;
                refreshMap(currentArea);
            }
            else if(currentArea == 2 && model.gary.x < 25 && model.gary.hamster_direction.equals("LEFT")) {
                currentArea = 0;
                model.npc.valid = false;
                model.npc2.valid = false;
                model.gary.x = screenSize * isize - 30;
                refreshMap(currentArea);
            }
            else if(currentArea == 1 && model.gary.x > screenSize*isize-30 && model.gary.hamster_direction.equals("RIGHT")) {
                currentArea = 3;
                model.npc3.valid = false;
                model.gary.x = 15;
                refreshMap(currentArea);
            }
            else if(currentArea == 3 && model.gary.x < 25 && model.gary.hamster_direction.equals("LEFT")) {
                currentArea = 1;
                model.npc3.valid = true;
                model.gary.x = screenSize * isize - 30;
                refreshMap(currentArea);
            }
            else if(currentArea == 2 && model.gary.y > screenSize*isize-40 && model.gary.hamster_direction.equals("DOWN")) {
                currentArea = 3;
                model.npc.valid = false;
                model.npc2.valid = false;
                model.gary.y = 15;
                refreshMap(currentArea);
            }
            else if(currentArea == 3 && model.gary.y < 30 && model.gary.hamster_direction.equals("UP")) {
                currentArea = 2;
                model.npc.valid = true;
                model.npc2.valid = true;
                model.gary.y = screenSize*isize-40;
                refreshMap(currentArea);
            }
            
            
            //Draw NPC
            if(currentArea == 2) {
                if(game1) {
                    game1Change();
                }
                g.drawImage(sprites[1], 5*isize, 6*isize, isize, isize, this);
            }
            
            for(int i =0; i < screenSize;i++) {
                for(int j = 0; j<screenSize;j++) {
                    g.drawImage(sprites[path[currentArea][i+screenSize*j]], i*isize, j*isize, isize, isize, this);
                }
            }
            
            for(int i =0; i < screenSize;i++) {
                if(currentArea == 0 || currentArea == 2)
                    g.drawImage(sprites[4], i*isize, -40, isize, isize, this);
                if(currentArea == 0 || currentArea == 1)
                    g.drawImage(sprites[4], -30, i*isize, isize, isize, this);
            }
//            g.drawImage(sprites[0], isize*4, isize*3, isize, isize, this);
//            g.drawImage(sprites[0], isize*4, isize*4, isize, isize, this);
//            
            //Draw sunflower seeds
            model.seed.seed.paintIcon(this, g, model.seed.x, model.seed.y);       
            
            //Draw NPC
            if(currentArea == 2) {
                if(model.npc.current++ % 5 == 0 ) {
                    model.npc.pic.paintIcon(this, g, model.npc.x, model.npc.y);
                    model.npc2.pic.paintIcon(this, g, model.npc2.x, model.npc2.y);
                }
                else {
                    model.npc.pic2.paintIcon(this, g, model.npc.x, model.npc.y);
                    model.npc2.pic2.paintIcon(this, g, model.npc2.x, model.npc2.y);
                    model.npc.current = 0;
                }
            }
            else if(currentArea == 1) {
                if(model.npc3.current++ % 7 == 0) {
                    model.npc3.pic.paintIcon(this, g, model.npc3.x, model.npc3.y);
                }
                else {
                    model.npc3.pic2.paintIcon(this, g, model.npc3.x, model.npc3.y);
                    model.npc3.current = 0;
                }
            }
            
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
                case KeyEvent.VK_SPACE:
                    mod.nearNPC();
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
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH; // / 4 * 3;
    
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
                JScrollPane scrollPane = new JScrollPane(v);
                getContentPane().add(scrollPane);
		setSize(WIDTH, HEIGHT);
//		getContentPane().add(v);
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