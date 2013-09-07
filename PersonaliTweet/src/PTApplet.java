
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
        Board b = new Board(100, 100);
        add(b);
        setSize(500, 500);
        //w.addBoard(b);
        //b.repaint();
    }
    
}
