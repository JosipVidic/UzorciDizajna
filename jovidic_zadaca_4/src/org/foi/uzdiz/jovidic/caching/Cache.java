
package org.foi.uzdiz.jovidic.caching;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.foi.uzdiz.jovidic.main.Main_Class;
import org.foi.uzdiz.jovidic.mvc.Controller;
import static org.foi.uzdiz.jovidic.mvc.Controller.direktorijSpremista;
import org.foi.uzdiz.jovidic.mvc.ResourceModel;
import org.foi.uzdiz.jovidic.strategy.StrategyContext;
import org.foi.uzdiz.jovidic.strategy.StrategyNK;
import org.foi.uzdiz.jovidic.strategy.StrategyNS;
import org.foi.uzdiz.jovidic.templatemethod.AddTemplate;
import org.foi.uzdiz.jovidic.templatemethod.UsedTemplate;

/**
 *
 * @author Klasa koja sadrži globalnu varijablu listaZaSpremiste i vrši akcije
 * nad njom
 */
public class Cache implements CacheI {

    public List<ResourceModel> listaZaSpremiste;

    /**
     * Metoda koja kreira inicijalnu listu ako već ne postoji.
     */
    @Override
    public void kreiraj() {
        deserijalizirajListu();
        serijalizirajListu();

    }

    /**
     *
     * Metoda koja dodaje objekt u listu
     *
     * @param model
     */
    @Override
    public void dodajObjekt(ResourceModel model) {
        deserijalizirajListu();
        AddTemplate dodan = new AddTemplate();
        dodan.updateDatoteka(model);
        listaZaSpremiste.add(model);
        serijalizirajListu();
    }

    /**
     * Metoda koja poziva strategiju za brisanje NK nad listom
     */
    @Override
    public void pobrisiNK() {
        StrategyContext context = new StrategyContext(new StrategyNK());
        context.izvrsiStrategiju();
    }

    /**
     * Metoda koja poziva strategiju za brisanje NS nad listom
     */
    @Override
    public void pobrisiNS() {
        StrategyContext context = new StrategyContext(new StrategyNS());
        context.izvrsiStrategiju();
    }

    /**
     * Serijalizacija liste
     */
    @Override
    public void serijalizirajListu() {
        try {
            FileOutputStream fileOut = new FileOutputStream("ListaSpremiste");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listaZaSpremiste);
            out.close();
            fileOut.close();

        } catch (IOException ex) {
            System.out.println("Serijalizcija neuspješna");
        }

    }

    /**
     * Deserijalizacija liste
     */
    @Override
    public void deserijalizirajListu() {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("ListaSpremiste"));
            listaZaSpremiste = (List<ResourceModel>) in.readObject();
            in.close();
        } catch (Exception e) {
            listaZaSpremiste = new ArrayList<>();

        }
    }

    /**
     * Metoda koja dohvaća objekt ako postoji u listi te ako postoji ažurira mu
     * zadnje krištenje i broj korištenja
     *
     * @param path
     * @return
     */
    @Override
    public boolean dohvatiObjekt(String path) {
        deserijalizirajListu();
        boolean flagPostoji = false;
        File datoteka = new File(path);

        for (ResourceModel m : listaZaSpremiste) {
            if (m.getNaziv().equals(datoteka.getName()) && m.getVelicinaIzvornika() == datoteka.length()) {

                Date now = new Date();
                m.setBrojKoristenja(m.getBrojKoristenja() + 1);
                m.setZadnjeKoristenje(now);
                UsedTemplate used = new UsedTemplate();
                used.updateDatoteka(m);
                flagPostoji = true;
                serijalizirajListu();
                return flagPostoji;

            }

        }

        serijalizirajListu();
        return flagPostoji;

    }

    /**
     * Metoda koja popunjava listu
     */
    @Override
    public void popuniListu() {
        deserijalizirajListu();
        listaZaSpremiste.clear();
        File folder = new File(direktorijSpremista);
        File[] listOfFiles = folder.listFiles();

        for (File f : listOfFiles) {
            if (f.isFile()) {
                Date date = new Date();
                byte[] all = new byte[(int) f.length()];
                String content = Arrays.toString(all);
                ResourceModel model = new ResourceModel(f.getName(), 1, date, date, f.length());
                dodajObjekt(model);
            }
        }
        serijalizirajListu();

    }

    /**
     * Metoda koja prazni listu na zahtjev korisnika kao i direktorij spremista
     */
    @Override
    public void isprazniNaZahtjev() {
        deserijalizirajListu();
        listaZaSpremiste.clear();
        serijalizirajListu();
        File f = new File(direktorijSpremista);
        Main_Class.kontroler.ocisti(f);
    }

    /**
     * Metoda koja vraća veličinu liste
     *
     * @return
     */
    @Override
    public double velicinaListe() {
        double velicina = 0;

        if (Controller.kB) {

            deserijalizirajListu();
            for (ResourceModel m : listaZaSpremiste) {

                velicina = velicina + m.getVelicinaIzvornika();
            }
            serijalizirajListu();
            return velicina / 1024;

        } else {
            deserijalizirajListu();
            velicina = listaZaSpremiste.size();
            serijalizirajListu();
            return velicina;

        }

    }

    /**
     * Metoda koja provjerava slobodan prostor
     *
     * @param datoteka
     */
    @Override
    public void provjeriSlobodniProstor(File datoteka) {

        double velicinaSpremljenih = velicinaListe();
        double velicinaDatoteke = 0;
        double velicinaKapaciteta = Main_Class.kontroler.kapacitetSpremista;

        if (Controller.kB) {
            velicinaDatoteke = datoteka.length() / 1024;
        } else {
            velicinaDatoteke = 1;

        }

        if (velicinaSpremljenih + velicinaDatoteke > velicinaKapaciteta) {
            Main_Class.pogled.ispisiNemaMjesta();

            if (Controller.strategija) {

                while (velicinaSpremljenih + velicinaDatoteke > velicinaKapaciteta) {

                    pobrisiNS();
                    velicinaSpremljenih = velicinaListe();

                }

            } else {
                while (velicinaSpremljenih + velicinaDatoteke > velicinaKapaciteta) {

                    pobrisiNK();
                    velicinaSpremljenih = velicinaListe();

                }

            }

        }

    }

    /**
     * Metoda koja prazni iz memorije N KB ili broja elemenata ovisno o unesenom
     * argumentu
     *
     * @param n
     */
    @Override
    public void isprazniMemorijaN(double n) {

        double velicinaSpremljenih = velicinaListe();
        double velicinaKapaciteta = Main_Class.kontroler.kapacitetSpremista;
        velicinaKapaciteta = velicinaKapaciteta - n;

        if (velicinaSpremljenih > velicinaKapaciteta) {
            if (Controller.strategija) {
                System.out.println("NS strategija izbacivanja");

                while (velicinaSpremljenih > velicinaKapaciteta) {

                    pobrisiNS();
                    velicinaSpremljenih = velicinaListe();

                }

            } else {
                System.out.println("NK strategija izbacivanja");

                while (velicinaSpremljenih > velicinaKapaciteta) {

                    pobrisiNK();
                    velicinaSpremljenih = velicinaListe();

                }

            }

        }
    }

}
