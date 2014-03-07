/*
 * Main.java
 *
 * Created on September 15, 2005, 12:33 AM
 *
 */

package gurpsgen;
import java.util.*;
import java.io.*;
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;


public class Main{
    
    /* start random number generator for the program
     * used for dice()
     */
    private static Random wheel = new Random();
    private static File file;
    
    
    private static String style = "html";
    private static String titleString = "GURPS Solar System Generator Output";
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    static String [] starInputs = {"Star Name", "Star Size", "Star type", "Bode constant", "binary?"};
    public static String [] starDetails = {"unknown", "Ia", "O", "10", "no"};
    public static String [] secondStarDetails = {"unknown", "Ia", "O", "10"};
    public static int index = 0;
    public static PrintWriter fileOutput;
    
    
    /**
     * @param args the command line arguments 
     */
    public static void main(String[] args) {
        
    
                InfoInput input = new InfoInput();
                input.createForm();
   
        
    }
     
    /* create a temporary file to hold the system info that we generate,
     * and set it up for writing  */
    public static void initFileOutput() {
        try {
            System.out.println("check 1");
            file = new File("GurpsTemp.html");
            file.createNewFile();
            fileOutput = new PrintWriter(new FileWriter(file));
            System.out.println("check 2");
        } catch (Exception e){
            //System.out.println("error creating output file");
        };
        printFileHeader();
    }
    
    
    /* display the file that has been created for the solar system */
    public static void showResults() {
        fileOutput.close();
        try {
            //cmd = "rundll32 url.dll,FileProtocolHandler http://some.url";
            //cmd = "rundll32 SHELL32.DLL,ShellExec_RunDLL " + file.getAbsolutePath();
            //System.out.println("1: "+file.getAbsolutePath() );
            Runtime.getRuntime().exec
                    ( "rundll32 url.dll,FileProtocolHandler " + file.getAbsolutePath() );
            //System.out.println("displaying output file");
        } catch (Exception e){
            //System.out.println("error displaying output file");
        };
    }
    
    
    /* ask the user if they would like to save the  file they 
     * just viewed, and if so save it under the name they chose for the sun */
    public static void saveFile() {
        boolean doSave = DialogDemo.showSaveQuery();
        if (doSave){
            File f2 = new File("GurpsSystem"+starDetails[0]+".html");
            file.renameTo(f2);
        }
    }

    
    /**a wrapper printing function, so that I can easily change to print to file
     *@param String output
     */
    public static void print(String output) {
        print(output, "", "");
    }
    
    public static void printCell(String contents){
        print(contents, "<td>", "</td>");
    }
    
    /* an html printing function, to work with the previous plain print */
    public static void print(String text, String firstTag, String endTag){
        if (style == "html"){
            fileOutput.println(firstTag + text + endTag);
        }
        else {
            fileOutput.println(text);
        }
    }
    
    /** a dice roll function
     * @param int number = number of dice to roll
     * @param int sides = number of sides on the dice
     */
    public static int dice(int number, int sides){
        double temp = wheel.nextDouble();   //between 1.0 and 0.0
        int roll = number * ((int)(temp*(sides - 1)) + 1);
        //print("roll of " +number +" die with "+sides+"sides = "+roll);
        return roll;
    }
    
    private static void printFileHeader(){
        print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN/\"") ;
	print("\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        print("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\">" );
        print("\n");
        print(titleString, "<title>", "</title>");
        print("<head> \n <b><h3 style=\"color:#ff0000;\">"+titleString+" </h3></b> \n</head>");
        print("<body> \n <table border=\"1\">");
    }
}
