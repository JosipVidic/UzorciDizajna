package org.foi.uzdiz.jovidic.composite;

import java.util.List;

/**
 *
 * @author jovidic
 *
 * Sučelje koje određuje metode u Composite strukturi.
 */
public interface ComponentKorisnici {


    public String getSifra();

    public String getNaziv();

    public int getVrsta();

    public void setSifra(String sifra);

    public void setNaziv(String naziv);

    public int getAdmin();

    public void setAdmin(int admin);

    public void setVrsta(int vrsta);

    public void addChild(ComponentKorisnici employee);

    public void removeChild(ComponentKorisnici employee);

    public void addParent(ComponentKorisnici employee);

    public void removeParent(ComponentKorisnici employee);
    
    public List<ComponentKorisnici> dajDjecu();

    public List<ComponentKorisnici> dajRoditelje();
    
    public void print (int i);
    
    public void print2 (int i);

}
