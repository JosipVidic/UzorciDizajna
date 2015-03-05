/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.factorymethod;

import java.util.List;

/**
 *
 * @author jovidic
 */
public interface RaceInterface {
    public List<RezultatFactory> generateContestent(int maxBrSudionika);
    public List<RezultatFactory> dodijeliBodove();
    public void score(List<Integer> vremena, List<RezultatFactory> bodovi);
    public void ispisPoUtrkama(List<RezultatFactory> lista);
    
}
