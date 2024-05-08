package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hrac.Hrac;
import balikKariet.BalikKariet;
import balikKariet.karty.zoznamInterface.NaVsetkych;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * vylozi sa tolko kariet kolko je hracov a postupne si kazdy vyberie 1
 */
public class Hokynarstvo extends Karta implements NaVsetkych {
    private BalikKariet balikKariet;
    private ArrayList<Karta> kartyNaVyber;
    //konstruktor inicializuje predka, kartyNaVyber a BalikKariet
    public Hokynarstvo(FarbaKarty farbaKarty, int cisloKarty, BalikKariet balikKariet) {
        super(farbaKarty, cisloKarty, OkrajKarty.HNEDY);
        this.balikKariet = balikKariet;
        this.kartyNaVyber = new ArrayList<>();
    }

    @Override
    public String nazov() {
        return "Hokynarstvo";
    }
    //ukaze zoznam kariet
    @Override
    public void pouzi(Collection<Hrac> collection, Hrac hracNaTahu) {
        for (int i = 0; i <  collection.size(); i++) {
            this.kartyNaVyber.add(this.balikKariet.dajKartu());
        }
        this.vyberSi(hracNaTahu);
        for (Hrac hrac : collection) {
            if (hrac.equals(hracNaTahu)) {
                continue;
            }
            this.vyberSi(hrac);
        }
    }
    //realizuje vyber
    public void vyberSi(Hrac hrac) {
        Scanner scanner = new Scanner(System.in);
        this.vypis();
        while (true) {
            try {
                int volba = scanner.nextInt();
                hrac.pridajKartu(this.kartyNaVyber.get(volba));
                this.kartyNaVyber.remove(volba);
                break;
            } catch (RuntimeException e) {
                System.out.println("Zla volba, vyber znova od 0-" + (this.kartyNaVyber.size() - 1));
                scanner.next();
            }
        }
    }

    public void vypis() {
        System.out.println("Na vyber su tieto karty: ");
        for (Karta karta : this.kartyNaVyber) {
            System.out.print(karta.nazov() + "  ");
        }
        System.out.println("Vyber si od 0-" + (this.kartyNaVyber.size() - 1));
    }
}
