
package org.foi.uzdiz.jovidic.chain;

import org.foi.uzdiz.jovidic.chain.model.ModelPromjena;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.foi.uzdiz.jovidic.main.MainClass.kontroler;
import org.foi.uzdiz.jovidic.mvc.ControllerIO;

/**
 *
 * @author jovidic
 *
 * Klasa koja implementira sučelje ChainPromjena te obzirom na parametre
 * ispisuje promjene na ekran, upisuje ih u unešenu datoteku, ili unosi u doatnu
 * datoteku
 */



public class PromjenaVelicina implements ChainPromjenaI {

    private ChainPromjenaI nextInChain;

    @Override
    public void setNext(ChainPromjenaI next) {
        nextInChain = next;
    }

    @Override
    public void process(ModelPromjena model, PrintWriter write, int i, String dat) {
        if (model.getTipPromjene() == 2) {

            //dodaj u listu
            if (dat == null && i == 1) {
                System.out.println("PROMJENA VELIČINE : Za " + model.getNaziv() + " se promijenila veličina, vrijeme citanja promjene je " + model.getVrijemeCitanja() + " velicina 1 je : " + model.getStaraVrijednost() + " a velicina 2 je : " + model.getNovaVrijednost());
//ako datoteka null samo spremaj u listu
                ControllerIO.listaSvihPromjena.add(model);

            }
            if (kontroler.datoteka != null && i == 1) {
                System.out.println("PROMJENA VELIČINE : Za " + model.getNaziv() + " se promijenila veličina, vrijeme citanja promjene je " + model.getVrijemeCitanja() + " velicina 1 je : " + model.getStaraVrijednost() + " a velicina 2 je : " + model.getNovaVrijednost());

//ako datoteka postoji spremi u listu i zapisi u datoteku
                ControllerIO.listaSvihPromjena.add(model);
                //pisi
                write(model, write, dat);

            }
            if (i == 2) {
                //ako je tip 2(dodatno pisanje) samo pisi u datoteku
                write(model, write, dat);
            }
        } else {
            if (dat == null && i == 1) {
                nextInChain.process(model, write, i, dat);

            }
            if (dat != null && i == 1) {
                nextInChain.process(model, write, i, dat);
            }
            if (i == 2) {
                nextInChain.process(model, write, i, dat);

            }
        }
    }

    @Override
    public void write(ModelPromjena model, PrintWriter out, String dat) {

        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(dat + ".txt", true)));

            String zapis = "CIKLUS ->" + model.getBrojCiklusaPromjene() + " PROMJENA -> PROMJENA VELIČINE : Za " + model.getNaziv() + " se promijenila veličina, vrijeme citanja promjene je " + model.getVrijemeCitanja() + " velicina 1 je : " + model.getStaraVrijednost() + " a velicina 2 je : " + model.getNovaVrijednost();
//System.out.println("PISEM " +zapis);
            out.println(zapis);

            out.close();

        } catch (IOException ex) {
            Logger.getLogger(PromjenaDat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
