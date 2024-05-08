package balikKariet.karty.zbrane;

import balikKariet.karty.FarbaKarty;

/**
 * zbran ktora ma dostrel 3
 */
public class Remington extends Zbran {
    //konstruktor inicializuje predka, dostrel 2
    public Remington(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, 2);
    }

    @Override
    public String nazov() {
        return "Remington";
    }
}
