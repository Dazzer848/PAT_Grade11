/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Person;
import Person.PersonManager2;
import Person.Person2;
/**
 *
 * @author dazzl
 */
public class Person2test {
    public static void main(String[] args) {
         Person2[] arr = PersonManager2.getUsersFromOperation(1);
         
         System.out.println(arr[0]);
    }
    
}
