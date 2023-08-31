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

    public Person() throws ClassNotFoundException, SQLException{
        DB.connect();
        ResultSet table = DB.query("SELECT * FROM darrenlDB.users;");
        table.next();
        
        System.out.println(table.getString(4));
    }
}
