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
    static{
        System.load("C:\\dev\\IS\\ISTrab1_Console\\x64\\Debug\\ISTrab1_DLL.dll");
    }
    static{
        System.load("C:\\dev\\IS\\ISTrab1_Console\\x64\\Debug\\ISTrab1_JNI.dll");
    }
    
    native static float energyProduction();
    native static int turnOn(int state);
    native static int isOn();
    native static String error();
   
    public float energy_production(){
        return windmill.energyProduction();
    }
    
    public int turn_on(int state){
        return windmill.turnOn(state);
    }
    
    public int is_on(){
        return windmill.isOn();
    }
    
    public String errorString(){
        return windmill.error();
    }
}


