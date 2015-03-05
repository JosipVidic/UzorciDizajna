
package org.foi.uzdiz.jovidic.memento.model;

import org.foi.uzdiz.jovidic.mvc.composite.model.ComponentInterface;

/**
 *
 * @author jovidic
 *
 * Model za Memento koji sadrži broj ciklusa, vrijeme očitavanja, Composite s
 * podacima te broj datoteka i direktorija.
 */
public class MementoModel {

    private int brojCiklusa;
    private long vrijemePromjene;
    private ComponentInterface rootComposita;
    private int brojFile;
    private int brojFolder;

    public MementoModel(int brojCiklusa, long vrijemePromjene, ComponentInterface rootComposita, int brojFile, int brojFolder) {
        this.brojCiklusa = brojCiklusa;
        this.vrijemePromjene = vrijemePromjene;
        this.rootComposita = rootComposita;
        this.brojFile = brojFile;
        this.brojFolder = brojFolder;
    }

    public int getBrojCiklusa() {
        return brojCiklusa;
    }

    public long getVrijemePromjene() {
        return vrijemePromjene;
    }

    public ComponentInterface getRootComposita() {
        return rootComposita;
    }

    public int getBrojFile() {
        return brojFile;
    }

    public int getBrojFolder() {
        return brojFolder;
    }

}
