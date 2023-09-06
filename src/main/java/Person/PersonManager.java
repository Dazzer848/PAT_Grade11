/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import DBMS.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dazzl
 */
public class PersonManager {

    private Person[] peopleArray;
    private int size = 0;

    public PersonManager() throws ClassNotFoundException, SQLException {
        peopleArray = new Person[100];
        DB.connect();
        ResultSet table = DB.query("SELECT * FROM darrenlDB.users;");

        try {
            while (table.next()) {
                String username = table.getString(1);
                int numOfOperationsIn = table.getInt(2);
                double totalMoneyEarned = table.getDouble(3);
                String password = table.getString(4);

                Person p = new Person(username, numOfOperationsIn, totalMoneyEarned, password);
                peopleArray[size] = p;
                size++;

            }
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
}
