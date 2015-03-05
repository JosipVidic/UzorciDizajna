
package org.foi.uzdiz.jovidic.templatemethod;

import org.foi.uzdiz.jovidic.mvc.ResourceModel;
import static org.foi.uzdiz.jovidic.templatemethod.LogTemplate.errorData;

/**
 *  Klasa koja dodaje log u listu devnika za promjenu broja korištenja objekta
 * @author jovidic
 */
public class UsedTemplate extends LogTemplate {

    @Override
    void dodajError(ResourceModel error) {
        String dodaj = "    *   Objekt "+error.getNaziv() + " je korišten  " + error.getBrojKoristenja() + " puta ";
        errorData.add(dodaj);
    }

}
