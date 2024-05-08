package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hrac.Hrac;
import balikKariet.karty.zoznamInterface.Individualna;

/**
 * karta prida hracovi na tahu zivot
 */
public class Pivo extends Karta implements Individualna {
    //konstruktor inicializuje predka
    public Pivo(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "Pivo";
    }

    @Override
    public boolean hramNaSeba() {
        return true;
    }

    @Override
    public void pouzi(Hrac hrac) {
        hrac.pridajZivot();
    }
}
