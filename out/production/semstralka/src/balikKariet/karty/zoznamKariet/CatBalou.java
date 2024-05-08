package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hra.Prikazy;
import hrac.Hrac;
import balikKariet.karty.zoznamInterface.Individualna;

/**
 * kartou sa da zobrat druhemu hracovi karta
 */
public class CatBalou extends Karta implements Individualna {
    //konstruktor inicializuje predka
    public CatBalou(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "CatBalou";
    }

    @Override
    public void pouzi(Hrac hrac) {
        Prikazy.branieKartyHracovi(hrac);
    }
}
