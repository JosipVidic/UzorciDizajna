/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.main;

/**
 *
 * @author jovidic Klasa Akcija predstavlja objekt akcija i sadrži njegov
 * konstruktor, gettere i setterem.
 */
public class Akcija {

    private String sifraAkcije;
    private String nazivKreatora;
    private int tipAkcije;
    private int vrstaAkcije;

    public Akcija(String sifraAkcije, String nazivKreatora, int tipAkcije, int vrstaAkcije) {
        this.sifraAkcije = sifraAkcije;
        this.nazivKreatora = nazivKreatora;
        this.tipAkcije = tipAkcije;
        this.vrstaAkcije = vrstaAkcije;
    }

    public void show() {
        System.out.println("Leaf je : " + sifraAkcije + nazivKreatora + vrstaAkcije + tipAkcije);
    }

    public String getSifra() {
        return sifraAkcije;
    }

    public String getNaziv() {
        return nazivKreatora;
    }

    public int getVrsta() {
        return vrstaAkcije;
    }

    public void setSifra(String sifra) {
        this.sifraAkcije = sifra;
    }

    public void setNaziv(String naziv) {
        this.nazivKreatora = naziv;
    }

    public void setVrsta(int vrsta) {
        this.vrstaAkcije = vrsta;
    }

    public int getTip() {
        return tipAkcije;
    }

    public void print() {
        System.out.println("-------------");

        System.out.println("Šifra akcije : " + getSifra() + " kreator akcije je: " + getNaziv());

        System.out.println("-------------");
    }

}
