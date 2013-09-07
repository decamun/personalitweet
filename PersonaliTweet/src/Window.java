import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class Window extends JFrame {
    public TestBoard board;
    //ImageIcon windowImg = new ImageIcon("sample path"); implement icon
    private ArrayList<Sprite> objects = new ArrayList<Sprite>();
    
    public Window(int width, int height) {
        //make drawing canvas *TestBoard
        board = new TestBoard(1000,1000);
        
        //set window settings
        this.setSize(width, height);
        this.setTitle("BoardTest");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //this.setIconImage(windowImg); implement icon
        this.setResizable(false);
        this.add(board);
        
    }
    
    
    public void addBoard(TestBoard board) {
        this.board = board;
    }
    
    public int addObject(Sprite s) {
        objects.add(s);
        return objects.size() - 1;
    }

    void removeObject(int index) {
        objects.remove(index);
        for(int i = index; i < objects.size(); i++) {
            objects.get(i).updateIndex(i);
        }
    }
    
    
    
    public void repaint() {
        board.repaint();
    }
}
