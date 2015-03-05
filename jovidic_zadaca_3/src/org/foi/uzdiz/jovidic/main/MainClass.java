package org.foi.uzdiz.jovidic.main;

import org.foi.uzdiz.jovidic.mvc.ControllerIO;
import org.foi.uzdiz.jovidic.mvc.ViewIO;


/**
 *
 * @author jovidic
 *
 * Main klasa koja instancira kontroler i view iz MVC uzorka te pokreće
 * kontrolora s dobivenim argumentima.
 */
public class MainClass {

    public static ControllerIO kontroler;
    public static ViewIO pogled;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        pogled = new ViewIO();
        kontroler = new ControllerIO();
        kontroler.run(args);

    }
}
