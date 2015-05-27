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
            TMyPlace available = new TMyPlace();
            List<TPlace> placeList = place.getPlace();
            int i=0;
            int maxGrass=0;
            
            for(i=1; i<(placeList.size()-1); i++){
                if(i<(placeList.size()-2)){
                    if(placeList.get(i-1)!=null && placeList.get(i+1)!=null && placeList.get(i)!=null){
                        if(!placeList.get(i-1).isWolf() && !placeList.get(i).isWolf() && !placeList.get(i+1).isWolf() && placeList.get(i).getGress()!=0 && !placeList.get(i).isObstacle()){
                            if(placeList.get(i).getGress()>maxGrass){
                                maxGrass = placeList.get(i).getGress();available.getPlace().clear();
                                available.getPlace().add(0, placeList.get(i));
                            }else if(maxGrass == placeList.get(i).getGress()){
                                available.getPlace().add(0, placeList.get(i));
                            }    
                        }
                    }else if(placeList.get(i-1)==null && placeList.get(i+1)!=null && placeList.get(i)!=null){
                        if(!placeList.get(i).isWolf() && !placeList.get(i+1).isWolf() && placeList.get(i).getGress()!=0 && !placeList.get(i).isObstacle()){
                            if(placeList.get(i).getGress()>maxGrass){
                                maxGrass = placeList.get(i).getGress();
                                available.getPlace().clear();
                                available.getPlace().add(0, placeList.get(i));
                            }else if(maxGrass == placeList.get(i).getGress()){
                                available.getPlace().add(0, placeList.get(i));
                            }                 
                        }

                    } else if (placeList.get(i-1)!=null && placeList.get(i+1)==null && placeList.get(i)!=null){
                        if(!placeList.get(i-1).isWolf()&& !placeList.get(i).isWolf() && placeList.get(i).getGress()!=0 && !placeList.get(i).isObstacle()){
                            if(placeList.get(i).getGress()>maxGrass){
                                maxGrass = placeList.get(i).getGress();
                                available.getPlace().clear();
                                available.getPlace().add(0, placeList.get(i));
                            }else if(maxGrass == placeList.get(i).getGress()){
                                available.getPlace().add(0, placeList.get(i));
                            }                
                        }                    
                    }
                } else{
                    if(placeList.get(i-1)!=null && placeList.get(i)!=null){                    
                        if(!placeList.get(i-1).isWolf() && !placeList.get(i).isWolf() && placeList.get(i).getGress()!=0 && !placeList.get(i).isObstacle()){
                            if(placeList.get(i).getGress()>maxGrass){
                                maxGrass = placeList.get(i).getGress();
                                available.getPlace().clear();
                                available.getPlace().add(0, placeList.get(i));
                            }else if(maxGrass == placeList.get(i).getGress()){
                                available.getPlace().add(0, placeList.get(i));
                            }                
                        }
                    }
                }
            }            
            
            if(available.getPlace().isEmpty()){                
                nextPlace.getPlace().add(placeList.get(0));
            }else{
                Random r = new Random();
                i = r.nextInt(available.getPlace().size()-1);
                nextPlace.getPlace().add(available.getPlace().get(i));
            }
            
            response.setPerformative(ACLMessage.INFORM);
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
