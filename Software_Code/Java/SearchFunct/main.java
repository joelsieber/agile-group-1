import java.io.*;
import java.util.*;
import java.lang.*;
/**
 * Search & Sort Functions
 *
 * Document Describing Tests
 * https://docs.google.com/document/d/1EYwUU2T9rr4NmJ4yZTtlWaTO0X1KGq0Bqhrc9-Y-Ots/edit?usp=sharing
 */
public class main{
    //test data composed of information from the database
    String[][] list = {{"871 - SEPTICEMIA OR SEVERE SEPSIS W/O MV >96 HOURS W MCC", "670108", "BAYLOR SCOTT & WHITE MEDICAL CENTER - MARBLE FALLS", "810 W HIGHWAY 71", "MARBLE FALLS", "TX", "78654", "TX - Austin", "89", "34461.67", "13104.79", "7695.337", ""}, {"871 - SEPTICEMIA OR SEVERE SEPSIS W/O MV >96 HOURS W MCC", "670120", "THE HOSPITALS OF PROVIDENCE TRANSMOUNTAIN CAMPUS", "2000 TRANSMOUNTAIN RD", "EL PASO", "TX", "79911", "TX - El Paso", "22", "147342.18", "18504.95", "13261.09", ""}, {"470 - MAJOR JOINT REPLACEMENT OR REATTACHMENT OR LOWER EXTREMITY W/O MCC", "670116", "WISE HEALTH SYSTEM", "3200 NORTH TARRANT PARKWAY", "FORT WORTH", "TX", "76177", "TX - Fort Worth", "30", "94373.47", "13979.7", "11226.63", ""}, {"689 - KIDNEY & URINARY TRACT INFECTIONS W MCC", "670108", "BAYLOR SCOTT & WHITE MEDICAL CENTER - MARBLE FALLS", "810 W HIGHWAY 71", "MARBLE FALLS", "TX", "78654", "TX - Austin", "13", "21297.62", "8200", "3424.308", ""}, {"190 - CHRONIC OBSTRUCTIVE PULMONARY DISEASE W MCC", "670120", "THE HOSPITALS OF PROVIDENCE TRANSMOUNTAIN CAMPUS", "2000 TRANSMOUNTAIN RD", "EL PASO", "TX", "79911", "TX - El Paso", "12", "64578.42", "8526.75", "7649.33", ""}};
    
    public main(){
        
    }
    //Method gets procedure name from user
    public String inputFromUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Query: ");
        String input = scanner.nextLine();
        return input;
    }
    //Search for procedures or codes
    public void codeProcedureSearch(){
        String query = inputFromUser();
        boolean[] found = new boolean[list.length]; 
        Arrays.fill(found, Boolean.FALSE); 
        
        for(int i = 0; i < list.length; i++){
            if(list[i][0].contains(query.toUpperCase())){//Searches through the DRG Definition field.
                found[i] = true;
            }
        }
        for(int i = 0; i < list.length; i++){
            if(found[i] == true){
                System.out.println(list[i][1] + "/" + list[i][2] + "/" + list[i][3] + "/" + list[i][4]+ "/" + list[i][5] + "/" + list[i][6] + "/" + list[i][7]);
            }
        }
    }
    //Search for locations
    public void locationSearch(){
        String query = inputFromUser();
        boolean[] found = new boolean[list.length]; 
        Arrays.fill(found, Boolean.FALSE);
        
        for(int i = 0; i < list.length; i++){
            if(list[i][7].toUpperCase().contains(query.toUpperCase())){//Searches through the state and city field.
                found[i] = true;
            }
        }
        for(int i = 0; i < list.length; i++){
            if(found[i] == true){
                System.out.println(list[i][1] + "/" + list[i][2] + "/" + list[i][3] + "/" + list[i][4]+ "/" + list[i][5] + "/" + list[i][6] + "/" + list[i][7]);
            }
        }
    }
    
    public void locationTest(){
        //User Coords
        double userx = 56.462;
        double usery = -2.971;
        //Provider Coords
        double[] provx = {30.111, 31.899, 32.897, 30.111, 31.899};
        double[] provy = {-97.359,-106.408 , -97.313, -97.359, -106.408};
        Location userLocation = new Location();
        Location[] provLocation = new Location[list.length];
        
        //Temporary Values created and added to remove the nulls in provLocation.
        for(int i = 0; i < list.length; i++){
            Location tempVal = new Location();
            provLocation[i] = tempVal;
        }
        
        //Sets coords into the classes.
        userLocation.setCoords(userx, usery);
        for(int i = 0; i < list.length; i++){
            provLocation[i].setCoords(provx[i], provy[i]);
        }
        
        double userLat = userLocation.getLatitude();
        double userLong = userLocation.getLongitude();
        double[] providerLat = new double[list.length];
        double[] providerLong = new double[list.length];
        
        for(int i = 0; i < list.length; i++){
            providerLat[i] = provLocation[i].getLatitude();
            providerLong[i] = provLocation[i].getLongitude();
        }
        
        distanceSort(userLat, userLong, providerLat, providerLong);
    }
    
    public void distanceSort(double currLat, double currLong, double[] providerLat, double[] providerLong){
        double[] distance = distanceCalc(currLat, currLong, providerLat, providerLong);
        
        //Adds distances to the list. *Will not work multiple times!*
        for (int i = 0; i < list.length; i++){
            list[i][12] = String.valueOf(distance[i]);
        }
        
        for(int i = 0; i < list.length; i++){
            int low = i;
            for(int j = 0; j < list[i].length; j++){
                for(int k = j; k < list.length; k++){
                    if(Double.parseDouble(list[j][12]) > Double.parseDouble(list[low][12])){// Changes to double are made to correctly compare the two values.
                        low = j;
                        swap(list[low], list[i]);
                    }
                }
            }
        }

        printMultiArray(list);
    }
    //Method finds distance between two points with longitude and latitude(As-Crows-Flys)
    //Reference:
    //https://www.movable-type.co.uk/scripts/latlong.html
    //Altered to use arrays
    public double[] distanceCalc(double currLat, double currLong, double[] providerLat, double[] providerLong){
        double[] distance = new double[list.length];
        double radius = 6371e3;
        double theta1 = Math.toRadians(currLat);
        double[] theta2 = new double[list.length];
        for (int i = 0; i < list.length; i++){
             theta2[i] = Math.toRadians(providerLat[i]);
        }
        double[] deltaTheta = new double[list.length];
        double[] deltaLambda = new double[list.length];
        double[] a = new double[list.length];
        double[] c = new double[list.length];
        
        for(int i = 0; i < list.length; i++){
            deltaTheta[i] = Math.toRadians(providerLat[i]-currLat);
            deltaLambda[i] = Math.toRadians(providerLong[i] - currLong);
        }
        
        for(int i = 0; i < list.length; i++){
            a[i] = (Math.sin(deltaTheta[i] / 2) * Math.sin(deltaTheta[i] / 2)) + Math.cos(theta1) * Math.cos(theta2[i]) * (Math.sin(deltaLambda[i] / 2) * Math.sin(deltaLambda[i] / 2)); 
        }
        
        for(int i = 0; i < list.length; i++){
            c[i] = 2 * Math.atan2(Math.sqrt(a[i]), Math.sqrt(1-a[i]));
        }
        
        for(int i = 0; i < list.length; i++){
            distance[i] = radius * c[i];
        }
        return distance;
    }
    //sorts the list based on price in an ascending order
    public void priceSort(){
        for(int i = 0; i < list.length; i++){
            int low = i;
            for(int j = 0; j < list[i].length; j++){
                for(int k = j; k < list.length; k++){
                    if (Double.parseDouble(list[j][11]) > Double.parseDouble(list[low][11])){
                        low = j;
                        swap(list[low], list[i]);
                    }
                }
            }
        }
        
        printMultiArray(list);
    }
    //Prints a 2-dimensional array
    public void printMultiArray(String[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                System.out.print("/" + array[i][j] + "/");
            }
            System.out.println();
        }
    }
    
    public void quickPrint(){
        printMultiArray(list);
    }
    //Swaps two lines in the list for the purpose of sorting
    public void swap(String[] x, String[] y){
        for(int i = 0; i < list[0].length; i++){
            String temp = x[i];
            x[i] = y[i];
            y[i] = temp;
        }
    }
}
