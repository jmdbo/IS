/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Device;

import Hardware.windmill;
/**
 *
 * @author Pedro
 */
public class windmill_server {
    private windmill lib;
    
    public int isOn(){
        return lib.is_on();
    }
    
    public float enerProd(){
        return lib.energy_production();
    }
    
    public int turnOn(int state){
        return lib.turn_on(state);
    }
    
    public String errorString(){
        return lib.errorString();
    }
    
    public int deviceserialNumber(){
        return lib.deviceSerialNumber();
    }
}
