/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;
import Hardware.windmill;

/**
 *
 * @author João
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        windmill lib = new windmill();
        float res = lib.energy_production();
        System.out.println(res);
    }
    
}
