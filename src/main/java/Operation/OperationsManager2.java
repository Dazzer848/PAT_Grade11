/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operation;

import DBMS.DB;
import Person.Person2;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import Person.PersonManager2;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author dazzl
 */
public class OperationsManager2 {

    private int size;
    private Operations2[] operationsArray;

    public OperationsManager2() throws ClassNotFoundException, SQLException {
        DB.connect();
        operationsArray = new Operations2[100];;

        ResultSet table = DB.query("SELECT * FROM darrenlDB.operations");

        while (table.next()) {

            int ID = table.getInt(1);
            String name = table.getString(2);
            String briefing = table.getString(3);
            String equipmentNeeded = table.getString(4);
            String rendevouz = table.getString(5);
            int grossIncome = table.getInt(6);
            String comments = table.getString(7);

            Operations2 operation = new Operations2(ID, name, briefing, equipmentNeeded, rendevouz, grossIncome, comments);
            operationsArray[size] = operation;
            size++;

        }
    }

    public void populateOperationsUI(JTextArea briefing, JTextArea users, JTextArea equipmentNeeded, JLabel header, int ID) {

        String usersWhoWereThere = PersonManager2.toStringOfUsersinOpertions(ID);
        for (int i = 0; i < size; i++) {
            if (operationsArray[i].getID() == ID) {
                Operations2 o = operationsArray[i];
                briefing.setText(o.getBriefing());
                users.setText(usersWhoWereThere);
                equipmentNeeded.setText(o.getEquipmentNeeded());
                header.setText("Operation " + o.getName());
                break;
            }
        }
        System.out.println("Operation not found please contact support");
    }

    public void populateOperatiosSummary(JTextArea usersWhoPartook, JTextField perOperator, JTextField marauderSquadCutarea, JTextField TAFpaid, JTextArea comments, int ID) {
        String usersWhoWereThere = PersonManager2.toStringOfUsersinOpertions(ID);
        Person2[] arr = PersonManager2.getUsersFromOperation(ID);
        int numUsers = arr.length;

        for (int i = 0; i < size; i++) {
            if (operationsArray[i].getID() == ID) {
                Operations2 o = operationsArray[i];
                usersWhoPartook.setText(usersWhoWereThere);

                //Accounting section
                int tafPaid = (int) (o.getGrossIncome() * 0.005);
                int profitAfterTax = (int) (o.getGrossIncome() - (o.getGrossIncome() * 0.005));
                int marauderSquadcut = (int) (profitAfterTax * 0.15);
                int paymentPerOperator = (int) (profitAfterTax - marauderSquadcut) / numUsers;

                perOperator.setText(paymentPerOperator + "");
                marauderSquadCutarea.setText(marauderSquadcut + "");
                TAFpaid.setText(tafPaid + "");
                break;
            }

        }
        System.out.println("Could find operation");
    }

    public boolean createOperation(int ID, JTextField name, JTextArea Briefing, JTextArea equipmentNeeded, JTextField rendevous) {
        boolean createdOperationSuccesfully = false;

        String inName = name.getText();
        String inBriefing = Briefing.getText();
        String inEquipmentNeeded = equipmentNeeded.getText();
        String inRendezvous = rendevous.getText();

        try {
            DB.query("INSERT INTO 'darrenlDB'.'operations'('ID','Name','Briefing','Equipment Needed','rendezvous')VALUES(" + ID + "," + inName + "," + inBriefing + "," + inEquipmentNeeded + "," + inRendezvous + ");");
            createdOperationSuccesfully = true;

        } catch (SQLException ex) {
            return createdOperationSuccesfully;
        }
        return createdOperationSuccesfully;
    }

    public void registerForOp(int operationID) {
        try {
            PersonManager2 manager = new PersonManager2();
            Person2 currentUser = manager.getCurrentUser();
            
            int ID = currentUser.getID();
            
            try {
                DB.query("INSERT INTO `darrenlDB`.`userOperations` (`userID`, `OperationID`) VALUES (" + ID + "," + operationID + ");");
                
            } catch (SQLException ex) {
                Logger.getLogger(OperationManeger.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperationsManager2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OperationsManager2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
