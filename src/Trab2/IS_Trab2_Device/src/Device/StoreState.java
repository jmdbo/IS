/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Device;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author André
 */
public class StoreState extends Thread{
    
    DeviceGUI myGUI;

    public StoreState(DeviceGUI gui) {
        this.myGUI = gui;
        this.start();
    }

    @Override
    public void run() {
        while (true) {            
            try {
                //Integrate with your DLL here
                //Remove the code from here
                int deviceSerialNumber = this.myGUI.getDeviceSerialNumber();
                int deviceState = this.myGUI.getDeviceState();
                int deviceError = this.myGUI.getDeviceMyError();
                int deviceEnergyProduction = this.myGUI.getDeviceEnergyProduction();
                //To here (DLL)
                
                //Integrate with Cloud callig Web Service's Method, using the variables:
                //deviceSerialNumber, deviceState, deviceError and deviceEnergyProduction   
                System.out.println("Send Device State");
                System.out.println("\tSerial Number: " + deviceSerialNumber);
                System.out.println("\tState: " + deviceState);
                System.out.println("\tErrors: " + deviceError);
                System.out.println("\tEnergy Production: " + deviceEnergyProduction);
                
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(StoreState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
