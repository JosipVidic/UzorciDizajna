package org.foi.uzdiz.jovidic.mvc;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.foi.uzdiz.jovidic.chain.ChainPromjenaI;
import org.foi.uzdiz.jovidic.chain.model.ModelPromjena;
import org.foi.uzdiz.jovidic.chain.PromjenaDat;
import org.foi.uzdiz.jovidic.chain.PromjenaDir;
import org.foi.uzdiz.jovidic.chain.PromjenaVelicina;
import org.foi.uzdiz.jovidic.chain.PromjenaVrijeme;
import org.foi.uzdiz.jovidic.factorymethod.FactoryI;
import org.foi.uzdiz.jovidic.factorymethod.TypeFactory;
import static org.foi.uzdiz.jovidic.main.MainClass.kontroler;
import org.foi.uzdiz.jovidic.thread.DretvaPromjene;
import static org.foi.uzdiz.jovidic.main.MainClass.pogled;
import org.foi.uzdiz.jovidic.memento.Caretaker;
import org.foi.uzdiz.jovidic.memento.Memento;
import org.foi.uzdiz.jovidic.memento.model.MementoModel;
import org.foi.uzdiz.jovidic.memento.Originator;
import org.foi.uzdiz.jovidic.mvc.composite.model.ComponentInterface;
import org.foi.uzdiz.jovidic.mvc.composite.model.CompositeDirectory;
import org.foi.uzdiz.jovidic.mvc.composite.model.LeafFile;

/**
 *
 * @author jovidic
 *
 * Kontroler koji obavlja poslovnu logiku odvojeno od prikaza korisniku.
 *
 */
public class ControllerIO {

    public File folder;

    public boolean thread = false;

    public boolean flag = true;

    public DretvaPromjene thrd;

    public ComponentInterface root = null;

    public ComponentInterface varijabilniRoot = null;

    public String rootPath = null;

    public String datoteka = null;

    public int pauza = 0;

    public static int brojacFile = 0;

    public static int brojacFolder = 0;

    public static TypeFactory factoryType = new TypeFactory();

    public static FactoryI factory;

    public static Originator originator = new Originator();

    public static Caretaker careTaker = new Caretaker();

    public static int brojacCiklusa = 0;

    public MementoModel N;
    public MementoModel M;

    public static ChainPromjenaI c1 = new PromjenaVrijeme();
    public static ChainPromjenaI c2 = new PromjenaVelicina();
    public static ChainPromjenaI c3 = new PromjenaDir();
    public static ChainPromjenaI c4 = new PromjenaDat();

    public static List<ModelPromjena> listaSvihPromjena = new ArrayList<>();

    public static int brojacIstih = 0;

    public PrintWriter pw;

    //public static List<ComponentInterface> listaZaUsporedbuN = new ArrayList<>();
    //spublic static List<ComponentInterface> listaZaUsporedbuM = new ArrayList<>();
    public ControllerIO() {
    }

    /**
     * Metoda koja poziva provjeru parametara, prvi ciklus čitanja te kreiranje
     * nultog modela za memento. Nakon tog poziva ispis menija i čeka daljnje
     * naredbe dok je opcija izlaz različita od "Q"
     *
     * @param args
     */
    public void run(String[] args) {

        String izlaz = "Q";
        String opcija = "";
        provjeriArgumente(args);
        c1.setNext(c2);
        c2.setNext(c3);
        c3.setNext(c4);

        if (datoteka != null) {
            try {
                pw = new PrintWriter(kontroler.datoteka + ".txt");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControllerIO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Nije unešena datoteka, promjene se neće zapisivati");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        folder = new File(rootPath);

        //Kreiraj root composite i kopiraj ga za pocetak citanja
        root = new CompositeDirectory(folder.getName(), folder.length(), true, folder.lastModified(), folder.getName());

        varijabilniRoot = root;

        citajDirektorij(folder, varijabilniRoot);
        root.postaviRazineStabla(0);

        //Spremi nulto stanje u memento
        MementoModel nultiModel = new MementoModel(brojacCiklusa, System.currentTimeMillis(), root, brojacFile, brojacFolder);
        originator.setState(nultiModel);
        careTaker.add(originator.saveStateToMemento());

        System.out.println("Spremljeno pocetno stanje ");

        do {

            opcija = pogled.ispisiMenu(opcija, br);

        } while (!opcija.contentEquals(izlaz));
        flag = false;
        ugasiDretvu();

    }

    /**
     * Metoda koja provjerava argumente korisničkog unosa na početku programa i
     * postavlja statičke varijable na temelju unešenih podatka.
     *
     * @param args
     */
    public void provjeriArgumente(String[] args) {

        String unos;
        //String sintaksa = "(.*) ([0-9]+) (.*\\.txt)";
        String sintaksa = "(((.*) ([0-9]+) (.*\\.txt))|((.*) ([0-9]+)))";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }

        String p = sb.toString().trim();
        Pattern pattern = Pattern.compile(sintaksa);
        Matcher m = pattern.matcher(p);
        boolean status = m.matches();
        if (status) {
            if (m.group(3) != null) {
                //ishodisni
                rootPath = m.group(3);
            }
            if (m.group(4) != null) {
                //pauza
                pauza = Integer.parseInt(m.group(4));
            }
            if (m.group(5) != null) {
                //izlaz
                datoteka = m.group(5);
            }
            if (m.group(7) != null) {
                //ishodisni
                rootPath = m.group(7);
            }
            if (m.group(8) != null) {
                pauza = Integer.parseInt(m.group(8));
            }

        } else {
            System.out.println("Neispravan unos parametara, unesite parametre u obliku : ");
            System.out.println("D:\\uzDiz_WORKSPACE\\testfolder 5 D:\\uzDiz_WORKSPACE\\izlaz.txt    ili   D:\\uzDiz_WORKSPACE\\testfolder\\izlaz.txt 5");
        }

    }

    /**
     * Rekurzivna metoda koja služi za čitanje na temelju određene putanje i
     * composite direktorija u koji treba spremiti kreirane podatke.
     *
     * @param path
     * @param spremiU
     */
    public void citajDirektorij(File path, ComponentInterface spremiU) {
        // ispisiDjecu(path);
        for (File procitano : path.listFiles()) {

            if (procitano.isFile()) {
                factory = factoryType.dajTip("FILE");
                LeafFile leafModel = (LeafFile) factory.kreiraj(procitano.getName(), procitano.length(), false, procitano.lastModified(), spremiU.getPutanjaStablo(), procitano.getName());

                spremiU.add(leafModel);
                //listaZaUsporedbuM.add(leafModel);
                brojacFile++;
            } else if (procitano.isDirectory()) {
                factory = factoryType.dajTip("DIR");
                CompositeDirectory compositeModel = (CompositeDirectory) factory.kreiraj(procitano.getName(), procitano.length(), true, procitano.lastModified(), spremiU.getPutanjaStablo(), procitano.getName());

                //procitaniPodaci.add(compositeModel);
                spremiU.add(compositeModel);
                //listaZaUsporedbuM.add(compositeModel);
                brojacFolder++;
                citajDirektorij(procitano, compositeModel);
            }

        }

    }

    /**
     * Metoda koja pruža ispis o informacijama stanja.
     */
    public void infoStanja() {

        Date datum = kontroler.dajDatum(originator.getState().getVrijemePromjene());
        System.out.println("Vrijeme ucitavanja je :" + datum);
        System.out.println("---------------------------------------------------------------");
        System.out.println("Redni broj ciklusa stanja je :" + originator.getState().getBrojCiklusa());
        System.out.println("---------------------------------------------------------------");
        System.out.println("Broj učitanih datoteka je : " + originator.getState().getBrojFile());
        System.out.println("---------------------------------------------------------------");
        System.out.println("Broj učitanih direktorija je : " + originator.getState().getBrojFolder() + " i 1 root direktorij");

    }

    /**
     * Metoda koja uspoređuje uneseni broj s brojem ciklusa stanja te ga
     * postavlja kao trenutno stanje.
     *
     * @param rbStanja
     */
    public void postaviTrenutnoN(int rbStanja) {

        for (Memento m : careTaker.getall()) {

            if (m.getState().getBrojCiklusa() == rbStanja) {
                originator.setState(m.getState());
            }

        }

    }

    /**
     * Funkcija koja uspoređuje 2 spremljena stanja s rednim brojevima ciklusa
     * rbN i rbM
     *
     * @param rbN
     * @param rbM
     */
    public void usporediSpremljenaStanja(int rbN, int rbM) {
        N = null;
        M = null;

        for (Memento m : careTaker.getall()) {

            if (m.getState().getBrojCiklusa() == rbN) {
                N = m.getState();
            }

        }
        for (Memento m : careTaker.getall()) {

            if (m.getState().getBrojCiklusa() == rbM) {
                M = m.getState();
            }

        }

        if (N != null && M != null) {
            usporediBrojeve(N.getBrojFile(), M.getBrojFile(), N.getBrojFolder(), M.getBrojFolder());

            usporediStabla(N.getRootComposita(), M.getRootComposita());
        } else {
            if (N == null) {
                System.out.println("Stanje ne postoji : " + rbN);

            }
            if (M == null) {
                System.out.println("Stanje ne postoji : " + rbM);

            }

        }

        N = null;
        M = null;

    }

    /**
     * Metoda koja kreira novi nulti Memento na temelju trenutnog te odbacuje
     * sva ostala stanja.
     */
    public int postaviTrenutnoOdbaciOstalo() {
        int staroStanjeBrojaC = originator.getState().getBrojCiklusa();
        brojacCiklusa = 0;
        MementoModel trenutnoJedino = new MementoModel(brojacCiklusa, originator.getState().getVrijemePromjene(), originator.getState().getRootComposita(), originator.getState().getBrojFile(), originator.getState().getBrojFolder());
        careTaker.clearStanja();
        originator.setState(trenutnoJedino);
        careTaker.add(originator.saveStateToMemento());
        return staroStanjeBrojaC;
    }

    /**
     * Metoda koja ispisuje redne brojeve i vrijeme spremanja svih stanja u
     * memoriji.
     */
    public void svaStanja() {

        for (Memento m : careTaker.getall()) {
            Date dae = kontroler.dajDatum(m.getState().getVrijemePromjene());
            System.out.println("Broj ciklusa stanja : " + m.getState().getBrojCiklusa() + " vrijeme spremanja : " + dae);

        }

    }

    /**
     * Funkcija koja uspoređuje elemente dva modela te ako su u potpunosti isti
     * povećava brojač
     *
     * @param q
     * @param w
     */
    public void usporediElemente(ComponentInterface q, ComponentInterface w) {
        Date vrijeme = kontroler.dajDatum(System.currentTimeMillis());
        int brojacPromjena = 0;
        if (q.getPutanjaStablo().equals(w.getPutanjaStablo())) {

            if (!q.getSize().equals(w.getSize())) {
                ModelPromjena mod = new ModelPromjena(2, vrijeme, brojacCiklusa, q.getName(), q.getSize().toString(), w.getSize().toString(), 0);
                c1.process(mod, pw, 1, datoteka);
                brojacPromjena++;
            }
            if (!q.getVrijemePromjene().equals(w.getVrijemePromjene())) {
                ModelPromjena mod = new ModelPromjena(1, vrijeme, brojacCiklusa, q.getName(), q.getVrijemePromjene().toString(), w.getVrijemePromjene().toString(), 0);
                c1.process(mod, pw, 1, datoteka);
                brojacPromjena++;
            }

        }

        if (!q.getName().equals(w.getName())) {
            brojacPromjena++;
        }
        if (!q.getPutanjaStablo().equals(w.getPutanjaStablo())) {
            brojacPromjena++;
        }
        if (!q.getSize().equals(w.getSize())) {
            brojacPromjena++;
        }
        if (!q.getVrijemePromjene().equals(w.getVrijemePromjene())) {
            brojacPromjena++;
        }
        if (q.getRazina() != w.getRazina()) {
            brojacPromjena++;
        }
        if (brojacPromjena == 0) {
            brojacIstih++;
            //ControllerIO.listaZaUsporedbuN.add(q);
        }

    }

    /**
     * Funkcija koja računa razliku između broja direktorija i datoteka
     *
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public void usporediBrojeve(int a, int b, int c, int d) {
        int razlika;

        Date date = dajDatum(System.currentTimeMillis());
        if (a != b) {

            if (a < b) {
                razlika = b - a;
                ModelPromjena mod1 = new ModelPromjena(4, date, brojacCiklusa, "", "", "", razlika);
                c1.process(mod1, pw, 1, datoteka);

            }
            if (a > b) {

                razlika = a - b;
                ModelPromjena mod2 = new ModelPromjena(4, date, brojacCiklusa, "", "", "", razlika);
                c1.process(mod2, pw, 1, datoteka);

            }

        }
        if (c != d) {
            if (c < d) {
                razlika = d - c;
                ModelPromjena mod3 = new ModelPromjena(3, null, brojacCiklusa, null, null, null, razlika);
                c1.process(mod3, pw, 1, datoteka);
            }
            if (c > d) {
                razlika = c - d;
                ModelPromjena mod4 = new ModelPromjena(3, null, brojacCiklusa, null, null, null, razlika);
                c1.process(mod4, pw, 1, datoteka);

            }
        }

    }

    /**
     *
     * Metoda koja uspoređuje stanja te ispisuje promjene.
     *
     *
     * @param q
     * @param w
     */
    public void usporediStabla(ComponentInterface q, ComponentInterface w) {

        if (q.getChild() != null) {

            for (ComponentInterface z : q.getChild()) {
                if (w.getChild() != null) {

                    for (ComponentInterface y : w.getChild()) {
                        usporediStabla(z, y);
                    }

                } else {
                    usporediElemente(z, w);
                }
                usporediElemente(z, w);

            }
            usporediElemente(q, w);
        } else {
            usporediElemente(q, w);

        }
    }

    /**
     * Metoda koja pretvara milisekunde u format datuma.
     *
     * @param milis
     * @return
     */
    public Date dajDatum(long milis) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");

        Date rezultat = new Date(milis);

        return rezultat;
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
        thrd = new DretvaPromjene(pauza * 1000);
        thrd.start();

    }

    /**
     * Metoda koja ispisuje svu djecu određenog roditelja na nekom pathu.
     *
     * @param path
     */
    public void ispisiDjecu(File path) {

        if (path.list() != null) {
            System.out.println("_________________________________________________");
            System.out.println("RODITELJ: " + path.getName());

            System.out.println("DJECA");
            for (String k : path.list()) {

                System.out.println("Procitao sam : " + k);

            }
            System.out.println("___________________________________________________");

        }

    }

    private int vratiBrojacFile() {
        return brojacFile;
    }

    private int vratiBrojacFolder() {
        return brojacFolder;
    }

    /**
     * Metoda koja služi za unos svih promjena iz liste promjena u dodatnu/ novu
     * datoteku
     *
     * @param dat
     */
    void zapisiUDatoteku(String dat) {
        PrintWriter pw1;
        File file = new File(dat + ".txt");
        try {
            pw1 = new PrintWriter(dat + ".txt");

            if (!listaSvihPromjena.isEmpty()) {

                for (ModelPromjena m : listaSvihPromjena) {
                    c1.process(m, pw1, 2, dat);

                }
                System.out.println("Podaci su zapisani u datoteku " + dat + ".txt");
                try {
                    Desktop.getDesktop().edit(file);
                } catch (IOException ex) {

                }

            } else {
                System.out.println("Nisu zabilježene nikakve promjene");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControllerIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
