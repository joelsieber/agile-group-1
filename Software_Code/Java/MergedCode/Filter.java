/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jodielaurenson
 */
public class Filter {
    Record records[];
    
   
    
    
    
    /**
     * this method takes in all records and uses the min and max parameters
     * to filter the array and returns a new array with records with prices
     * between the min and max
     * 
     * @param records the array of records, contains all data
     * @param min the minimum price given by the user
     * @param max the maximum price given by the user
     * @return an array of records only between the given price range
     */
    public Record[] restrictToPrice(Record records[], double min, double max){
        Record recordsInRange[] = new Record[records.length]; //recordsInRange is initialized
        int pos = 0; 
        for(int i = 0; i < records.length; i++){ //go through every record
            
            if(records[i].getPrice()>=min && records[i].getPrice()<=max){ //true if current price is in range
                recordsInRange[pos] = records[i]; //adds the record to the refined array
                
                pos++;
            }
        }

        //creates a new array that is same as recordsInRange but without all the nulls
        Record returnRecords[] = new Record[pos]; 
        for(int i = 0; i < pos; i++){
            returnRecords[i] = recordsInRange[i];
        }
        return returnRecords;
    }
    
    /**
     * This method uses the Haversine formula to calculate the distance between 
     * two points given the longitude and latitude. The equation was taken from
     * https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
     * 
     * @param currentLocation the location of the user stored in an object
     * @param targetLocation the location of the target, stored in an object
     * @return the distance between the two points in kilometres
     */
    public double calculateDistance(Location currentLocation, Location targetLocation){
        final double RADIUS_OF_EARTH = 6371;
        double latitudeDifference = Math.toRadians(currentLocation.getLatitude()-targetLocation.getLatitude()); //calculated difference between latitude in radians
        double longitudeDifference = Math.toRadians(currentLocation.getLongitude()-targetLocation.getLongitude());//calculated difference between longitude in radians
        
        //calculates the first part of Haversines formula
        double x = Math.sin(latitudeDifference/2)*Math.sin(latitudeDifference/2)+Math.cos(Math.toRadians(currentLocation.getLatitude()))*Math.cos(Math.toRadians(targetLocation.getLatitude()))*Math.sin(longitudeDifference/2)*Math.sin(longitudeDifference/2);
        double y = 2*Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
        double distance = RADIUS_OF_EARTH * y;
        System.out.println("Distance between "+currentLocation.getZip()+" and "+targetLocation.getZip()+" is: "+(int)distance+"km");
        return distance;
        
    }
    
    /**
     * goes through all records and returns all within the distance range
     * 
     * @param currentLocation location of the user
     * @param range maximum distance in kilometres the user wants displayed
     * @param locations all records
     * @return 
     */
    public Location[] filterByLocation(Location currentLocation, int range, Location[] locations){
        Location locationsInRange[] = new Location[locations.length]; 
        int counter = 0;
        for(int l = 0;l<locations.length;l++){
            double currDistance = calculateDistance(currentLocation, locations[l]);
            if(currDistance<range){
                locationsInRange[counter]=locations[l];
                counter++;
            }
        }
        //creates a new array that is same as recordsInRange but without all the nulls
        Location returnLocations[] = new Location[counter]; 
        for(int i = 0; i < counter; i++){
            returnLocations[i] = locationsInRange[i];
        }
        return returnLocations;
    }
    
    /**public void searchLongAndLat(int zip, Location[] locations, Record[] records){
        for(int i = 0; i < locations.length; i++){
            if(locations[i].getZip()==zip){
                calculateDistance(locations[i], )
            }
        }
    }*/
    
    public Location getCurrentLocation(int zip, Location[] locations){
        int min = 0;
        int max = locations.length-1;
        int middle = -1;
        while(min<=max){
            middle = (int)Math.floor((min+max)/2);
            if(locations[middle].getZip()<zip){
                min = middle+1;
            }
            else if(locations[middle].getZip()>zip){
                max = middle-1;
            }
            else{
                return locations[middle];
            }
            
        }
        return null;
    }
    
    /**
     * checks if record array is null or not
     * 
     * @param records array of records to compare
     * @return true if empty and false if not empty
     */
    public boolean isEmpty(Record[] records){
        if(records[0]==null){
            return true;
        }
        return false;
    }
    
    
    
    
    
    
    
        /*
     CallableStatement statement = connection.prepareCall("{ call customer_fnaem(?, ?) }");
    proc.setString(1, "lisa");
    proc.setString(2, "test");
    cs.execute();

    

    public void runSQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db.com:3306/core","username","password");
            Statement stmt = con.createStatement();
            String sql = "SELECT *";
            stmt.execute(sql);


            con.close();
        } catch(Exception e){ System.out.println("Error: "+e);}
    }*/

    
}




 