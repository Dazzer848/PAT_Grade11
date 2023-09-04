/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import DBMS.DB;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dazzl
 */
public class Person {
    private String username;
    private int numOfOperationsIn;
    private double totalMoneyEarned;
    private String password;

    public Person(String inUsername, int numOfOperationsIN, double inTotalMoney, String inPassword) throws ClassNotFoundException, SQLException{
        this.username = inUsername;
        this.numOfOperationsIn = numOfOperationsIN;
        this.totalMoneyEarned = inTotalMoney;
        this.password = inPassword;
        }
    
    public String getUsername(){
        return this.username;
    }
        
        //DEFINE THESE CLASSES. ALL YOU NEED TO DO IS SET A WHILE LOOP AND IT CAN POPULATE THE FIELDS OF TH OBJECCT
    }

