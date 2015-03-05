package org.foi.uzdiz.jovidic.iterator;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.jovidic.composite.ComponentKorisnici;
import org.foi.uzdiz.jovidic.composite.CompositeGrupa;
import org.foi.uzdiz.jovidic.main.Akcija;
import org.foi.uzdiz.jovidic.composite.LeafClan;
import org.foi.uzdiz.jovidic.main.Objekti;
import org.foi.uzdiz.jovidic.main.Helper;
import org.foi.uzdiz.jovidic.main.MainClass;
import static org.foi.uzdiz.jovidic.main.MainClass.pocetnaLista;
import org.foi.uzdiz.jovidic.template.StructureError;
import org.foi.uzdiz.jovidic.template.TypeError;

/**
 *
 * @author jovidic
 *
 * Konkretna implementacija iteratora koja služi za sortiranje i kreiranje
 * objekta iz prosljeđenog zapisa.
 */
public class ConcretePodaci implements Container {

    public List<String> zapisSplit = new ArrayList<>();
    public static List<ComponentKorisnici> clanoviDefiniranje = new ArrayList<>();
    public static List<ComponentKorisnici> grupeDefiniranje = new ArrayList<>();
    public static List<Objekti> objektiDefiniranje = new ArrayList<>();

    public static List<ComponentKorisnici> korisnici = new ArrayList<>();
    public static List<Akcija> akcijaLista = new ArrayList<>();

    @Override
    public Iterator getIterator() {
        return new ProvjeraIterator();
    }

    private class ProvjeraIterator implements Iterator {

        int index;
        Boolean postoji = false;
        Boolean postoji2 = false;

        @Override
        public boolean hasNext() {
            if (index < pocetnaLista.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {

                return pocetnaLista.get(index++);
            }
            return null;
        }

        @Override
        public String currentItem() {

            return pocetnaLista.get(index);
        }

        /**
         * Metoda koja ovisno o vrsti zapisa (definiranje struktura ili akcija)
         * pokreće metode za kreiranje objekta
         *
         * @param zapis
         */
        @Override
        public void sortiraj(String zapis) {
            int tip = 0;

            if (zapis.startsWith("0")) {
                tip = 1;
            } else if (zapis.startsWith("1")) {
                tip = 2;
            } else if (zapis.startsWith("2")) {
                tip = 3;
            } else {

                TypeError error = new TypeError();
                error.updateDatoteka(true, zapis, index);

            }
            switch (tip) {
                case 1:
                    //Kreirane definicije
                    if (zapis.endsWith("0")) {
                        kreirajDefiniciju(zapis, 0);
                    } else if (zapis.endsWith("1")) {
                        kreirajDefiniciju(zapis, 1);
                    } else if (zapis.endsWith("2")) {
                        kreirajDefiniciju(zapis, 2);
                    }
                    break;

                case 2:

                    kreirajStrukturu(zapis);

                    break;

                case 3:

                    kreirajAkcije(zapis);
                    break;
            }

        }

        /**
         * Metoda koja ovisno o unosu kreira grupu ili korisnika iz zapisa.
         *
         * @param zapis
         * @param tip
         */
        public void kreirajDefiniciju(String zapis, int tip) {

            zapisSplit = Helper.delimitString(zapis);
            String sifra = null;
            String naziv = null;
            int vrsta = 0;
            int brojac = 0;

            for (String s : zapisSplit) {
                switch (brojac) {
                    case 0:
                        break;
                    case 1:
                        sifra = s;
                        break;
                    case 2:
                        naziv = s;
                        break;
                    case 3:
                        vrsta = Integer.parseInt(s);
                        break;

                }

                brojac++;

            }

            if (tip == 0) {

                ComponentKorisnici korisnik = new LeafClan(sifra, naziv, vrsta, null, 0);

                korisnici.add(korisnik);

            } else if (tip == 1) {
                ComponentKorisnici korisnik = new CompositeGrupa(sifra, naziv, vrsta, null);

                korisnici.add(korisnik);

            } else if (tip == 2) {
                Objekti korisnik = new Objekti(sifra, naziv, vrsta);
                objektiDefiniranje.add(korisnik);
                //System.out.println("KREIRAO SAM OBJEKT" + korisnik.getNaziv());
            } else {

            }

        }

        /**
         * Metoda koja unaprijed kreiranim objektima dodaje roditelje i djecu.
         *
         * @param zapis
         */
        private void kreirajStrukturu(String zapis) {
            postoji = false;
            postoji2 = false;
            zapisSplit = Helper.delimitString(zapis);
            String sifra = null;
            String roditelj = null;
            int uloga = 0;
            int brojac = 0;

            for (String s : zapisSplit) {
                switch (brojac) {
                    case 0:
                        break;
                    case 1:
                        sifra = s;
                        break;
                    case 2:
                        roditelj = s;
                        break;
                    case 3:
                        uloga = Integer.parseInt(s);
                        break;

                }

                brojac++;

            }

            for (ComponentKorisnici k : korisnici) {
                if (k.getSifra().equals(sifra)) {
                    //System.out.println("k naziv " + k.getNaziv());
                    postoji = true;
                }

            }

            for (ComponentKorisnici m : korisnici) {
                if (m.getSifra().equals(roditelj)) {
                    //System.out.println("m naziv " + m.getNaziv());
                    postoji2 = true;
                }

            }

            if (postoji) {
                if (postoji2) {
                    for (ComponentKorisnici s : korisnici) {

                        if (s.getSifra().equals(sifra)) {

                            for (ComponentKorisnici l : korisnici) {
                                if (l.getSifra().equals(roditelj)) {

                                    s.addParent(l);

                                    l.addChild(s);

                                }

                            }
                            s.setAdmin(uloga);
                        }

                    }

                }
            } else {

                StructureError erro = new StructureError();
                erro.updateDatoteka(true, zapis, index);

            }

            Helper.removeDuplicatesList(korisnici);
            postoji = postoji2 = false;
        }

        /**
         * Metoda koja služi za kreiranje akcije.
         *
         * @param zapis
         */
        public void kreirajAkcije(String zapis) {
            postoji = false;
            postoji2 = false;
            zapisSplit = Helper.delimitString(zapis);
            String sifraA = null;
            String nazivA = null;
            int vrstaA = 0;
            int tipA = 0;
            int brojac = 0;

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
//provjere postoji li korisnik i objekt
            for (ComponentKorisnici k : korisnici) {
                String lo = k.getSifra();
                if (lo.equals(nazivA)) {

                    this.postoji = true;

                }

            }

            for (Objekti o : objektiDefiniranje) {
                String la = o.getSifra();
                if (la.equals(sifraA)) {

                    this.postoji2 = true;

                }

            }

            //System.out.println("Postoji je " + postoji + " a postoji2 je " + postoji2);
            if (postoji) {

                if (postoji2) {
                    Akcija akcija = new Akcija(sifraA, nazivA, tipA, vrstaA);
                    //System.out.println("KREIRAO SAM AKCIJU " + sifraA + " i napravio ju je " + nazivA);
                    akcijaLista.add(akcija);

                } else {
//u slucaju da ne postoji objekt ili korisnik 
                    StructureError erro = new StructureError();
                    erro.updateDatoteka(true, zapis, index);

                }

            } else {

                StructureError erro = new StructureError();
                erro.updateDatoteka(true, zapis, index);
            }
        }

        @Override
        public void nadiIshodisnuGrupu() {
            for (ComponentKorisnici k : korisnici) {
                if (k.dajRoditelje().isEmpty()) {
                    MainClass.ishodisnaGrupa = k;
                }

            }
        }
    }
}
