package balikKariet.karty.zbrane;

import balikKariet.karty.FarbaKarty;

/**
 * zbran ktora ma dostrel 1 ale hrac moze strielan neobmedzene Bangov
 */
public class Volcanic extends Zbran {
    //konstruktor inicializuje predka, dostrel 1
    public Volcanic(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, 0);
    }

    @Override
    public String nazov() {
        return "Volcanic";
    }
}
