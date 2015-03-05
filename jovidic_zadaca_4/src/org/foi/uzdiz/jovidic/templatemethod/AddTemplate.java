
package org.foi.uzdiz.jovidic.templatemethod;

import org.foi.uzdiz.jovidic.mvc.ResourceModel;
import static org.foi.uzdiz.jovidic.templatemethod.LogTemplate.errorData;

/**
 *  Klasa koja dodaje log u listu devnika za dodavanje objekta u spremište
 * @author jovidic
 */
public class AddTemplate extends LogTemplate {

    @Override
    void dodajError(ResourceModel error) {
        String dodaj = "    +   Objekt "+error.getNaziv() + " je dodan u spremište " + error.getVrijemeSpremanja();
        errorData.add(dodaj);
    }

}
