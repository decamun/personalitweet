
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Xiaomao
 */
public class PTApplet extends JApplet{

    private Window w;
    @Override
    public void init()
    {
         try {
        javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                setGUI();
            }
        });
    } catch (Exception e) {
        System.err.println("createGUI didn't successfully complete");
    }
    }
    
    public void setGUI( )
    {
        //w = new Window(1000, 1000);
        this.setSize(new Dimension(1000, 1000));
        Board b = new Board(1000, 1000);
        add(b);
        //w.addBoard(b);
        //b.repaint();
    }
    
}
