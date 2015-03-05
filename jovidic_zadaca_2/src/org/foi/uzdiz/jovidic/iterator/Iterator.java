package org.foi.uzdiz.jovidic.iterator;

/**
 *
 * @author josip
 *
 * Sučelje koje definira metode iteratora.
 */
public interface Iterator {

    public boolean hasNext();

    public Object next();

    public String currentItem();

    public void sortiraj(String zapis);
    
    public void nadiIshodisnuGrupu();
}
