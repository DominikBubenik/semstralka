package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import balikKariet.karty.zoznamInterface.Vylozitelna;
import hrac.Hrac;

/**
 * zvysi vzdialenost o 1 od ostatnych hracov
 */
public class Mustang extends Karta implements Vylozitelna {
    //konstruktor inicializuje predka
    public Mustang(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.MODRY);
    }

    @Override
    public String nazov() {
        return "Mustang";
    }

    @Override
    public boolean hramNaSeba() {
        return true;
    }

    @Override
    public void vyloz(Hrac hrac) {
        hrac.pridajKartuPredSeba(this);
    }
}
