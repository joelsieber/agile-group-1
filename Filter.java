/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agilejava;

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
}
