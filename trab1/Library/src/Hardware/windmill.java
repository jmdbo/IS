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
    static{System.load("C:\\dev\\IS\\ISTrab1_Console\\x64\\Debug\\ISTrab1_DLL.dll");}
    static{System.load("C:\\dev\\IS\\ISTrab1_Console\\x64\\Debug\\ISTrab1_JNI.dll");}
    public native static float energyProduction();
    public native static int turnOn(int state);
    public native static int isOn();
    public native static String error();
    
     public int _isOn(){
        return this.isOn();
    }
     
    public String _error(){
        return this.error();
    }
}
