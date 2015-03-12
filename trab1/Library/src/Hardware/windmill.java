/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hardware;

/**
 *
 * @author João Barata Oliveira
 * @author Pedro Martins
 */
public class windmill {
    static{System.load("C:\\dev\\IS\\trab1\\trab1_VS\\x64\\Debug\\trab1_dll.dll");}
    native static float energyProduction();
    native static int turnOn(int state);
    native static int isOn();
    native static String error(); 
    
    public static void main(String[] args){
        System.out.println(error());
    }
}


