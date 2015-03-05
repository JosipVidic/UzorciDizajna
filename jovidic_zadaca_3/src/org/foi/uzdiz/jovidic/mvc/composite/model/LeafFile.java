
package org.foi.uzdiz.jovidic.mvc.composite.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jovidic
 *
 * Model za datoteku koja implementira sučelje ComponentInterface
 */
public class LeafFile implements ComponentInterface {

    private String name;
    private Long size;
    private Boolean dir;
    private Long vrijemePromjene;
    private String putanjaStablo;
    private int razina;

    //naziv vrsta vrijeme promjene ii veličina
    List<CompositeDirectory> employees = new ArrayList<>();

    public LeafFile(String name, long size, boolean dir, long vrijemePromjene, String putanjaKrozStablo) {
        this.name = name;
        this.size = size;
        this.dir = dir;
        this.vrijemePromjene = vrijemePromjene;
        this.putanjaStablo = putanjaKrozStablo;
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public void add(ComponentInterface employee) {
        //Nije implementirano jer objekt leaf i ne moze biti roditelj
    }

    @Override
    public String getPutanjaStablo() {
        return putanjaStablo;
    }

    @Override
    public Long getVrijemePromjene() {
        return vrijemePromjene;
    }

    @Override
    public void remove(ComponentInterface employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComponentInterface> getChild() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isDir() {
        return dir;
    }

    @Override
    public void print(int i) {

        char[] charArray = new char[i];
        Arrays.fill(charArray, '\t');

        String str = new String(charArray);
        char[] charArray2 = new char[i + 1];
        Arrays.fill(charArray2, '\t');
        String str2 = new String(charArray);

        System.out.println(str2 + " ↳  Datoteka (razina " + razina + ")=> " + name + "   velicine ==> " + size + "   putanja u stablu  ==>    " + putanjaStablo);

    }
/*
    @Override
    public void dajElementeRazine(List<ComponentInterface> o, ComponentInterface i, int pocetniNivo) {
        if (razina == pocetniNivo) {
            System.out.println("JA SAM RAZINE 2");
            factory = factoryType.dajTip("FILE");
            LeafFile leafModel = (LeafFile) factory.kreiraj(name, size, false, vrijemePromjene, putanjaStablo, null);
            o.add(leafModel);

        }
    }*/

    @Override
    public int postaviRazineStabla(int i) {
        setRazinu(i);
        return i;
    }

    @Override
    public void setRazinu(int razina) {
        this.razina = razina;
    }

    @Override
    public int getRazina() {
        return razina;
    }

}
