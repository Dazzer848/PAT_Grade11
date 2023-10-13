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
//    public PersonManager(){
//        try {
//            DB.connect();
//            ResultSet table = DB.query("SELECT * FROM darrenlDB.users;");
//
//            while (table.next()) {
//                int ID = table.getInt(1);
//                String username = table.getString(2);
//                String password = table.getString(3);
//                int totalEarned = table.getInt(4);
//
//                Person p = new Person(ID, username, password, totalEarned);
//                personArray[sizeOfPersonArray] = p;
//                sizeOfPersonArray++;
//
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    
     public static Person [] getUsers(){
         
         Person[] arr = null;
         int count = 0;
         try {
             DB.connect();
            ResultSet q1 = DB.query("SELECT COUNT(*) FROM darrenlDB.users;");
            q1.next();
            int numPeeps = q1.getInt(1);
            
            arr = new Person[numPeeps];
            
            
            ResultSet table = DB.query("SELECT * FROM darrenlDB.users;");

            while (table.next()) {
                int ID = table.getInt(1);
                String username = table.getString(2);
                String password = table.getString(3);
                int totalEarned = table.getInt(4);

                Person p = new Person(ID, username, password, totalEarned);
                arr[count] = p;
                count++;

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return arr;

    }
     
     public static Person [] getUsersFromOperation(int operation){
         
         Person[] arr = null;
         int count = 0;
         try {
             DB.connect();
            
            ResultSet q1 = DB.query("SELECT COUNT(*) FROM userOperations WHERE OperationID = " + operation + ";");
            q1.next();
            int numPeeps = q1.getInt(1);
            
            arr = new Person[numPeeps];
            
            
            ResultSet table = DB.query("SELECT * FROM darrenlDB.users WHERE users.ID IN (SELECT userID FROM userOperations WHERE OperationID = "+ operation+");");

            while (table.next()) {
                int ID = table.getInt(1);
                String username = table.getString(2);
                String password = table.getString(3);
                int totalEarned = table.getInt(4);

                Person p = new Person(ID, username, password, totalEarned);
                arr[count] = p;
                count++;

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return arr;

    }

     
     public static String toStringOfUsersinOpertions(int ID){
         String users = "";
         
         Person[] arr = PersonManager.getUsersFromOperation(ID);
         for(int i = 0; i < arr.length; i++){
             users += arr[i].getUsername() + "\n";
         }
         return users;
     }
     
     public static void createNewAccount(String username, String password){
        try {
            DB.update("INSERT INTO `darrenlDB`.`users` (`username`, `password`) VALUES (\"" + username + "\",\"" + password + "\");");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public static boolean canCreateAccount(JTextField username, JTextField password, JTextField confirmPassword){
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
         
         if(password.getText().equals(confirmPassword.getText())){
             passwordGood = true;
         }
         
         if(passwordGood == true && usernameGood == true){
             canLogIn = true;
         }
         return canLogIn;
     }
     
     public static boolean canLogIn(JTextField username, JTextField password){
         boolean canLogIn = false;
         
         Person[] arr = getUsers();
         for(int i = 0; i < arr.length; i++){
             String usernames = arr[i].getUsername();
             String passworda = arr[i].getPassword();
             if(arr[i].getUsername().equals(username.getText())){
                 if(arr[i].getPassword().equals(password.getText())){
                     canLogIn = true;
                     return canLogIn;
                 }
             }
         }
         return canLogIn;
     }
     
     public static void payPeople(int OperationID, int paymentPerOperator, int paymentForMarauder){
         Person[] arr = getUsersFromOperation(OperationID);
         int totalMarauder = arr[0].getTotalMade() + paymentForMarauder;
         
        try {
            DB.update("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + totalMarauder + " WHERE (`ID` = '1') and (`username` = 'MarauderSquadron');");
        } catch (SQLException ex) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
         for(int i = 1; i < arr.length; i++){
             int IDofUser = arr[i].getID();
             int totalEarned = arr[i].getTotalMade() + paymentPerOperator;
             try {
                 DB.update("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + totalEarned + " WHERE (`ID` = " + IDofUser + ");");
             } catch (SQLException ex) {
                 Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         }
         
     }
     
     public static boolean payMarauderSquad(int Amounr){
         
         boolean successfulPayemnt = false;
         
         Person[] arr = getUsers();
         
         Person MarauderSquad = arr[0];
         int amountAfterTax = (int) (Amounr - (Amounr * 0.005));
         int totalMarauder = MarauderSquad.getTotalMade() + amountAfterTax;
        try {
            DB.update("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + totalMarauder + " WHERE (`ID` = '1') and (`username` = 'MarauderSquadron');");
            successfulPayemnt = true;
            return successfulPayemnt;
        } catch (SQLException ex) {
            return successfulPayemnt;
        }
         
     }
     
     public static void adminPopulation(String usernameToLookFor, JTextField usernameDisplay, JTextField operationIn, JTextField totalEarned){
         Person[] arr = getUsers();
         for(int i = 0; i < arr.length; i++){
             if(arr[i].getUsername().equals(usernameToLookFor)){
                 usernameDisplay.setText(arr[i].getUsername());
                 operationIn.setText("STILL NEEDS TO BE WORKED ON!");
                 totalEarned.setText(arr[i].getTotalMade() + "");
             }
             else{
                 usernameDisplay.setText("USER NOT FOUND");
                 operationIn.setText("USER NOT FOUND");
                 totalEarned.setText("USER NOT FOUND");
             }
         }
         
     }
     
     public static void adminUpdateUser(JTextField usernameDisplay, JTextField opertionsIN, JTextField totalEarned){
         Person[] arr = getUsers();
         
         for(int i = 0; i < arr.length; i++){
            if(!usernameDisplay.getText().equals(arr[i].getUsername())){
                String newUsername = usernameDisplay.getText();
                try {
                    DB.query("UPDATE `darrenlDB`.`users` SET `username` = " + newUsername + "WHERE (`username` = " + arr[i].getUsername() + ");");
                } catch (SQLException ex) {
                    Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
                }
             
            }
            else if(!totalEarned.getText().equals(arr[i].getTotalMade())){
                int newTotalEarned = Integer.parseInt(totalEarned.getText());
                try {
                    DB.query("UPDATE `darrenlDB`.`users` SET `totalEarned` = " + newTotalEarned + "WHERE (`totalEarned` = " + arr[i].getTotalMade() + ");");
                } catch (SQLException ex) {
                    Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //CODE IN THE OPERATIONS ONE!
         
         
     }
     }
     
     public void setCurrentUser(String username){
         Person[] arr = getUsers();
         for(int i = 0; i < arr.length; i++){
             String usernmae = arr[i].getUsername();
             if(usernmae.equals(username)){
                 currentUser = arr[i];
             }
         }
     }
     
     public Person getCurrentUser() throws ClassNotFoundException, SQLException{
         
         return currentUser;
     }
    
}
