/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Environment;

import Common.DFInteraction;
import Common.MessageManagement;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.netbeans.xml.schema.updateschema.TMyPlace;
import org.netbeans.xml.schema.updateschema.TPlace;

/**
 *
 * @author André
 */
public class UpdateStates extends SequentialBehaviour {

    public UpdateStates(Agent a, long period) throws JAXBException, FIPAException {
        super(a);
        this.addSubBehaviour(new Communications(a, ParallelBehaviour.WHEN_ALL));
        this.addSubBehaviour(new UpdateEnvironment(period));
        this.addSubBehaviour(new GameOverVerification(period));
    }

    private static class Communications extends ParallelBehaviour {

        public Communications(Agent a, int endCondition) throws JAXBException, FIPAException {
            super(a, endCondition);

            for (String cowName : ((EnvironmentAgent) myAgent).cowAgents.keySet()) {
                TMyPlace myPlace = createMyPlace(((EnvironmentAgent) myAgent).cowAgents.get(cowName).getXx(), ((EnvironmentAgent) myAgent).cowAgents.get(cowName).getYy());
                DFAgentDescription[] SearchByName = DFInteraction.SearchInDFbyName(cowName, myAgent);
                if (SearchByName.length != 0) {
                    this.addSubBehaviour(new SendUpdateStateInitiator(myAgent, SendUpdateStateInitiator.BuiltMessage(SearchByName[0].getName(), MessageManagement.createPlaceStateContent(myPlace))));
                }
            }
            //send update for all wolfs
            for (String wolfName : ((EnvironmentAgent) myAgent).wolfAgents.keySet()) {
                TMyPlace myPlace = createMyPlace(((EnvironmentAgent) myAgent).wolfAgents.get(wolfName).getXx(), ((EnvironmentAgent) myAgent).wolfAgents.get(wolfName).getYy());
                DFAgentDescription[] SearchByName = DFInteraction.SearchInDFbyName(wolfName, myAgent);
                if (SearchByName.length != 0) {
                    this.addSubBehaviour(new SendUpdateStateInitiator(myAgent, SendUpdateStateInitiator.BuiltMessage(SearchByName[0].getName(), MessageManagement.createPlaceStateContent(myPlace))));
                }
            }
        }

        /*
         * Create MyPlace for each entity (Wolf/Cow)
         */
        private TMyPlace createMyPlace(int posX, int posY) {
            TMyPlace myPlace = new TMyPlace();
            myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY]);
            if (posY == 14 && posX == 0) {
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY - 1]);
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
            } else if (posY == 14 && posX == 14) {
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY]);
            } else if (posY == 0 && posX == 14) {
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY + 1]);
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY]);
            } else if (posY == 0 && posX == 0) {
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY]);
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
            } else if (posY == 14) {
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY]);
            } else if (posX == 14) {
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY + 1]);
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY]);
            } else if (posY == 0) {
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY]);
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY]);
            } else if (posX == 0) {
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY - 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY - 1]);
                myPlace.getPlace().add(new TPlace());
                myPlace.getPlace().add(new TPlace());
            } else {
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX + 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY + 1]);
                myPlace.getPlace().add(((EnvironmentAgent) myAgent).myEnvironment[posX - 1][posY]);
            }
            return myPlace;
        }
    }

    private static class UpdateEnvironment extends OneShotBehaviour {

        long period;

        public UpdateEnvironment(long prd) {
            period = prd;
        }

        @Override
        public void action() {
            try {
                ((EnvironmentAgent) myAgent).myGUI.updateGUI(((EnvironmentAgent) myAgent).myEnvironment);
                Thread.sleep(period / 2);
                ((EnvironmentAgent) myAgent).updateGress();
                ((EnvironmentAgent) myAgent).myGUI.updateGUI(((EnvironmentAgent) myAgent).myEnvironment);
                Thread.sleep(period / 2);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateStates.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private static class GameOverVerification extends OneShotBehaviour {

        long period;

        public GameOverVerification(long prd) {
            period = prd;
        }

        @Override
        public void action() {
            try {
                if (((EnvironmentAgent) myAgent).cowAgents.isEmpty()) {
                    ((EnvironmentAgent) myAgent).myGUI.gameOver();
                } else {
                    ((EnvironmentAgent) myAgent).addBehaviour(new UpdateStates(myAgent, period));
                }
            } catch (JAXBException | FIPAException ex) {
                Logger.getLogger(UpdateStates.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
