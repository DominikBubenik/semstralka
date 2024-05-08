package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;

/**
 * obranna karta proti Bangu
 */
public class Vedla extends Karta {
    //konstruktor inicializuje predka
    public Vedla(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "Vedla";
    }

}
