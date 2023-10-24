/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;

import DBMS.DB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dazzl
 */
public class AppManager {
    public static PersonManager pm;
    
    public static void init(){
        try {
            //Connects to the Database
            DB.connect();
        } catch (ClassNotFoundException ex) {
            
            System.out.println("The class could not be found. Pleasse contact a techy");
            Logger.getLogger(AppManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("An error occured during the SQL process, this is fatal and needs to be resolved");
            Logger.getLogger(AppManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(pm == null){
            //Creates a new AppManager
            pm = new PersonManager();
        }
    }
    
    
    //AppManager.pm.searchForPersonUsingID();
}
