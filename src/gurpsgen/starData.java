/*
 * starData.java
 *
 * Created on September 15, 2005, 12:48 AM
 *
 */

package gurpsgen;
import java.lang.*;
import java.util.*;
import gurpsgen.*;

/**
 *
 * @author Jac
 */
public class starData {
    
    double BodeConstant;
    StarStats starStats;
    
    public static String[] planetHeader = {"name", "size",  "gravity", "tilt", "composition",  "atmosphere"};
    

    /** Creates a new instance of starData */
    public starData() {
         
        starStats = new StarStats();
        habitableWorlds[] worlds = null;
        BodeConstant = Double.valueOf(Main.starDetails[3]).doubleValue();
       
        /** find which star type and size it is*/
        int star = starStats.getStarType();
                
        starStats.printStarDetails();

        int numOrbits = starStats.calculateNumOrbits();
        //array of orbits (the distance from the sun for each planet)
        double [] orbits = populateOrbits(numOrbits);
        /*generate world in each orbit */
        worlds = new habitableWorlds[numOrbits];
        for (int ii=0; ii < numOrbits; ii++) {
            worlds[ii] = new habitableWorlds(starStats, ii, orbits[ii]);
            worlds[ii].printWorld();
        }
        printPlanetDetailHeader(); 
        for (int jj =0; jj < numOrbits; jj++) {
            worlds[jj].printWorldDetails();
        }
        if (numOrbits == 0){
            Main.print("no planets exist in this system");
        }
        
        Main.showResults();
        Main.saveFile();
        return;
    }
    
    private double[] populateOrbits(int num){
        if (num == 0){
            return null;
        }
        double[] orbits = new double[num];
        orbits[0] = Main.dice(1, 6)*0.1;
        for (int k = 1; k < num; k++) {
            orbits[k] = (orbits[0]+(k*BodeConstant));
        }
        return orbits;
    }
    
    
    
    private void printPlanetDetailHeader() {
        /* print heading line */
        
        //{"Name",    "Orbit",  "Distance", "Type", 	"Diameter", "Density", "Gravity", 
       // "Atmosphere", "Moons"}
        
        //  {"name",      "distance", "size", "density", "gravity", "tilt",    "degrees", 
        // "composition",  "atmosphere",        "day length (hours)"};
        Main.print("<tr>");
        for (int ij = 0; ij<planetHeader.length; ij++){
            Main.printCell(planetHeader[ij]);
        }
        Main.print("</tr>");
    }
    
    
}






