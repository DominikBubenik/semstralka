package balikKariet.karty.zoznamKariet;

import balikKariet.BalikKariet;
import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import balikKariet.karty.zoznamInterface.Vylozitelna;
import hrac.Hrac;
import hrac.postavy.zoznam.LuckyDuke;

/**
 * na zaciatku tahu hrac otoci kartu, ak je pikova 2-9 strati 3 zivoty inak dynamit ide dalej
 */
public class Dynamit extends Karta implements Vylozitelna {
    //konstruktor inicializuje predka
    public Dynamit(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.MODRY);
    }

    @Override
    public String nazov() {
        return "Dynamit";
    }

    @Override
    public void vyloz(Hrac hrac) {
        hrac.pridajKartuPredSeba(this);
    }
    //v metode sa otaca karta z balika a podla toho vybuchne/posunie sa dalej
    public boolean efektPlati(Hrac hrac) {
        Karta karta;
        if (hrac instanceof LuckyDuke lucky) {
            karta = lucky.otacanie();
        } else {
            karta = BalikKariet.getInstance().dajKartu();
            System.out.println("otocil si " + karta.toString());
        }
        BalikKariet.getInstance().zahodKartu(karta);
        if (karta.getCisloKarty() < 10 && karta.getFarbaKarty().equals(FarbaKarty.PIKOVA)) {
            System.out.println("Dynamit vybuchol, stracas 3 zivoty");
            hrac.uberZivot();
            hrac.uberZivot();
            hrac.uberZivot();
            return true;
        }
        System.out.println("nie je pikova 2-9, prezil si " + hrac.getMeno());
        return false;
    }
    @Override
    public boolean hramNaSeba() {
        return true;
    }
}
