/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Environment;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

/**
 *
 * @author André
 */
public class KillYourselfInitiator extends AchieveREInitiator {

    public KillYourselfInitiator(Agent a, ACLMessage msg) {
        super(a, msg);
    }

    public static ACLMessage BuiltMessage(AID receiver) {
        //Aula 2
    }

    @Override
    protected void handleInform(ACLMessage inform) {
        System.out.println(inform.getSender().getLocalName() + " was removed from the platform");
    }
}
