/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OldManagers;

import java.time.LocalDateTime;

/**
 *
 * @author dazzl
 */
public class OLDoperations {
    private String name;
    //private LocalDateTime dateOfOperation;
    private OLDperson[] operators;
    private int totalMoneyMade;
    private int TAFpaid;
    private int paymentPerOperator;
    private String comments;
    private String briefing;
    private int ID;
    private String equipment;
    private int nuimberOfPeople;
    // We will work the ID's based off of the size of the array. We will set the ID to size as this is always more than the index of 
    
    public OLDoperations(String name, OLDperson[] operators, int totalMoneyMade, int TAFpaid, int paymentPerOperator, String comments, String briefing, int ID, String equipment,int numberOf) {
        this.name = name;
        //this.dateOfOperation = dateOfOperation;
        this.operators = operators;
        this.totalMoneyMade = totalMoneyMade;
        this.TAFpaid = TAFpaid;
        this.paymentPerOperator = paymentPerOperator;
        this.comments = comments;
        this.briefing = briefing;
        this.ID = ID;
        this.equipment = equipment;
        this.nuimberOfPeople = numberOf;
    }

    public int getTAFpaid() {
        return TAFpaid;
    }

    public int getID() {
        return ID;
    }

    public void setTAFpaid() {
        this.TAFpaid = (int) (totalMoneyMade * 0.005);
        //Update DBMS
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    //public LocalDateTime getDateOfOperation() {     
        //return dateOfOperation;
    //}

    //This cant be a static method but it needs to be why?
    public OLDperson[] getOperators() {
        return operators;
    }

    public int getTotalMoneyMade() {
        return totalMoneyMade;
    }

    public int getPaymentPerOperator() {
        return paymentPerOperator;
    }

    public String getComments() {
        return comments;
    }
    
    // This needs to be a static method but it cant be. Why?
    public String getBriefing() {
        return briefing;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    // This cant be a static method but it needs to be! How fix
    public String getEquipment(){
        return equipment;
    }

    public void setDateOfOperation(LocalDateTime dateOfOperation) {
//        this.dateOfOperation = dateOfOperation;
    }

    public void setOperators(OLDperson[] operators) {
        this.operators = operators;
    }

    public void setTotalMoneyMade(int totalMoneyMade) {
        this.totalMoneyMade = totalMoneyMade;
    }

    public void setPaymentPerOperator(int paymentPerOperator) {
        this.paymentPerOperator = (int) paymentPerOperator;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }
    
    public String toStringUsers(){
        
        String toDisplay = "";
        for(int i = 1; i < operators.length; i++){
            toDisplay += operators[i].getUsername() + "\n";
        }
        return toDisplay;
    }
    
}
