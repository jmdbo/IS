/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import DataBaseManagement.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author João Barata
 * @author Pedro Martins
 */
@WebService(serviceName = "DeviceWebService")
public class DeviceWebService {
    /**
     * Web service operation
     */
    DataBaseManagement dbMgmt = new DataBaseManagement();
    
    @WebMethod(operationName = "insertState")
    public Boolean insertState(@WebParam(name = "serialNumber") int serialNumber, @WebParam(name = "state") int state,
            @WebParam(name = "error") int error, @WebParam(name = "energyProduction") int energyProduction,
            @WebParam(name = "ip") String ip, @WebParam(name = "port") int port) {
        //TODO write your implementation code here:
        Boolean result;
        try{
            result = dbMgmt.insertState(serialNumber, state, error, energyProduction, ip, port);
        }catch(Exception ex){
            result = null;
        }
        return result;
    }
}
