
import java.util.Calendar;
import java.awt.datatransfer.*;
import java.awt.*;
 
/**
 * 
 * @author Crunchify.com 
 * Program: How to keep a program running until the user terminates it? 
 * Version: 1.0
 * 
 */
 
public class Handler implements Runnable
{
 
	public static void perform() {
            System.out.println("Entered Perform");
        
        String x=null;

        

        try{
               Clipboard c=Toolkit.getDefaultToolkit().getSystemClipboard();

            // Get data stored in the clipboard that is in the form of a string (text)
            x=(String)c.getData(DataFlavor.stringFlavor);
            System.out.println(x.matches("^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$"));
            MakeRequest.makeGetRequest(x);
        }
        catch(Exception e){

            
        }
 
	}

    @Override
    public void run() {
        try{
        while(true){
        System.out.println("Entered While");
        Thread.currentThread().sleep(3000);
        System.out.println("After Wait");
        perform();
        System.out.println("After Perform");
        Thread.currentThread().sleep(3000);
        System.out.println("After While 2");
        }
        }catch(Exception E){}
    }
 
	
}