/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Device;

//restlet import
import org.restlet.Restlet;
import org.restlet.resource.ServerResource;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;

/**
 *
 * @author Pedro
 */
public class este_reource extends ServerResource {
    @Get
    public String turnOn(){
        //return lib.turn_on(1);
        return "Adeus";
    }
    
    @Delete
    public int remove(){
        return 1;
    }
}
