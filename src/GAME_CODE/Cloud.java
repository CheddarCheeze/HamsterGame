package GAME_CODE;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class Cloud extends Sprite
{
    static final int SPACING = 200;  	//minimum number of updates between tubes
    static Image img_cloud1 = null;
    static Image img_cloud2 = null;
    static boolean cloud1 = true;
    static int numClouds = 0;
    static int timeBetweenClouds = SPACING;	//counter for spacing tubes
    
    public Cloud(Random rnd_gen)
    {
        this.pos_x = 1000 + rnd_gen.nextInt(200);
        this.pos_y = rnd_gen.nextInt(600);
        if (img_cloud1 == null) try{
                this.img_cloud1 = ImageIO.read(new File("src/GAME_CODE/cloud1.png"));
        } catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
        
        if (img_cloud2 == null) try{
                this.img_cloud2 = ImageIO.read(new File("src/GAME_CODE/CLOUD2.png"));
        } catch(Exception e) { e.printStackTrace(System.err); System.exit(1); }
    }
    
    static void tryAddCloud(Random rnd_gen, LinkedList<Sprite> sprites)
    {
        timeBetweenClouds++;			//increment spacing counter
        //add new tubes if necessary
        if (Cloud.timeBetweenClouds > SPACING) {
                Cloud newCloud = new Cloud(rnd_gen);
                sprites.add(newCloud);
                timeBetweenClouds = 0;
        }
    }

    @Override
    Sprite replicate(LinkedList<Sprite> new_model_sprite) { return null; }

    @Override
    boolean update() 
    {
        this.pos_x -= 2;
        
        if(pos_x < -300) {
            numClouds--;		//model removes from list if out of range
            return true;
        } else
            return false;
    }

    @Override
    void draw(Graphics g) 
    {
        if (this.cloud1) {
                //draw top tube
                g.drawImage(this.img_cloud1, this.pos_x, this.pos_y, null);
        }
        else {
                //draw bottom tube
                g.drawImage(this.img_cloud2, this.pos_x, this.pos_y, null);
        }
    }

    @Override
    int state(boolean change) { return 0; }
}
