
package org.foi.uzdiz.jovidic.mvc.composite.model;

import java.util.List;

/**
 *
 * @author jovidic
 *
 * Sučelje za composite uzorak dizajna.
 */
public interface ComponentInterface {

    public void add(ComponentInterface employee);

    public void remove(ComponentInterface employee);

    public List<ComponentInterface> getChild();

    public String getName();

    public Long getSize();

    public Long getVrijemePromjene();

    public void print(int i);

    public Boolean isDir();

    public String getPutanjaStablo();

    //public void dajElementeRazine(List<ComponentInterface> o,ComponentInterface i, int pocetniNivo);

    public int postaviRazineStabla(int i);

    public void setRazinu(int razina);

    public int getRazina();
}
