/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import DBMS.DB;
import Operation.Operation;
import Operation.OperationManeger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dazzl
 */
public class PersonManager {

    private Person[] peopleArray;
    private int size = 0;
    private int sizeOfOperationsArray = 0;

    public PersonManager() throws ClassNotFoundException, SQLException {
        peopleArray = new Person[100];
        Operation[] OperationsIN = new Operation[100];
        
        DB.connect();
        ResultSet table = DB.query("SELECT * FROM darrenlDB.users;");

        try {
            while (table.next()) {
                String username = table.getString(1);
                
                
                OperationManeger maneger = new OperationManeger();
                
                //NEED TO GET THE OPERATION ARRAY HERE
                String operations = table.getString(3);
                Scanner lineSC = new Scanner(operations).useDelimiter(",");
                while(lineSC.hasNext()){
                    int IDofOperation = (int)(lineSC.nextInt());
                    Operation o = maneger.searchForOperationViaIDD(IDofOperation);
                    OperationsIN[sizeOfOperationsArray] = o;
                    sizeOfOperationsArray++;
                    
                    
                    
                }
                
                //How to get the object arrays!
                //Firstly populate the objects array, then loop through the names of the operations the user was in and then go from there!
                // what we can do it extract the ID's of the operations and use a scanner on that string and then
                
                
                // ENSURE YOU CHANGE THE COLLUM HEADER!
                int ID = table.getInt(3);
                int totalMoneyEarned = table.getInt(3);
                String password = table.getString(4);

                Person p = new Person(username, OperationsIN, totalMoneyEarned, password, ID);
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
         
         
         public Person searchForPersonUsingID(int id) {
             for(int i = 0; i < size; i++){
                 if(peopleArray[i].getID() == id){
                     return peopleArray[i];
                 }
             }
             System.out.println("Could find this person");
             return null;
         }
}
