
import java.awt.*;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class TextSprite extends Sprite {
    JTextField textBox; 
    public TextSprite() {
        textBox = new JTextField();
    }
    
    @Override
    public void paint(Graphics2D g) {
        System.out.println("tried to paint");
        textBox.paint(g);
        if(textBox.isDisplayable()) {
            System.out.println("finished painting");
        }
        
    }
            
}
