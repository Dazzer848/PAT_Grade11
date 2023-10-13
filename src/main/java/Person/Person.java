/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import OldManagers.OLDoperations;
import java.sql.SQLException;

/**
 *
 * @author dazzl
 */
public class Person {
        private int ID;
        private String username;
        private String password;
        private int totalMade;
        public Person(int IDin, String usernameIn, String passwordIN, int totalMadeIN) throws ClassNotFoundException, SQLException{
            this.ID = IDin;
            this.username = usernameIn;
            this.password = passwordIN;
            this.totalMade = totalMadeIN;
        }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTotalMade() {
        return totalMade;
    }
    
}
