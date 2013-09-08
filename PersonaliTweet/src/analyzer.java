
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class analyzer {

        Category[] theCategories;

        public analyzer(String initialFile) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(initialFile)));
                theCategories = new Category[Integer.parseInt(in.readLine())];
                for (int i = 0; i < theCategories.length; i++) {
                    theCategories[i] = new Category(in.readLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found dumbass");
            } catch (IOException e) {
                System.out.println("IO Exception motherfucker...");
            }
        }

        void analyze(ArrayList<String> tweets) {
            Pattern p;
            Matcher engine;
            for (int x = 0; x < tweets.size(); x++) {
                for (int i = 0; i < theCategories.length; i++) {
                    for (int m = 0; m < theCategories[i].words.length; m++) {
                        p = Pattern.compile(theCategories[i].words[m], Pattern.CASE_INSENSITIVE);
                        engine = p.matcher(tweets.get(x));
                        while (engine.find()) {
                            theCategories[i].counter++;
                            System.out.println(theCategories[i].words[m]);
                        }
                    }
                }
            }
        }
    }
