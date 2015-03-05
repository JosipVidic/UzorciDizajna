/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.singleton;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.jovidic.factorymethod.RezultatFactory;
import org.foi.uzdiz.jovidic.main.MainClass;

/**
 * Klasa koja sluzi za upis podataka u datoteku
 *
 * @author jovidic
 */
public class DatotekaSingleton {

    String dat = MainClass.datoteka;

    private DatotekaSingleton() {
    }

    public static DatotekaSingleton getInstance() {
        return DatotekaUpisHolder.INSTANCE;
    }

    private static class DatotekaUpisHolder {

        private static final DatotekaSingleton INSTANCE = new DatotekaSingleton();
    }

    List<List<RezultatFactory>> listaSvihLista = new ArrayList<>();
    List<RezultatFactory> lista = new ArrayList<>();

    public boolean prolaz = false;
    public String spol;

    public void kreirajTxt(List<List<RezultatFactory>> lista, List<RezultatFactory> lista2) {

        this.listaSvihLista = lista;
        this.lista = lista2;
        PrintWriter writer = null;
        String path = "output/" + dat + ".txt";
        try {
            writer = new PrintWriter(path, "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatotekaSingleton.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DatotekaSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.println("Tablica bodova po skupinama i utrkama");
        writer.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        upisiPoSkupinama(writer);
        writer.println("\n");

        writer.println("Tablica bodova po utrkama");
        writer.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        upisiPoUtrkama(writer);
        writer.println("\n");
        writer.close();

        System.out.println("Kreirao sam datoteku ");

    }

    private void upisiPoUtrkama(PrintWriter writer) {
        for (int i = 0; i <= ContestSingleton.getInstance().R; i++) {
            SystemOutSingleton.getInstance().sortirajPoBodovima(lista);
            SystemOutSingleton.getInstance().disqualify(lista);
            for (RezultatFactory n : lista) {

                if (n.getIdUtrke() == i) {
                    if (prolaz == false) {
                        if (n.isSpol() == true) {
                            spol = "Zene";
                        } else {
                            spol = "Muski";
                        }
                        writer.println("ISPIS REZULTATA ZA UTRKU: " + n.getIdUtrke());
                        writer.println("Bodovi ----- Redni broj ----- ID ----- Skupina ----- Vrijeme");
                        writer.println("_____________________________________________________________");
                        prolaz = true;
                    }

                    writer.println(n.getBodovi() + "--------------" + n.getRedniBroj() + "-------------" + n.getIdNatjecatelja() + "------------" + n.getSkupina() + "----------" + n.getVrijeme());

                }
            }
            prolaz = false;

        }
    }

    private void upisiPoSkupinama(PrintWriter writer) {
        for (int i = 0; i <= ContestSingleton.getInstance().R; i++) {

            for (List<RezultatFactory> r : listaSvihLista) {

                SystemOutSingleton.getInstance().sortirajPoBodovima(r);
                SystemOutSingleton.getInstance().disqualify(r);

                for (RezultatFactory n : r) {
                    if (n.getIdUtrke() == i) {
                        if (prolaz == false) {
                            if (n.isSpol() == true) {
                                spol = "Zene";
                            } else {
                                spol = "Muski";
                            }
                            writer.println("ISPIS REZULTATA ZA SKUPINU: " + n.getSkupina() + " " + spol + " za utrku " + n.getIdUtrke());
                            writer.println("Bodovi ----- Redni broj ----- ID ----- Skupina ----- Vrijeme");
                            writer.println("_____________________________________________________________");
                            prolaz = true;
                        }

                        writer.println(n.getBodovi() + "--------------" + n.getRedniBroj() + "-------------" + n.getIdNatjecatelja() + "------------" + n.getSkupina() + "----------" + n.getVrijeme());

                    }
                }
                prolaz = false;
            }

        }

    }
}
