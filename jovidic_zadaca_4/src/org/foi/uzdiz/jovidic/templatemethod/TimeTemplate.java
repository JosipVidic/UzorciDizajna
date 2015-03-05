
package org.foi.uzdiz.jovidic.templatemethod;

import org.foi.uzdiz.jovidic.mvc.ResourceModel;
import static org.foi.uzdiz.jovidic.templatemethod.LogTemplate.errorData;

/**
 *  Klasa koja dodaje log u listu devnika za vrijeme korištenja elementa u memoriji
 * @author jovidic
 */
public class TimeTemplate extends LogTemplate {

    @Override
    void dodajError(ResourceModel error) {
   
        long seconds = (error.getZadnjeKoristenje().getTime() - error.getVrijemeSpremanja().getTime())/1000;

        
        String dodaj = "    t   Objekt "+error.getNaziv() + " je bio u spremištu " + seconds + " sekundi";
        errorData.add(dodaj);
    }

}
