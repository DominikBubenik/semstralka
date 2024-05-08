package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hra.Prikazy;
import hrac.Hrac;
import balikKariet.karty.zoznamInterface.Individualna;
import hrac.postavy.zoznam.Jordounnais;

/**
 * kartou Bang sa da zobrat druhemu hracovi zobrat zivot
 */
public class Bang extends Karta implements Individualna {
    //konstruktor inicializuje predka
    public Bang(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "Bang";
    }

    //vystreli Bang a skontroluje ci hrac nema Barel
    @Override
    public void pouzi(Hrac braniaci) {
        String nazovKarty = "Vedla";
        if (braniaci.maToPredSebou("Barel")) {
            Barel barel =  (Barel)braniaci.dajEfektKarty("Barel").get();
            if (barel.vysiel(braniaci)) {
                return;
            }
        }
        if (braniaci instanceof Jordounnais jord) {
            if (jord.otacanie()) {
                return;
            }
        }
        Prikazy.odpovedAnoNie(braniaci, nazovKarty);
    }
}
