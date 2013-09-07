
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
    
    
    public void paint(Graphics g) {
        System.out.println("tried to paint");
       
        if(textBox.isDisplayable()) {
            System.out.println("finished painting");
        }
        
    }
            
}
