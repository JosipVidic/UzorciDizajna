package org.foi.uzdiz.jovidic.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.jovidic.main.Main_Class;
import static org.foi.uzdiz.jovidic.main.Main_Class.kontroler;
import static org.foi.uzdiz.jovidic.mvc.Controller.maxKolPod;

/**
 *
 * @author jovidic
 *
 * Klasa koja sudjeluje u ispisu podataka korisniku
 */
public class View {

    /**
     * Metoda preuzima path od korisnika
     */
    public void uzmiPath() {

        String putanja = "";

        while (!putanja.equals("Q")) {

            BufferedReader brSet = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Unesite putanju do datoteke (Q za izbornik): ");
            try {
                if (putanja == "Q") {
                    return;
                } else {

                    putanja = brSet.readLine();

                    Main_Class.kontroler.citajNBajtova(putanja, maxKolPod);

                }

            } catch (IOException ex) {

                System.out.println("Ne postoji putanja");
            }

        }

    }

    /**
     * Metoda koja ispisuje menu te čeka na korisnikov unos
     *
     * @param opcija
     * @param br
     * @return
     */
    public String ispisiMenu(String opcija, BufferedReader br) {
        System.out.println("|===================================================================================================================================|");
        System.out.println("|                                                         MENU                                                                      |");
        System.out.println("|===================================================================================================================================|");
        System.out.println("| Opcije:                                                                                                                           |");
        System.out.println("|        1. Ucitaj datoteku                                                                                                         |");
        System.out.println("|        2. Popis datoteka u serijaliziranoj datoteci ListaSpremiste                                                                |");
        System.out.println("|        3. Očisti spremište i isprazni serijaliziranu datoteku ListaSpremiste                                                      |");
        System.out.println("|        4. Obriši N KB ili elemenata (ovisno o argumentima)                                                                        |");
        System.out.println("|        Q. Izlaz                                                                                                                   |");
        System.out.println("|===================================================================================================================================|");
        System.out.println("|   Odaberite opciju :                                                                                                              |");
        System.out.println("|===================================================================================================================================|");

        try {
            opcija = br.readLine();
        } catch (IOException ex) {
            System.err.println("Molimo unesite u rasponu 1-9 ili Q za izlaz iz programa.");
        }
        switch (opcija) {
            case "1":
                System.out.println("|===================================================================================================================================|");
                uzmiPath();
                System.out.println("|===================================================================================================================================|");
                break;
            case "2":
                System.out.println("|===================================================================================================================================|");
                System.out.println("TRENUTNO DATOTEKA U MEMORIJI: ");
                ispisiListuIzMemorije();
                System.out.println("|===================================================================================================================================|");
                break;
            case "3":
                System.out.println("|===================================================================================================================================|");
                kontroler.memorija.isprazniNaZahtjev();
                System.out.println("Spremište pobrisano");
                System.out.println("|===================================================================================================================================|");
                break;
            case "4":
                System.out.println("|===================================================================================================================================|");
                BufferedReader brSet = new BufferedReader(new InputStreamReader(System.in));
                double opcijaDelete = 0;
                if (kontroler.kB) {
                    System.out.println("Molimo unesite velicinu u KB koju želite pobrisati (za brisanje po elementima pokrenite program bez KB argumenta) )");
                    try {
                        opcijaDelete = Double.parseDouble(brSet.readLine());
                    } catch (IOException ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Molimo unesite velicinu u BROJU ELEMENATA kojih želite pobrisati (za brisanje po elementima pokrenite program sa KB argumentom) )");
                    try {
                        opcijaDelete = Double.parseDouble(brSet.readLine());
                    } catch (IOException ex) {
                        System.out.println("Molimo unesite veličinu u pravilnom formatu");
                    }
                }

                kontroler.isprazniN(opcijaDelete);
                System.out.println("|===================================================================================================================================|");

                break;

            case "Q":
                System.out.println("---------------------------------------------------------------");
                kontroler.ugasiDretvu();
                System.out.println("Izasli ste iz programa");
                System.out.println("---------------------------------------------------------------");
                return opcija;

        }
        return opcija;

    }

    /**
     * Metoda koja ispisuje mogucnosti za unos u slucaju pogreske
     */
    public void ispisiMogucnosti() {
        System.out.println("|===================================================================================================================================|");

        System.out.println("Pogrešno ste unijeli argumente");

        System.out.println("Mogućnost NS ima CLEAN BR");
        System.out.println("1000 20 D:\\uzDiz_WORKSPACE\\jovidic_zadaca_4\\spremiste 1000 NS dnevnik.txt CLEAN");
        System.out.println("Mogućnost NS nema CLEAN BR");
        System.out.println("1000 20 D:\\uzDiz_WORKSPACE\\jovidic_zadaca_4\\spremiste 1000 NS dnevnik.txt");
        System.out.println("Mogućnost NS ima CLEAN KB");
        System.out.println("1000 20 D:\\uzDiz_WORKSPACE\\jovidic_zadaca_4\\spremiste 1000 KB NS dnevnik.txt CLEAN");
        System.out.println("Mogućnost NS nema CLEAN KB");
        System.out.println("1000 20 D:\\uzDiz_WORKSPACE\\jovidic_zadaca_4\\spremiste 1000 KB NS dnevnik.txt");
        System.out.println("Mogućnost NK ima CLEAN BR");
        System.out.println("1000 20 D:\\uzDiz_WORKSPACE\\jovidic_zadaca_4\\spremiste 1000 NK dnevnik.txt CLEAN");
        System.out.println("Mogućnost NK nema CLEAN BR");
        System.out.println("1000 20 D:\\uzDiz_WORKSPACE\\jovidic_zadaca_4\\spremiste 1000 NK dnevnik.txt");
        System.out.println("Mogućnost NK ima CLEAN KB");
        System.out.println("1000 20 D:\\uzDiz_WORKSPACE\\jovidic_zadaca_4\\spremiste 1000 KB NK dnevnik.txt CLEAN");
        System.out.println("Mogućnost NK nema CLEAN KB");
        System.out.println("1000 20 D:\\uzDiz_WORKSPACE\\jovidic_zadaca_4\\spremiste 1000 KB NK dnevnik.txt");

        System.out.println("|===================================================================================================================================|");

    }

    /**
     * Metoda koja ispisuje listu iz memorije
     */
    public void ispisiListuIzMemorije() {

        File f = new File("ListaSpremiste");
        if (f.exists()) {
            kontroler.memorija.deserijalizirajListu();

            for (ResourceModel r : kontroler.memorija.listaZaSpremiste) {
                System.out.println("Naziv - " + r.getNaziv() + "         Broj koristenja - " + r.getBrojKoristenja() + "         Vrijeme spremanja - " + r.getVrijemeSpremanja() + "         Vrijeme zadnjeg korištenja - " + r.getZadnjeKoristenje() + "           Velicina - " + r.getVelicinaIzvornika());

            }
            kontroler.memorija.serijalizirajListu();

        } else {
            kontroler.memorija.popuniListu();

            kontroler.memorija.deserijalizirajListu();
            for (ResourceModel r : kontroler.memorija.listaZaSpremiste) {
                System.out.println("Naziv - " + r.getNaziv() + "         Broj koristenja - " + r.getBrojKoristenja() + "         Vrijeme spremanja - " + r.getVrijemeSpremanja() + "         Vrijeme zadnjeg korištenja - " + r.getZadnjeKoristenje() + "           Velicina - " + r.getVelicinaIzvornika());

            }
            kontroler.memorija.serijalizirajListu();
        }

    }

    /**
     * Metoda koja javlja da postoji element u memoriji
     *
     * @param n
     */
    public void javiDaPostoji(String n) {
        System.out.println("Datoteka " + n + " već postoji u memoriji, ažuriram broj korištenja i vrijeme zadnjeg korištenja");
    }

    /**
     * Metoda koja ispisuje N pročitanih znakova iz datoteke
     *
     * @param n
     * @param path
     * @param firstN
     */
    void ispisiProcitano(int n, String path, byte[] firstN) {
        System.out.println("|===================================================================================================================================|");

        System.out.println("PROCITAO SAM PRVIH  " + n + " BAJTOVA DATOTEKE " + path);
        System.out.println("--------------------------------------------------------");
        System.out.println(Charset.defaultCharset().decode(ByteBuffer.wrap(firstN)));
        System.out.println("--------------------------------------------------------");
        System.out.println("|===================================================================================================================================|");

    }

    /**
     * Metoda koja ispisuje da nema mjesta u spremištu ovisno o strategiji
     */
    public void ispisiNemaMjesta() {
        if (Controller.strategija) {
            System.out.println("|===================================================================================================================================|");

            System.out.println("Nema mjesta u spremištu, brišem elemente po strategiji NS (najstariji element)");
            System.out.println("|===================================================================================================================================|");

        } else {
            System.out.println("|===================================================================================================================================|");

            System.out.println("Nema mjesta u spremištu, brišem elemente po strategiji NK (najkorišteniji element)");
            System.out.println("|===================================================================================================================================|");

        }
    }

}
