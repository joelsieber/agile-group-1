/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agilejava;
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author jodielaurenson
 */
public class AgileJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
                
    }
    
    /**
     * This method uses the scanner class and gets user input
     * 
     * @param records an array of record class that holds all data
     */
    public void getPriceInput(Record records[]){
        Filter filter = new Filter();
        Scanner scan = new Scanner(System.in);
        System.out.println("Minimum price range: ");
        float min = scan.nextInt();
        System.out.println("Maximum price range: ");
        float max = scan.nextInt();
        filter.restrictToPrice(records, min, max); //calls a method that filters the records
    }
    
    
    
    
}
