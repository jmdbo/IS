/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Device;
//import restlet
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.resource.ServerResource;
import org.restlet.routing.TemplateRoute;

/**
 *
 * @author Pedro
 */
public class Server_Application extends ServerResource {
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        //router.attachDefault(new Directory(getContext(), "war:///"));
        router.attach("/windmill", windmill_server.class);
        System.out.println("Entrei");
        return router;
        
        
    }
    
}
