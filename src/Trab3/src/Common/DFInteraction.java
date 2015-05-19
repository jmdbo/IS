/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 *
 * @author
 * André
 */
public class DFInteraction {

    public static void RegisterInDF(Agent myAgent, String name, String type) throws FIPAException {
        //Aula 1
    }

    public static DFAgentDescription[] SearchInDFbyType(String type, Agent myAgent) throws FIPAException {
        //Aula 1
    }
    
    public static DFAgentDescription[] SearchInDFbyName(String name, Agent myAgent) throws FIPAException{
        //Aula 1
    }

    public static boolean DeregisterFromDF(Agent myAgent) throws FIPAException {
        //Aula 1
    }
}
