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
import java.util.ArrayList;


/**
 *
 * @author João
 */
@WebService(serviceName = "FrontEndWebService")
public class FrontEndWebService {
    DataBaseManagement dbMgmt = new DataBaseManagement();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createDB")
    public Boolean createDB() {
        //Create the DB and returns the result of DB creation
        return dbMgmt.createDB();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addUser")
    public Boolean addUser(@WebParam(name = "UserName") String UserName,
            @WebParam(name = "Name") String Name, @WebParam(name = "Telephone") int Telephone,
            @WebParam(name = "Residence") String Residence) {
        return dbMgmt.InsertCustomer(UserName, Name, Telephone, Residence);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addDevice")
    public Boolean addDevice(@WebParam(name = "serialNumber") int serialNumber,
            @WebParam(name = "userName") String userName, 
            @WebParam(name = "model") String model,
            @WebParam(name = "friendlyName") String friendlyName,
            @WebParam(name = "deviceType") int deviceType) {
        return dbMgmt.AssociateDevice(serialNumber, userName, model, friendlyName, deviceType);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserDevices")
    public ArrayList<String> getUserDevices(@WebParam(name = "userName") String userName) {
        //TODO write your implementation code here:
        return dbMgmt.getMyDevices(userName);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserHistory")
    public ArrayList<String> getUserHistory(@WebParam(name = "userName") String userName) {
        //TODO write your implementation code here:
        return dbMgmt.getCustomerStates(userName);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getDeviceHistory")
    public String getDeviceHistory(@WebParam(name = "serialNumber") int serialNumber) {
        //TODO write your implementation code here:
        return null;
    }
    
}
