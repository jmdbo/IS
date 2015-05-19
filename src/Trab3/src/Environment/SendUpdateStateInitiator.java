/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Environment;

import Common.DFInteraction;
import Common.MessageManagement;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.netbeans.xml.schema.updateschema.TMyPlace;
import org.netbeans.xml.schema.updateschema.TPlace;

/**
 *
 * @author André
 */
public class SendUpdateStateInitiator extends AchieveREInitiator {

    public SendUpdateStateInitiator(Agent a, ACLMessage msg) {
        super(a, msg);
    }

    public static ACLMessage BuiltMessage(AID receiver, String content) {
        //Aula 2
    }

    @Override
    protected void handleInform(ACLMessage inform) {
        try {
            TMyPlace myEnvironment = MessageManagement.retrievePlaceStateObject(inform.getContent());
            TPlace myPlace = myEnvironment.getPlace().get(0);
            if (((EnvironmentAgent) myAgent).cowAgents.containsKey(inform.getSender().getLocalName())) {
                int lastX = ((EnvironmentAgent) myAgent).cowAgents.get(inform.getSender().getLocalName()).getXx();
                int lastY = ((EnvironmentAgent) myAgent).cowAgents.get(inform.getSender().getLocalName()).getYy();
                if (!(((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isWolf()
                        && ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].getGress() > 0)
                        && !((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isObstacle()
                        && !((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isCow()) {
                    ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setEntity(null);
                    ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setCow(false);
                    ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].setEntity(inform.getSender().getLocalName());
                    ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].setCow(true);
                    ((EnvironmentAgent) myAgent).cowAgents.get(inform.getSender().getLocalName()).setXx(myPlace.getPosition().getXx());
                    ((EnvironmentAgent) myAgent).cowAgents.get(inform.getSender().getLocalName()).setYy(myPlace.getPosition().getYy());
                } else {
                    if (((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isWolf()) {
                        ((EnvironmentAgent) myAgent).addBehaviour(new KillYourselfInitiator(myAgent, KillYourselfInitiator.BuiltMessage(inform.getSender())));
                        ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setEntity(null);
                        ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setCow(false);
                        ((EnvironmentAgent) myAgent).cowAgents.remove(inform.getSender().getLocalName());
                    }
                    if (((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].getGress() == 0) {
                        ((EnvironmentAgent) myAgent).addBehaviour(new KillYourselfInitiator(myAgent, KillYourselfInitiator.BuiltMessage(inform.getSender())));
                        ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setEntity(null);
                        ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setCow(false);
                        ((EnvironmentAgent) myAgent).cowAgents.remove(inform.getSender().getLocalName());
                    }
                    if (((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isObstacle()) {
                        if (((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].getGress() == 0) {
                            ((EnvironmentAgent) myAgent).addBehaviour(new KillYourselfInitiator(myAgent, KillYourselfInitiator.BuiltMessage(inform.getSender())));
                            ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setEntity(null);
                            ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setCow(false);
                            ((EnvironmentAgent) myAgent).cowAgents.remove(inform.getSender().getLocalName());
                        }
                    }
                    if (((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isCow()) {
                        if (((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].getGress() == 0) {
                            ((EnvironmentAgent) myAgent).addBehaviour(new KillYourselfInitiator(myAgent, KillYourselfInitiator.BuiltMessage(inform.getSender())));
                            ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setEntity(null);
                            ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setCow(false);
                            ((EnvironmentAgent) myAgent).cowAgents.remove(inform.getSender().getLocalName());
                        }
                    }
                }
            } else {
                int lastX = ((EnvironmentAgent) myAgent).wolfAgents.get(inform.getSender().getLocalName()).getXx();
                int lastY = ((EnvironmentAgent) myAgent).wolfAgents.get(inform.getSender().getLocalName()).getYy();
                if (!(((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isWolf()
                        && ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].getGress() > 0)
                        && !((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isObstacle()
                        && !((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isCow()) {
                    ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setEntity(null);
                    ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setWolf(false);
                    ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].setEntity(inform.getSender().getLocalName());
                    ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].setWolf(true);
                    ((EnvironmentAgent) myAgent).wolfAgents.get(inform.getSender().getLocalName()).setXx(myPlace.getPosition().getXx());
                    ((EnvironmentAgent) myAgent).wolfAgents.get(inform.getSender().getLocalName()).setYy(myPlace.getPosition().getYy());
                } else {
                    if (((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isWolf()) {
                        //não faz nada
                    }
                    if (((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].getGress() == 0) {
                        //não faz nada
                    }
                    if (((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isObstacle()) {
                        //não faz nada
                    }
                    if (((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].isCow()) {
                        String lastEntity = ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].getEntity();
                        DFAgentDescription[] SearchByName = DFInteraction.SearchInDFbyName(lastEntity, myAgent);
                        if (SearchByName.length != 0) {
                            ((EnvironmentAgent) myAgent).addBehaviour(new KillYourselfInitiator(myAgent, KillYourselfInitiator.BuiltMessage(SearchByName[0].getName())));
                            //UpdateTable
                            ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setEntity(null);
                            ((EnvironmentAgent) myAgent).myEnvironment[lastX][lastY].setWolf(false);
                            ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].setEntity(inform.getSender().getLocalName());
                            ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].setWolf(true);
                            ((EnvironmentAgent) myAgent).wolfAgents.get(inform.getSender().getLocalName()).setXx(myPlace.getPosition().getXx());
                            ((EnvironmentAgent) myAgent).wolfAgents.get(inform.getSender().getLocalName()).setYy(myPlace.getPosition().getYy());
                            ((EnvironmentAgent) myAgent).myEnvironment[myPlace.getPosition().getXx()][myPlace.getPosition().getYy()].setCow(false);
                            ((EnvironmentAgent) myAgent).cowAgents.remove(lastEntity);
                        }
                    }
                }
            }
        } catch (JAXBException ex) {
            Logger.getLogger(SendUpdateStateInitiator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FIPAException ex) {
            Logger.getLogger(SendUpdateStateInitiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
