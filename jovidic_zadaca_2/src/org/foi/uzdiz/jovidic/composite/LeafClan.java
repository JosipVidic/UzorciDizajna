/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jovidic
 *
 * Leaf za korisnika u composite strukturi.
 */
public class LeafClan implements ComponentKorisnici {

    private List<ComponentKorisnici> childComponents = new ArrayList<>();
    private List<ComponentKorisnici> parentComponents = new ArrayList<>();
    private String sifra;
    private String naziv;
    private int vrsta;
    private CompositeGrupa parent;
    private int admin;

    public LeafClan(String sifra, String naziv, int vrsta, CompositeGrupa parent, int admin) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.vrsta = vrsta;
        this.parent = parent;
        this.admin = admin;
    }

    public LeafClan() {

    }

    @Override
    public int getAdmin() {
        return admin;
    }

    @Override
    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public void addParent(ComponentKorisnici employee) {
        parentComponents.add(employee);
    }

    @Override
    public void removeParent(ComponentKorisnici employee) {
        parentComponents.remove(employee);
    }



    public void show() {
        System.out.println("Leaf je : " + sifra + naziv + vrsta + parent);
    }

    public void addChild(ComponentKorisnici component) {
        childComponents.add(component);
    }

    public void removeChild(ComponentKorisnici component) {
        childComponents.remove(component);
    }


    @Override
    public String getSifra() {
        return sifra;
    }

    @Override
    public String getNaziv() {
        return naziv;
    }

    @Override
    public int getVrsta() {
        return vrsta;
    }

    @Override
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }



    @Override
    public List<ComponentKorisnici> dajRoditelje() {
        return parentComponents;
    }


    @Override
    public List<ComponentKorisnici> dajDjecu() {
        return childComponents;
    }

    @Override
    public void print(int i) {
        char[] charArray = new char[i];
        Arrays.fill(charArray, '\t');

        String str = new String(charArray);
        char[] charArray2 = new char[i + 1];
        Arrays.fill(charArray2, '\t');
        String str2 = new String(charArray);

        System.out.println(str2 + " ↳  Naziv => " + naziv);
    }

    @Override
    public void print2(int i) {
        char[] charArray = new char[i];
        Arrays.fill(charArray, '\t');

        String str = new String(charArray);
        char[] charArray2 = new char[i + 1];
        Arrays.fill(charArray2, '\t');
        String str2 = new String(charArray);
        for (ComponentKorisnici e : parentComponents) {
            e.print2(i + 1);
        }
    }

}
