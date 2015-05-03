/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Device;

import java.util.logging.Level;
import java.util.logging.Logger;
import Hardware.windmill;
import static java.lang.Boolean.TRUE;


/**
 *
 * @author André
 */
public class StoreState extends Thread{
    
    DeviceGUI myGUI;
    private windmill lib;
    
    public StoreState(DeviceGUI gui) {
        this.myGUI = gui;
        this.start();
        lib = new windmill();
    }
    

    @Override
    public void run() {
        Boolean a=true;
        while (a) {            
            try {
                //Integrate with your DLL here
                
                int deviceSerialNumber = lib.deviceSerialNumber();
                int deviceState = lib.is_on();
                String deviceError = lib.errorString();
                float deviceEnergyProduction = lib.energy_production();
                //To here (DLL)
                
                //Integrate with Cloud callig Web Service's Method, using the variables:
                //deviceSerialNumber, deviceState, deviceError and deviceEnergyProduction   
                System.out.println("Send Device State");
                System.out.println("\tSerial Number: " + deviceSerialNumber);
                System.out.println("\tState: " + deviceState);
                System.out.println("\tErrors: " + deviceError);
                System.out.println("\tEnergy Production: " + deviceEnergyProduction);
                myGUI.energyProduction =(int) deviceEnergyProduction;
                myGUI.serialNumber=deviceSerialNumber;
                myGUI.state=deviceState;
                
        
                //myGUI.error= deviceError;
                
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(StoreState.class.getName()).log(Level.SEVERE, null, ex);
                a=false;
            }
        }
    }
    
}
