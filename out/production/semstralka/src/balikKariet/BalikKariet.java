package balikKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.zbrane.Remington;
import balikKariet.karty.zbrane.Schofield;
import balikKariet.karty.zbrane.Volcanic;
import balikKariet.karty.zoznamKariet.Bang;
import balikKariet.karty.zoznamKariet.Hokynarstvo;
import balikKariet.karty.zoznamKariet.Vezenie;
import balikKariet.karty.zoznamKariet.Vedla;
import balikKariet.karty.zoznamKariet.Panika;
import balikKariet.karty.zoznamKariet.Pivo;
import balikKariet.karty.zoznamKariet.Barel;
import balikKariet.karty.zoznamKariet.Duel;
import balikKariet.karty.zoznamKariet.Salon;
import balikKariet.karty.zoznamKariet.Dynamit;
import balikKariet.karty.zoznamKariet.Indiani;
import balikKariet.karty.zoznamKariet.Dostavnik;
import balikKariet.karty.zoznamKariet.Mustang;
import balikKariet.karty.zoznamKariet.Apallosa;
import balikKariet.karty.zoznamKariet.CatBalou;
import balikKariet.karty.zoznamKariet.Gulomet;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.Arrays;

/**
 * trieda vytvara hraci balicek zo vsetkych kariet kt su v ponuke kariet a v urcitom mnozstve
 * je navrhovy vzor Singleton
 */
public class BalikKariet {
    private static BalikKariet instancia;
    private ArrayList<Karta> balik;
    private Karta zahodenaKarta;

    private BalikKariet() {
        this.balik = new ArrayList<>();
        this.novyBalik();
    }
    //vytvorenie instancie
    public static BalikKariet getInstance() {
        if (BalikKariet.instancia == null) {
            BalikKariet.instancia = new BalikKariet();
        }
        return BalikKariet.instancia;
    }

    //metoda vytvara karty v danom pocte, na konci zamiesa
    public void novyBalik() {
        List<FarbaKarty> farby = Arrays.stream(FarbaKarty.values()).toList();

        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            this.balik.add(new Bang(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 15; i++) {
            this.balik.add(new Vedla(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 5; i++) {
            this.balik.add(new Panika(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 5; i++) {
            this.balik.add(new CatBalou(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 3; i++) {
            this.balik.add(new Indiani(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 3; i++) {
            this.balik.add(new Hokynarstvo(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2, this));
        }
        for (int i = 0; i < 4; i++) {
            this.balik.add(new Barel(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 2; i++) {
            this.balik.add(new Duel(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 5; i++) {
            this.balik.add(new Pivo(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 2; i++) {
            this.balik.add(new Vezenie(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 2; i++) {
            this.balik.add(new Schofield(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 2; i++) {
            this.balik.add(new Remington(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }
        for (int i = 0; i < 2; i++) {
            this.balik.add(new Volcanic(farby.get(random.nextInt(farby.size())), random.nextInt(12) + 2));
        }

        this.balik.add(new Dostavnik(FarbaKarty.PIKOVA, 9, this));
        this.balik.add(new Dostavnik(FarbaKarty.PIKOVA, 9, this));
        this.balik.add(new Gulomet(FarbaKarty.SRDCOVA,  10));
        this.balik.add(new Salon(FarbaKarty.SRDCOVA,  5));
        this.balik.add(new Apallosa(FarbaKarty.PIKOVA,  7));
        this.balik.add(new Mustang(FarbaKarty.SRDCOVA,  10));
        this.balik.add(new Mustang(FarbaKarty.KRIZOVA,  8));
        this.balik.add(new Dynamit(FarbaKarty.KRIZOVA,  7));
        Collections.shuffle(this.balik);
    }

    public Karta dajKartu() {
        if (this.balik.size() == 0) {
            this.novyBalik();
        }
        Karta karta = this.balik.get(0);
        this.balik.remove(0);
        return karta;
    }

    //metoda vytvara odhadzovaci balicek
    public void vratKartu(Karta karta) {
        this.balik.add(0, karta);
    }

    public Optional<Karta> dajZahodenu() {
        return Optional.ofNullable(this.zahodenaKarta);
    }

    public void zahodKartu(Karta karta) {
        this.zahodenaKarta = karta;
    }
}
