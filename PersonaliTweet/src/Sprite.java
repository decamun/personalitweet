
import java.awt.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class Sprite {
    
    private int index;
    
    public Sprite() {
        index = PersonaliTweet.window.addObject(this);
    }
    
    public void removeSelf() {
        PersonaliTweet.window.removeObject(index);
    }
    
    public void paint(Graphics2D g) {
        g.drawRect(10, 10, 10, 10);
    }

    void updateIndex(int i) {
        this.index = i;
    }
    
}
