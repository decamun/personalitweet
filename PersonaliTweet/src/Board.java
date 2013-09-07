import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class Board extends JPanel {
    ArrayList<Sprite> objects;
    JTextField tf;
    public Board(ArrayList<Sprite> objects) {
        this.objects = objects;
        tf = new JTextField(30);
        add(tf);
        
    }
    public String getText()
    {
        return tf.getText();
    }
    @Override
    public void paintComponent(Graphics g) {
       // g.drawRect(100, 100, 100, 100);
//        for(Sprite object : objects) {
//            object.paint(g);
//        }
    }
  
}
