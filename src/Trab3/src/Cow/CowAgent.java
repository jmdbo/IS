/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cow;

import Common.Constants;
import Common.DFInteraction;
import jade.core.Agent;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author André
 */
public class CowAgent extends Agent {

    @Override
    protected void setup() {
        try {
            DFInteraction.RegisterInDF(this, this.getLocalName(), Constants.DF_SERVICE_COW);
        } catch (FIPAException ex) {
            Logger.getLogger(CowAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Aula 1
        this.addBehaviour(new KillYourselfResponder(this, MessageTemplate.MatchOntology(Constants.ONTOLOGY_KILL_YOURSELF)));
        this.addBehaviour(new UpdateStateResponder(this, MessageTemplate.MatchOntology(Constants.ONTOLOGY_UPDATE_STATE)));
    }

    @Override
    protected void takeDown() {
        try {
            DFInteraction.DeregisterFromDF(this);
        } catch (FIPAException ex) {
            Logger.getLogger(CowAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
