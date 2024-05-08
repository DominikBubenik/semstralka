package balikKariet.karty.zoznamKariet;

import balikKariet.BalikKariet;
import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import balikKariet.karty.zoznamInterface.Vylozitelna;
import hrac.Hrac;
import hrac.postavy.zoznam.LuckyDuke;

/**
 * ak vyde efekt Barelu tak efekt karty Bang neplati
 */
public class Barel extends Karta implements Vylozitelna {
    //konstruktor inicializuje predka
    public Barel(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.MODRY);
    }

    @Override
    public String nazov() {
        return "Barel";
    }

    @Override
    public void vyloz(Hrac hrac) {
        hrac.pridajKartuPredSeba(this);
    }

    @Override
    public boolean hramNaSeba() {
        return true;
    }

    public boolean vysiel(Hrac braniaci) {
        Karta karta;
        if (braniaci instanceof LuckyDuke luky) {
            karta = luky.otacanie();
        } else {
            karta = BalikKariet.getInstance().dajKartu();
            System.out.println("otocil si " + karta.toString());
        }
        BalikKariet.getInstance().zahodKartu(karta);
        if (karta.getFarbaKarty().equals(FarbaKarty.SRDCOVA)) {
            System.out.println("Barel vysiel");
            return true;
        }
        System.out.println("Barel nevysiel");
        return false;
    }
}
