package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hrac.Hrac;
import balikKariet.BalikKariet;
import balikKariet.karty.zoznamInterface.Individualna;

/**
 * karta prida hracovi 2 karty
 */
public class Dostavnik extends Karta implements Individualna {
    private BalikKariet balikKariet;
    //konstruktor inicializuje predka a BalikKariet
    public Dostavnik(FarbaKarty farbaKarty, int cisloKarty, BalikKariet balikKariet) {
        super(farbaKarty, cisloKarty, OkrajKarty.HNEDY);
        this.balikKariet = balikKariet;
    }

    @Override
    public String nazov() {
        return "Dostavnik";
    }

    @Override
    public boolean hramNaSeba() {
        return true;
    }

    @Override
    public void pouzi(Hrac hrac) {
        hrac.pridajKartu(this.balikKariet.dajKartu());
        hrac.pridajKartu(this.balikKariet.dajKartu());
    }
}
