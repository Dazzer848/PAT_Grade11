/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operation;

/**
 *
 * @author dazzl
 */
public class Operations2 {
    private int ID;
    private String name;
    private String briefing;
    private String equipmentNeeded;
    private String redezvous;
    private int grossIncome;
    private String comments;
    
    
    public Operations2(int inID, String inName, String inBriefing, String inequipmentNeeded, String inRedezvous, int inGrossIncome, String inComments){
        this.ID = inID;
        this.name = inName;
        this.briefing = inBriefing;
        this.equipmentNeeded = inequipmentNeeded;
        this.redezvous = inRedezvous;
        this.grossIncome = inGrossIncome;
        this.comments = inComments;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getBriefing() {
        return briefing;
    }

    public String getEquipmentNeeded() {
        return equipmentNeeded;
    }

    public String getRedezvous() {
        return redezvous;
    }

    public int getGrossIncome() {
        return grossIncome;
    }

    public String getComments() {
        return comments;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }

    public void setEquipmentNeeded(String equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }

    public void setRedezvous(String redezvous) {
        this.redezvous = redezvous;
    }

    public void setGrossIncome(int grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
