/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wolf;

import Common.MessageManagement;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.netbeans.xml.schema.updateschema.TMyPlace;
import org.netbeans.xml.schema.updateschema.TPlace;

/**
 *
 * @author André
 */
public class UpdateStateResponder extends AchieveREResponder {
    
    private SecureRandom r;

    public UpdateStateResponder(Agent a, MessageTemplate mt) {
        super(a, mt);
        r = new SecureRandom();
    }

    @Override
    protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
        //Aula 2 e //Aula 3
        TMyPlace place = null;
               
        ACLMessage response;
        try{
            place = MessageManagement.retrievePlaceStateObject(request.getContent());
        }catch(JAXBException ex){
            Logger.getLogger(UpdateStateResponder.class.getName()).log(Level.SEVERE, null, ex);
        }
        response = request.createReply();
        if(place!=null){
            response.setPerformative(ACLMessage.AGREE);            
        }else{
            response.setPerformative(ACLMessage.REFUSE);
        }
        response.setOntology(Common.Constants.ONTOLOGY_UPDATE_STATE);
        return response;
    }
    
    @Override
    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response){
        TMyPlace place = null;
        int i;        
        ACLMessage response2;
        List<TPlace> placeList;
        try{
            place = MessageManagement.retrievePlaceStateObject(request.getContent());
        }catch(JAXBException ex){
            Logger.getLogger(UpdateStateResponder.class.getName()).log(Level.SEVERE, null, ex);
        }
        response2 = request.createReply();
        if(place!=null){
            TMyPlace nextPlace = new TMyPlace();
            TMyPlace available = new TMyPlace();
            Boolean foundCow = false;
            
            placeList=place.getPlace();
            for(i=1; i<placeList.size(); i++){
                if(placeList.get(i)!=null && placeList.get(i).getPosition()!= null){
                    if( placeList.get(i).isCow() && !placeList.get(i).isObstacle()){
                        if(!foundCow){
                            available.getPlace().clear();                            
                            foundCow = true;                            
                        }
                        available.getPlace().add(placeList.get(i));                        
                    }else if(!placeList.get(i).isObstacle() && !foundCow ){
                        available.getPlace().add(placeList.get(i));
                    }                                                
                }                
            }
            
            if(available.getPlace().isEmpty()){                
                nextPlace.getPlace().add(placeList.get(0));
            }else if(available.getPlace().size()!=1){
                i = r.nextInt(available.getPlace().size()-1);
                nextPlace.getPlace().add(available.getPlace().get(i));
            }else{
                nextPlace.getPlace().add(available.getPlace().get(0));
            }
            
            
            response2.setPerformative(ACLMessage.INFORM);
            String replyStr = "";
            try{
                if(nextPlace.getPlace().get(0).getPosition()==null){
                    System.err.println("");
                }
                replyStr = Common.MessageManagement.createPlaceStateContent(nextPlace);
            }catch(JAXBException ex){
                Logger.getLogger(UpdateStateResponder.class.getName()).log(Level.SEVERE, null, ex);
            }
            response2.setContent(replyStr);
            
        }else{
            response2.setPerformative(ACLMessage.FAILURE);
            
        }
        response2.setOntology(Common.Constants.ONTOLOGY_UPDATE_STATE);
        return response2;
    }

}
