/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.jovidic.factorymethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.foi.uzdiz.jovidic.main.MainClass;
import org.foi.uzdiz.jovidic.singleton.ContestSingleton;
import org.foi.uzdiz.jovidic.singleton.SystemOutSingleton;

/**
 * Klasa u kojoj se generiraju natjecatelji, vremena, dodaju bodovi
 *
 * @author jovidic
 */
public class RaceFactory extends Thread implements RaceInterface {

    NumberGenerator generatorBrojeva;
    ContestSingleton natjecanje;
    Set<RezultatFactory> listaNatjecatelja = new HashSet<>();
    List<RezultatFactory> listaRezultata;
    public List<RezultatFactory> rez = new ArrayList<>();
    Set<Integer> setID = new HashSet<>();
    List<Integer> listaID;
    public static List<RezultatFactory> vezanaLista = new ArrayList<>();
    public boolean prolaz = false;

    /**
     * Metoda koja služi za dodjelu bodova ovisno o postignutom vremenu na
     * utrci.
     *
     * @return
     */
    public List<RezultatFactory> dodijeliBodove() {
        int max = MainClass.brojSudionika;
        rez = generateContestent(max);

        int brojac = natjecanje.R;

        List<RezultatFactory> bodovi14M = new ArrayList<>();
        List<Integer> vrijeme14M = new ArrayList<>();

        List<RezultatFactory> bodovi18M = new ArrayList<>();
        List<Integer> vrijeme18M = new ArrayList<>();

        List<RezultatFactory> bodovi25M = new ArrayList<>();
        List<Integer> vrijeme25M = new ArrayList<>();

        List<RezultatFactory> bodovi35M = new ArrayList<>();
        List<Integer> vrijeme35M = new ArrayList<>();

        List<RezultatFactory> bodovi45M = new ArrayList<>();
        List<Integer> vrijeme45M = new ArrayList<>();

        List<RezultatFactory> bodovi77M = new ArrayList<>();
        List<Integer> vrijemeOstaliM = new ArrayList<>();

        List<RezultatFactory> bodovi14Z = new ArrayList<>();
        List<Integer> vremena14Z = new ArrayList<>();

        List<RezultatFactory> bodovi18Z = new ArrayList<>();
        List<Integer> vremena18Z = new ArrayList<>();

        List<RezultatFactory> bodovi25Z = new ArrayList<>();
        List<Integer> vremena25Z = new ArrayList<>();

        List<RezultatFactory> bodovi35Z = new ArrayList<>();
        List<Integer> vrijeme35Z = new ArrayList<>();

        List<RezultatFactory> bodovi45Z = new ArrayList<>();
        List<Integer> vrijeme45Z = new ArrayList<>();

        List<RezultatFactory> bodoviOstaloZ = new ArrayList<>();
        List<Integer> vrijemeOstali = new ArrayList<>();

        for (int i = 0; i < brojac + 1; i++) {
            for (RezultatFactory n : rez) {
                if (n.isSpol() == false && n.getSkupina() == 14 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijeme14M.add(n.getVrijeme());
                    bodovi14M.add(n);
                }

                if (n.isSpol() == false && n.getSkupina() == 18 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijeme18M.add(n.getVrijeme());
                    bodovi18M.add(n);
                }

                if (n.isSpol() == false && n.getSkupina() == 25 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijeme25M.add(n.getVrijeme());
                    bodovi25M.add(n);
                }

                if (n.isSpol() == false && n.getSkupina() == 35 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijeme35M.add(n.getVrijeme());
                    bodovi35M.add(n);
                }

                if (n.isSpol() == false && n.getSkupina() == 45 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijeme45M.add(n.getVrijeme());
                    bodovi45M.add(n);
                }

                if (n.isSpol() == false && n.getSkupina() > 45 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijemeOstaliM.add(n.getVrijeme());
                    bodovi77M.add(n);
                }

                if (n.isSpol() == true && n.getSkupina() == 14 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vremena14Z.add(n.getVrijeme());
                    bodovi14Z.add(n);
                }

                if (n.isSpol() == true && n.getSkupina() == 18 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vremena18Z.add(n.getVrijeme());
                    bodovi18Z.add(n);
                }

                if (n.isSpol() == true && n.getSkupina() == 25 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vremena25Z.add(n.getVrijeme());
                    bodovi25Z.add(n);
                }

                if (n.isSpol() == true && n.getSkupina() == 35 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijeme35Z.add(n.getVrijeme());
                    bodovi35Z.add(n);
                }

                if (n.isSpol() == true && n.getSkupina() == 45 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijeme45Z.add(n.getVrijeme());
                    bodovi45Z.add(n);
                }

                if (n.isSpol() == true && n.getSkupina() > 45 && n.getIdUtrke() == i && n.getVrijeme() > 100) {
                    vrijemeOstali.add(n.getVrijeme());
                    bodoviOstaloZ.add(n);
                }
            }
        }
        Collections.sort(vrijeme14M);
        Collections.sort(vrijeme18M);
        Collections.sort(vrijeme25M);
        Collections.sort(vrijeme35M);
        Collections.sort(vrijeme45M);
        Collections.sort(vrijemeOstaliM);

        score(vrijeme14M, bodovi14M);
        score(vrijeme18M, bodovi18M);
        score(vrijeme25M, bodovi25M);
        score(vrijeme35M, bodovi35M);
        score(vrijeme45M, bodovi45M);
        score(vrijemeOstaliM, bodovi77M);

        Collections.sort(vremena14Z);
        Collections.sort(vremena18Z);
        Collections.sort(vremena25Z);
        Collections.sort(vrijeme35Z);
        Collections.sort(vrijeme45Z);
        Collections.sort(vrijemeOstali);

        score(vremena14Z, bodovi14Z);
        score(vremena18Z, bodovi18Z);
        score(vremena25Z, bodovi25Z);
        score(vrijeme35Z, bodovi35Z);
        score(vrijeme45Z, bodovi45Z);
        score(vrijemeOstali, bodoviOstaloZ);

        bodovi14M.clear();
        bodovi18M.clear();
        bodovi25M.clear();
        bodovi35M.clear();
        bodovi45M.clear();
        bodovi77M.clear();

        bodovi14Z.clear();
        bodovi18Z.clear();
        bodovi25Z.clear();
        bodovi35Z.clear();
        bodovi45Z.clear();
        bodoviOstaloZ.clear();

        vrijeme14M.clear();
        vrijeme18M.clear();
        vrijeme25M.clear();
        vrijeme35M.clear();
        vrijeme45M.clear();
        vrijemeOstaliM.clear();

        vremena14Z.clear();
        vremena18Z.clear();
        vremena25Z.clear();
        vrijeme35Z.clear();
        vrijeme45Z.clear();
        vrijemeOstali.clear();

        for (RezultatFactory n : rez) {

            vezanaLista.add(n);
        }
        ispisPoUtrkama(rez);

        System.out.println("-Utrka je zavrsila");
        return vezanaLista;

    }

    @Override
    public void interrupt() {

        super.interrupt();
    }

    @Override
    public void run() {
        System.out.println("-Počela je utrka");
        //generirajBrojNatjecatelja(50);
        dodijeliBodove();
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     * Metoda kojom se generira broj natjecatelja po dobnim i spolnim skupinama.
     *
     * @param maxBrSudionika
     * @return
     */
    public List<RezultatFactory> generateContestent(int maxBrSudionika) {
        generatorBrojeva = new NumberGenerator();
        boolean spol;
        int dob;
        int ID = 0;
        int redniBroj;
        int bodovi = 0;
        int skupina = 0;
        int vrijeme;

        int R = generatorBrojeva.generateInRange(maxBrSudionika, 1);
        for (int i = 1; i <= R; i++) {

            //spol
            int R_spol = generatorBrojeva.generateInRange(2, 1);
            if (R_spol == 1) //zensko
            {
                spol = true;
            } else //musko
            {
                spol = false;
            }

            dob = generatorBrojeva.generateInRange(77, 7);

           
            while (setID.size() != R) {
                //ID se generira na temelju raspona skupine
                if (dob >= 7 && dob <= 14) {
                    ID = generatorBrojeva.generateInRange(maxBrSudionika, 1);
                } else if (dob >= 15 && dob <= 18) {
                    ID = generatorBrojeva.generateInRange(maxBrSudionika * 2, maxBrSudionika + 1);
                } else if (dob >= 19 && dob <= 25) {
                    ID = generatorBrojeva.generateInRange(maxBrSudionika * 3, maxBrSudionika * 2 + 1);
                } else if (dob >= 26 && dob <= 35) {
                    ID = generatorBrojeva.generateInRange(maxBrSudionika * 4, maxBrSudionika * 3 + 1);
                } else if (dob >= 36 && dob <= 45) {
                    ID = generatorBrojeva.generateInRange(maxBrSudionika * 5, maxBrSudionika * 4 + 1);
                } else if (dob >= 46 && dob <= 77) {
                    ID = generatorBrojeva.generateInRange(maxBrSudionika * 6, maxBrSudionika * 5 + 1);
                }
                setID.add(ID);
            }

            listaID = new ArrayList<>(setID);

            //redniBroj
            redniBroj = i;

            //vrijeme sudionika  
            vrijeme = generatorBrojeva.generateInRange(1000, 0);

            //skupina
            if (dob >= 7 && dob <= 14) {
                skupina = 14;
            } else if (dob >= 15 && dob <= 18) {
                skupina = 18;
            } else if (dob >= 19 && dob <= 25) {
                skupina = 25;
            } else if (dob >= 26 && dob <= 35) {
                skupina = 35;
            } else if (dob >= 36 && dob <= 45) {
                skupina = 45;
            } else if (dob >= 46 && dob <= 77) {
                skupina = 77;
            }

            natjecanje = ContestSingleton.getInstance();
            RezultatFactory natjecatelj = new RezultatFactory(listaID.get(i - 1), redniBroj, bodovi, skupina, vrijeme, spol, dob, natjecanje.utrkaID);
            listaNatjecatelja.add(natjecatelj);

            listaRezultata = new ArrayList<>(listaNatjecatelja);

            //listaRezultata.add(natjecatelj);
        }

        listaNatjecatelja.clear();
        setID.clear();
        listaID.clear();

        return listaRezultata;
    }

    /**
     * Metoda koja na temelju liste vremena dodjeljuje bodove natjecatelju.
     *
     * @param vremena
     * @param bodovi
     */
    public void score(List<Integer> vremena, List<RezultatFactory> bodovi) {
        for (int j = 0; j < vremena.size(); j++) {
            for (RezultatFactory nat : bodovi) {
                if (vremena.get(j).equals(nat.getVrijeme()) && j == 0) {
                    nat.setBodovi(25);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 1) {
                    nat.setBodovi(20);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 2) {
                    nat.setBodovi(15);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 3) {
                    nat.setBodovi(12);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 4) {
                    nat.setBodovi(10);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 5) {
                    nat.setBodovi(8);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 6) {
                    nat.setBodovi(6);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 7) {
                    nat.setBodovi(4);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 8) {
                    nat.setBodovi(2);
                }

                if (vremena.get(j).equals(nat.getVrijeme()) && j == 9) {
                    nat.setBodovi(1);
                }
            }
        }

    }

    /**
     * Ispis rezultata zajedno po bez podjele po dobnim i spolnim skupinama, s
     * obzirom da je RaceFactory dretva, metoda se izvrši svaki put kod utrke.
     *
     * @param lista
     */
    public void ispisPoUtrkama(List<RezultatFactory> lista) {
        SystemOutSingleton.getInstance().disqualify(lista);

        SystemOutSingleton.getInstance().sortirajPoBodovima(lista);
        for (RezultatFactory n : lista) {

            if (prolaz == false) {

                System.out.println("ISPIS REZULTATA ZA UTRKU");
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
        prolaz = false;

        //kraj
    }
}
