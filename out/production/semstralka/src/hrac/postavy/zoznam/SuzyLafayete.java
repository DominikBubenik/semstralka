package hrac.postavy.zoznam;

import balikKariet.BalikKariet;
import balikKariet.karty.Karta;
import hrac.Hrac;

import java.util.Optional;
/**
 * trieda vytvara postavu hraca ktora ked nema ziadnu kartu na ruke tak si 1 zoberie
 * @author Dominik Bubeník
 */
public class SuzyLafayete extends Hrac {
    //konstruktor inicializuje predka
    public SuzyLafayete() {
        super(4);
    }

    @Override
    public void zahodKartu(String nazov) {
        super.zahodKartu(nazov);
        this.pridajKartu();
    }

    @Override
    public Optional<Karta> odovzdajKartuZRuky(int poradie) {
        Optional<Karta> karta = super.odovzdajKartuZRuky(poradie);
        this.pridajKartu();
        return karta;
    }

    @Override
    public Optional<Karta> chceHratKartu(String nazov) {
        Optional<Karta> karta = super.chceHratKartu(nazov);
        this.pridajKartu();
        return karta;
    }

    public void pridajKartu() {
        if (super.getPocetKarietNaRuke() == 0) {
            super.pridajKartu(BalikKariet.getInstance().dajKartu());
        }
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " pokial nema ziadne karty na ruke, 1 si zoberie");
    }
}
