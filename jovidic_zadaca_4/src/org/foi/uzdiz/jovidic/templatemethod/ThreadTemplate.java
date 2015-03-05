package org.foi.uzdiz.jovidic.templatemethod;

import org.foi.uzdiz.jovidic.mvc.ResourceModel;

/**
 * Klasa koja dodaje log u listu devnika za promjenu objekta od strane dretve
 *
 * @author jovidic
 */
public class ThreadTemplate extends LogTemplate {

    @Override
    void dodajError(ResourceModel error) {

        String dodaj = "    d   Dretva je izmjenila objekt " + error.getNaziv() + " sada mu je vrijeme koristenja " + error.getBrojKoristenja() + " a vrijeme zadnjeg koristenja " + error.getZadnjeKoristenje();
        errorData.add(dodaj);

    }

}
