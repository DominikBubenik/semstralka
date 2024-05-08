package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import balikKariet.karty.zoznamInterface.Vylozitelna;
import hrac.Hrac;

/**
 * karta znizuje vydialenost medzi hracmi
 */
public class Apallosa extends Karta implements Vylozitelna {
    //konstruktor inicializuje predka
    public Apallosa(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.MODRY);
    }

    @Override
    public String nazov() {
        return "Apallosa";
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
