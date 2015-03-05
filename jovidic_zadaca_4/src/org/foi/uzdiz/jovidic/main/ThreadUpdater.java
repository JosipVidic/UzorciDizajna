
package org.foi.uzdiz.jovidic.main;

import java.util.Date;
import static org.foi.uzdiz.jovidic.main.Main_Class.kontroler;
import org.foi.uzdiz.jovidic.mvc.ResourceModel;
import org.foi.uzdiz.jovidic.templatemethod.ThreadTemplate;

/**
 * Dretva koja pristupa zadnjem korištenom objektu te ažura njegov broj
 * korištenja i vrijeme pristupa te spava N milisekundi
 *
 * @author jovidic
 */
public class ThreadUpdater extends Thread {

    int pz;

    public ThreadUpdater(int pauza) {

        this.pz = pauza;
    }

    @Override
    public void interrupt() {
        System.out.println("Dretva je prestala sa radom");
        super.interrupt();
    }

    @Override
    public void run() {
        while (Main_Class.kontroler.flag) {

            if (Main_Class.kontroler.thread) {

                long milisStart = System.currentTimeMillis();

                provjeriStanje();

                long milisEnd = System.currentTimeMillis();
                long milisWork = milisEnd - milisStart;
                try {
                    long sleep = pz - milisWork;

                    System.out.println("Spavam : " + sleep + " milisekundi");
                    Thread.sleep(sleep);

                } catch (InterruptedException ex) {
                }

            } else {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }

            }
        }
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metoda koja provjerava da li postoji lista i da li je prazna te poziva
     * metodu za pronalazak zadnjeg unesenog element
     */
    private void provjeriStanje() {

        kontroler.memorija.deserijalizirajListu();
        if (kontroler.memorija.listaZaSpremiste != null) {

            if (!kontroler.memorija.listaZaSpremiste.isEmpty()) {

                nadiZadnjiUneseni();

            }
        }
        kontroler.memorija.serijalizirajListu();

    }

    /**
     * Pronalazi zadnji uneseni element i ažurira ga
     */
    private void nadiZadnjiUneseni() {

        ResourceModel t = kontroler.memorija.listaZaSpremiste.get(0);
        Date tempMax = t.getVrijemeSpremanja();
        ResourceModel tempModel = t;

        for (ResourceModel l : kontroler.memorija.listaZaSpremiste) {
            if (tempMax.after(l.getVrijemeSpremanja())) {
                tempMax = l.getVrijemeSpremanja();
                tempModel = l;
            }
        }

        for (ResourceModel k : kontroler.memorija.listaZaSpremiste) {
            if (k.equals(tempModel)) {
                Date now = new Date();
                k.setZadnjeKoristenje(now);
                k.setBrojKoristenja(k.getBrojKoristenja() + 1);
                ThreadTemplate templ = new ThreadTemplate();
                templ.updateDatoteka(k);
                break;
            }
        }

    }

}
