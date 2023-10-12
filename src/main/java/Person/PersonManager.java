/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import DBMS.DB;
import Operation.Operations;
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

    private int size = 0;
    private int sizeOfOperationsArray = 0;
    private Person[] peopleArray = new Person[100];
    private Operations[] OperationsIN = new Operations[100];
    //private OperationManeger maneger = new OperationManeger();
    public PersonManager() throws ClassNotFoundException, SQLException {
        
        //
        //peopleArray = new Person[100];
        //Operations[] OperationsIN = new Operations[100];
        
        DB.connect();
        ResultSet table = DB.query("SELECT * FROM darrenlDB.users;");

        try {
            while (table.next()) {
                String username = table.getString(1);
                String password = table.getString(2);
                String operationString = table.getString(3);
                int totalEarned = table.getInt(4);
                int ID = table.getInt(5);
                
                OperationManeger maneger = new OperationManeger();
                Scanner lineSC = new Scanner(operationString).useDelimiter(",");
                while(lineSC.hasNext()){
                    int IDofOperation = (int)(lineSC.nextInt());
                    Operations o = maneger.searchForOperationViaIDD(IDofOperation);
                    OperationsIN[sizeOfOperationsArray] = o;
                    sizeOfOperationsArray++;
                    
                Person p = new Person(username, OperationsIN, totalEarned, password, ID);
                peopleArray[size] = p;
                size++;

                    
                }
                
                //How to get the object arrays!
                //Firstly populate the objects array, then loop through the names of the operations the user was in and then go from there!
                // what we can do it extract the ID's of the operations and use a scanner on that string and then


            }
            System.out.println("Mother!");
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
                
                //Update the DBMS with this user's colum
            }
        }
        System.out.println("Couldnt find that Person");
    }
     
     public Person searchForPersonViaUsername(String username){
         for(int i = 0; i < size; i++){
             if(username.equals(peopleArray[i])){
                 return peopleArray[i];
             }
         }
         return null;
     }
     
         public void updatePassword(String usernameToUpdate, String newPassword){
        for(int i = 0; i <= size; i++){
            if(peopleArray[i].getUsername().equals(usernameToUpdate)){
                peopleArray[i].setPassword(newPassword);
                
                //Find this user in the database and update the DBMS
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
         
         public void createNewPerson(String username, String password) throws SQLException{
             
             // How do I insert the Strings I get from this method into the method!
             //DB.query("INSERT INTO `darrenlDB`.`users` (`username`, `password`, `ID`) VALUES ("usernmae", "password", "2");");
             
         }
         public boolean canLogIng(String username, String password){
             boolean canLogIn = false;
             
             
             for(int i = 0; i < size; i++){
                 
             
             if(username.equals(peopleArray[size].getUsername())){
                 if(password.equals(peopleArray[size].getPassword())){
                     canLogIn = true;
                     return canLogIn;
                 }
             }
         }
             return canLogIn;
         }
         public boolean checkPasswordMatches(String password, String confirmPassword){
             boolean isTheSame = false;
             
             if(password.equals(confirmPassword)){
                 isTheSame = true;
                 return isTheSame;
             }
             return isTheSame;
         }
}
