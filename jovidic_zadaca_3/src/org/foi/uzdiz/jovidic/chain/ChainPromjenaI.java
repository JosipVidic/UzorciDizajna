
package org.foi.uzdiz.jovidic.chain;

import org.foi.uzdiz.jovidic.chain.model.ModelPromjena;
import java.io.PrintWriter;

/**
 *
 * @author jovidic 
 * 
 * Sučelje koje će implementirati klase promjena
 */
public interface ChainPromjenaI {

    public abstract void setNext(ChainPromjenaI next);

    public abstract void process(ModelPromjena model, PrintWriter writer, int tip, String dat);

    public abstract void write(ModelPromjena model, PrintWriter writer, String dat);

}
