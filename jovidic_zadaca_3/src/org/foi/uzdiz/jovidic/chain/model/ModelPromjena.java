
package org.foi.uzdiz.jovidic.chain.model;

import java.util.Date;

/**
 *
 * @author jovidic Klasa predstavlja model za promjene koji će se koristiti u
 * Chain of R. uzorku dizanja za prikaz promjena na ekran i upis u dodatnu
 * datoteku
 */
public class ModelPromjena {

    //tip promjene, 1-vrijeme, 2-size, 3-datoteka, 4-file
    private int tipPromjene;
    private int brojCiklusaPromjene;
    private Date vrijemeCitanja;
    private String naziv;
    private String staraVrijednost;
    private String novaVrijednost;
    private int razlika;

    public ModelPromjena(int tipPromjene, Date vrijeme, int ciklus, String promjena, String stara, String nova, int razlika) {
        this.tipPromjene = tipPromjene;
        this.brojCiklusaPromjene = ciklus;
        this.vrijemeCitanja = vrijeme;
        this.naziv = promjena;
        this.staraVrijednost = stara;
        this.novaVrijednost = nova;
        this.razlika = razlika;
    }

    public int getTipPromjene() {
        return tipPromjene;
    }

    public int getBrojCiklusaPromjene() {
        return brojCiklusaPromjene;
    }

    public int getRazlika() {
        return razlika;
    }

    public void setRazlika(int razlika) {
        this.razlika = razlika;
    }

    public Date getVrijemeCitanja() {
        return vrijemeCitanja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getStaraVrijednost() {
        return staraVrijednost;
    }

    public void setStaraVrijednost(String staraVrijednost) {
        this.staraVrijednost = staraVrijednost;
    }

    public String getNovaVrijednost() {
        return novaVrijednost;
    }

    public void setNovaVrijednost(String novaVrijednost) {
        this.novaVrijednost = novaVrijednost;
    }

    public void setTipPromjene(int tipPromjene) {
        this.tipPromjene = tipPromjene;
    }

    public void setBrojCiklusaPromjene(int brojCiklusaPromjene) {
        this.brojCiklusaPromjene = brojCiklusaPromjene;
    }

    public void setVrijemeCitanja(Date vrijemeCitanja) {
        this.vrijemeCitanja = vrijemeCitanja;
    }

}
