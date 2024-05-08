package balikKariet.karty.zoznamKariet;

import balikKariet.BalikKariet;
import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import balikKariet.karty.zoznamInterface.Vylozitelna;
import hrac.Hrac;
import hrac.postavy.zoznam.LuckyDuke;

/**
 * hrac otoci na zaciatku tahu kartu, ak je srdcova hra normalny tak inak 1 kolo stoji
 */
public class Vezenie extends Karta implements Vylozitelna {
    //konstruktor inicializuje predka
    public Vezenie(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.MODRY);
    }

    @Override
    public String nazov() {
        return "Vezenie";
    }

    @Override
    public void vyloz(Hrac hrac) {
        hrac.pridajKartuPredSeba(this);
    }
    //otacanie karty
    public boolean efektPlati(Hrac hrac) {
        Karta karta;
        if (hrac instanceof LuckyDuke luky) {
            karta = luky.otacanie();
        } else {
            karta = BalikKariet.getInstance().dajKartu();
            System.out.println("otocil si " + karta.toString());
        }
        BalikKariet.getInstance().zahodKartu(karta);
        if (karta.getFarbaKarty().equals(FarbaKarty.SRDCOVA)) {
            System.out.println("Je srdcova, si volny");
            return true;
        }
        System.out.println("nie je srdcova, jeden tah nehras " + hrac.getMeno());
        return false;
    }

    @Override
    public boolean hramNaSeba() {
        return false;
    }
}
