/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cow;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

/**
 *
 * @author André
 */
public class KillYourselfResponder extends AchieveREResponder{

    public KillYourselfResponder(Agent a, MessageTemplate mt) {
        super(a, mt);
    }

    @Override
    protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
        //Aula 2
        ACLMessage reply = request.createReply();
        //TODO: Perguntar isto ao Rocha!
        reply.setPerformative(ACLMessage.AGREE);
        reply.setOntology(Common.Constants.ONTOLOGY_KILL_YOURSELF);
        return reply;
    }
    @Override
    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response){
        myAgent.doDelete();
        ACLMessage reply = request.createReply();
        reply.setPerformative(ACLMessage.INFORM);
        reply.setOntology(Common.Constants.ONTOLOGY_KILL_YOURSELF);
        return reply;        
    }
}
