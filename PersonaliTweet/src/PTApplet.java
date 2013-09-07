
import java.lang.reflect.InvocationTargetException;
import javax.swing.JApplet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Xiaomao
 */
public class PTApplet extends JApplet{


    @Override
    public void init()
    {
         try {
        javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
            public void run() {
                setGUI();
                add(new Board(1000, 1000));
            }
        });
    } catch (Exception e) {
        System.err.println("createGUI didn't successfully complete");
    }
    }
    
    public void setGUI( )
    {
        Board b = new Board(100, 100);
        b.repaint();
    }
    
}
