package hrac;

import balikKariet.BalikKariet;
import balikKariet.karty.Karta;
import balikKariet.karty.zbrane.Zbran;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * trieda reprezentuje samotneho hraca, ma pocet zivotov, charakter, zoznam kariet, vie hrat karty...
 */
public class Hrac {
    private ArrayList<Karta> zoznamKarietPredSebou;
    private String meno;
    private int pocetZivotov;
    private final int zivotyMax;
    private ArrayList<Karta> zoznamKarietNaRuke;
    private Charakter charakter;
    private boolean mozeHratBang;
    private int vzdialenost;
    private int vidiNaVzdialenost;
    private int dostrelNaVzdialenost;

    //nastavi sa pocet zivotov, pridaju sa karty na ruke, dostrel
    public Hrac(int zivotyMax) {
        this.zivotyMax = zivotyMax;
        this.pocetZivotov = this.zivotyMax;
        this.zoznamKarietNaRuke = new ArrayList<>();
        this.zoznamKarietPredSebou = new ArrayList<>();
        for (int i = 0; i < this.pocetZivotov; i++) {
            this.zoznamKarietNaRuke.add(BalikKariet.getInstance().dajKartu());
        }
        this.meno = "Default";
        this.mozeHratBang = true;
        this.vzdialenost = 0;
        this.dostrelNaVzdialenost = 1;
        this.vidiNaVzdialenost = 1;
    }

    public int getVzdialenost() {
        if (this.maToPredSebou("Mustang")) {
            return this.vzdialenost + 1;
        }
        return this.vzdialenost;
    }

    public Charakter getCharakter() {
        return this.charakter;
    }


    public int getVidiNaVzdialenost() {
        if (this.maToPredSebou("Apallosa")) {
            return this.vidiNaVzdialenost + 1;
        }
        return this.vidiNaVzdialenost;
    }
    //vrati vzdialenost na ktoru moze hrac strielat Bang
    public int getDostrelNaVzdialenost() {
        if (this.zoznamKarietPredSebou.stream().anyMatch(karta -> karta instanceof Zbran)) {
            Zbran zbran = (Zbran)this.zoznamKarietPredSebou.stream().filter(karta -> karta instanceof Zbran).findAny().get();
            return zbran.getDostrel() + this.getVidiNaVzdialenost();
        }
        return this.dostrelNaVzdialenost;
    }

    public void setVzdialenost(int vzdialenost) {
        this.vzdialenost = vzdialenost;
    }

    public void setCharakter(Charakter charakter) {
        this.charakter = charakter;
        if (charakter.equals(Charakter.SHERIF)) {
            this.pocetZivotov = this.zivotyMax + 1;
        }
    }

    public boolean getMozeHratBang() {
        if (this.maToPredSebou("Volcanic")) {
            return true;
        }
        return this.mozeHratBang;
    }

    public void setMozeHratBang(boolean b) {
        this.mozeHratBang = b;
    }

    public int getPocetZivotov() {
        return this.pocetZivotov;
    }

    public String getMeno() {
        return this.meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }
    public int getPocetKarietNaRuke() {
        return this.zoznamKarietNaRuke.size();
    }

    public int getPocetKarietPredSebou() {
        return this.zoznamKarietPredSebou.size();

    }
    //hracovi prida 2 karty
    public void faza1() {
        this.pridajKartu(BalikKariet.getInstance().dajKartu());
        this.pridajKartu(BalikKariet.getInstance().dajKartu());
    }
    //skontroluje ci hrac ma danu kartu na ruke
    public boolean maTutoKartu(String nazov) {
        return this.zoznamKarietNaRuke.stream().anyMatch(karta -> karta.nazov().equals(nazov));
    }
    //hrac odovzda kartu z ruky podla nahodneho vyberu
    public Optional<Karta> odovzdajKartuZRuky(int poradie) {
        Optional<Karta> karta = Optional.ofNullable(this.zoznamKarietNaRuke.get(poradie));
        karta.filter(this.zoznamKarietNaRuke:: contains).ifPresent(this.zoznamKarietNaRuke::remove);
        return karta;
    }

    //hrac zahodi kartu
    public void zahodKartu(String nazov) {
        Optional<Karta> zahodKarta = this.zoznamKarietNaRuke.stream().filter(karta -> karta.nazov().equals(nazov)).findAny();
        if (zahodKarta.isPresent()) {
            this.zoznamKarietNaRuke.remove(zahodKarta.get());
            BalikKariet.getInstance().zahodKartu(zahodKarta.get());
        }
    }

    //skontroluje ci hrac ma danu kartu na stole
    public boolean maToPredSebou(String nazov) {
        return this.zoznamKarietPredSebou.stream().anyMatch(karta -> karta.nazov().equals(nazov));
    }
    //hrac odovzda kartu zo stola
    public Optional<Karta> odovzdajKartuZoStola(int poradie) {
        Optional<Karta> karta = Optional.ofNullable(this.zoznamKarietPredSebou.get(poradie));
        karta.filter(this.zoznamKarietPredSebou:: contains).ifPresent(this.zoznamKarietPredSebou::remove);
        return karta;
    }
    //vrati len efekt hladanej karty
    public Optional<Karta> dajEfektKarty(String nazov) {
        Optional<Karta> karta = this.zoznamKarietPredSebou.stream().filter(kar -> kar.nazov().equals(nazov)).findAny();
        return karta;
    }

    public Optional<Karta> zahodKartuZoStola(String nazov) {
        Optional<Karta> karta = this.zoznamKarietPredSebou.stream().filter(kar -> kar.nazov().equals(nazov)).findAny();
        karta.filter(this.zoznamKarietPredSebou:: contains).ifPresent(this.zoznamKarietPredSebou::remove);
        karta.ifPresent(k -> BalikKariet.getInstance().zahodKartu(k));
        return karta;
    }

    public void pridajKartu(Karta karta) {
        this.zoznamKarietNaRuke.add(karta);
    }

    //vylozi kartu pred seba a skontroluje ci tam uz taka nie je
    public void pridajKartuPredSeba(Karta karta) {
        Optional<Karta> vylozena = this.zoznamKarietPredSebou.stream().filter(kar -> kar.nazov().equals(karta.nazov())).findAny();
        if (vylozena.isPresent()) {
            this.zahodKartuZoStola(karta.nazov());
        } else if (karta instanceof Zbran && this.zoznamKarietPredSebou.stream().anyMatch(zbran -> zbran instanceof Zbran)) {
            Zbran zbran = (Zbran)this.zoznamKarietPredSebou.stream().filter(z -> z instanceof Zbran).findAny().get();
            this.zahodKartuZoStola(zbran.nazov());
            //this.zoznamKarietPredSebou.remove(zbran);
        }
        this.zoznamKarietPredSebou.add(karta);
    }
    //hrac strati zivot, ak ma pivo a je to jeho posledny, tak sa moze zachanit
    public void uberZivot() {
        if (this.pocetZivotov > 1) {
            this.pocetZivotov--;
            System.out.println(this.meno + " stratil zivot, aktualny pocet zivotov= " + this.pocetZivotov);
        } else {
            if (this.maTutoKartu("Pivo")) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Chces zahrat zachranne pivo? ano/nie");
                while (true) {
                    String volba = sc.next();
                    if (volba.equals("ano")) {
                        this.zahodKartu("Pivo");
                        System.out.println("Zachranil si sa, mas 1 zivot");
                        break;
                    } else if (volba.equals("nie")) {
                        this.pocetZivotov = 0;
                        System.out.println(this.meno + " stratil zivot a zomrel");
                        break;
                    } else {
                        System.out.println("zly vstup");
                    }
                }
            } else {
                this.pocetZivotov = 0;
                System.out.println(this.meno + " stratil zivot a zomrel");
            }
        }
    }
    //metoda prida zivot ak nema plny pocet
    public void pridajZivot() {
        if (this.zivotyMax > this.pocetZivotov) {
            this.pocetZivotov++;
            System.out.println(this.getMeno() + " si pridal zivot (" + this.pocetZivotov + ")");
        } else {
            System.out.println("Mas plny pocet zivotov");
        }
    }
    //vrati konkretnu kartu na ktoru sa hrac pyta
    public Optional<Karta> chceHratKartu(String nazov) {
        Optional<Karta> hladanaKarta = this.zoznamKarietNaRuke.stream().filter(karta -> karta.nazov().equals(nazov)).findAny();
        hladanaKarta.filter(this.zoznamKarietNaRuke:: contains).ifPresent(this.zoznamKarietNaRuke::remove);
        return hladanaKarta;
    }
    public void vypisKarty() {
        System.out.println("Karty na ruke: ");
        for (Karta karta : this.zoznamKarietNaRuke) {
            System.out.print(karta.nazov() + " ");
        }
        System.out.println();
    }

    public void vypisInfo() {
        System.out.println("Meno hraca> " + this.meno + "; vzdialenost> " + this.getVzdialenost() + "; zivoty> " + this.pocetZivotov
                + "; pocet kariet na ruke> " + this.getPocetKarietNaRuke());
        System.out.println("Karty na stole: ");
        for (Karta karta : this.zoznamKarietPredSebou) {
            System.out.print(karta.nazov() + "  ");
        }
    }

    public void vypisPostavy() {
        System.out.println("zatial nema postavu");
    }
}
