
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
        w = new Window(1000, 1000);
        TestBoard b = new TestBoard(100, 100);
        add(new Window(1000, 1000));
        w.addBoard(b);
        b.repaint();
    }
    
}
