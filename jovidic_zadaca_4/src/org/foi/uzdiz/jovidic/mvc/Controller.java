package org.foi.uzdiz.jovidic.mvc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.jovidic.caching.Cache;
import org.foi.uzdiz.jovidic.main.ThreadUpdater;
import org.foi.uzdiz.jovidic.main.Main_Class;
import org.foi.uzdiz.jovidic.templatemethod.InstanceTemplate;

/**
 * Klasa koja pokreće sve elemente za rad programa i sadrži globalne varijable
 * unesene argumentima
 *
 * @author jovidic
 */
public class Controller {

    public static int maxKolPod;
    public static int intervalDretve;
    public static String direktorijSpremista;
    public double kapacitetSpremista;
    public static boolean kB;//ako true KB ako ne B
    public static boolean strategija;//ako true onda NS ako false onda NK
    public static String dnevnik;
    public static List<File> listaDatotekaSpremiste = new ArrayList<>();
    public static boolean cleanPostoji;//ako da brisi spremiste ako ne ne brisi
    public static boolean izvrsenaProvjera;
    public Cache memorija;
    public long trenutnaVelicina;
    public boolean thread = false;
    public boolean flag = true;
    public ThreadUpdater thrd;

    public void run(String[] args) {
        provjeriArgumente(args);

        trenutnaVelicina = 0;
        memorija = new Cache();
        memorija.kreiraj();

        String opcija = "";
        String izlaz = "Q";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        provjeriSpremiste();
        pokreniDretvu();

        InstanceTemplate nova = new InstanceTemplate();
        nova.updateDatoteka(null);

        if (izvrsenaProvjera) {

            do {

                opcija = Main_Class.pogled.ispisiMenu(opcija, br);

            } while (!opcija.contentEquals(izlaz));

        }

    }

    /**
     * Metoda koja čita N bajtova te ovisno o postojanju u listi kreira novi
     * element
     *
     * @param path
     * @param n
     */
    public void citajNBajtova(String path, int n) {
        popisDatUSprem();
        ukupnaVelDat();

        try {
            BufferedInputStream bufferedInput = new BufferedInputStream(new FileInputStream(path));
            BufferedInputStream bufferAll = new BufferedInputStream(new FileInputStream(path));
            int b = 0;
            byte[] firstN = new byte[n];

            try {
                File datoteka = new File(path);

                bufferedInput.read(firstN);

                if (!memorija.dohvatiObjekt(path)) {
                    memorija.provjeriSlobodniProstor(datoteka);
                    File datotekaZaKreiranje = new File(direktorijSpremista + "\\" + datoteka.getName());
                    Date now = new Date();
                    ResourceModel model = new ResourceModel(datoteka.getName(), 1, now, now, datoteka.length());
                    memorija.dodajObjekt(model);
                    copyFile(datoteka, datotekaZaKreiranje);
                } else {
                    Main_Class.pogled.javiDaPostoji(datoteka.getName());

                }

                Main_Class.pogled.ispisiProcitano(n, path, firstN);

                bufferedInput.close();
            } catch (IOException ex) {
                System.out.println("Pogreška u čitanju datoteke");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Ne postoji datoteka");
        }
    }

    /**
     * Metoda koja služi za provjeru argumenata na temelju regExa
     *
     * @param args
     */
    public void provjeriArgumente(String[] args) {
        String unos;
        String sintaksa = "^(([0-9]+) ([0-9]+) (.*) (([0-9]+)|([0-9]+) (KB)) (NS|NK) (([a-z0-9]*\\.txt)|(([a-z0-9]*\\.txt) (CLEAN))))$";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }

        String p = sb.toString().trim();
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher m = pattern.matcher(p);
        boolean status = m.matches();
        if (status) {

            if (m.group(2) != null) {
                maxKolPod = Integer.parseInt(m.group(2));
            }
            if (m.group(3) != null) {
                intervalDretve = Integer.parseInt(m.group(3));
            }
            if (m.group(4) != null) {
                direktorijSpremista = m.group(4);
            }
            if (m.group(8) == null) {
                if (m.group(5) != null) {
                    kB = false;
                    kapacitetSpremista = Double.parseDouble(m.group(6));
                }
            }
            if (m.group(8) != null) {
                if (m.group(7) != null) {
                    kB = true;
                    kapacitetSpremista = Double.parseDouble(m.group(7));
                }
            }
            if (m.group(9) != null) {
                String strategijaString = m.group(9);

                if (strategijaString.equals("NS")) {

                    strategija = true;
                } else if (strategijaString.equals("NK")) {

                    strategija = false;
                }
            }
            if (m.group(14) != null) {
                cleanPostoji = true;
                dnevnik = m.group(13);

            }
            if (m.group(14) == null) {
                if (m.group(10) != null) {
                    cleanPostoji = false;
                    dnevnik = m.group(10);

                }
            }
        } else {
            Main_Class.pogled.ispisiMogucnosti();
        }

    }

    /**
     * Metoda koja provjerava spremište te ga briše u slučaju postojanja
     * argumenta CLEAN
     */
    private void provjeriSpremiste() {
        File f = new File(direktorijSpremista);
        if (f.exists() && f.isDirectory()) {

            if (cleanPostoji) {
                memorija.isprazniNaZahtjev();
                izvrsenaProvjera = true;
            }
            izvrsenaProvjera = true;
        } else {

            f.mkdir();
            if (cleanPostoji) {
                memorija.isprazniNaZahtjev();
                izvrsenaProvjera = true;
            }
            izvrsenaProvjera = true;
        }

    }

    /**
     * Metoda koja fizički briše sve datoteke u spremištu
     *
     * @param f
     */
    public void ocisti(File f) {
        trenutnaVelicina = 0;
        for (File file : f.listFiles()) {
            file.delete();
        }
    }

    /**
     * Metoda koja kopira datoteku s lokacije a na lokaciju b
     *
     * @param sourceFile
     * @param destFile
     * @throws FileNotFoundException
     */
    private void copyFile(File sourceFile, File destFile) throws FileNotFoundException {

        if (!sourceFile.exists()) {
            return;
        }
        if (!destFile.exists()) {
            try {
                destFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            try {
                destination.transferFrom(source, 0, source.size());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (source != null) {
            try {
                source.close();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (destination != null) {
            try {
                destination.close();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Metoda koja popisuje datoteke u spremištu
     */
    private void popisDatUSprem() {
        File folder = new File(direktorijSpremista);
        File[] listOfFiles = folder.listFiles();

        for (File f : listOfFiles) {
            if (f.isFile()) {
                listaDatotekaSpremiste.add(f);
            }
        }

    }

    /**
     * Metoda koja računa ukupnu veličinu datoteka u spremištu
     *
     */
    public void ukupnaVelDat() {
        trenutnaVelicina = 0;
        File folder = new File(direktorijSpremista);
        File[] listOfFiles = folder.listFiles();

        for (File f : listOfFiles) {
            if (f.isFile()) {
                trenutnaVelicina = trenutnaVelicina + f.length();
            }
        }
    }

    /**
     * Metoda koja poziva pražnjenje memorije
     *
     * @param n
     */
    public void isprazniN(double n) {
        memorija.isprazniMemorijaN(n);
    }

    /**
     *
     * Metoda koja postavlja zastavice za dretvu na false te ju gasi.
     */
    public void ugasiDretvu() {
        thread = false;
        flag = false;
        try {
            thrd.interrupt();
        } catch (Exception e) {
        }

    }

    /**
     * Metoda koja postavlja zastavice za dretvu na true te ju pokreće.
     */
    public void pokreniDretvu() {
        thread = true;
        flag = true;
        thrd = new ThreadUpdater(intervalDretve * 1000);
        thrd.start();

    }

}
