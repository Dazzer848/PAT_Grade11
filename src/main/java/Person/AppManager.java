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
            DB.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AppManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(pm == null){
            pm = new PersonManager();
        }
    }
    
    
    //AppManager.pm.searchForPersonUsingID();
}
