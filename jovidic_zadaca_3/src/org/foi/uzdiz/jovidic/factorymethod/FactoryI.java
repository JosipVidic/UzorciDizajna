package org.foi.uzdiz.jovidic.factorymethod;

import org.foi.uzdiz.jovidic.mvc.composite.model.ComponentInterface;

/**
 *
 * @author jovidic
 *
 * Sučelje za FactoryMethod koji sadrži metode koje klase moraju implementirati
 */
public interface FactoryI {

    public ComponentInterface kreiraj(String naziv, long velicina, boolean isDir, long vrijemePromjene, String prethodnaPutanja, String putanjaKrozStablo);

}
