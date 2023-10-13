/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operation;

import DBMS.DB;
import Person.AppManager;
import Person.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import Person.PersonManager;
import UI.Operation;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author dazzl
 */
public class OperationManager {

    private int size;
    private Operations[] operationsArray;

    public OperationManager() throws ClassNotFoundException, SQLException {
        DB.connect();
        operationsArray = new Operations[100];;

        ResultSet table = DB.query("SELECT * FROM darrenlDB.operations");

        while (table.next()) {

            int ID = table.getInt(1);
            String name = table.getString(2);
            String briefing = table.getString(3);
            String equipmentNeeded = table.getString(4);
            String rendevouz = table.getString(5);
            int grossIncome = table.getInt(6);
            String comments = table.getString(7);

            Operations operation = new Operations(ID, name, briefing, equipmentNeeded, rendevouz, grossIncome, comments);
            operationsArray[size] = operation;
            size++;

        }
    }

    public void populateOperationsUI(JTextArea briefing, JTextArea users, JTextArea equipmentNeeded, JLabel header, int ID) {

        String usersWhoWereThere = PersonManager.toStringOfUsersinOpertions(ID);
        for (int i = 0; i < size; i++) {
            if (operationsArray[i].getID() == ID) {
                Operations o = operationsArray[i];
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
        String usersWhoWereThere = PersonManager.toStringOfUsersinOpertions(ID);
        Person[] arr = PersonManager.getUsersFromOperation(ID);
        int numUsers = arr.length;

        for (int i = 0; i < size; i++) {
            if (operationsArray[i].getID() == ID) {
                Operations o = operationsArray[i];
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

    public void registerForOp(int operationID, JButton errorDisplay) {
        try {
            Person currentUser = AppManager.pm.getCurrentUser();

            int ID = currentUser.getID();

            try {
                DB.update("INSERT INTO `darrenlDB`.`userOperations` (`userID`, `operationID`) VALUES (\"" + ID + "\",\"" + operationID + "\");");
            } catch (SQLException ex) {
                System.out.println("This user is already registered!");
                errorDisplay.setText("You are already registered for this operation please return Home!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OperationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populateOperationsButtons(JButton one, JButton two, JButton three, JButton four, JButton five, JButton six) {
        try {
            ResultSet numbeOfOperations = DB.query("SELECT COUNT(DISTINCT operationID) AS UniqueOperationCount FROM darrenlDB.userOperations;");
            numbeOfOperations.next();
            int numberOfOps = numbeOfOperations.getInt(1);

            if (numberOfOps == 1) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());
                
                two.setVisible(false);
                three.setVisible(false);
                four.setVisible(false);
                five.setVisible(false);
                six.setVisible(false);
            }

            if (numberOfOps == 2) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());
                
                three.setVisible(false);
                four.setVisible(false);
                five.setVisible(false);
                six.setVisible(false);
            }
            if (numberOfOps == 3) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());

                three.setVisible(true);
                three.setText(searchForOperationUsingID(3).getName());
                
                four.setVisible(false);
                five.setVisible(false);
                six.setVisible(false);
            }

            if (numberOfOps == 4) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());

                three.setVisible(true);
                three.setText(searchForOperationUsingID(3).getName());
                
                four.setVisible(true);
                four.setText(searchForOperationUsingID(4).getName());
                
                five.setVisible(false);
                six.setVisible(false);
            }

            if (numberOfOps == 5) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());

                three.setVisible(true);
                three.setText(searchForOperationUsingID(3).getName());
                
                four.setVisible(true);
                four.setText(searchForOperationUsingID(4).getName());
                
                five.setVisible(true);
                five.setText(searchForOperationUsingID(5).getName());
                
                                
                                
                six.setVisible(false);
            }
            if (numberOfOps == 6) {
                one.setVisible(true);
                one.setText(searchForOperationUsingID(1).getName());

                two.setVisible(true);
                two.setText(searchForOperationUsingID(2).getName());

                three.setVisible(true);
                three.setText(searchForOperationUsingID(3).getName());
                
                four.setVisible(true);
                four.setText(searchForOperationUsingID(4).getName());
                
                five.setVisible(true);
                five.setText(searchForOperationUsingID(5).getName());
                
                six.setVisible(true);
                six.setText(searchForOperationUsingID(6).getName());
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(OperationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Operations searchForOperationUsingID(int ID) {
        for (int i = 0; i < size; i++) {
            if (operationsArray[i].getID() == ID) {
                Operations o = operationsArray[i];
                return o;
            }
        }
        return null;
    }
}
