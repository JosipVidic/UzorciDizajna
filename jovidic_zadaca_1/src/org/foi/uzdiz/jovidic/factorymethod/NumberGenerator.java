package org.foi.uzdiz.jovidic.factorymethod;

import java.util.Random;

/**
 * Pomoćna klasa koja služi za keneriranje brojeva u intervalu
 *
 * @author jovidic
 */
public class NumberGenerator {

    /**
     * Metoda generira random broj unutar unesenog ranga.
     *
     * @param max
     * @param min
     * @return
     */
    public int generateInRange(int max, int min) {
        Random r = new Random();
        int R = r.nextInt(max + 1 - min) + min;
        return R;
    }

}
