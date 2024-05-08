package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hra.Prikazy;
import hrac.Hrac;
import balikKariet.karty.zoznamInterface.NaVsetkych;

import java.util.Collection;

/**
 * kazdy hrac musi odhodit Vedla okrem hraca na tahu
 */
public class Gulomet extends Karta implements NaVsetkych {
    //konstruktor inicializuje predka
    public Gulomet(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "Gulomet";
    }

    @Override
    public void pouzi(Collection<Hrac> collection, Hrac hracNaTahu) {
        for (Hrac hrac : collection) {
            if (hrac.equals(hracNaTahu)) {
                continue;
            }
            Prikazy.odpovedAnoNie(hrac, "Vedla");
        }
    }
}
