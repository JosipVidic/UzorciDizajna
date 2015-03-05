package org.foi.uzdiz.jovidic.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.jovidic.composite.ComponentKorisnici;
import org.foi.uzdiz.jovidic.iterator.Iterator;
import org.foi.uzdiz.jovidic.iterator.ConcretePodaci;
import static org.foi.uzdiz.jovidic.iterator.ConcretePodaci.korisnici;

/**
 *
 * @author jovidic
 *
 * Main klasa koja sadrži menu, te poziva iterator koji ovisno o broju zapisa
 * kreira pripadajuće objekte.
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static String datoteka;
    public static List<String> pocetnaLista = new ArrayList<>();
    public static ComponentKorisnici ishodisnaGrupa;

    public static void main(String[] args) {

        //ucitavanje pocetnog parametra naziva datoteke
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcija = 0;
        String unos;
        String sintaksa = "(.*\\.txt$)";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }

        String p = sb.toString().trim();
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher m = pattern.matcher(p);
        boolean status = m.matches();
        if (status) {
            if (m.group(1) != null) {
                datoteka = m.group(1);
                System.out.println("NAZIV DATOTEKE: " + datoteka);
                iteriraj(datoteka);
                
                System.out.println("ISPIS STABLA");
                System.out.println("*******************************************************");
                ishodisnaGrupa.print(0);
                System.out.println("*******************************************************");
            }
        } else {
            System.out.println("Neispravan unos datoteke");
        }
        //menu
        while (opcija != 5 || opcija == 0) {

            System.out.println("============================================");
            System.out.println("|                MENU                      |");
            System.out.println("============================================");
            System.out.println("| Opcije:                                  |");
            System.out.println("|        1. Pregled stanja                 |");
            System.out.println("|        2. Unos dodatne akcije            |");
            System.out.println("|        3. Brisanje postojeće akcije      |");
            System.out.println("|        4. Ucitaj dodatnu datoteku        |");
            System.out.println("|        5. Izlaz                          |");
            System.out.println("============================================");
            System.out.println(" Odaberite opciju : ");
            try {
                opcija = Integer.parseInt(br.readLine());
            } catch (IOException ex) {
                System.err.println("Molimo unesite u rasponu 1-4.");
            }
            switch (opcija) {
                case 1:
                    //pregled stanja
                    System.out.println("Unesite sifru, tip akcije i vrstu akcije s tabulatorom");
                    System.out.println("NPR: 00001	0	1");
                    System.out.println("NPR: 00005	2	2");
                    System.out.println("NPR: 00007	2	2");
                     {
                        try {
                            unos = br.readLine();
                            System.out.println("********************************************************************************");
                            System.out.println("NOVO");
                            Helper.pregledAkcija(unos);
                            System.out.println("********************************************************************************");
                        } catch (IOException ex) {
                            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case 2:
                    //unos akcije
                    System.out.println("Za unos akcije unesite sifru akcije, sifru korisnika, tip i vrstu akcije odvojeno tabulatorom");
                    System.out.println("NPR: 00100  00001   0   1");

                     {
                        try {
                            unos = br.readLine();
                            //System.out.println("UNOS : " + unos);
                            System.out.println("********************************************************************************");
                            Helper.dodajAkciju(unos);
                            System.out.println("********************************************************************************");
                        } catch (IOException ex) {
                            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case 3:
                    //brisanje akcije
                    System.out.println("Za brisanje akcije unesite šifru akcije i šifru korisnika/grupe");
                    System.out.println("NPR: 00001  00100");
                    System.out.println("NPR: 00005  00104");
                    System.out.println("NPR: 00007  00105");
                     {
                        try {
                            unos = br.readLine();
                            //System.out.println("UNOS : " + unos);
                            System.out.println("********************************************************************************");
                            Helper.brisanjeAkcija(unos);
                            System.out.println("********************************************************************************");
                        } catch (IOException ex) {
                            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;
                case 4:
                    //dodatna datoteka
                    System.out.println("Unesite dodatnu datoteku za unos , NPR: dodatna.txt");

                    try {
                        String unos2 = br.readLine();
                        List<String> procitano = Helper.readFile(unos2);
                        int brojacln = 0;

                        System.out.println("********************************************************************************");
                        System.out.println("Dodatna datoteka " + unos2 + " je dodana u postojeću strukturu");
                        iteriraj(datoteka);
                        System.out.println("********************************************************************************");

                    } catch (IOException ex) {
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case 5:
                    //izlaz iz programa
                    System.out.println("Izasli ste iz programa");
                    break;

            }
        }
    }

    /**
     * Metoda koja prima za parametar sve podatkeu datoteci te koristi vanjski
     * iterator za prolaz po zapisima.
     *
     * @param dat
     */
    private static void iteriraj(String dat) {
        int brojac = 0;

        try {

            pocetnaLista = Helper.readFile(dat);

            ConcretePodaci concretePodaci = new ConcretePodaci();

            for (Iterator iter = concretePodaci.getIterator(); iter.hasNext();) {

                brojac++;
                String trenutniRed = (String) iter.next();

                iter.sortiraj(trenutniRed);

            }
            
            concretePodaci.getIterator().nadiIshodisnuGrupu();

            Helper.data.clear();
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
