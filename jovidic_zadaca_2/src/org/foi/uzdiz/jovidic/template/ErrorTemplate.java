/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.template;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.jovidic.main.Helper;

/**
 *
 * @author jovidic
 *
 * Klasa koja sluzi kao predlozak obradi pogresaka u zapisima.
 */
public abstract class ErrorTemplate {

    public static List<String> errorData = new ArrayList<>();

    public void updateDatoteka(Boolean tip, String error, int i) {
        procitajError(tip, error, i);
        dodajError(error, i);
        zapisiDatoteku();
    }

    abstract void procitajError(Boolean tip, String er, int i);

    abstract void dodajError(String error, int i);

    public void zapisiDatoteku() {
        PrintWriter pw;
        try {
            pw = new PrintWriter("error.txt");
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ErrorTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintWriter out;

        try {

            out = new PrintWriter(new BufferedWriter(new FileWriter("error.txt", true)));
            List<String> ocisti = Helper.removeDuplicates(errorData);
            for (String s : ocisti) {
                out.println(s);
            }
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(ErrorTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
