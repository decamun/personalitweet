
import java.util.ArrayList;
import twitter4j.*;
import java.io.*;
import java.util.*;

//PennApps Fall 2013 alksdjf;lsaf;lsaf;lsadjf
//this works muthafucka!

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class PersonaliTweet {

    
    /**
     * @param args the command line arguments
     */
    static class category{
		
	String[] words;
	int counter;
	String name;
		
	public category(String cat){
		try{
			BufferedReader in=new BufferedReader(new FileReader(new File(cat)));
			words=new String[Integer.parseInt(in.readLine())];
			for (int i=0; i<words.length; i++){
				words[i]=in.readLine();
			}
			in.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch (IOException e){
			System.out.println("IO Exception motherfucker...");
		}
		name=cat;
	}	
    }
	
    static class analyzer{
	category[] theCategories;
		
	public analyzer(String initialFile){
            try{
                BufferedReader in=new BufferedReader(new FileReader(new File(initialFile)));
		theCategories=new category[Integer.parseInt(in.readLine())];
		for (int i=0; i<theCategories.length; i++){
                    theCategories[i]=new category(in.readLine());
		}
		in.close();
            }
            catch(FileNotFoundException e){
                System.out.println("File not found dumbass");
            }
            catch (IOException e){
		System.out.println("IO Exception motherfucker...");
            }
        }
    }
    
    static Window window = new Window(1000, 1000);
    
    //Main function
    public static void main(String[] args) {
        
        
        System.out.println("blah");
        Sprite testObj = new Sprite();
        window.repaint();
        analyzer test=new analyzer("categories.txt");
	for (int i=0; i<test.theCategories.length; i++){
            System.out.println(test.theCategories[i].name);
            for (int m=0; m<test.theCategories[i].words.length; m++){
                System.out.println(test.theCategories[i].words[m]);
                //this is a change
                //this is another change
            }
	}
    }
    
    
}



//End of the line. Code above this line should work...
