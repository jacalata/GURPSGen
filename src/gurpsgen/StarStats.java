/*
 * StarStats.java
 *
 * Created on July 22, 2006, 3:14 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package gurpsgen;

/**
 *
 * @author Jac
 */
public class StarStats {
    
        /** the stats for each type/size of star are stored as a two-dimensional
     *  array, where the index into the array for a particular star is
     * [(typeIndex*(starSizes.length+1))+sizeIndex][statIndex]
     *
     * {0:stellar mass, 1:biozone start, 2:biozone end, 3:inner limit,
     * 4:stellar radius, 5:planets on, 6,7:orbits (nDice, modifier), 8:life Mod}
     **/
    
    private double [][] starStats =
    {
        { 70,  790, 1190,   16,  0.2,  0,  0,   0,  -12},    //O
        { 60,  630,  950,   13,  0.1,  0,  0,   0,  -12},
        {0},
        {0},
        {0},
        { 50,  500,  750,   10,    0,  0,  0,   0,   -9},
        {0},
        { 50,  500,  750,   10,  0.2,  0,  0,   0,  -10},  //B
        { 40,  320,  480,  6.3,  0.1,  0,  0,   0,  -10},
        { 35,  250,  375,    5,  0.1,  3,  3,   1,  -10},
        { 30,  200,  300,    4,    0,  3,  3,   1,  -10},
        { 20,  180,  270,  3.8,    0,  3,  3,   1,  -10},
        { 10,   20,   45,  0.6,    0,  4,  3,   0,   -9},
        {0},
        { 30,  200,  300,  4.0,  0.6,  3,  3,   3,  -10},  //A
        { 16,   50,   75,  1.0,  0.2,  3,  3,   2,  -10},
        { 10,   20,   30,  0.4,    0,  3,  3,   2,  -10},
        {  6,    5,  7.5,    0,    0,  3,  3,   1,  -10},
        {  4,    4,    6,    0,    0,  4,  3,   0,  -10},
        {  3,  3.1,  4.7,    0,    0,  5,  3,  -1,   -9},
        {0},
        { 15,  200,  300,    4,  0.8,  4,  3,   3,  -10},  //F
        { 13,   50,   75,    1,  0.2,  4,  3,   2,  -10},
        {  8,   13,   19,  0.3,    0,  4,  3,   1,   -9},
        {2.5,  2.5,  3.7,  0.1,    0,  4,   3,   0,   -9},
        {2.2,    2,    3,    0,    0,  6,  3,   0,   -9},
        {1.9,  1.6,  2.4,    0,    0, 13,  3,  -1,   -8},
        {0},
        { 12,  160,  240,  3.1,  1.4,  6,  3,   3,  -10},  //G
        { 10,   50,   75,    1,  0.4,  6,  3,   2,  -10},
        {  6,   13,   19,  0.3,  0.1,  6,  3,   1,   -9},
        {2.7,  3.1,  4.7,  0.1,    0,  6,  3,   0,   -8},
        {1.8,  1.0,  1.5,    0,    0,  7,  3,  -1,   -6},
        {1.1,  0.8,  1.2,    0,    0,  16, 3,  -2,    0},
        {0.8,  0.5,  0.8,    0,    0,  16, 2,   1,    1},
        { 15,  125,  190,  2.5,    3,  10, 3,   2,  -10}, //K
        { 12,   50,   75,    1,    1,  16, 3,   2,  -10},
        {  6,   13,   19,  0.3,  0.2,  16, 3,   1,   -9},
        {  3,    4,  5.9,  0.1,    0,  16, 3,   0,   -7},
        {2.3,    1,  1.5,    0,    0,  16, 3,  -1,   -5},
        {0.9,  0.5,  0.6,    0,    0,  16, 3,  -2,    0},
        {0.5,  0.2,  0.3,    0,    0,  16, 2,   1,    1},
        {20,   100,  150,    2,    7,  16, 3,   0,  -10},  //M
        {16,    50,   76,    1,  4.2,  16, 3,   0,  -10},
        {8,     16,   24,  0.3,  1.1,  16, 3,   0,   -6},
        {4,      5,  7.5,  0.1,  0.3,  16, 3,   0,   -6},
        {0},
        {0.3,  0.1,  0.2,    0,    0,  16, 3,  -2,    1},
        {0.2,  0.1,  0.1,    0,    0,  16, 2,   2,    2},
        {0.8, 0.03, 0.03,    0,    0,   0, 0,   0,   -10}   //D
    };
    
    public static String[] starTypes = {"O", "B", "A", "F", "G", "K", "M", "D"};
    public static String[] starSizes = {"Ia", "Ib", "II", "III", "IV", "V", "VI"};
    private int typeIndex, sizeIndex;
    private int star;
    private String starName, type, size;
    
    /** Creates a new instance of StarStats */
    public StarStats() {
        starName = Main.starDetails[0];
        type = Main.starDetails[1];
        size = Main.starDetails[2];
    }
    
    public String starName(){
        return starName;
    }
    
    public int getStarType(){

        for (int i=0; i<starTypes.length; i++){
            if (type.equals(starTypes[i])) {
                typeIndex = i;
                break;
            }
        }
        
        for (int j=0; j<starSizes.length; j++){
            if (size.equals(starSizes[j])) {
                sizeIndex = j;
                break;
            }
        }
        
        //check for invalid combinations of type/size
        star = typeIndex*starSizes.length + sizeIndex;
        
        if (starStats[star][0] == 0){
            Main.print("Invalid combination of star type and size");
            Main.print("Selected star GV");
            return 0;
        } else {
            Main.print("star = "+star);
            return star;
        } 
    }
    
    public double stellarMass(){
        return starStats[star][0]; 
    }
    
    public double minimumBiozone(){
        return starStats[star][1];
    }
    
    public double maximumBiozone(){
        return starStats[star][2];
    }
    
    public double innerLimit(){
        return starStats[star][3];
    }
    
    public double stellarRadius(){
        return starStats[star][4];
    }
    
    public double planetTest() {
        return starStats[star][5];
    }
        
    public double orbitsNDice() {
        return starStats[star][6];
    }
    
    public double orbitsModifier() {
        return starStats[star][7];
    }
    
    public double lifeModifier() {
        return starStats[star][8];
    }
    
        /**roll 3d6 to check if star has any planets, against the table*/
    public int calculateNumOrbits(){
        
        int x = Main.dice(3, 6);
        if (x <= planetTest() ){
            //number of orbits = nd6 + modifier
            int nDice = (int)orbitsNDice();
            int modifier = (int)orbitsModifier();
            int orbits = Main.dice(nDice, 6) + modifier;
            return orbits;
            
        } else {
          //  System.out.println("x= "+x + "; needed to be lower than "+starStats[star][5]);
            return 0;
        }
    }
    
    
    public void printStarDetails(){
                //Create a string with the header details about the system
        String details = "";
        //add Star Name
        details += ""+ Main.starInputs[0] +": " + starName +"\t";
        //add Star Type
        details += "" +Main.starInputs[2] +":" + type + size +"\n";
        Main.print(details);
    }
    
    
}
