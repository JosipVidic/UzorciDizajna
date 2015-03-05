
package org.foi.uzdiz.jovidic.memento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jovidic
 *
 * Objekt koji je odgovoran za dohvaćanje stanja Mementa.
 */
public class Caretaker {

    private List<Memento> listaStanja = new ArrayList<>();

    public void add(Memento state) {
        listaStanja.add(state);
    }

    public Memento get(int index) {
        return listaStanja.get(index);
    }

    public List<Memento> getall() {
        return listaStanja;
    }

    /**
     * Funkcija koja briše listu stanja
     */
    public void clearStanja() {
        listaStanja.clear();

    }

}
