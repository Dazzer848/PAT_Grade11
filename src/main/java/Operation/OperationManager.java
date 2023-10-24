/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operation;

import DBMS.DB;
import Person.AppManager;
import Person.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import Person.PersonManager;
import UI.Operation;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author dazzl
 */
public class OperationManager {

    private int size;
    private Operations[] operationsArray;

    public OperationManager() throws ClassNotFoundException, SQLException {
        
        // Connecting to the Database and making a new Operations array
        DB.connect();
        operationsArray = new Operations[100];;

        
        //Retrieving all the Operations stored in the DB
        ResultSet table = DB.query("SELECT * FROM darrenlDB.operations");

        while (table.next()) {

            //Getting the associated fields from the results table
            int ID = table.getInt(1);
            String name = table.getString(2);
            String briefing = table.getString(3);
            String equipmentNeeded = table.getString(4);
            String rendevouz = table.getString(5);
            int grossIncome = table.getInt(6);
            String comments = table.getString(7);

            
            //Adding the operation to the Array
            Operations operation = new Operations(ID, name, briefing, equipmentNeeded, rendevouz, grossIncome, comments);
            operationsArray[size] = operation;
            size++;

        }
    }

    public void populateOperationsUI(JTextArea briefing, JTextArea users, JTextArea equipmentNeeded, JLabel header, int ID) {

        //Gets the users in a String format of who was there. ( See PersonManager )
        String usersWhoWereThere = PersonManager.toStringOfUsersinOpertions(ID);
        
        //Creates a for loop
        for (int i = 0; i < size; i++) {
            
            //This is the check that returns the correct Operation opbject based off of an ID.
            if (operationsArray[i].getID() == ID) {
                
                //Gets the required operation from the Database
                Operations o = operationsArray[i];
                
                //Populates the UI using the correct fields
                
                briefing.setText(o.getBriefing());
                users.setText(usersWhoWereThere);
                equipmentNeeded.setText(o.getEquipmentNeeded());
                header.setText("Operation " + o.getName());
                break;
            }
        }
        System.out.println("Operation not found please contact support");
    }
    
   
    
    public void populateOperatiosSummary(JTextArea usersWhoPartook, JTextField perOperator, JTextField marauderSquadCutarea, JTextField TAFpaid, JTextArea comments, int ID) {
        
         int tafPaid = 0;
         int profitAfterTax =0;
         int marauderSquadcut = 0;
         int paymentPerOperator = 0;
                
        
        
        //Gets the string of users who were there using the PersonManager class
        String usersWhoWereThere = PersonManager.toStringOfUsersinOpertions(ID);
        
        // Creates the Array of users who partook in the operation based off of its ID
        Person[] arr = PersonManager.getUsersFromOperation(ID);
        
        //Gets the number of people
        int numUsers = arr.length;
        
        
        //Creates a for loop which loops through the array.
        for (int i = 0; i < size; i++) {
            
            //Checks for the correct operation based off its ID
            if (operationsArray[i].getID() == ID) {
                
                //Creates the operation Object of the operation we are currently working on
                Operations o = operationsArray[i];
                usersWhoPartook.setText(usersWhoWereThere);
                
                //Error checks to see what things are doing there thing
                
                if(numUsers == 0){
                    comments.setText("THERE ARE NO USERS REGISTERD FOR THIS OPERATION. THEREFOR NO MONEY CAN BE PAID!");
                    usersWhoPartook.setText("THERE ARE NO USERS REGISTERD FOR THIS OPERATION. THEREFOR NO MONEY CAN BE PAID!");
                    numUsers = 1;
                }
                    
                
                //Accounting section
                else{
                tafPaid = (int) (o.getGrossIncome() * 0.005);
                profitAfterTax = (int) (o.getGrossIncome() - (o.getGrossIncome() * 0.005));
                marauderSquadcut = (int) (profitAfterTax * 0.15);
                paymentPerOperator = (int) (profitAfterTax - marauderSquadcut) / numUsers;
                
                }
                
                
                
                
                // Sets the UI to the correct values
                perOperator.setText(paymentPerOperator + "");
                marauderSquadCutarea.setText(marauderSquadcut + "");
                TAFpaid.setText(tafPaid + "");
                break;
            }

        }
    }

    public boolean createOperation(JTextField name, JTextArea Briefing, JTextArea equipmentNeeded, JTextField rendevous) {
        
        // Error checking boolean
        boolean createdOperationSuccesfully = false;
        
        
        // Gets the name and required fields for the Operation object
        String inName = name.getText();
        String inBriefing = Briefing.getText();
        String inEquipmentNeeded = equipmentNeeded.getText();
        String inRendezvous = rendevous.getText();

            try {
                
                // Does the required SQL query and adds the Operation to the Database
                DB.update("INSERT INTO darrenlDB.operations(Name,Briefing,EquipmentNeeded,rendezvous)VALUES(\"" + inName + "\",\"" + inBriefing + "\",\"" + inEquipmentNeeded + "\",\"" + inRendezvous + "\");");
                
            } catch (SQLException ex) {
                System.out.println("One of your fields is wrong, please enter the correct Data.");
                equipmentNeeded.setText("You have enetered incorrect Data, please delete this and re write it");
                name.setText("You have enetered incorrect Data, please delete this and re write it");
                Briefing.setText("You have enetered incorrect Data, please delete this and re write it");
                rendevous.setText("You have enetered incorrect Data, please delete this and re write it");
                
                
                
                
            }
            
            createdOperationSuccesfully = true;
        
        return createdOperationSuccesfully;
    }

    public void registerForOp(int operationID, JButton errorDisplay) {
        
        
        try {
            
            //Gets the user that is currently logged in
            Person currentUser = AppManager.pm.getCurrentUser();

            // returns the ID of the curreny user
            int ID = currentUser.getID();

            try {
                
                //SQL query adds the currentUser ID to the table to indicate they have registerd for the operation
                DB.update("INSERT INTO `darrenlDB`.`userOperations` (`userID`, `operationID`) VALUES (\"" + ID + "\",\"" + operationID + "\");");
            } catch (SQLException ex) {
                
                // The error display which is sent if SQL returns that there is already a value for that User ( indicating they are registerd )
                System.out.println("This user is already registered!");
                errorDisplay.setText("You are already registered for this operation please return Home!");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("An error occured in the App Manager class, this is fatal and needs a fix from a support techy");
        } catch (SQLException ex) {
            System.out.println("");
        }
    }

    public void populateOperationsButtons(JButton one, JButton two, JButton three, JButton four, JButton five, JButton six) {
        try {
            
            // Returns an int for the amount of operations in the Databse
            ResultSet resultSet = DB.query("SELECT COUNT(*) FROM darrenlDB.operations;");
            resultSet.next();
            
            // This if chain checks how many operations there are and therefor how many buttons to make visible
            int numberOfOps = resultSet.getInt(1);

            if (numberOfOps == 1) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());
                
                two.setVisible(false);
                three.setVisible(false);
                four.setVisible(false);
                five.setVisible(false);
                six.setVisible(false);
            }

            if (numberOfOps == 2) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());
                
                three.setVisible(false);
                four.setVisible(false);
                five.setVisible(false);
                six.setVisible(false);
            }
            if (numberOfOps == 3) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());

                three.setVisible(true);
                three.setText(searchForOperationUsingID(3).getName());
                
                four.setVisible(false);
                five.setVisible(false);
                six.setVisible(false);
            }

            if (numberOfOps == 4) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());

                three.setVisible(true);
                three.setText(searchForOperationUsingID(3).getName());
                
                four.setVisible(true);
                four.setText(searchForOperationUsingID(4).getName());
                
                five.setVisible(false);
                six.setVisible(false);
            }

            if (numberOfOps == 5) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());

                three.setVisible(true);
                three.setText(searchForOperationUsingID(3).getName());
                
                four.setVisible(true);
                four.setText(searchForOperationUsingID(4).getName());
                
                five.setVisible(true);
                five.setText(searchForOperationUsingID(5).getName());
                
                                
                                
                six.setVisible(false);
            }
            if (numberOfOps == 6) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());

                three.setVisible(true);
                three.setText(searchForOperationUsingID(3).getName());
                
                four.setVisible(true);
                four.setText(searchForOperationUsingID(4).getName());
                
                five.setVisible(true);
                five.setText(searchForOperationUsingID(5).getName());
                
                six.setVisible(true);
                six.setText(searchForOperationUsingID(6).getName());
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(OperationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    // Method that returns the required operation
    public Operations searchForOperationUsingID(int ID) {
        for (int i = 0; i < size; i++) {
            
            //Checks the operation array to retrieve the required operation
            if (operationsArray[i].getID() == ID) {
                Operations o = operationsArray[i];
                
                //Returns the Operation Object that is associated with that ID
                return o;
            }
        }
        return null;
    }
    
    //Searches based on the Name of an operation
    public Operations searchForOperationsUsingName(String nameOfOperation){
        Operations operationToFind = null;
        
        for(int i = 0; i < size; i++){
            
            //Finds the Operation based on the Name supplied
            if(operationsArray[i].getName().equals(nameOfOperation)){
                operationToFind = operationsArray[i];
                return operationToFind;
            }
        }
       return operationToFind;         
    }
    
}
