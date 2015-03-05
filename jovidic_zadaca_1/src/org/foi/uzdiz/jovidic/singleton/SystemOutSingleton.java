/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.foi.uzdiz.jovidic.factorymethod.RaceFactory;
import org.foi.uzdiz.jovidic.factorymethod.RezultatFactory;

/**
 * Klasa koja služi za ispis konačnih podataka o utrkama u konzolu.
 *
 * @author jovidic
 */
public class SystemOutSingleton {

    private SystemOutSingleton() {
    }

    public static SystemOutSingleton getInstance() {
        return OutputTablicaHolder.INSTANCE;
    }

    private static class OutputTablicaHolder {

        private static final SystemOutSingleton INSTANCE = new SystemOutSingleton();

    }

    private List<RezultatFactory> listaRezultata = new ArrayList<>();
    private List<RezultatFactory> muski = new ArrayList<>();
    private List<RezultatFactory> zenske = new ArrayList<>();
    private List<RezultatFactory> rezultati14zenske = new ArrayList<>();
    private List<RezultatFactory> rezultati18zenske = new ArrayList<>();
    private List<RezultatFactory> rezultati25zenske = new ArrayList<>();
    private List<RezultatFactory> rezultati35zenske = new ArrayList<>();
    private List<RezultatFactory> rezultati45zenske = new ArrayList<>();
    private List<RezultatFactory> rezultatiostalozenske = new ArrayList<>();
    private List<RezultatFactory> rezultati14muski = new ArrayList<>();
    private List<RezultatFactory> rezultati18muski = new ArrayList<>();
    private List<RezultatFactory> rezultati25muski = new ArrayList<>();
    private List<RezultatFactory> rezultati35muski = new ArrayList<>();
    private List<RezultatFactory> rezultati45muski = new ArrayList<>();
    private List<RezultatFactory> rezultatiostalomuski = new ArrayList<>();
    public boolean prolaz = false;
    public List<List<RezultatFactory>> listaSvihLista = new ArrayList<>();
    public String spol;
    //public int kreiranBrojUtrka = 2;

    public List<List<RezultatFactory>> getListaSvihLista() {
        return listaSvihLista;
    }

    public List<RezultatFactory> getListaRezultata() {
        return listaRezultata;
    }

    public void setListaRezultata(List<RezultatFactory> listaRezultata) {
        this.listaRezultata = listaRezultata;
    }

    public void ispisiRezultate(List<RezultatFactory> lista) {

        this.listaRezultata = lista;

        for (RezultatFactory r : listaRezultata) {      //sortiranje liste po spolu 
            if (r.isSpol() == true) {
                zenske.add(r);
            } else {
                muski.add(r);
            }
        }

        for (RezultatFactory r : zenske) {       //sortiranje liste po zenskim skupinama
            if (r.getSkupina() == 14) {
                rezultati14zenske.add(r);
            } else if (r.getSkupina() == 25) {
                rezultati25zenske.add(r);
            } else if (r.getSkupina() == 35) {
                rezultati35zenske.add(r);
            } else if (r.getSkupina() == 45) {
                rezultati45zenske.add(r);
            } else if (r.getSkupina() == 77) {
                rezultatiostalozenske.add(r);
            } else if (r.getSkupina() == 18) {
                rezultati18zenske.add(r);
            }
        }
        for (RezultatFactory r : muski) {
            //sortiranje liste po muskim skupinama

            if (r.getSkupina() == 14) {
                rezultati14muski.add(r);
            } else if (r.getSkupina() == 25) {
                rezultati25muski.add(r);
            } else if (r.getSkupina() == 35) {
                rezultati35muski.add(r);
            } else if (r.getSkupina() == 45) {
                rezultati45muski.add(r);
            } else if (r.getSkupina() == 77) {
                rezultatiostalomuski.add(r);
            } else if (r.getSkupina() == 18) {
                rezultati18muski.add(r);
            }
        }

        if (rezultati14muski != null) {//dodavanje u listu svih lista
            listaSvihLista.add(rezultati14muski);
        }
        if (rezultati18muski != null) {
            listaSvihLista.add(rezultati18muski);
        }
        if (rezultati25muski != null) {
            listaSvihLista.add(rezultati25muski);
        }
        if (rezultati35muski != null) {
            listaSvihLista.add(rezultati35muski);
        }
        if (rezultati45muski != null) {
            listaSvihLista.add(rezultati45muski);
        }
        if (rezultatiostalomuski != null) {
            listaSvihLista.add(rezultatiostalomuski);
        }
        if (rezultati14zenske != null) {
            listaSvihLista.add(rezultati14zenske);
        }
        if (rezultati18zenske != null) {
            listaSvihLista.add(rezultati18zenske);
        }
        if (rezultati25zenske != null) {
            listaSvihLista.add(rezultati25zenske);
        }
        if (rezultati35zenske != null) {
            listaSvihLista.add(rezultati35zenske);
        }
        if (rezultati45zenske != null) {
            listaSvihLista.add(rezultati45zenske);
        }
        if (rezultatiostalozenske != null) {
            listaSvihLista.add(rezultatiostalozenske);
        }

        //Konačan ispis rezultata sortiran po utrkama, listama rezultata sortiranim po bodovima
        System.out.println("ISPIS TABLICE PO UTRKAMA I SKUPINAMA");
         System.out.println("*Napomena: Natjecatelji s vremenom manjim od 100ms su diskvalificirani");
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        ispisPoSkupinama(listaSvihLista);
        System.out.println("\n");
        //System.out.println("\n");
        //.out.println("ISPIS TABLICE PO UTRKAMA");
        //System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //ispisPoUtrkama(listaRezultata);
        List<List<RezultatFactory>> listaSvihLista = SystemOutSingleton.getInstance().getListaSvihLista();
        RaceFactory utrka = new RaceFactory();
        utrka.interrupt();
        DatotekaSingleton.getInstance().kreirajTxt(listaSvihLista, utrka.vezanaLista);
    }

    private void ispisPoSkupinama(List<List<RezultatFactory>> lista) {
        if (lista != null) {

            for (List<RezultatFactory> r : lista) {
                sortirajPoBodovima(r);
                disqualify(r);
                for (int i = 0; i <= ContestSingleton.getInstance().R; i++) {//pocetak

                    for (RezultatFactory n : r) {
                        if (n.getIdUtrke() == i) {

                            if (prolaz == false) {
                                if (n.isSpol() == true) {
                                    spol = "Zene";
                                } else {
                                    spol = "Muski";
                                }

                                System.out.println("ISPIS REZULTATA ZA SKUPINU: " + n.getSkupina() + " " + spol + " za utrku " + n.getIdUtrke());
                                System.out.format("%10s%15s%15s%15s%20s", "Bodovi", "Redni broj", "ID", "Dob", "Vrijeme");
                                System.out.println();
                                System.out.println("______________________________________________________________________________");
                                prolaz = true;

                            }
                            System.out.format("%10s%15s%15s%15s%20s",
                                    n.getBodovi(), n.getRedniBroj(), n.getIdNatjecatelja(),
                                    n.getDob(), n.getVrijeme());
                            System.out.println();

                        }
                    }
                    prolaz = false;

                    //kraj
                }
            }

        }

    }

    public void disqualify(List<RezultatFactory> list) {
        List<RezultatFactory> toRemove = new ArrayList<>();
        for (RezultatFactory a : list) {
            if (a.getVrijeme() <= 100) {
                toRemove.add(a);
            }
        }
        list.removeAll(toRemove);

    }

    public void sortirajPoBodovima(List<RezultatFactory> list) {
        Collections.sort(list, new Comparator<RezultatFactory>() {
            @Override
            public int compare(RezultatFactory z1, RezultatFactory z2) {
                if (z1.getVrijeme() > z2.getVrijeme()) {
                    return 1;
                }
                if (z1.getVrijeme() < z2.getVrijeme()) {
                    return -1;
                }
                return 0;
            }
        });
    }

}
