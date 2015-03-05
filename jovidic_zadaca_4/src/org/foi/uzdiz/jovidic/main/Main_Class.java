package org.foi.uzdiz.jovidic.main;

import org.foi.uzdiz.jovidic.mvc.Controller;
import org.foi.uzdiz.jovidic.mvc.View;

/**
 *  Main klasa koja instancira i pokreće kontroler te instancira pogled
 * @author jovidic
 */
public class Main_Class {

    public static Controller kontroler;
    public static View pogled;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        kontroler = new Controller();
        pogled = new View();
        kontroler.run(args);

    }

}
