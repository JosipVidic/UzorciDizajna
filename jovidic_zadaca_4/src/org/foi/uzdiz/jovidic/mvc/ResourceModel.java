
package org.foi.uzdiz.jovidic.mvc;

import java.io.Serializable;
import java.util.Date;

/**
 *  POJO objekt koji predstavlja podatkovni model aplikacije
 * @author jovidic
 */
public class ResourceModel implements Serializable{
    String naziv;
    int brojKoristenja;
    Date zadnjeKoristenje;
    Date vrijemeSpremanja;
    long velicinaIzvornika;

    public ResourceModel(String naziv, int brojKoristenja, Date zadnjeKoristenje, Date vrijemeSpremanja, long velicinaIzvorika) {
        this.naziv = naziv;
        this.brojKoristenja = brojKoristenja;
        this.zadnjeKoristenje = zadnjeKoristenje;
        this.vrijemeSpremanja = vrijemeSpremanja;
        this.velicinaIzvornika = velicinaIzvorika;
    }

    public String getNaziv() {
        return naziv;
    }

    public long getVelicinaIzvornika() {
        return velicinaIzvornika;
    }

    public void setVelicinaIzvornika(long velicinaIzvornika) {
        this.velicinaIzvornika = velicinaIzvornika;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojKoristenja() {
        return brojKoristenja;
    }

    public void setBrojKoristenja(int brojKoristenja) {
        this.brojKoristenja = brojKoristenja;
    }

    public Date getZadnjeKoristenje() {
        return zadnjeKoristenje;
    }

    public void setZadnjeKoristenje(Date zadnjeKoristenje) {
        this.zadnjeKoristenje = zadnjeKoristenje;
    }

    public Date getVrijemeSpremanja() {
        return vrijemeSpremanja;
    }

    public void setVrijemeSpremanja(Date vrijemeSpremanja) {
        this.vrijemeSpremanja = vrijemeSpremanja;
    }
    
    
    
}
