/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OldManagers;

import OldManagers.OLDoperations;
import DBMS.DB;
import java.time.LocalDateTime;
import Person.Person;
import Person.PersonManager;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dazzl
 */
public class OLDOperationManeger {
    int size;
    OLDoperations[] operationsArray;
    int sizeOfPeopleArray = 0;
    //PersonManager manaeger = new OLDpersonManager();

    public OLDOperationManeger() throws ClassNotFoundException, SQLException {
        operationsArray = new OLDoperations[100];
        OLDperson[] usersWhoPatook = new OLDperson[100];
        
        DB.connect();
        ResultSet table = DB.query("SELECT * FROM darrenlDB.operations");
        while(table.next()){
            //Getting the ID
            int ID = table.getInt(1);
            
            //Getting the name of the operation
            String nameOfOperation = table.getString(2);    
            
            
            //Getting the briefing of the operations
            String briefing = table.getString(3);
            
            //Getting the equipment needed for the operation
            String equipment = table.getString(4);
            
            //Getting the place where to meet for the operation
            String rendevous = table.getString(5);
            
            //Getting the unique Identifier fot the Operation
            
            //Getting the String of users whic hwill then be scanned trough
            String listOfUsers = table.getString(6);
            
            Scanner lineSC = new Scanner(listOfUsers).useDelimiter(",");
            OLDpersonManager manaeger = new OLDpersonManager();
            
            while(lineSC.hasNext()){
                int IDofUser = lineSC.nextInt();
                OLDperson userWhoPartook = manaeger.searchForPersonUsingID(IDofUser);
                usersWhoPatook[sizeOfPeopleArray] = userWhoPartook;
                sizeOfPeopleArray++;
            }
            
            
            int totalMade = table.getInt(7);
            int paymentPerOperator = table.getInt(8);
            int TAFpaid = table.getInt(9);
            String comments = table.getString(10);
            int numberOf = table.getInt(11);
            
            //NEED TO ERROR CHECK THIS! 
            //Also fix up the ordering of things in the database it looks terrible!
            //Lastly ensure to add a comments column!
            OLDoperations o = new OLDoperations(nameOfOperation, usersWhoPatook, totalMade, TAFpaid, paymentPerOperator, comments, briefing, ID, equipment, numberOf);
            operationsArray[size] = o;
            size++;
                        
        }
        
    }
    
    
    
    public OLDoperations searchForOperation(String operationNameToLookFor){
       
        //Loops trough the array of operations to see if it can find the Name field of an operation that equals the one they want to find
        for(int i = 0; i < size; i++){
            if(operationsArray[i].getName().equalsIgnoreCase(operationNameToLookFor)){
                return operationsArray[i];
            }
           
        }
        // Returns a null value which will be checked for in the method this means there is no operation that has been found
        return null;
    }
    
    public OLDoperations searchForOperationViaIDD(int ID){
                //Loops trough the array of operations to see if it can find the Name field of an operation that equals the one they want to find
        for(int i = 0; i < size; i++){
            if(operationsArray[i].getID() == ID){
                return operationsArray[i];
            }
           
        }
        return null;
    }
    
    // This creates an entirely new OLDoperations
    // Note how some field are missing this is due to the fact that we dont need to populate them as of yet
    //Ask Mr B for help with this fact
    public void createNewOperation(String inName, LocalDateTime inDate, OLDperson[] operatorsIn, String inBriefing){
        OLDoperations o = new OLDoperations(inName,operatorsIn,-1,-1,-1,"",inBriefing, 0, "wow", 4);
        
        operationsArray[size] = o;
        size++;
        
        // This operation must be snent to the DBMS! Ensure that you can send it through!
    }
    
    public void deleteOperation(){
        for(int i = 0; i <= size; i++){
            /// INSERT THE DELETION THINGS HERE
        }
    }
    
    public void updateOperationName(String inUpdatedName, String OperationToUpdate){
        for(int i = 0; i <= size; i++){
           if(operationsArray[i].getName().equals(OperationToUpdate)){
               operationsArray[i].setName(inUpdatedName);
               break;
           }
        }
        System.out.println("The operation couldnt be found");
    }
    
    public void updateDateOfOperation(LocalDateTime timeToUpdate, String OperationToUpdate){
        for(int i = 0; i <= size; i++){
           if(operationsArray[i].getName().equals(OperationToUpdate)){
               operationsArray[i].setDateOfOperation(timeToUpdate);
               break;
           }
        }
        System.out.println("The operation couldnt be found");
    }
    
       public void updateOperators(int IDofOperationToUpdate, OLDperson userToAdd){
          OLDoperations op = searchForOperationViaIDD(IDofOperationToUpdate);
          OLDperson[] listOfUser = op.getOperators();
          
          //Need to get the size
          //listOfUser[]          
    }
           public void updateTotalMoneyMade(int moneyToUpdate, String OperationToUpdate){
        for(int i = 0; i <= size; i++){
           if(operationsArray[i].getName().equals(OperationToUpdate)){
               operationsArray[i].setTotalMoneyMade(moneyToUpdate);
               break;
           }
        }
        System.out.println("The operation couldnt be found");
    }
           
           public void payOperater(String nameOfOperation) throws ClassNotFoundException, SQLException{
               double paymentPerOperator = 0.0;
               
               for(int i = 0; i <= size; i++){
                   if(operationsArray[i].getName().equals(nameOfOperation)){
                       int totalMoneyMade = operationsArray[i].getTotalMoneyMade();
                       OLDperson[] operatiors = operationsArray[i].getOperators();
                       //Get the size of the operatior
                       
                       //THIS IS DEBUGGING AND NEEDS TO BE CHANGED
                       int operators = 1;   
                       
                       paymentPerOperator = operationsArray[i].getTotalMoneyMade() / operators;
                       
                       for(int f = 0; f <= size - 1; f++){
                           operatiors[f].updateTotralMoneyEarned(paymentPerOperator);
                      }
                       
                   }
               
               
           }
        
    
    
   
}
}
