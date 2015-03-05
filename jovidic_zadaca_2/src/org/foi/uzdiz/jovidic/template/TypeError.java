/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.template;

/**
 *
 * @author jovidic Klasa opisuje gresku kod citanja ili obrade zapisa ovisno o
 * tipu zapisa.
 */
public class TypeError extends ErrorTemplate {

    @Override
    void dodajError(String e, int i) {

        String dodaj = "Postoji pogreska u zapisu (krivi tip) na liniji : " + i + "     ==>    " + e;
        errorData.add(dodaj);
    }

    @Override
    void procitajError(Boolean tip, String er, int i) {
        if (tip = false) {

            System.out.println("Pogreska kod procesuiranja greske");
        } else {

            System.out.println("Postoji pogreska u zapisu (krivi tip) u liniji br " + i + " zapisujem u datoteku error.txt");

        }

    }

}
