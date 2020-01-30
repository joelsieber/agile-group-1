/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This class creates a connection to the database
 * @author jodielaurenson
 */
public class Database {
    
    /**
     * sets up the connection to mysql and runs a simple select query
     * @return 
     */
    public Connection setUpConnection(){
        Connection con;
        try {  
            Class.forName("com.mysql.jdbc.Driver");
        
            
           con = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/19agileteam1db","19agileteam1","8760.at1.0678"); //connection details
           
           
           
           Statement stmt = con.createStatement();
           ResultSet result = stmt.executeQuery("SELECT * FROM provider WHERE providerState = 'FL'"); //the query being executed, selects all results in florida
           
           getResult(result);
           
           return con;
           
        } catch (ClassNotFoundException ex) {
            System.out.println("Error1");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            System.out.println("Error2");
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param result 
     */
    public void getResult(ResultSet result){
        try {
            while(result.next()){
                int providerID = result.getInt("providerID");
                System.out.println("ID: "+providerID);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
