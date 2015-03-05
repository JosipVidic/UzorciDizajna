package org.foi.uzdiz.jovidic.thread;

import java.io.File;
import java.util.Date;
import org.foi.uzdiz.jovidic.main.MainClass;
import org.foi.uzdiz.jovidic.memento.model.MementoModel;
import static org.foi.uzdiz.jovidic.mvc.ControllerIO.brojacCiklusa;
import static org.foi.uzdiz.jovidic.mvc.ControllerIO.brojacFile;
import static org.foi.uzdiz.jovidic.mvc.ControllerIO.brojacFolder;
import static org.foi.uzdiz.jovidic.mvc.ControllerIO.brojacIstih;
import static org.foi.uzdiz.jovidic.mvc.ControllerIO.careTaker;
import static org.foi.uzdiz.jovidic.mvc.ControllerIO.originator;
import org.foi.uzdiz.jovidic.mvc.composite.model.ComponentInterface;
import org.foi.uzdiz.jovidic.mvc.composite.model.CompositeDirectory;

/**
 *
 * @author jovidic
 */
public class DretvaPromjene extends Thread {

    public File folder;
    int pz;
    public ComponentInterface rootKopija = null;
    public ComponentInterface varijabilniRootKopija = null;
    //public List<ComponentInterface> podaciZaUsporedbu = new ArrayList<>();

    public int brojacUkupno = 0;
    public ComponentInterface trenutnoTekuce;

    public DretvaPromjene(int pauza) {

        this.pz = pauza;
    }

    @Override
    public void interrupt() {
        super.interrupt();
    }

    @Override
    public void run() {
        while (MainClass.kontroler.flag) {

            if (MainClass.kontroler.thread) {

                long milisStart = System.currentTimeMillis();
                rootKopija = null;
                varijabilniRootKopija = null;
                brojacFile = 0;
                brojacFolder = 0;

                provjeriStanje();

                long milisEnd = System.currentTimeMillis();
                long milisWork = milisEnd - milisStart;
                try {
                    long sleep = pz - milisWork;

                    System.out.println("Spavam : " + sleep + " milisekundi");
                    Thread.sleep(sleep);

                    //super.run(); //To change body of generated methods, choose Tools | Templates.
                } catch (InterruptedException ex) {
                }

            } else {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }

            }
        }
        this.interrupt();
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     * Metoda koja provjerava stanje te poziva rekurzivnu funkciju za čitanje
     * direktorija.
     */
    private void provjeriStanje() {
        System.out.println("---------------------------------------------------------------");
        brojacUkupno = originator.getState().getBrojFile() + originator.getState().getBrojFolder() + 1;

        folder = new File(MainClass.kontroler.rootPath);

        //folder = new File("D:\\uzDiz_WORKSPACE\\usporedbaFoldeer\\testfolder");
        rootKopija = null;
        rootKopija = new CompositeDirectory(folder.getName(), folder.length(), true, folder.lastModified(), folder.getName());

        varijabilniRootKopija = rootKopija;

        MainClass.kontroler.citajDirektorij(folder, rootKopija);

        rootKopija.postaviRazineStabla(0);

        trenutnoTekuce = originator.getState().getRootComposita();
        MainClass.kontroler.usporediStabla(trenutnoTekuce, rootKopija);
        MainClass.kontroler.usporediBrojeve(originator.getState().getBrojFile(), brojacFile, originator.getState().getBrojFolder(), brojacFolder);

        Date sad = MainClass.kontroler.dajDatum(System.currentTimeMillis());
        if (brojacUkupno == brojacIstih) {

            System.out.println("Nema promjene u stablu, stanje nije spremljeno , vrijeme citanja je " + sad);

        } else {
            brojacCiklusa++;

            int brojPromjena = brojacUkupno - brojacIstih;
            System.out.println("Doslo je do promjene kod  " + brojPromjena + " elemenata, spremam stanje pod rednim brojem ciklusa : " + brojacCiklusa);
            System.out.println("Spremljeno stanje br: " + brojacCiklusa + " vrijeme citanja je " + sad);

            MementoModel model = new MementoModel(brojacCiklusa, System.currentTimeMillis(), rootKopija, brojacFile, brojacFolder);

            originator.setState(model);
            careTaker.add(originator.saveStateToMemento());
        }

        brojacIstih = 0;
        System.out.println("---------------------------------------------------------------");

    }

}
