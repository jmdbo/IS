/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cow;

import Common.MessageManagement;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.netbeans.xml.schema.updateschema.TMyPlace;

/**
 *
 * @author André
 */
public class UpdateStateResponder extends AchieveREResponder {

    public UpdateStateResponder(Agent a, MessageTemplate mt) {
        super(a, mt);
    }

    @Override
    protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
        //Aula 2 e Aula 3
        TMyPlace place = null;
        ACLMessage response;
        try{
            place = MessageManagement.retrievePlaceStateObject(request.getContent());
        }catch(JAXBException ex){
            Logger.getLogger(UpdateStateResponder.class.getName()).log(Level.SEVERE, null, ex);
        }
        response = request.createReply();
        if(place!=null){
            TMyPlace nextPlace = new TMyPlace();
            nextPlace.getPlace().add(place.getPlace().get(1));
            //TODO: Perguntar isto ao Rocha!
            response.setPerformative(ACLMessage.AGREE);
            /*******************-Perguntar ao Prof!!!-***********************************/
            String replyStr = "";
            try{
                replyStr = Common.MessageManagement.createPlaceStateContent(nextPlace);
            }catch(JAXBException ex){
                Logger.getLogger(UpdateStateResponder.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.setContent(replyStr);
            
        }else{            
            //TODO: Perguntar isto ao Rocha!
            response.setPerformative(ACLMessage.REFUSE);
            /*******************-Perguntar ao Prof!!!-***********************************/
            
        }
        response.setOntology(Common.Constants.ONTOLOGY_UPDATE_STATE);
        return response;
    }
}
