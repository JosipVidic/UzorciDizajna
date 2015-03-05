/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.singleton;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.jovidic.factorymethod.NumberGenerator;
import org.foi.uzdiz.jovidic.factorymethod.RaceFactory;
import org.foi.uzdiz.jovidic.main.MainClass;

/**
 * Klasa koja generira broj utrka te kreira taj broj dretvi za utrke
 *
 * @author jovidic
 */
public class ContestSingleton {

    private NumberGenerator gb;
    public int utrkaID;
    public int R;

    private ContestSingleton() {
    }

    public static ContestSingleton getInstance() {
        return NatjecanjeHolder.INSTANCE;
    }

    private static class NatjecanjeHolder {

        private static final ContestSingleton INSTANCE = new ContestSingleton();
    }

    public void generirajBrUtrka(int maxBrUtrka) {
        gb = new NumberGenerator();
        RaceFactory utrka = new RaceFactory();
        R = gb.generateInRange(maxBrUtrka, 1);
        System.out.println("Broj generiranih utrka je " + R);
        
        
        for (int i = 1; i <= R; i++) {
            utrkaID = i;
            utrka.run();
            if (MainClass.pauza == 1) {
                try {
                    System.out.println("Odmaramo nakon utrke, pijemo Cedevitu, pauza 3 sekunde...");
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ContestSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        SystemOutSingleton.getInstance().ispisiRezultate(utrka.vezanaLista);
    }
}
