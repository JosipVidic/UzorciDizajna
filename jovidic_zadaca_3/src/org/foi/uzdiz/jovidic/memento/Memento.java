
package org.foi.uzdiz.jovidic.memento;

import org.foi.uzdiz.jovidic.memento.model.MementoModel;

/**
 *
 * @author jovidic
 *
 * Sadrži stanje objekta koji će se dohvatiti.
 */
public class Memento {

    private MementoModel stanje;

    public Memento(MementoModel stanje) {
        this.stanje = stanje;
    }

    public MementoModel getState() {
        return stanje;
    }

}
