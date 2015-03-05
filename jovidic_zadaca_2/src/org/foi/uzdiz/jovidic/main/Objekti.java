
package org.foi.uzdiz.jovidic.main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jovidic
 * 
 * Leaf za objekt u Composite strukturi.
 */
public class Objekti{

    private String sifra;
    private String naziv;
    private int vrsta;

    public Objekti(String sifra, String naziv, int vrsta) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.vrsta = vrsta;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrsta() {
        return vrsta;
    }

    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }



    
}
