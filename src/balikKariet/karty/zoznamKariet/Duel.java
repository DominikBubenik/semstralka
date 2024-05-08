package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hra.Prikazy;
import hrac.Hrac;
import balikKariet.karty.zoznamInterface.Dualna;

/**
 * po pouzity karty dvaja hraci odhadzuju Bang
 */
public class Duel extends Karta implements Dualna {
    //konstruktor inicializuje predka
    public Duel(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "Duel";
    }

    @Override
    public void pouzi(Hrac utociaci, Hrac braniaci) {
        while (true) {
            if (!Prikazy.odpovedAnoNie(braniaci, "Bang")) {
                break;
            } else if (!Prikazy.odpovedAnoNie(utociaci, "Bang")) {
                break;
            }
        }
    }
}
