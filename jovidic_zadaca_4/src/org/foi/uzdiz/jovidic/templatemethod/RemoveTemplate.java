package org.foi.uzdiz.jovidic.templatemethod;

import org.foi.uzdiz.jovidic.mvc.ResourceModel;

/**
 * Klasa koja dodaje log u listu devnika za uklanjanje objekta iz liste
 *
 * @author jovidic
 */
public class RemoveTemplate extends LogTemplate {

    @Override
    void dodajError(ResourceModel error) {
        String dodaj = "    -   Objekt " + error.getNaziv() + " je uklonjen iz spremišta u " + error.getZadnjeKoristenje();
        errorData.add(dodaj);
    }

}
