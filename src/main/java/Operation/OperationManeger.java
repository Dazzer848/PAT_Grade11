/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operation;

import java.time.LocalDateTime;
import Person.Person;
import java.sql.Array;
import java.sql.SQLException;
import java.util.HashSet;
/**
 *
 * @author dazzl
 */
public class OperationManeger {
    int size;
    Operation[] operationsArray;

    public OperationManeger() {
        operationsArray = new Operation[100];
        
        //Connect!
        //Load all the things into the class
        
    }
    
    
    
    public Operation searchForOperation(String operationNameToLookFor){
        
        //Loops trough the array of operations to see if it can find the Name field of an operation that equals the one they want to find
        for(int i = 0; i < size; i++){
            if(operationsArray[i].getName().equalsIgnoreCase(operationNameToLookFor)){
                return operationsArray[i];
            }
           
        }
        // Returns a null value which will be checked for in the method this means there is no operation that has been found
        return null;
    }
    
    // This creates an entirely new Operation
    // Note how some field are missing this is due to the fact that we dont need to populate them as of yet
    //Ask Mr B for help with this fact
    public Operation createNewOperation(String inName, LocalDateTime inDate, Person[] operatorsIn, String inBriefing){
        Operation o = new Operation(inName,inDate,operatorsIn,-1,-1,-1,"",inBriefing);
        
        return o;
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
    
       public void updateOperators(Person[] newOperatorList, String OperationToUpdate){
        for(int i = 0; i <= size; i++){
           if(operationsArray[i].getName().equals(OperationToUpdate)){
               operationsArray[i].setOperators(newOperatorList);
               break;
           }
        }
        System.out.println("The operation couldnt be found");
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
                       Person[] operatiors = operationsArray[i].getOperators();
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
