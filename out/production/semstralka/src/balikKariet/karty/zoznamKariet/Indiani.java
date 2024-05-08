package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hra.Prikazy;
import hrac.Hrac;
import balikKariet.karty.zoznamInterface.NaVsetkych;

import java.util.Collection;

/**
 * vsetci hraci musia odhodit Bang inak stratia zivot, okrem hraca na tahu
 */
public class Indiani extends Karta implements NaVsetkych {
    //konstruktor inicializuje predka
    public Indiani(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "Indiani";
    }

    @Override
    public void pouzi(Collection<Hrac> collection, Hrac hracNaTahu) {
        String nazovKarty = "Bang";

        for (Hrac hrac : collection) {
            if (hracNaTahu.equals(hrac)) {
                continue;
            }
            Prikazy.odpovedAnoNie(hrac, nazovKarty);
        }
    }
}
