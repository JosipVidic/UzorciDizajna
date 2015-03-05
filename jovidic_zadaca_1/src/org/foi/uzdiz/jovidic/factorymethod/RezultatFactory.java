/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.factorymethod;

/**
 *  POJO koji definira izgled objekta rezultat
 * @author jovidic
 */
public class RezultatFactory {

    int idNatjecatelja;
    int redniBroj;
    int bodovi;
    int skupina;
    int vrijeme;
    boolean spol;//0 za musko, 1 za zensko
    int dob;
    int idUtrke;

    public RezultatFactory(int idNatjecatelja, int redniBroj, int bodovi, int skupina, int vrijeme, boolean spol, int dob, int idUtrke) {
        this.idNatjecatelja = idNatjecatelja;
        this.redniBroj = redniBroj;
        this.bodovi = bodovi;
        this.skupina = skupina;
        this.vrijeme = vrijeme;
        this.spol = spol;
        this.dob = dob;
        this.idUtrke = idUtrke;
    }

    public RezultatFactory() {
    }

    public boolean isSpol() {
        return spol;
    }

    public void setSpol(boolean spol) {
        this.spol = spol;
    }

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public int getIdNatjecatelja() {
        return idNatjecatelja;
    }

    public void setIdNatjecatelja(int idNatjecatelja) {
        this.idNatjecatelja = idNatjecatelja;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public int getSkupina() {
        return skupina;
    }

    public void setSkupina(int skupina) {
        this.skupina = skupina;
    }

    public int getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(int vrijeme) {
        this.vrijeme = vrijeme;
    }

    public int getIdUtrke() {
        return idUtrke;
    }

    public void setIdUtrke(int idUtrke) {
        this.idUtrke = idUtrke;
    }

}
