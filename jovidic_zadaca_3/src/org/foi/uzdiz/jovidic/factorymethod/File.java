
package org.foi.uzdiz.jovidic.factorymethod;

import org.foi.uzdiz.jovidic.mvc.composite.model.LeafFile;

/**
 *
 * @author jovidic Klasa koja implementira FactoryMethod sučelje te kreira novi
 * Leaf datoteka.
 */
public class File implements FactoryI {

    @Override
    public LeafFile kreiraj(String naziv, long velicina, boolean isDir, long vrijemePromjene, String prethodnaPutanja, String putanjaKrozStablo) {
        LeafFile i = new LeafFile(naziv, velicina, false, vrijemePromjene, prethodnaPutanja + "->" + putanjaKrozStablo);
        return i;
    }

}
