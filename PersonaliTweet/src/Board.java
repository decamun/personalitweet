import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.util.*;
import javax.swing.JPanel;

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
    public Board(ArrayList<Sprite> objects) {
        this.objects = objects;
    }
    @Override
    public void paint(Graphics g) {
        for(Sprite object : objects) {
            object.paint((Graphics2D)g);
        }
    }
}
