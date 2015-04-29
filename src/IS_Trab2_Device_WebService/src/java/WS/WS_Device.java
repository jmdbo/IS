/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.restlet.resource.ServerResource;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Delete;

import Device.windmill_server;
/**
 *
 * @author Pedro
 */
@WebService(serviceName = "WS_Device")
public class WS_Device extends ServerResource{

    /**
     * This is a sample web service operation
     */
    
    private windmill_server lib_device;
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @Get
    public int turnOn(int state){
        return lib_device.turnOn(state);
    }
    
    @Get
    public int deviceserialNumber(){
        return lib_device.deviceserialNumber();
    }
    
    @Get
    public int isOn(){
        return lib_device.isOn();
    }
    
    @Delete
    public boolean turnOf(){
        return false;
    }
    
}
