
package org.foi.uzdiz.jovidic.memento;

import org.foi.uzdiz.jovidic.memento.model.MementoModel;

/**
 *
 * @author jovidic
 *
 * Kreira i sprema stanja u Memento objekte.
 */
public class Originator {

    private MementoModel stanje;

    public void setState(MementoModel state) {
        this.stanje = state;
    }

    public MementoModel getState() {
        return stanje;
    }

    public Memento saveStateToMemento() {
        return new Memento(stanje);
    }

    public void getStateFromMemento(Memento memento) {
        stanje = memento.getState();
    }
}
