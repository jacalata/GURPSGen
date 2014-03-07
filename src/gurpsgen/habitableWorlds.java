/*
 * habitableWorlds.java
 *
 * Created on September 15, 2005, 4:10 AM
 */

package gurpsgen;
import gurpsgen.*;
import java.text.*;
import java.lang.Math.*;
/**
 *
 * @author Jac
 */
public class habitableWorlds {
    
    String name;
    boolean exists;
    boolean terrestrial;
    double orbit; //distance from sun in
    int orbitNumber; //number of orbits out (eg; for earth this is 3)
    int zone; //where it is in relation to the biozone
    double solarMass;
    int density;
    int size;   // diameter in miles
    int earthDiameter = 7915;
    double gravity;
    String composition;
    String tiltType;
    int degrees;    //tilt in degrees
    String atmosphere;
    int dayLength; // in earth hours
    int yearLength; //in earth hours
    int numDaysPerYear; //local days
    String atmosphericPressure = "unknown";
    String atmosphereGases = "unknown";
    String climate = "unknown";
    int climateType;
    double humidity;
    double waterSurface;   //percentage of the surface covered in water
    int numMoonlets;
    int numSmallMoons;
    int numMedMoons;
    int numLargeMoons;
    String terrain;
    
    
    String Metallic = "mostly silicates, lots of metals, rare elements," +
            " volcanoes and earthquakes, high background radiation," +
            " very hot!";
    String HighIron = "more metallic and volatile than Earth, but not " +
            "as extreme as a metallic planet";
    String MediumIron = "basically like Earth or Venus";
    String LowIron = "metals and volcanoes are rare, very little civilisation"+
            "because of the lack of materials to develop technology.";
    String Silicate = "almost no metals, volcanoes, earthquakes or heat." +
            "Harmful radiation from sun due to low magnetic field";
    String GasGiant = "an uninhabitable ball of frozen gas!";
    String [] compositionTypes = {
            "Metallic ",
            "High Iron",
            "Med. Iron",
            "Low Iron ",
            "Silicate ",
            "Gas Giant"
    };
    String [] compositionDescriptions = {Metallic, HighIron, MediumIron, LowIron, Silicate, GasGiant};
    
    String [] tilt = {  
            "None      ",
            "Minor     ",
            "Earthlike ",
            "Major     ",
            "Gross     "
    };
    
    String [] atmospheres = {  "",  //0
            "hostile greenhouse ",  //1
            "hot rockball       ",  //2
            "terrestrial        ",  //3
            "terrestrial, no air",  //4
            "asteroid belt      ",  //5
            "large gas giant    ",  //6
            "huge gas giant     ",  //7
    };
    
    String [] atmospherePressure = {
                "none      ",  //0
                "trace     ",  //1
                "very thin ",  //2
                "thin      ",  //3
                "standard  ",  //4
                "dense     ",  //5
                "very dense",  //6
                "superdense",  //7
    };
    
    String [] gases = {
                "hydrogen, CO2, methane", //0
                "oxygen-nitrogen       ", //1
                "polluted              ", //2
                "hydrogen with methane ", //3
                "methane with hydrogen ", //4
                "carbon oxides         ", //5
                "nitrogen              ", //6
                "ammonia               ", //7
                "chlorine              ", //8
                "fluorine              ", //9
                "high oxygen           ", //10
                "nitrides              ", //11
                "sulfur compounds      ", //12
                "water vapour          ", //13
    };
    
    String [] climates = { 
                "very hot    ",//0
                "hot         ",//1
                "tropical    ",//2
                "warm        ",//3
                "earth-normal",//4
                "cool        ",//5
                "chilly      ",//6
                "cold        ",//7
                "very cold   ",//8
                "frozen      ",//9
    };
    
    String [] terrains = {
                "barren             ", //0
                "icy                ", //1
                "hilly/rough        ", //2
                "mountain/volcanic  ", //3
                "plain/steppes      ", //4
                "forest/jungle      ", //5
                "marsh/swamp        ", //6
    };
    
    StarStats star;
    
    /** Creates a new instance of habitableWorlds */
    public habitableWorlds(StarStats starStats, int position, double distance) {
        System.out.println("new habitable world");
        star = starStats;
        if (distance <= star.innerLimit() ){
            this.empty();
        } else {
            DecimalFormat nameFmt = new DecimalFormat("00");
            name = star.starName() + " " + nameFmt.format(position);
            orbit = distance;
            orbitNumber = position;
            zone = findOrbitBiozone(orbit);
            solarMass = star.stellarMass();
            exists = true;
            size = Main.dice(2, 6) * 1000;
            density = (Main.dice(3, 6))/10 + Main.dice(1,6);
            gravity = size * density * 0.0000229;
            defineAxialTilt();
            defineComposition();
            defineAtmosphere();
            defineDayLength();
            defineClimate();
            defineWaterSurface();
            defineTerrain();
            defineHumidity();
            defineMoons();
            defineYear();
        }
    }
    
    public void printWorld(){
        if (exists) {
            DecimalFormat orbitFmt = new DecimalFormat("0.0");
            DecimalFormat sizeFmt = new DecimalFormat("00000");
            DecimalFormat gravityFmt = new DecimalFormat("0.0000");
            Main.print(""+name +", " + sizeFmt.format(size)
                    +",      "+gravityFmt.format(gravity) +", "+tiltType
                    +",    " + composition +",    "+atmosphere);
        }
        //else Main.print("empty orbit");
    }
    
    
        public void printWorldDetails(){
        if (exists & terrestrial) {
            DecimalFormat orbitFmt = new DecimalFormat("0.0");
            DecimalFormat sizeFmt = new DecimalFormat("00000");
            DecimalFormat gravityFmt = new DecimalFormat("0.0000");
            DecimalFormat degreesFmt = new DecimalFormat("00");
            DecimalFormat humidityFmt = new DecimalFormat("00.00");
            
            String biozone = "";
            if (zone == 1)
                biozone+="within";
            else if (zone == 0)
                biozone+="too close for";
            else 
                biozone+="further out than";
            biozone+=" the biozone";  
            Main.print("<tr>");
            Main.printCell(name);
            Main.printCell(orbitFmt.format(orbit)+"AU, "+biozone);
            Main.printCell(sizeFmt.format(size));
            Main.printCell(""+density);
            Main.printCell(gravityFmt.format(gravity)+"newtons");
            Main.printCell(tiltType+" ("+degreesFmt.format(degrees)+"degrees)");
            Main.printCell(composition);
            Main.printCell(atmosphere);
            Main.printCell(dayLength +" earth hours");
            Main.printCell(numDaysPerYear+" local days");
            Main.printCell(atmosphericPressure);
            Main.printCell(atmosphereGases);
            Main.printCell( climate() );
            Main.printCell(humidityFmt.format(waterSurface)+"% of the planet");
            Main.printCell(humidityFmt.format(humidity)+"%");
            //Main.printCell(numMoonlets+" moonlets, "+numSmallMoons+" small moons, "+numMedMoons
             //           +" medium moons and "+numLargeMoons+" large moons.");
            Main.printCell(terrain);
            Main.print("</tr>\n<tr>");
            Main.printCell("");
            String moons = ""+numMoonlets+" moonlets, "+numSmallMoons+" small moons, "+numMedMoons
                        +" medium moons and "+numLargeMoons+" large moons.";
            int availablecolumns = (starData.planetHeader.length-1);
            System.out.println("available columns: "+availablecolumns);
            Main.print(moons, "<td colspan = \""+availablecolumns+"\">", "</td>");
            Main.print("</tr>");
            
            
         /*   Main.print(name, "<td>", "</td>") 
                    +"\n   distance from sun = " +orbitFmt.format(orbit)+"AU, "+biozone
                    +"\n   size = " + sizeFmt.format(size)
                    +"\n   density = " + density
                    +"\n   gravity = "+gravityFmt.format(gravity)+"newtons"
                    +"\n   tilt = "+tiltType+" ("+degreesFmt.format(degrees)+"degrees)"
                    +"\n   composition = " + composition 
                    +"\n   atmosphere = "+atmosphere 
                    +"\n   length of day = "+dayLength +" earth hours"
                    +"\n   length of year = "+numDaysPerYear+" local days"
                    +"\n   pressure = "+atmosphericPressure 
                    +"\n   atmospheric gases = "+atmosphereGases
                    +"\n   climate = "+climate()
                    +"\n   water surface = "+humidityFmt.format(waterSurface)+"% of the planet"
                    +"\n   humidity = "+humidityFmt.format(humidity)+"%"
                    +"\n   Moons: "+numMoonlets+" moonlets, "+numSmallMoons+" small moons, "+numMedMoons
                        +" medium moons and "+numLargeMoons+" large moons."
                    +"\n   terrain is "+terrain);
          **/
        }
    }
    
    
    public void empty(){
        exists = false;
    }
    
    public boolean exists() {
        return exists;
    }
    
    private int findOrbitBiozone(double orbit){
        int zone;
        if ( orbit < star.minimumBiozone() ) zone = 0;//closer than biozone
        else if ( orbit < star.maximumBiozone() ) zone = 1; //in biozone
        else { //further than biozone
            if ( orbit > 10 * star.minimumBiozone() ){
                //greater than 10 times further out than the edge of the biozone
                zone = 3;
            } else
                zone = 2;
        }
        //Main.print(name + i+" ZONE = "+ZONE);
        return zone;
    }
    
    /** size: roll 2d and multiply by 1000 miles
     */
    public int size() {
        return size;
    }
    
    /** density
     * roll 3d and divide by 10, then roll 1d and add the two numbers
     */
    public int density() {
        return density;
    }
    
    
    /** composition
     * density -
     * 7.1 or higher : (metallic)
     * 6.1. -7 : (high iron)
     * 4.6 -6 : (medium iron)
     * 3.1 - 4.5: (low-iron)
     * 1.3 -3: (silicate)
     * 0.6 - 2.5: (gas giant)
     *
     */
    public String composition() {
        return composition;
    }
    
    private void defineComposition(){
        if (density >= 7.1) {
            composition = compositionTypes[0];
        } else if (density >= 6.1){
            composition = compositionTypes[1];
        } else if (density >=4.6){
            composition = compositionTypes[2];
        } else if (density >=3.1){
            composition = compositionTypes[3];
        } else if (density >=1.3){
            composition = compositionTypes[4];
        } else {
            composition = compositionTypes[5];
        }
    }
    
    /** gravity = diameter * density * 0.0000229 **/
    public double gravity(){
        return gravity;
    }
    
    /** axial tilt: find type of tilt by rolling2d6, then find exact degree**/
    public String axialTilt(){
        return (tiltType + ", " + degrees + "degrees");
    }
    
    private void defineAxialTilt() {
        
        int tiltEffect = Main.dice(2,6);
        switch (tiltEffect){
            case 2:
            case 3: tiltType = tilt[0];
            case 4:
            case 5:
            case 6:
            case 7: {
                tiltType = tilt[1];
                degrees  = 3*Main.dice(1,6);
                break;
            }
            case 8:
            case 9:
            case 10:{
                tiltType = tilt[2];
                degrees = 20+Main.dice(2,6);
                break;
            }
            case 11:{
                tiltType = tilt[3];
                degrees = 30+Main.dice(3,6);
                break;
            }
            default:{
                tiltType = tilt[4];
                degrees = 40+(10*Main.dice(1,6));
            }
        }
    }
    
    public String atmosphere(){
        return atmosphere;
    }
    
    public void defineAtmosphere(){
        
        int atmos = 0;
        int type = 0;
        /** for orbits that are closer to the star than the start of the biozone, roll 2d
         * 2-4 = empty, 5,6 = hostile greenhouse world, 7-9 = hot rockball,
         * 10,11 = asteroid belt, 12 = huge gas giant(or empty if in orbit 1)
         **/
        switch(zone) {
            case(0):
                atmos = Main.dice(2, 6);
                //Main.print("for closer than biozone: atmos = "+atmos);
                switch (atmos){
                    case 2:
                    case 3:
                    case 4: this.empty();    //empty orbit after all!
                    break;
                    case 5:
                    case 6: type = 1; //hostile greenhouse world
                    break;
                    case 7:
                    case 8:
                    case 9: type = 2;    //hot rockball
                    break;
                    case 10:
                    case 11: type = 5;
                             name = ""; //asteroid belt - they don't have names
                    break;
                    default:
                        if (orbitNumber == 0) {
                            this.empty();
                        } else{
                            type = 7;    //gas giant
                        }
                        break;
                        
                        
                }
                
                
                /** for orbits that are within the biozone, roll 2d6
                 * 2,3 = empty, 4-8 = terrestrial world,
                 * 9,10 = asteroid belt, 11 = large gas giant, 12 = huge gas giant
                 **/
            case(1):
               atmos = Main.dice(2, 6);
                //Main.print("for within biozone: atmos = "+atmos);
                switch (atmos){
                    case 2:
                    case 3:this.empty();    //empty orbit after all!
                    break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:type = 3;
                    terrestrial  = true;
                    this.defineTerrestrialAtmosphere();//terrestrial
                    break;
                    case 9:
                    case 10:type = 5;      //asteroid belt;
                    break;
                    case 11: type = 6;   //large gas giant
                    break;
                    case 12: type = 7;   //huge gas giant
                    break;
                    
                    
                }
                
                /** for orbits that are further away than the biozone, roll 1d, adding one if the
                 * orbit is more than 10 times the outer radius of the biozone
                 * 1 = terrestrial world, 2 = asteroid belt, 3 = empty orbit,
                 * 4-6 = large gas giant, 7 = terrestrial type world with little to no atmosphere
                 **/
            default:
                atmos = Main.dice(1, 6);
                if (zone == 3){
                    atmos+=1;
                }
                //Main.print("for outside biozone: atmos = "+atmos);
                switch (atmos){
                    case 1: type = 3;     //terrestrial
                    terrestrial = true;
                    this.defineTerrestrialAtmosphere();
                    break;
                    case 2: type = 5;   //asteroid belt;
                    break;
                    case 3: this.empty();    //empty orbit after all!
                    break;
                    case 4:
                    case 5:
                    case 6: type = 6;   //large gas giant
                    break;
                    case 7: type = 4;     //terrestrial no air
                    terrestrial = true;
                    break;
                    
                }
        }
        
        //Main.print("in atmosphere: type = "+type);
        atmosphere = atmospheres[type];
        //Main.print(type+"");
        return;
    }

    
    
    /** length of day */
    public int dayLength(){
        return dayLength;
    }
    
    private void defineDayLength() {
        int roll = Main.dice(2, 6);
        if (orbit == 1)
            roll-=4;
        else if (orbit == 2)
            roll -=2;
        
        
        if (size >= earthDiameter * 9)
            roll+=3;
        else if (size >= earthDiameter * 6)
            roll+=2;
        else if (size >= earthDiameter * 3)
            roll+=1;
        else if (size <= earthDiameter/2) // || large moon/binary world
            roll-=1;
        
        
        switch (roll) {
            case 1:
            case 2:
                dayLength = Main.dice(2,6) * 10 * 24; //earthdays
                break;
            case 3:
                dayLength = Main.dice(1,6) * 12 * 24; //easrthdays
                break;
            case 4:
                dayLength = Main.dice(1,6) * 5 * 24; //earthdays
                break;
            case 5:
                dayLength = Main.dice(2,6) * 10 ; //hours
                break;
            case 6:
                dayLength = Main.dice(1,6) * 10 ; //hours
                break;
            case 7:
                dayLength = Main.dice(7,6); //hours
                break;
            case 8:
                dayLength = Main.dice(6,6); //hours
                break;
            case 9:
                dayLength = Main.dice(5,6); //hours
                break;
            case 10:
                dayLength = Main.dice(4,6); //hours
                break;
            default:
                dayLength = Main.dice(3,6); //hours
                break;
        }
    }
    
    
    
    /** terrestrial atmospheres
     * to define a terrestrial worlds atmosphere, roll 2 dice for pressure
     *(subtract 1 for each full 20% decrease in diameter relative to earth
     *(add 1 for each full 20% increase "" ""
     *(subtract 2 for M class stars, subtract 1 for K-class stars ??
     *
     *
     *if atmosphere exists, then roll 2 dice for composition of the atmosphere:

     **/
    public void defineTerrestrialAtmosphere(){
        int pressure = Main.dice(2,6);
        double x = ((double)size / (double)earthDiameter);
        //Main.print("size = "+size+" earthDiameter = "+earthDiameter);
        //Main.print("pressure: "+pressure+", x:"+x);
        pressure += (-(1-x) / .2);
        //Main.print("pressure: "+pressure);
        int composition = Main.dice(2,6);
        if (pressure < 0) pressure = 0;
        
        switch (pressure){
            case(1):
            case(2):
            case(3):
                atmosphericPressure = atmospherePressure[0]; //none
                composition = 0;
            case(4):
                atmosphericPressure = atmospherePressure[1]; //trace
                break;
            case(5):
                atmosphericPressure = atmospherePressure[2]; //very thin
                break;
            case(6):
                atmosphericPressure = atmospherePressure[3]; //thin
                break;
            case(7):
            case(8):
            case(9):atmosphericPressure = atmospherePressure[4]; //standard
            break;
            case(10): atmosphericPressure = atmospherePressure[5]; //dense
            case(11): atmosphericPressure = atmospherePressure[6]; // very dense
            default: atmosphericPressure = atmospherePressure[7]; //superdense
        }
        
        switch(composition){
            case(0): atmosphereGases = "none"; break;
            case(5):
                atmosphereGases = gases[0];// 'reducing' atmosphere, hydrogen, CO2, methane
                break;
            case(6): // exotic (calculate type)
                atmosphereGases = "";
                defineExoticAtmosphere(1);
                break;
            case(7):
            case(8):
            case(9): atmosphereGases = gases[1];//oxygen-nitrogen
            break;
            case(10): atmosphereGases = gases[2];//polluted
            break;
            default: //corrosive
                atmosphereGases = "";
                defineExoticAtmosphere(2);
                break;
                
        }
    }
    
    
    /** exotic atmospheric gases
     *to find the exact composition of exotic, corrosive or superdense atmospheres, */
    public void defineExoticAtmosphere(int type){
        
        //roll 1d2 for the number of gases in the atmosphere
        int numGases = Main.dice(1, 2);
        // should add 1 for planets in the biozone, add 2 if closer than biozone
        
        for (int i =0; i < numGases; i++){
            //roll 1d1 for which type of gas is in the atmosphere
            int gasType = Main.dice(1,6);
            //first gas makes up 1d1 * 10% of the atmosphere, second is the remainder
            // any others are trace
            double amount = 0;
            if ((i == 0) && (numGases != 1)) amount = Main.dice(1,6) *0.1;
            else if (numGases == 1) amount = 100;
            else if (i == 1)amount = 100 - amount;
            else amount = 0.00123;
            
            if (type == 1){ //exotic or superdense
                switch(gasType){
                    case(1): atmosphereGases += gases[3];//hydrogen with methane
                    break;
                    case(2): atmosphereGases += gases[4];//methane with hydrogen
                    break;
                    case(3): atmosphereGases += gases[5];//carbon oxides
                    break;
                    case(4): if (numGases == 1) gasType = 5;
                    //corrosive: roll on next table
                    type = 2;
                    break;
                    default: atmosphereGases += gases[6];//nitrogen
                    break;
                    
                }
                atmosphereGases += "("+amount+"%) ";
            }
            if (type == 2){ //corrosive
                switch(gasType){
                    case(1):
                    case(2): atmosphereGases += gases[7];//ammonia
                    break;
                    case(3): atmosphereGases += gases[8];//chlorine
                    break;
                    case(4): atmosphereGases += gases[9];//fluorine
                    break;
                    case(5): atmosphereGases += gases[10];//high oxygen
                    break;
                    case(6): atmosphereGases += gases[11];//nitrides
                    break;
                    case(7): atmosphereGases += gases[12];//sulfur compounds
                    break;
                    default: atmosphereGases += gases[13];//water vapour
                    break;
                    
                }
            }
            
        }
    }
    
    
    public String climate() {
        return climate;
    }
    /**climate
     **/
    public void defineClimate() {
        
        climateType = Main.dice(3, 6);
        // if in a multi star system roll-=1
        // if the world is a moon of a gas giant roll+=3
        //if the sun is calss M roll+=2
        //if the sun is class K roll +=1
        switch(climateType){
            
            case(2):
            case(3):
            case(4):
            case(5):
                climate = climates[0];//very hot
                break;
            case(6):
            case(7):
                climate = climates[1];//hot
                break;
            case(8): climate = climates[2];//tropical
                break;
            case(9): climate = climates[3];//warm
                break;
            case(10): climate = climates[4];//earth-normal:
                break;
            case(11): climate = climates[5];//cool
                break;
            case(12): climate = climates[6];//chilly
                break;
            case(13): climate = climates[7];//cold
                break;
            case(14):
            case(15): climate = climates[8];//very cold
                break;
            default: climate = climates[9];//frozen
            Main.print("climate = "+climate);
        }
    }
    
    
    /**water surface */
    public void defineWaterSurface(){
        if (zone == 1)
            waterSurface = Main.dice(2,6)-2 * 0.1;
        else waterSurface = 0;
    }

     /*
     **terrain
     *primary = 2d6
     *2-4: barren if cool or warmer, else icy (roll again if surface water >30%)
     *5,6: hilly/rough - roll again if surface > 70% water
     *7: mountain/volcanic - roll again if surface > 30% water
     *8: plain/steppes - roll again if surface > 80% water
     *9, 10: forest.jungle - roll again if no plant life or water < 40%
     *11,12: marsh/swamp - if surface water <70%, surface water = 70%
     */
    public void defineTerrain(){
        defineTerrain(0);
    }
    
    public void defineTerrain(int recursionLevel){
         recursionLevel++;
        int primary = Main.dice(2,6);
        if (recursionLevel > 4){
            primary = 12;
        }
        switch(primary){
            case 2:
            case 3:
            case 4:
                if (waterSurface > .30) {
                    defineTerrain(recursionLevel);
                    break;
                } else {
                    if (climateType > 6 ){   //cold or very cold or frozen){
                        terrain = terrains[1]; // icy;
                    } else {
                        terrain = terrains[0];//barren;
                    }
                }
                break;
            case 5:
            case 6:
                if (waterSurface > .70) {
                    defineTerrain(recursionLevel);
                    break;
                } else{
                    terrain = terrains[2];//hilly/rough
                }
                break;
            case 7:
                if (waterSurface > .30){
                    defineTerrain(recursionLevel);
                    break;
                } else {
                    terrain = terrains[3];//mountain/volcanic
                }
                break;
            case 8:
                if (waterSurface > .80){
                    defineTerrain(recursionLevel);
                     break;
                } else {
                    terrain = terrains[4];//plain/steppes
                }
                break;
            case 9:
            case 10:
                if (waterSurface > .40){    //or there is no plant life?
                    defineTerrain(recursionLevel);
                     break;
                } else {
                    terrain = terrains[5];//forest/jungle
                }
                break;
                
            default:
                    if (waterSurface > .70){    //or there is no plant life?
                        waterSurface = .70;
                    }
                    terrain = terrains[6];//marsh/swamp
            break;
            
        }
     
    }

     /**length of year
     *length = sqroot( (orbit^3) / mass of star)
     *number of local /year = yearlength * 8766 / dayLength
     **/
    public void defineYear(){

        yearLength = (int) (Math.sqrt(Math.pow(orbit, 3)) / solarMass);
        numDaysPerYear = yearLength * 8766/dayLength;
    }
    
    /** moons for terrestrial worlds
     *num Moonlets = 1d4
     *num small moons = 1d4
     *num med moons = 1d5
     *num large moons = 1d5
     *modify each roll by -1 if size<=earthDiameter, +=1 if size >=1.5* earthDiameter
     *
     **/
    public void defineMoons(){
         int temp;
        if (size<=earthDiameter){
            temp = -1;
        } else if (size >=1.5* earthDiameter) {
            temp = 1;
        }
     numMoonlets = Main.dice(1,4);
     numSmallMoons = Main.dice(1,4);
     numMedMoons = Main.dice(1,5);
     numLargeMoons = Main.dice(1,5);
   
    }
    
    /** humidity */
     public void defineHumidity() {
         humidity = Main.dice(2,6)-2 * 0.1 + (0.1 * waterSurface);
     }
     /*water on uninhabitable worlds
     **/
    
    
    
    
}
