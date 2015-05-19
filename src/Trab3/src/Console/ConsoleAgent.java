/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Environment.EnvironmentAgent;
import jade.core.Agent;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author André
 */
public class ConsoleAgent extends Agent implements frameToAgentCom{
    
    ConsoleFrame myFrame;
    frameToAgentCom myFrameToAgentCom;
    
    @Override
    protected void setup() {
        myFrame = new ConsoleFrame(this);
        myFrame.setVisible(true);
    }

    @Override
    public void startNewEnvironment(int cows, int wolfs, int obstacles) {
        try {
            EnvironmentAgent newEnv = new EnvironmentAgent();
            newEnv.setArguments(new Object[]{""+obstacles, ""+wolfs, ""+cows});
            AgentController agent = this.getContainerController().acceptNewAgent("EnvironmentAgent", newEnv);
            agent.start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(ConsoleAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
