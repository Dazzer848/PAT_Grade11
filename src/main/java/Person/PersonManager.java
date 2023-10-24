package Person;


import DBMS.DB;
import OldManagers.OLDOperationManeger;
import OldManagers.OLDoperations;
import OldManagers.OLDperson;
import Person.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author dazzl
 */
public class PersonManager {
    private Person currentUser = null;
    
     public static Person [] getUsers(){
         
         // Creates an Array of people
         Person[] arr = null;
         int count = 0;
         try {
             
             // Connects to the Database
             DB.connect();
             
             //Returns the number of Users
            ResultSet q1 = DB.query("SELECT COUNT(*) FROM darrenlDB.users;");
            q1.next();
            
            // Stores the Number of users
            int numPeeps = q1.getInt(1);
            
            // Creates an Array with a max size of the amount of people in the Database
            arr = new Person[numPeeps];
            
            //Selects all the Users from the table
            ResultSet table = DB.query("SELECT * FROM darrenlDB.users;");
            
            //Loops through the table and populates all the required fields of the People
            while (table.next()) {
                
                //Retrieves the Fields for the PersonObject
                int ID = table.getInt(1);
                String username = table.getString(2);
                String password = table.getString(3);
                int totalEarned = table.getInt(4);
                
                
                //Creates the New Person
                Person p = new Person(ID, username, password, totalEarned);
                
                //Adds the person to the array and increases the count
                arr[count] = p;
                count++;

            }
        } catch (ClassNotFoundException ex) {
            
             System.out.println("The class could not be found, this is a fatal error and needs to be fixed by a techy");
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            
             System.out.println("There was an error with SQL or a query please contact a techy");
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return arr;

    }
     
     public static Person [] getUsersFromOperation(int operationID){
         
         //Creates a null Person array
         Person[] arr = null;
         int count = 0;
         try {
             
             //Connects to the Database
             DB.connect();
            
            // Gets the number of Users who have registered for that operation
            ResultSet q1 = DB.query("SELECT COUNT(*) FROM userOperations WHERE OperationID = " + operationID + ";");
            q1.next();
            int numPeeps = q1.getInt(1);
            
            
            //Creates a person array with the size to the exact amount of people
            arr = new Person[numPeeps];
            
            
            //Requests the ID of the users registered to the Database
            ResultSet table = DB.query("SELECT * FROM darrenlDB.users WHERE users.ID IN (SELECT userID FROM userOperations WHERE OperationID = "+ operationID +");");

            
            //Loops through the result table and populates the fields of the Person Object
            while (table.next()) {
                int ID = table.getInt(1);
                String username = table.getString(2);
                String password = table.getString(3);
                int totalEarned = table.getInt(4);

                //Creates the Person object
                Person p = new Person(ID, username, password, totalEarned);
                
                //Adds the person to the Array and then increases count
                arr[count] = p;
                count++;

            }
        } catch (ClassNotFoundException ex) {
            
             System.out.println("The class could not be found");
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             System.out.println("An error occured during the SQL process, this is fatal and needs to be fixed");
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return arr;

    }

     
     public static String toStringOfUsersinOpertions(int ID){
         
         //Creates the String of Users
         String users = "";
         
         //Uses the method getUsersFromOperation that returns the Person Array of the people who were in the Operation based off of the ID
         Person[] arr = PersonManager.getUsersFromOperation(ID);
         for(int i = 0; i < arr.length; i++){
             //Adds the username to the string
             users += arr[i].getUsername() + "\n";
         }
         return users;
     }
     
     
     public static void createNewAccount(String username, String password){
        try {
           //Creates a new account
            DB.update("INSERT INTO `darrenlDB`.`users` (`username`, `password`) VALUES (\"" + username + "\",\"" + password + "\");");
            
            
        } catch (SQLException ex) {
            System.out.println("An error occured during the SQL statement please contact support");
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public static boolean canCreateAccount(JTextField username, JTextField password, JTextField confirmPassword){
         
         //Creates the error checking booleans that ensure the User can create the account
         boolean canLogIn = false;
         boolean usernameGood = true;
         boolean passwordGood = false;
         
         //Username checking
         Person[] arr = getUsers();
         for(int i = 0; i < arr.length; i++){
             if(arr[i].getUsername().equals(username.getText())){
                 String usernameEntered = username.getText();
                 String usernameinDB = arr[i].getUsername();
                 usernameGood = false;
                 break;
             }
         }
         
        //Password checking
         if(password.getText().equals(confirmPassword.getText())){
             passwordGood = true;
         }
         
         //The combined check.
         if(passwordGood == true && usernameGood == true){
             canLogIn = true;
         }
         
         //returns if the User can log in or not
         return canLogIn;
     }
     
     public static boolean canLogIn(JTextField username, JTextField password){
         boolean canLogIn = false;
         
         //Gets the users from the Array
         Person[] arr = getUsers();
         
         //Loops throught the array to check if the User can log in
         for(int i = 0; i < arr.length; i++){
             
             //Gets the users at the Array
             String usernames = arr[i].getUsername();
             String passworda = arr[i].getPassword();
             
             //Checks to see if the entered username then the Username in the Array is correct
             if(arr[i].getUsername().equals(username.getText())){
                 
                 //Checks to see if the entered password then the password in the Array is correct
                 if(arr[i].getPassword().equals(password.getText())){
                     
                     //If both these checks pass it returns the fac that the user Can Log In
                     canLogIn = true;
                     return canLogIn;
                 }
             }
         }
         return canLogIn;
     }
     
     public static void payPeople(int OperationID, int paymentPerOperator, int paymentForMarauder){
        try {
            
            //Creates the Person array of users in the Operation
            Person[] arr = getUsersFromOperation(OperationID);
            
            
            
            ResultSet results = DB.query("SELECT totalEarned FROM darrenlDB.users WHERE ID = 1;");
            
            results.next();
            
            int currentMarauderBalance = results.getInt(1);
            
            //Gets the amount that needs to be paid for Marauders.
            int totalMarauder = currentMarauderBalance + paymentForMarauder;
            
            
            //Updates the Marauder Squadron total made
            System.out.println("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + totalMarauder + " WHERE (`ID` = '1') and (`username` = 'MarauderSquadron');");
            DB.update("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + totalMarauder + " WHERE (`ID` = '1') and (`username` = 'MarauderSquadron');");
            
            
            //Loops through the array of people who were there and then adds the correct details
            for(int i = 0; i < arr.length; i++){
                
                //Gets the ID of the current user
                int IDofUser = arr[i].getID();
                int totalEarned = arr[i].getTotalMade() + paymentPerOperator;
                try {
                    //Updates the amount that the user is earning
                    System.out.println("\"UPDATE `darrenlDB`.`users` SET `totalEarned` = \" + totalEarned + \" WHERE (`ID` = '\" + IDofUser + \"');");
                    DB.update("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + totalEarned + " WHERE (`ID` = '" + IDofUser + "');");
                    
                } catch (SQLException ex) {
                    System.out.println("An error occured during the SQL statement please contact support");
                    Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (SQLException ex) {
            
            System.out.println("An error occured during the SQL statement please contact the techy");
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     
     public static boolean payMarauderSquad(int Amount){
         //Error checking boolean
         boolean successfulPayemnt = false;
         
         //Gets the users
         Person[] arr = getUsers();
         
         //Gets the MarauderSquadron user account
         Person MarauderSquad = arr[0];
         
         //Accounting section
         int amountAfterTax = (int) (Amount - (Amount * 0.005));
         int totalMarauder = MarauderSquad.getTotalMade() + amountAfterTax;
        try {
            
            //Updates the total amount for the Marauder Squadron thing
            DB.update("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + totalMarauder + " WHERE (`ID` = '1') and (`username` = 'MarauderSquadron');");
            
            
            //Returns the error checking boolean
            successfulPayemnt = true;
            return successfulPayemnt;
            
        } catch (SQLException ex) {
            return successfulPayemnt;
        }
         
     }
     
     public static void adminPopulation(String usernameToLookFor, JTextField usernameDisplay, JTextField operationIn, JTextField totalEarned){
         
         //Gets the total Array of users
         Person[] arr = getUsers();
         
         //Checks the Array and populates the admin table
         for(int i = 0; i < arr.length; i++){
             
             //This checks to see which username the and returns its fields. 
             if(arr[i].getUsername().equals(usernameToLookFor)){
                 
                 //Populates the UI screens.
                 usernameDisplay.setText(arr[i].getUsername());
                 operationIn.setText("STILL NEEDS TO BE WORKED ON!");
                 totalEarned.setText(arr[i].getTotalMade() + "");
                 break;
             }
             else{
                 
                 //Returns the error checkiin values.
                 usernameDisplay.setText("USER NOT FOUND");
                 operationIn.setText("USER NOT FOUND");
                 totalEarned.setText("USER NOT FOUND");
             }
         }
         
     }
     
     public static void adminUpdateUser(JTextField usernameDisplay, JTextField opertionsIN, JTextField totalEarned, String userToLookFor){
         //Gets the Array of Users
         Person[] arr = getUsers();
         Person p = null;
         
         for(int i = 0; i < arr.length; i++){
             if(arr[i].getUsername().equals(userToLookFor)){
                  p = arr[i];
             }
         }
         
         //Loops throught he Array to check if the admin has updated one of the values
             
             //Checking if the username in the TextBox is not the same as the one in the Database, if so then update the Database
            if(!usernameDisplay.getText().equals(p.getUsername())){
                String newUsername = usernameDisplay.getText();
                try {
                    
                    //Updates the Database
                    DB.update("UPDATE darrenlDB.users SET username = \"" + newUsername + "\"WHERE (username = \"" + p.getUsername() + "\");");
                } catch (SQLException ex) {
                    System.out.println("Please update a value before pressing the Update button");
                }
             
            }
            
            //Checking to see if the total earned that is in the Text box is the same as the Database, if not update the User in the Database
            else if(!totalEarned.getText().equals(p.getTotalMade())){
                int newTotalEarned = Integer.parseInt(totalEarned.getText());
                try {
                    
                    //Updates the database
                    DB.query("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + newTotalEarned + "WHERE (`totalEarned` = " + p.getTotalMade() + ");");
                } catch (SQLException ex) {
                    System.out.println("Please update a value before pressing the Update button");
                    
                }
            }
            //CODE IN THE OPERATIONS ONE!
         
         
     }
     
     
     public void setCurrentUser(String username){
         
         //Gets the array of people in the database
         Person[] arr = getUsers();
         
         //Loops through the Database
         for(int i = 0; i < arr.length; i++){
             
             //Checks to see if the username is the same as the one logged in
             String usernmae = arr[i].getUsername();
             
             //Sets the current user
             if(usernmae.equals(username)){
                 currentUser = arr[i];
             }
         }
     }
     
     public Person getCurrentUser() throws ClassNotFoundException, SQLException{
         
         //Returns the current user object
         return currentUser;
     }
     
     
     public void deleteUser(String usernameToDelete){
      
         Person[] arr = getUsers();
         Person p = null;
         
         boolean deleted = false;
         int largestID = 0;
         
         for(int i = 0; i < arr.length; i++){
             if(arr[i].getUsername().equals(usernameToDelete)){
                 try {
                     DB.update("DELETE FROM `darrenlDB`.`users` WHERE (`username` = '" + arr[i].getUsername() + "');");
                     DB.update("DELETE FROM `darrenlDB`.`userOperations` WHERE (`userID` = '" + arr[i].getID() + "');");
                     deleted = true;
                     
                     
                 } catch (SQLException ex) {
                     Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             else if(deleted == false){
                        
                     int oldID = arr[i].getID();
                     int IDtoSet = arr[i+1].getID();
                     IDtoSet = IDtoSet - 1;
                     
                     largestID = IDtoSet;
                 try {
                     DB.update("UPDATE `darrenlDB`.`users` SET `ID` = '" + IDtoSet + "' WHERE (`ID` = '" + oldID + "');");
                 } catch (SQLException ex) {
                     Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             else{
                                         
                     int oldID = arr[i].getID();
                     int IDtoSet = oldID - 1;
                     largestID = IDtoSet;
                 try {
                     DB.update("UPDATE `darrenlDB`.`users` SET `ID` = '" + IDtoSet + "' WHERE (`ID` = '" + oldID + "');");
                 } catch (SQLException ex) {
                     Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
         
        try {
            int newIncrement = largestID + 1;
            DB.update("ALTER TABLE `darrenlDB`.`users` AUTO_INCREMENT = " + newIncrement + " ;");
        } catch (SQLException ex) {
            System.out.println("An error occured during the deletion process. Please contact support");
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
