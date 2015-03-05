package org.foi.uzdiz.jovidic.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.jovidic.singleton.ContestSingleton;

/**
 * Main klasa projekta koja provjerava unesenu komandu te sprema argumente te
 * pokrece klasu Command koja koristi argumente za određene akcije.
 *
 * @author jovidic
 */
public class MainClass {

    public static int maxUtrka;
    public static int brojSudionika;
    public static int pauza;
    public static String datoteka;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        maxUtrka = 0;
        brojSudionika = 0;
        pauza = 0;
        datoteka = null;

        String sintaksa = "^([A-Za-z0-9_-]+) ([0-9]*) ([0-9]*) ([0-1]) ([A-Za-z0-9_-]+)$";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        String p = sb.toString().trim();
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher m = pattern.matcher(p);
        boolean status = m.matches();
        int brojac = 0;
        if (status) {
            System.out.println("UNESENI PARAMETRI: ");
            if (m.group(2) != null) {
                maxUtrka = Integer.parseInt(m.group(2));
                System.out.println("MAX UTRKA: " + maxUtrka);
                brojac++;
            }
            if (m.group(3) != null) {
                brojSudionika = Integer.parseInt(m.group(3));
                System.out.println("BROJ SUDIONIKA PO SKUPINI: " + brojSudionika);
                brojac++;
            }
            if (m.group(4) != null) {
                pauza = Integer.parseInt(m.group(4));
                System.out.println("PAUZA: " + pauza);
                brojac++;
            }
            if (m.group(5) != null) {
                datoteka = m.group(5);
                System.out.println("DATOTEKA: " + datoteka + ".txt");
                brojac++;

            } else {
                System.out.println("Komanda je neispravna!");
            }

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\n");
        }
        if (brojac == 4) {

            ContestSingleton natjecanje = ContestSingleton.getInstance();
            natjecanje.generirajBrUtrka(maxUtrka);
        } else {
            System.out.println("Komanda je neispravna!");
        }

    }
}
