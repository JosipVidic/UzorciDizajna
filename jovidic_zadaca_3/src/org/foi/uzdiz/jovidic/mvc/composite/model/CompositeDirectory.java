
package org.foi.uzdiz.jovidic.mvc.composite.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jovidic
 *
 * Model za direktorij koja implementira sučelje ComponentInterface
 */
public class CompositeDirectory implements ComponentInterface {

    private String name;
    private Long size;
    private Boolean dir;
    private Long vrijemePromjene;
    private String putanjaStablo;
    private int razina;

    List<ComponentInterface> children = new ArrayList<>();

    public CompositeDirectory(String name, long size, boolean dir, long vrijemePromjene, String putanjaKrozStablo) {
        this.name = name;
        this.size = size;
        this.dir = dir;
        this.vrijemePromjene = vrijemePromjene;
        this.putanjaStablo = putanjaKrozStablo;

    }

    @Override
    public String getPutanjaStablo() {
        return putanjaStablo;
    }

    @Override
    public void add(ComponentInterface employee) {
        children.add(employee);
    }

    @Override
    public void remove(ComponentInterface employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComponentInterface> getChild() {
        return children;
    }

    @Override
    public String getName() {
        return name;
    }

    public void remove(CompositeDirectory employee) {
        children.remove(employee);
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public Long getVrijemePromjene() {
        return vrijemePromjene;
    }

    @Override
    public Boolean isDir() {
        return dir;
    }

    public void setDir(Boolean dir) {
        this.dir = dir;
    }

    @Override
    public void print(int i) {

        char[] charArray = new char[i];
        Arrays.fill(charArray, '\t');
        String str = new String(charArray);

        char[] charArray2 = new char[i + 1];
        Arrays.fill(charArray2, '\t');
        String str2 = new String(charArray);
        System.out.println(str2 + " ↳  Direktorij (razina " + razina + ")=> " + name);

        //+ " velicine      ==>     " + getSize() + " putanja u stablu       ===>   " + getPutanjaStablo()
        for (ComponentInterface e : children) {
            e.print(i + 1);
        }

    }
/*
    @Override
    public void dajElementeRazine(List<ComponentInterface> o, ComponentInterface i, int pocetniNivo) {
        if (razina == pocetniNivo) {

            System.out.println("JA SAM RAZINE 2");
            factory = factoryType.dajTip("DIR");
            CompositeDirectory compositeModel = (CompositeDirectory) factory.kreiraj(name, size, false, vrijemePromjene, putanjaStablo, null);
            o.add(compositeModel);

        } else if(razina!=pocetniNivo) {

            for (ComponentInterface u : children) {
                dajElementeRazine(o, u, pocetniNivo);
            }

        }
    }*/

    @Override
    public int postaviRazineStabla(int i) {
        for (ComponentInterface o : children) {
            o.postaviRazineStabla(i + 1);
        }
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
