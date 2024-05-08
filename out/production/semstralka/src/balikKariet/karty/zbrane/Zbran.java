package balikKariet.karty.zbrane;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import balikKariet.karty.zoznamInterface.Vylozitelna;
import hrac.Hrac;

/**
 * trieda je priamym potomkom vsetkych zbrani v hre, dodatocny atribut ma dostrel
 */
public abstract class Zbran extends Karta implements Vylozitelna {
    private int dostrel;
    //konstruktor inicializuje predka, dostrel sa zadava v potomkoch
    public Zbran(FarbaKarty farba, int cisloKarty, int dostrel) {
        super(farba, cisloKarty, OkrajKarty.MODRY);
        this.dostrel = dostrel;
    }

    public int getDostrel() {
        return this.dostrel;
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
