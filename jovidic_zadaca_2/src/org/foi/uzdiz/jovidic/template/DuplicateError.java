/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.template;

/**
 *
 * @author jovidic
 *
 * Klasa koja sluzi zapisu greske o duplikatu u vanjsku datoteku.
 */
public class DuplicateError extends ErrorTemplate {

    @Override
    void dodajError(String e, int i) {
        String dodaj = "Postoji duplikat kod definiranja na liniji : " + i + " => " + e;
        errorData.add(dodaj);
    }

    @Override
    void procitajError(Boolean tip, String er, int i) {
        if (tip = false) {

            System.out.println("Pogreska kod procesuiranja greske");
        } else {
            System.out.println("Postoji duplikat kod definiranja na liniji br " + i + " zapisujem u datoteku error.txt");

        }

    }

}
