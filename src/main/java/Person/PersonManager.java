/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import DBMS.DB;
import Operation.Operation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dazzl
 */
public class PersonManager {

    private Person[] peopleArray;
    private int size = 0;

    public PersonManager() throws ClassNotFoundException, SQLException {
        peopleArray = new Person[100];
        DB.connect();
        ResultSet table = DB.query("SELECT * FROM darrenlDB.users;");

        try {
            while (table.next()) {
                String username = table.getString(1);
                
                //NEED TO GET THE OPERATION ARRAY HERE
                int numOfOperationsIn = table.getInt(2);
                
                
                
                int totalMoneyEarned = table.getInt(3);
                String password = table.getString(4);

                Person p = new Person(username, numOfOperationsIn, totalMoneyEarned, password);
                peopleArray[size] = p;
                size++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String toString(String findUsername){
        String usernametooFind = "";
        for(int i = 0; i <= size; i++){
            if(findUsername.equals(peopleArray[i].getUsername())){
                usernametooFind = peopleArray[i].getUsername();
            }
            
        }
        return usernametooFind;
    }
    
    public void updateNumOperations(String usernameToUpdate, int newNumberOfOperationsw){
        for(int i = 0; i <= size; i++){
            if(peopleArray[i].getUsername().equals(usernameToUpdate)){
                peopleArray[i].setNumOfOperationsIn(newNumberOfOperationsw);
                break;
            }
        }
        System.out.println("Couldnt find that Person");
    }
    
     public void updateTotalMoneyEarned(String usernameToUpdate, int newTotalMoneyEarned){
        for(int i = 0; i <= size; i++){
            if(peopleArray[i].getUsername().equals(usernameToUpdate)){
                peopleArray[i].setTotalMoneyEarned(newTotalMoneyEarned);
                break;
            }
        }
        System.out.println("Couldnt find that Person");
    }
     
         public void updatePassword(String usernameToUpdate, String newPassword){
        for(int i = 0; i <= size; i++){
            if(peopleArray[i].getUsername().equals(usernameToUpdate)){
                peopleArray[i].setPassword(newPassword);
                break;
            }
        }
        System.out.println("Couldnt find that Person");
    }
         
         public int getSize(){
             return size;
         }
}
