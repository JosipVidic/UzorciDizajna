
package org.foi.uzdiz.jovidic.strategy;

import java.io.File;
import java.util.Date;
import static org.foi.uzdiz.jovidic.main.Main_Class.kontroler;
import org.foi.uzdiz.jovidic.mvc.Controller;
import org.foi.uzdiz.jovidic.mvc.ResourceModel;
import org.foi.uzdiz.jovidic.templatemethod.RemoveTemplate;
import org.foi.uzdiz.jovidic.templatemethod.TimeTemplate;

/**
 * Klasa koja služi za izbacivanje elementa po strategiji najstariji
 *
 * @author jovidic
 */
public class StrategyNS implements StrategyI {

    /**
     * Metoda koja izbacuje element iz liste
     */
    @Override
    public void izbaci() {

        kontroler.memorija.deserijalizirajListu();

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
                kontroler.memorija.listaZaSpremiste.remove(k);
                RemoveTemplate dodan = new RemoveTemplate();
                dodan.updateDatoteka(k);
                TimeTemplate vrijeme = new TimeTemplate();
                vrijeme.updateDatoteka(k);
                System.out.println("NASAO SAM ZA BRISANJE " + tempModel.getNaziv());
                String temp = Controller.direktorijSpremista + "\\" + tempModel.getNaziv();
                File f = new File(temp);
                f.delete();
                break;
            }
        }

        kontroler.memorija.serijalizirajListu();
    }

}
