
package org.foi.uzdiz.jovidic.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.jovidic.main.MainClass;
import static org.foi.uzdiz.jovidic.main.MainClass.kontroler;
import static org.foi.uzdiz.jovidic.mvc.ControllerIO.brojacCiklusa;
import static org.foi.uzdiz.jovidic.mvc.ControllerIO.originator;
import org.foi.uzdiz.jovidic.mvc.composite.model.ComponentInterface;

/**
 *
 * @author jovidic
 *
 * Klasa koja ispisuje korisniku menu te ostale rezultate ovisno o korisnikovom
 * unosu kojeg prima.
 */
public class ViewIO {

    public int opcijaSet;
    public int opcijaSet1;
    public int opcijaSet2;
    public int trenutnoStanjeRBR;

    /**
     * Metoda koja ispisuje stablo počevši od određenog izvorišta.
     *
     * @param root
     */
    public void ispisiStablo(ComponentInterface root) {

        System.out.println("Ispis stabla po razinama kreće od direktorija : " + root.getName());
        System.out.println("=====================================================================================================================================");
        System.out.println("");
        root.print(1);
        System.out.println("");
        System.out.println("=====================================================================================================================================");

    }

    /**
     * Metoda koja ispisuje menu.
     *
     * @param opcija
     * @param br
     * @return
     */
    public String ispisiMenu(String opcija, BufferedReader br) {
        trenutnoStanjeRBR = originator.getState().getBrojCiklusa();
        System.out.println("Trenutno stanje je stanje s rednim brojem : " + trenutnoStanjeRBR);
        System.out.println("|===================================================================================================================================|");
        System.out.println("|                                                         MENU                                                                      |");
        System.out.println("|===================================================================================================================================|");
        System.out.println("| Opcije:                                                                                                                           |");
        System.out.println("|        1. Ispis ukupnog broja direktorija i datoteka u trenutne strukture                                                         |");
        System.out.println("|        2. Ispis sadržaja strukture direktorija i datoteka trenutne strukture                                                      |");
        System.out.println("|        3. Izvršavanje dretve za dohvat trenutnog stanja                                                                           |");
        System.out.println("|        4. Prekid izvršavanja dretve                                                                                               |");
        System.out.println("|        5. Ispis rednog broja i vremena spremljenih promjena stanja strukture                                                      |");
        System.out.println("|        6. n - postavljanje stanja strukture na promjenu broj n čime ono postaje novo trenutno stanje strukture                    |");
        System.out.println("|        7. n m - uspoređivanje stanja strukture promjena broj n u odnosu na promjenu broj m                                        |");
        System.out.println("|        8. Postavljanje trenutnog stanja strukture kao početno stanje i odbacivanje svih prethodnih stanja                         |");
        System.out.println("|        9. Ispis promjena u dodatnu datoteku                                                                                       |");
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

                System.out.println("---------------------------------------------------------------");
                kontroler.infoStanja();
                System.out.println("---------------------------------------------------------------");

                break;
            case "2":
                ComponentInterface u = originator.getState().getRootComposita();
                System.out.println("Ispisujem stanje s rednim brojem : " + originator.getState().getBrojCiklusa());
                ispisiStablo(u);
                break;
            case "3":
                System.out.println("---------------------------------------------------------------");
                MainClass.kontroler.pokreniDretvu();
                System.out.println("Kreiram dretvu, započinjem dodatno učitavanje");
                System.out.println("---------------------------------------------------------------");
                break;
            case "4":
                System.out.println("---------------------------------------------------------------");
                MainClass.kontroler.ugasiDretvu();
                System.out.println("Prekinuto izvršavanje dretve");
                System.out.println("---------------------------------------------------------------");
                break;
            case "5":
                System.out.println("---------------------------------------------------------------");
                kontroler.svaStanja();
                System.out.println("---------------------------------------------------------------");
                break;
            case "6":

                BufferedReader brSet = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Unesite redni broj stanja: ");
                 {
                    try {
                        opcijaSet = Integer.parseInt(brSet.readLine());

                        if (opcijaSet <= brojacCiklusa) {

                            kontroler.postaviTrenutnoN(opcijaSet);

                            System.out.println("Stanje s rednim brojem ciklusa :" + opcijaSet + " postavljeno je za trenutno stanje");

                        } else {
                            System.out.println("Ne postoji stanje s rednim brojem cilkusa :" + opcijaSet);
                        }

                    } catch (IOException ex) {
                        System.out.println("Krivo uneseno stanje");
                    }
                }
                break;
            case "7":
                System.out.println("Unesite redni broj stanja N: ");
                BufferedReader brSet1 = new BufferedReader(new InputStreamReader(System.in));
                 {
                    try {
                        opcijaSet1 = Integer.parseInt(brSet1.readLine());
                    } catch (IOException ex) {
                        Logger.getLogger(ViewIO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Unesite redni broj stanja M: ");
                BufferedReader brSet2 = new BufferedReader(new InputStreamReader(System.in));
                 {
                    try {
                        opcijaSet2 = Integer.parseInt(brSet2.readLine());
                    } catch (IOException ex) {
                        Logger.getLogger(ViewIO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (opcijaSet1 == opcijaSet2) {
                    System.out.println("Nema promjena");

                } else {

                    kontroler.usporediSpremljenaStanja(opcijaSet1, opcijaSet2);
                }

                break;
            case "8":
                System.out.println("---------------------------------------------------------------");
                int staro = kontroler.postaviTrenutnoOdbaciOstalo();
                System.out.println("Obrisana su sva stanja osim trenutnog s STARIM brojem ciklusa : " + staro);
                kontroler.infoStanja();
                System.out.println("---------------------------------------------------------------");
                break;

            case "9":
                BufferedReader brSet3 = new BufferedReader(new InputStreamReader(System.in));
                String dat = null;
                System.out.println("Unesite naziv datoteke za unos podataka(bez ekstenzije)");

                 {
                    try {
                        dat = brSet3.readLine();

                    } catch (IOException ex) {
                        Logger.getLogger(ViewIO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dat != null) {

                    kontroler.zapisiUDatoteku(dat);

                }else{
                    System.out.println("Niste unijeli datoteku");
                
                }
                break;
            case "Q":
                MainClass.kontroler.thread = false;

                System.out.println("---------------------------------------------------------------");
                //MainClass.kontroler.thrd.interrupt();
                System.out.println("Izasli ste iz programa");
                System.out.println("---------------------------------------------------------------");
                return opcija;

        }
        return opcija;

    }
    /*
     public void ispisiProcitanePodatke() {
     for (ComponentInterface i : MainClass.kontroler.procitaniPodaci) {
     if (i.equals(MainClass.kontroler.root)) {
     i.print(0);
     }

     }

     }*/

}
