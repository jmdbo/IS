/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wolf;

import Common.Constants;
import Common.DFInteraction;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 * André
 */
public class WolfAgent extends Agent{

    @Override
    protected void setup() {
        try {
            DFInteraction.RegisterInDF(this, this.getLocalName(), Constants.DF_SERVICE_WOLF);
        } catch (FIPAException ex) {
            Logger.getLogger(WolfAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Aula 1
    }

    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException ex) {
            Logger.getLogger(WolfAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
