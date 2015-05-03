/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Device;

import Hardware.windmill;
//restlet import
import org.restlet.Restlet;
import org.restlet.resource.ServerResource;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.routing.Router;
import org.restlet.routing.TemplateRoute;

/**
 *
 * @author Pedro
 */

public class windmill_server extends ServerResource{
    private windmill lib;
    
    @Get
    public String turnOn(){
        //return lib.turn_on(1);
        System.out.println("1");
        return "Ola";
    }
    
    @Delete
    public int remove(){
        return lib.turn_on(0);
    }
    
    /*public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        //router.attachDefault(new Directory(getContext(), "war:///"));
        router.attach("/windmill",this.getClass());
        //srouter.attach(getContext()+"/este", este_reource.class);
        router.attachDefault(este_reource.class);
                
        return router;
    }*/
}
