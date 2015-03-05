
package org.foi.uzdiz.jovidic.factorymethod;

import org.foi.uzdiz.jovidic.mvc.composite.model.CompositeDirectory;

/**
 *
 * @author jovidic
 *
 * Klasa koja implementira FactoryMethod sučelje te kreira novi Composite
 * direktorij.
 */
public class Directory implements FactoryI {

    @Override
    public CompositeDirectory kreiraj(String naziv, long velicina, boolean isDir, long vrijemePromjene, String prethodnaPutanja, String putanjaKrozStablo) {
        CompositeDirectory i = new CompositeDirectory(naziv, velicina, true, vrijemePromjene, prethodnaPutanja + "->" + putanjaKrozStablo);
        return i;
    }

}
