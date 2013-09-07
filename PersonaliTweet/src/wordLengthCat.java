
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class wordLengthCat extends Category {
    
    public wordLengthCat() {
        super("");
        this.name  = "word length";
    }
    
    public void evaluateMatch(String tweet) {
        if(tweet.length() > 12) {
            this.counter++;
        }
    }
    
}
