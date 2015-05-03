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
        lib = new windmill();
        this.start();
    }
    private static Boolean insertState(int serialNumber, int state, int error, int energyProduction) {
        webservices.DeviceWebService_Service service = new webservices.DeviceWebService_Service();
        webservices.DeviceWebService port = service.getDeviceWebServicePort();
        return port.insertState(serialNumber, state, error, energyProduction);
    }

    @Override
    public void run() {
        Boolean a=true;
        while (a) {            
            try {
                //Integrate with your DLL here
                
                int deviceSerialNumber = lib.deviceSerialNumber();
                int deviceState = lib.is_on();
                String parse = lib.errorString();
                parse = parse.replaceAll("(\\r|\\n)", "");
                if(deviceState==0)
                    deviceState=2;
                int deviceError = Integer.parseInt(parse);
                float deviceEnergyProduction = lib.energy_production();
                //To here (DLL)
                Boolean result = insertState(deviceSerialNumber,deviceState,deviceError,(int)deviceEnergyProduction);
                //Integrate with Cloud callig Web Service's Method, using the variables:
                //deviceSerialNumber, deviceState, deviceError and deviceEnergyProduction   
                
                System.out.println("Send Device State");
                System.out.println("\tSerial Number: " + deviceSerialNumber);
                System.out.println("\tState: " + deviceState);
                System.out.println("\tErrors: " + deviceError);
                System.out.println("\tEnergy Production: " + deviceEnergyProduction);
                System.out.println("\tInsert State: "+result.toString());
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
