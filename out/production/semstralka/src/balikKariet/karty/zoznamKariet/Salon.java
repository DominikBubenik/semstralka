package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hrac.Hrac;
import balikKariet.karty.zoznamInterface.NaVsetkych;

import java.util.Collection;

/**
 * karta prida zivot vsetkym hracom
 */
public class Salon extends Karta implements NaVsetkych {
    //konstruktor inicializuje predka
    public Salon(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "Salon";
    }

    @Override
    public void pouzi(Collection<Hrac> collection, Hrac hracNaTahu) {
        for (Hrac hrac : collection) {
            hrac.pridajZivot();
        }
    }
}
