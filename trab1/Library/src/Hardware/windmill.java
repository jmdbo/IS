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
    native static float energyProduction();
    native static int turnOn(int state);
    native static int isOn();
    native static String error(); 
}
