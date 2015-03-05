
package org.foi.uzdiz.jovidic.factorymethod;

/**
 *
 * @author jovidic
 *
 * Klasa koja definira tip mogućih implementacija FactoryMethod sučelja
 */
public class TypeFactory {

    public FactoryI dajTip(String tip) {
        if (tip == null) {
            return null;
        } else if (tip.equalsIgnoreCase("FILE")) {
            return new File();

        } else if (tip.equalsIgnoreCase("DIR")) {
            return new Directory();

        }

        return null;
    }

}
