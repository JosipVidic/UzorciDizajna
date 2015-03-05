package org.foi.uzdiz.jovidic.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import org.foi.uzdiz.jovidic.composite.ComponentKorisnici;
import org.foi.uzdiz.jovidic.iterator.ConcretePodaci;
import static org.foi.uzdiz.jovidic.iterator.ConcretePodaci.akcijaLista;
import org.foi.uzdiz.jovidic.template.DuplicateError;

/**
 *
 * @author jovidic
 *
 * Pomoćna klasa koja sadrži metode za čitanje datoteke, brisanje i pregled
 * akcija, kao i pretraživanja duplikata u zapisu.
 */
public class Helper {

    public static List<String> data = new ArrayList<String>();

    /**
     * Metoda koja cita datoteku s primljane putanje i vraća listu zapisa
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<String> readFile(String fileName) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        int brojacLinija = 0;
        while ((line = br.readLine()) != null) {
            brojacLinija++;
            if (line == null) {
                break;
            }
            if (data.contains(line)) {

                DuplicateError error = new DuplicateError();
                error.updateDatoteka(true, line, brojacLinija);
            } else {
                data.add(line);
            }

        }
        br.close();
        return data;
    }

    /**
     * Metoda koja uklanja duplikate iz liste stringova.
     *
     * @param strings
     * @return
     */
    public static List<String> removeDuplicates(List<String> strings) {

        List<String> novo = new ArrayList<>();
        Set set = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        set.addAll(strings);

        novo.addAll(set);

        return novo;

    }

    /**
     * Metoda koja uklanja dupliate iz liste objekta korisnici.
     *
     * @param list
     * @return
     */
    public static List<ComponentKorisnici> removeDuplicatesList(List<ComponentKorisnici> list) {

        List<ComponentKorisnici> result = new ArrayList<>();

        HashSet<ComponentKorisnici> set = new HashSet<>();

        for (ComponentKorisnici item : list) {

            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    /**
     * Metoda koja služi za čitanje zapisa odvojeno tabulatorom
     *
     * @param zapis
     * @return
     */
    public static List<String> delimitString(String zapis) {
        List<String> rezultat = new ArrayList<>();
        String[] split = zapis.split("\t");

        for (String s : split) {
            rezultat.add(s);
        }

        return rezultat;
    }

    /**
     * Metoda koja služi za čitanje zapisa odvojeno tabulatorom koristeći
     * tokenizer.
     *
     * @param zapis
     * @return
     */
    public static List<String> delimitTokenizer(String zapis) {

        List<String> rezultat = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(zapis, "\t");
        while (st.hasMoreTokens()) {
            rezultat.add(st.nextToken());
        }
        return rezultat;
    }

    public static void pregledAkcija(String unos) {
        List<String> zapisSplit = delimitTokenizer(unos);
        String sifra = null;
        int tip = 0;
        int vrsta = 0;
        int brojac = 1;
        for (String s : zapisSplit) {
            switch (brojac) {
                case 0:
                    break;
                case 1:
                    sifra = s;
                    break;
                case 2:
                    tip = Integer.parseInt(s);
                    break;
                case 3:
                    vrsta = Integer.parseInt(s);
                    break;

            }
            brojac++;
        }
        System.out.println("DJECA KORISNIKA:");
        for (ComponentKorisnici k : ConcretePodaci.korisnici) {

            if (k.getSifra().equals(sifra)) {

                k.print(0);

            }

        }
        
        System.out.println("RODITELJI KORISNIKA:");
        for (ComponentKorisnici k : ConcretePodaci.korisnici) {

            if (k.getSifra().equals(sifra)) {

                k.print2(0);

            }

        }

        for (Akcija akcija : ConcretePodaci.akcijaLista) {

            if (akcija.getNaziv().equals(sifra) && akcija.getVrsta() == vrsta && akcija.getTip() == tip) {
                System.out.println("Korisnik ima pravo pregledati akciju " + akcija.getSifra());
                for (ComponentKorisnici u : ConcretePodaci.korisnici) {
                    if (u.getSifra().equals(sifra) && u.getAdmin() == 1) {
                        System.out.println("Korisnik je administrator i ima pravo na brisanje akcije");
                    }

                }
            }

        }

    }



    /**
     * Metoda koja služi za brisanje akcija
     *
     * @param unos
     */
    public static void brisanjeAkcija(String unos) {

        List<String> zapisSplit = delimitTokenizer(unos);

        String sifraAkcije = null;
        String sifraKorisnika = null;

        int brojac = 1;
        for (String s : zapisSplit) {
            switch (brojac) {
                case 0:
                    break;
                case 1:
                    sifraKorisnika = s;
                    break;
                case 2:
                    sifraAkcije = s;
                    break;

            }
            brojac++;
        }

        List<Akcija> toRemove = new ArrayList<>();

        List<String> pravoBrisanja = new ArrayList<>();

        pravoBrisanja.add(sifraKorisnika);//lista koja sadrži korisnike koji imaju pravo brisanja
        System.out.println("PRAVO BRISANJA IMAJU ");
        for (String s : pravoBrisanja) {
            System.out.println("Korisnik  " + s);

        }

        //System.out.println("sifra akcije je " + sifraAkcije + "  a sifra korisnika je  " + sifraKorisnika);
        for (Akcija a : ConcretePodaci.akcijaLista) {
            if (a.getSifra().equals(sifraAkcije)) {

                if (a.getNaziv().equals(sifraKorisnika)) {

                    System.out.println("BRISANJE OSOBNE AKCIJE SA ŠIFROM " + sifraAkcije);

                    //akcijaLista.remove(a);
                    toRemove.add(a);
                }

            }

        }
        if (toRemove.isEmpty() == true) {
            System.out.println("Ne postoji akcija s unesenom šifrom");

        }
        akcijaLista.removeAll(toRemove);
        toRemove.clear();
    }

    /**
     * Metoda koja služi za dodavanje akcija.
     *
     * @param zapis
     */
    public static void dodajAkciju(String zapis) {
        boolean postoji = false;
        boolean postoji2 = false;

        List<String> zapisSplit = Helper.delimitString(zapis);
        String sifraA = null;
        String nazivA = null;
        int vrstaA = 0;
        int tipA = 0;
        int brojac = 1;

        for (String s : zapisSplit) {
            switch (brojac) {
                case 0:
                    break;
                case 1:
                    sifraA = s;
                    break;
                case 2:
                    nazivA = s;
                    break;
                case 3:
                    vrstaA = Integer.parseInt(s);
                    break;

                case 4:
                    tipA = Integer.parseInt(s);
                    break;

            }

            brojac++;

        }

        for (ComponentKorisnici k : ConcretePodaci.korisnici) {
            if (k.getSifra().equals(nazivA)) {
                postoji = true;
            }

        }
        for (Objekti o : ConcretePodaci.objektiDefiniranje) {
            if (o.getSifra().equals(sifraA)) {
                postoji2 = true;

            }

        }
        //System.out.println("POSTOJI " + postoji + "POSTOJI2 " + postoji2);

        if (postoji) {

            if (postoji2) {

                Akcija akcija = new Akcija(sifraA, nazivA, tipA, vrstaA);
                System.out.println("Kreirana je akcija:  " + sifraA + " i napravio ju je " + nazivA + " s tipom " + tipA + " i vrstom " + vrstaA);
                akcijaLista.add(akcija);

            } else {
                System.out.println("Ne postoji objekt s unesenom sifrom");
            }

        } else {
            System.out.println("Ne postoji korisnik/grupa s unesenom sifrom");
        }

    }

}
