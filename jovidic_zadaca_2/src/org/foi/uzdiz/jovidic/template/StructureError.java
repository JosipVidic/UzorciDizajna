/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.template;

/**
 *
 * @author jovidic
 *  Klasa koja sluzi zapisu strukturne greske u vanjsku datoteku.
 */
public class StructureError extends ErrorTemplate{
    
    
    
    @Override
    void dodajError(String e, int i) {
        String dodaj = "Postoji strukturna pogreska na liniji : " + i + " => " + e;
        errorData.add(dodaj);
    }

    @Override
    void procitajError(Boolean tip, String er, int i) {
        if (tip = false) {

            System.out.println("Pogreska kod procesuiranja greske");
        } else {
            System.out.println("Postoji strukturna pogreska na liniji br " + i + " zapisujem u datoteku error.txt");

        }

    }
}
