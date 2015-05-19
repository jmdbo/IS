/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Environment;

import jade.core.behaviours.SimpleBehaviour;
import jade.domain.FIPAException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author
 * André
 */
public class StartSimulation extends SimpleBehaviour{

    private boolean done = false;
    
    @Override
    public void action() {
        if(((EnvironmentAgent) myAgent).myGUI.isToStart()){
            try {
                ((EnvironmentAgent) myAgent).addBehaviour(new UpdateStates(myAgent, ((EnvironmentAgent) myAgent).myGUI.getPeriod()));
                done = true;
            } catch (JAXBException | FIPAException ex) {
                Logger.getLogger(StartSimulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean done() {
        return done;
    }
    
}
