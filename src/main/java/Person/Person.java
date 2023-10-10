/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import DBMS.DB;
import Operation.Operation;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dazzl
 */
public class Person {
    private String username;
    private Operation[] OperationsIn;
    private int ID;
    private int totalMoneyEarned;
    private String password;
    private int sizeOfOperationsIN = 0;

    public Person(String inUsername, Operation[] OperationsIN, int inTotalMoney, String inPassword, int inID) throws ClassNotFoundException, SQLException{
        this.username = inUsername;
        this.ID = inID;
        //Want to make an Operations array
        this.OperationsIn = new Operation[100];
        this.totalMoneyEarned = inTotalMoney;
        this.password = inPassword;
        }
    
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void updateOperationsIn(Operation newOperationToAdd) {
        // Ensure you add this array to a DBMS side which will add in the operations ID to the DBMS
        OperationsIn[sizeOfOperationsIN] = newOperationToAdd;
        
        //Ensure you send this new list to the DBMS
        
    }

    public void setTotalMoneyEarned(int totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Operation[] getOperationsIn() {
        return OperationsIn;
    }

    public double getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public String getPassword() {
        return password;
    }
   public void updateTotralMoneyEarned(double inputAmount) throws ClassNotFoundException, SQLException{
       PersonManager p = new PersonManager();
       totalMoneyEarned += inputAmount;
       p.updateTotalMoneyEarned(username, totalMoneyEarned);
   }
   
   public int getID(){
       return ID;
   }
    }

