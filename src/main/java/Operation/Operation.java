/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operation;

/**
 *
 * @author dazzl
 */
public class Operation {
    private String name;
    private int dateOfOperation;
    //private Person[] operators;
    private int totalMoneyMade;
    private int TAFpaid;
    private double paymentPerOperator;
    private String comments;
    private String briefing;

    public String getName() {
        return name;
    }

    public int getDateOfOperation() {
        return dateOfOperation;
    }

//    public Person[] getOperators() {
//        return operators;
//    }

    public int getTotalMoneyMade() {
        return totalMoneyMade;
    }

    public double getPaymentPerOperator() {
        return paymentPerOperator;
    }

    public String getComments() {
        return comments;
    }

    public String getBriefing() {
        return briefing;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfOperation(int dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

//    public void setOperators(Person[] operators) {
//        this.operators = operators;
//    }

    public void setTotalMoneyMade(int totalMoneyMade) {
        this.totalMoneyMade = totalMoneyMade;
    }

    public void setPaymentPerOperator(double paymentPerOperator) {
        this.paymentPerOperator = paymentPerOperator;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }
    
    
}
