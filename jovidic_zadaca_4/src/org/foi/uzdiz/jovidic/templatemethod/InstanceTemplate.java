
package org.foi.uzdiz.jovidic.templatemethod;

import java.util.Date;
import org.foi.uzdiz.jovidic.mvc.ResourceModel;

/**
 *  Klasa koja dodaje log kreiranje nove instance programa
 * @author jovidic
 */
public class InstanceTemplate extends LogTemplate {

    @Override
    void dodajError(ResourceModel error) {
        Date now = new Date();
        String dodajNice = "================================================================================================================================================";
        String dodaj = "Nova instanca programa pokrenuta je u " + now;
        errorData.add(dodajNice);
        errorData.add(dodaj);
        errorData.add(dodajNice);
    }

}
