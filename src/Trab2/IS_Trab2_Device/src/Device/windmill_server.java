/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Device;

import Hardware.windmill;
//restlet import
import org.restlet.resource.ServerResource;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;


/**
 *
 * @author Pedro
 */

public class windmill_server extends ServerResource{
    private windmill lib;
    
    @Get
    public String turnOn(){
        System.out.println("On");
        return Integer.toString(lib.turn_on(1));
    }
    
    @Delete
    public String remove(){
        System.out.println("Off");
        return Integer.toString(lib.turn_on(0));
    }
    
    public windmill_server(){
        
    }
    @Override
    protected void doInit() throws ResourceException {
        lib = (windmill) getContext().getAttributes().get("HARDWARE");
    }
}
