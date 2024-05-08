package balikKariet.karty.zbrane;

import balikKariet.karty.FarbaKarty;

/**
 * zbran ktora ma dostrel 2
 */
public class Schofield extends Zbran {
    //konstruktor inicializuje predka, dostrel 1
    public Schofield(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, 1);
    }

    @Override
    public String nazov() {
        return "Schofield";
    }
}
