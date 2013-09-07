
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
public class Category {

        String[] words;
        int counter;
        String name;

        public Category(String cat) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(cat)));
                words = new String[Integer.parseInt(in.readLine())];
                for (int i = 0; i < words.length; i++) {
                    words[i] = in.readLine();
                }
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("IO Exception motherfucker...");
            }
            name = cat;
        }
    }
