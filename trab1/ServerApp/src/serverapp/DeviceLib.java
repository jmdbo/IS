/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp;

import java.util.StringTokenizer;

/**
 *
 * @author André
 */
public class DeviceLib {

    public DeviceLib() {
        //AULA 3 - inicializar as lib
    }

    protected String callHardware(String content) {
        String reply = null;
        StringTokenizer st = new StringTokenizer(content, utilities.constants.token);
        String operation = st.nextToken();
        if (operation.equals("ChangeState")) {
            // AULA 3 - Chamar a DLL para mudar o estado
            int state = Integer.parseInt(st.nextToken());
            System.err.println("ERROR: Impossible to collect data");            //Deve desaparecer
        }
        //Aula 3 - Esta parte deve ser removida e a chamada à DLL deve ser feita aqui
        System.err.println("ERROR: Impossible to collect data");                //Deve desaparecer
        reply = "-1" + utilities.constants.token +                              //Deve desaparecer
                "-1" + utilities.constants.token +                              //Deve desaparecer
                "-1" + utilities.constants.token;                               //Deve desaparecer
        return reply;
    }
}
