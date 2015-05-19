/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Environment;

import Common.Constants;
import Common.DFInteraction;
import Cow.CowAgent;
import Simulation.EnvironmentGUI;
import Wolf.WolfAgent;
import jade.core.Agent;
import jade.domain.FIPAException;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.xml.schema.updateschema.TPlace;
import org.netbeans.xml.schema.updateschema.TPosition;

/**
 *
 * @author André
 */
public class EnvironmentAgent extends Agent {

    /*
     * Arguments
     * 0 - Number of Obstacles
     * 1 - Number of Wolfs
     * 2 - Number of Cows
     */
    protected TPlace[][] myEnvironment;
    protected EnvironmentGUI myGUI;
    protected HashMap<String, TPosition> wolfAgents = new HashMap<>();
    protected HashMap<String, TPosition> cowAgents = new HashMap<>();
    protected LinkedList<String> pendingUpdate = new LinkedList<>();

    @Override
    protected void setup() {
        try {
            DFInteraction.RegisterInDF(this, this.getLocalName(), Constants.DF_SERVICE_ENVIRONMENT);
            /*
             * Start Environment
             */
            myEnvironment = new TPlace[15][15];
            int obstacles = Integer.parseInt((String) this.getArguments()[0]);
            int wolfs = Integer.parseInt((String) this.getArguments()[1]);
            int cows = Integer.parseInt((String) this.getArguments()[2]);
            generateEnvironment(obstacles, wolfs, cows);
            /*
             * Start GUI
             */
            myGUI = new EnvironmentGUI();
            myGUI.updateGUI(myEnvironment);
            myGUI.setVisible(true);
            /*
             * Waitting for start behaviour
             */
            this.addBehaviour(new StartSimulation());

        } catch (StaleProxyException | FIPAException ex) {
            Logger.getLogger(EnvironmentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void takeDown() {
        try {
            DFInteraction.DeregisterFromDF(this);
        } catch (FIPAException ex) {
            Logger.getLogger(EnvironmentAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Generate all the Environment
     */
    private void generateEnvironment(int obstacles, int wolfs, int cows) throws StaleProxyException {
        startBase();
        putObstacles(obstacles);
        putWolfs(wolfs);
        putCows(cows);
    }

    /*
     * Start all places with gress and position
     */
    private void startBase() {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                myEnvironment[x][y] = new TPlace();
                Random rand = new Random();
                myEnvironment[x][y].setGress(rand.nextInt((3 - 1) + 1) + 1);
                TPosition pos = new TPosition();
                pos.setXx(x);
                pos.setYy(y);
                myEnvironment[x][y].setPosition(pos);
            }
        }
    }

    /*
     * Put obstacles
     */
    private void putObstacles(int obstacles) {
        for (int i = 0; i < obstacles; i++) {
            int posX;
            int posY;
            do {
                Random randX = new Random();
                Random randY = new Random();
                posX = randX.nextInt((14 - 0) + 1) + 0;
                posY = randY.nextInt((14 - 0) + 1) + 0;
            } while (myEnvironment[posX][posY].isObstacle() || myEnvironment[posX][posY].isCow() || myEnvironment[posX][posY].isWolf());
            myEnvironment[posX][posY].setObstacle(true);
        }
    }

    /*
     * Put wolfs in the environment and launch all wolf agents
     */
    private void putWolfs(int wolfs) throws StaleProxyException {
        for (int i = 0; i < wolfs; i++) {
            int posX;
            int posY;
            do {
                Random randX = new Random();
                Random randY = new Random();
                posX = randX.nextInt((14 - 0) + 1) + 0;
                posY = randY.nextInt((14 - 0) + 1) + 0;
            } while (myEnvironment[posX][posY].isObstacle() || myEnvironment[posX][posY].isCow() || myEnvironment[posX][posY].isWolf());
            myEnvironment[posX][posY].setWolf(true);
            myEnvironment[posX][posY].setEntity("Wolf_" + i);
            launchNewWolfAgent("Wolf_" + i);
            TPosition tPosition = new TPosition();
            tPosition.setXx(posX);
            tPosition.setYy(posY);
            this.wolfAgents.put("Wolf_" + i, tPosition);
        }
    }

    /*
     * Put cows in the environment and launch all cow agents
     */
    private void putCows(int cows) throws StaleProxyException {
        for (int i = 0; i < cows; i++) {
            int posX;
            int posY;
            do {
                Random randX = new Random();
                Random randY = new Random();
                posX = randX.nextInt((14 - 0) + 1) + 0;
                posY = randY.nextInt((14 - 0) + 1) + 0;
            } while (myEnvironment[posX][posY].isObstacle() || myEnvironment[posX][posY].isCow() || myEnvironment[posX][posY].isWolf());
            myEnvironment[posX][posY].setCow(true);
            myEnvironment[posX][posY].setEntity("Cow_" + i);
            launchNewCowAgent("Cow_" + i);
            TPosition tPosition = new TPosition();
            tPosition.setXx(posX);
            tPosition.setYy(posY);
            this.cowAgents.put("Cow_" + i, tPosition);
        }
    }

    /*
     * Launch a new Wolf Agent
     */
    private void launchNewWolfAgent(String name) throws StaleProxyException {
        WolfAgent newWolf = new WolfAgent();
        newWolf.setArguments(new Object[]{});
        AgentController agent = this.getContainerController().acceptNewAgent(name, newWolf);
        agent.start();
    }

    /*
     * Launch a new Cow Agent
     */
    private void launchNewCowAgent(String name) throws StaleProxyException {
        CowAgent newCow = new CowAgent();
        newCow.setArguments(new Object[]{});
        AgentController agent = this.getContainerController().acceptNewAgent(name, newCow);
        agent.start();
    }

    /*
     * Update Gress
     */
    protected void updateGress() {
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 15; y++) {
                if (myEnvironment[x][y].isCow()) {
                    myEnvironment[x][y].setGress(myEnvironment[x][y].getGress() - 1);
                } else if (!myEnvironment[x][y].isWolf() && !myEnvironment[x][y].isObstacle() && myEnvironment[x][y].getGress() < 3) {
                    myEnvironment[x][y].setGress(myEnvironment[x][y].getGress() + 1);
                }
            }
        }
    }
    
    protected void removeWolf(String wolfName){
        TPosition wolfPosition = this.wolfAgents.get(wolfName);
        this.myEnvironment[wolfPosition.getXx()][wolfPosition.getYy()].setWolf(false);
        this.myEnvironment[wolfPosition.getXx()][wolfPosition.getYy()].setEntity(null);
        this.wolfAgents.remove(wolfName);
    }
}
