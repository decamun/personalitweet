
import java.util.regex.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Xiaomao
 */
public class regexTest {
 
    public static void main(String[] args)
    {
        Pattern p =  Pattern.compile("hello", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher("HELLo hellowhatsuup");
        while(m.find())
            System.out.println("works");
        
    }
}
