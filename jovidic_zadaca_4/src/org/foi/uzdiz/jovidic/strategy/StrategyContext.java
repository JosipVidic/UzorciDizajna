
package org.foi.uzdiz.jovidic.strategy;

/**
 * Klasa koja sadrži kontekst strategije te poziva izbacivanje elementa
 *
 * @author jovidic
 */
public class StrategyContext {

    private StrategyI strategy;

    public StrategyContext(StrategyI strategy) {
        this.strategy = strategy;
    }

    public void izvrsiStrategiju() {
        strategy.izbaci();
    }
}
