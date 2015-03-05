
package org.foi.uzdiz.jovidic.caching;

import java.io.File;
import org.foi.uzdiz.jovidic.mvc.ResourceModel;

/**
 *
 * @author Suželje za caching uzorak dizajna
 */
public interface CacheI {
    public void kreiraj();
    public boolean dohvatiObjekt(String path);
    public void pobrisiNK();
    public void pobrisiNS();
    public void dodajObjekt(ResourceModel model);
    public void serijalizirajListu();
    public void deserijalizirajListu();
    public void popuniListu();
    public double velicinaListe();
    public void provjeriSlobodniProstor(File datoteka);
    public void isprazniNaZahtjev();
    public void isprazniMemorijaN(double n);
}
