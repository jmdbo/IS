/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp;

import java.util.StringTokenizer;
import Hardware.windmill;

/**
 *
 * @author André
 */
public class DeviceLib {
    
    private windmill lib;

    public DeviceLib() {
        //AULA 3 - inicializar as lib
        lib = new windmill();
    }

    protected String callHardware(String content) {
        
        String reply = null;
        StringTokenizer st = new StringTokenizer(content, utilities.constants.token);
        String operation = st.nextToken();
        if (operation.equals("ChangeState")) {
            // AULA 3 - Chamar a DLL para mudar o estado
            int state = Integer.parseInt(st.nextToken());
            int rval = lib.turn_on(state);
            if(rval==0){
                System.err.println("System was already in pretended state!");
            }
        }
        //Aula 3 - Esta parte deve ser removida e a chamada à DLL deve ser feita aqui
        //System.err.println("ERROR: Impossible to collect data");
        //Get current system state
        int isOn=lib.is_on();
        //Get current power production
        float power = lib.energy_production();
        System.out.println(power);
        //Get error state
        String err = lib.errorString();
        //Construct reply with acquired data
        reply = isOn + utilities.constants.token +                              //Deve desaparecer
                power + utilities.constants.token +                              //Deve desaparecer
                err + utilities.constants.token;                               //Deve desaparecer
        return reply;
    }
}
