/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OldManagers;

/**
 *
 * @author dazzl
 */
public class test {
    public static void main(String[] args) {
        
        int ID = 1;
        String inNmae = "woop";
        String inBriefingString = "go thpm";
        String inEquipmentNeeded = "a spoon";
        String rendezvous = "mothers hosue";
        
        
        
        System.out.println("INSERT INTO 'darrenlDB'.'operations'('ID','Name','Briefing','Equipment Needed','rendezvous')VALUES(\"" + ID + "\",\"" + inNmae + "\",\"" + inBriefingString + "\",\"" + inEquipmentNeeded + "\",\"" + rendezvous + "\");");
    }
    
}
