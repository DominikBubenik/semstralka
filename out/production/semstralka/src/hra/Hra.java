package hra;

import hrac.BalikCharakterov;
import hrac.Charakter;
import hrac.Hrac;
import balikKariet.BalikKariet;
import hrac.postavy.BalikPostav;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * obsahuje herny cyklus, vytvara celu hru- postavy, pocet hracov
 */
public class Hra {
    private VykonavacPrikazov vykonavac;
    private BalikKariet balikKariet;
    private ArrayList<Hrac> zoznamHracov;
    //inicializuje zoznam hracov, balik kariet, vykonavac prikazov
    public Hra() {
        this.zoznamHracov = new ArrayList<>();
        this.balikKariet = BalikKariet.getInstance();
        this.vykonavac = new VykonavacPrikazov(Collections.unmodifiableCollection(this.zoznamHracov), this);
        this.privitanie();
        this.zaciatok();
    }

    public void privitanie() {
        System.out.println("Vitaj v kartovej hre Bang!");
        System.out.println("Ak nebudes vediet co mas robit zadaj '?'");
        System.out.println("prikazy su: zobraz hracov/karty; zahraj [nazov karty]; info [meno hraca]; pouzi; skonci; koniec tahu");
        System.out.println("Podme na to!");
    }
    //rozda postavy a charakteri pre dany pocet hracov
    public void zaciatok() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Zadaj pocet hracov 2 - 7");
        int pocetHracov = 0;
        try {
            pocetHracov = scanner.nextInt();
            if (pocetHracov < 2 || pocetHracov > 7) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("Zly vstup, zadaj cele cislo 2-7!");
            this.zaciatok();
        }
        BalikCharakterov balikCharakterov = new BalikCharakterov(pocetHracov);
        BalikPostav balikPostav = new BalikPostav();
        //nastavenie postav, mien, charakterov
        for (int i = 0; i < pocetHracov; i++) {
            System.out.println(i + 1 + ". hrac, zadaj svoje meno: ");
            String meno = scanner.next();
            Charakter charakter = balikCharakterov.dajCharakter();
            this.zoznamHracov.add(balikPostav.dajPostavu());
            this.zoznamHracov.get(i).setMeno(meno);
            this.zoznamHracov.get(i).setCharakter(charakter);
            System.out.println(meno + " tvoja postava je " + this.zoznamHracov.get(i).getClass().getSimpleName() + ", tvoj charakter " + charakter);
        }
        Hrac serif = this.zoznamHracov.stream().filter(hrac -> hrac.getCharakter().equals(Charakter.SHERIF)).findAny().get();
        this.zoznamHracov.remove(serif);
        this.zoznamHracov.add(0, serif);
        this.priebehHry();
    }
    //herny cyklus
    public void priebehHry() {
        int poradie = 0;
        int nasledujuci = 1;
        while (this.hraPokracuje()) {

            if (this.zoznamHracov.size() == poradie) {
                poradie = 0;
            }
            if (this.zoznamHracov.size() == nasledujuci) {
                nasledujuci = 0;
            }
            boolean koniecTahu = false;
            this.nastavVzdialenosti(); //pre novy tah sa zmenia vzdialenosti medzi hracomNaTahu a ostatnymi
            //novy tah
            Tah tah = new Tah(Collections.unmodifiableCollection(this.zoznamHracov), this.zoznamHracov.get(poradie), this.zoznamHracov.get(nasledujuci));
            koniecTahu = tah.ukoncenieTahuPredcasne();
            if (!koniecTahu) {
                System.out.println("Si na tahu " + tah.getHracNaTahu().getMeno());
                tah.getHracNaTahu().setMozeHratBang(true);
                this.vykonavac.setTah(tah);

                while (!koniecTahu) {
                    koniecTahu = this.vykonavac.nacitajPrikaz();
                }
            }
            tah.koniecTahu();
            poradie++;
            nasledujuci++;
            this.zoznamHracov = new ArrayList<>(tah.getZoznamHracov()); //aktualizuje zoznam hracov
        }
    }

    //nastavi vzdialenosti podla poctu hracov
    public void nastavVzdialenosti() {
        if (this.zoznamHracov.size() % 2 == 0) {
            int hranica = (int)Math.floor(this.zoznamHracov.size() / 2);
            int x = hranica;
            for (int i = 0; i < this.zoznamHracov.size(); i++) {
                if (i < hranica) {
                    this.zoznamHracov.get(i).setVzdialenost(i);
                } else {
                    this.zoznamHracov.get(i).setVzdialenost(x);
                    x--;
                }
            }
        } else {
            int hranica = (int)Math.floor(this.zoznamHracov.size() / 2);
            int x = hranica;
            for (int i = 0; i < this.zoznamHracov.size(); i++) {
                if (i <= hranica) {
                    this.zoznamHracov.get(i).setVzdialenost(i);
                } else {
                    this.zoznamHracov.get(i).setVzdialenost(x);
                    x--;
                }
            }
        }

    }

    //kontrola ci hra ma dostatok hracov
    public boolean hraPokracuje() {
        boolean banditi = this.zoznamHracov.stream().anyMatch(hrac -> hrac.getCharakter().equals(Charakter.BANDITA));
        boolean odpadlik = this.zoznamHracov.stream().anyMatch(hrac -> hrac.getCharakter().equals(Charakter.ODPADLIK));
        boolean vice = this.zoznamHracov.stream().anyMatch(hrac -> hrac.getCharakter().equals(Charakter.VICE));
        boolean sherif = this.zoznamHracov.stream().anyMatch(hrac -> hrac.getCharakter().equals(Charakter.SHERIF));
        if (!sherif) {
            if (banditi) {
                System.out.println("Sherif zomrel vyhrali Banditi!");
            } else if (!vice && !banditi) {
                System.out.println("Sherif zomrel vyhral Odpadlik");
            }
            return false;
        } else if (!banditi && !odpadlik) {
            System.out.println("Vyhral Sherif a Vicovia");
            return false;
        }
        return true;
    }



}
