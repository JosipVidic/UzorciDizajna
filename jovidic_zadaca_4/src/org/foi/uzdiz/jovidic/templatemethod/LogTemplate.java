package org.foi.uzdiz.jovidic.templatemethod;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.uzdiz.jovidic.main.Main_Class;
import org.foi.uzdiz.jovidic.mvc.ResourceModel;

/**
 *
 * @author jovidic
 *
 * Klasa koja sluzi kao predlozak obradi pogresaka u zapisima.
 */
public abstract class LogTemplate {

    public static List<String> errorData = new ArrayList<>();

    public void updateDatoteka(ResourceModel error) {
        dodajError(error);
        zapisiDatoteku();
    }

    abstract void dodajError(ResourceModel error);

    /**
     * Metoda koja služi za zapis podataka u datoteku ovisno o argumentu
     */
    public void zapisiDatoteku() {

        PrintWriter out;

        try {

            out = new PrintWriter(new BufferedWriter(new FileWriter(Main_Class.kontroler.dnevnik, true)));

            for (String s : errorData) {
                out.println(s);
            }
            errorData.clear();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(LogTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
