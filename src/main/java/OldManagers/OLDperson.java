/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OldManagers;

import DBMS.DB;
import OldManagers.OLDoperations;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dazzl
 */
public class OLDperson {
    private String username;
    private OLDoperations[] OperationsIn;
    private int ID;
    private int totalMoneyEarned;
    private String password;
    private int sizeOfOperationsIN = 0;

    public OLDperson(String inUsername, OLDoperations[] OperationsIN, int inTotalMoney, String inPassword, int inID) throws ClassNotFoundException, SQLException{
        this.username = inUsername;
        this.ID = inID;
        //Want to make an OLDoperations array
        this.OperationsIn = new OLDoperations[100];
        this.totalMoneyEarned = inTotalMoney;
        this.password = inPassword;
        }
    
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void updateOperationsIn(OLDoperations newOperationToAdd) {
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

    public OLDoperations[] getOperationsIn() {
        return OperationsIn;
    }

    public double getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public String getPassword() {
        return password;
    }
   public void updateTotralMoneyEarned(double inputAmount) throws ClassNotFoundException, SQLException{
       OLDpersonManager p = new OLDpersonManager();
       totalMoneyEarned += inputAmount;
       p.updateTotalMoneyEarned(username, totalMoneyEarned);
   }
   
   public int getID(){
       return ID;
   }
    }

