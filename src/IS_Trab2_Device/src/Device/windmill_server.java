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


/**
 *
 * @author Pedro
 */

public class windmill_server extends ServerResource{
    private windmill lib;
    
    @Get
    public int turnOn(){
        System.out.println("On");
        return lib.turn_on(1);
    }
    
    @Delete
    public int remove(){
        System.out.println("Off");
        return lib.turn_on(0);
    }
}
