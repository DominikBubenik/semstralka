package balikKariet.karty.zoznamKariet;

import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.OkrajKarty;
import hra.Prikazy;
import hrac.Hrac;
import balikKariet.karty.zoznamInterface.Dualna;

import java.util.Optional;

/**
 * hrac si moze zobrat kartu od nejakeho hraca na vzdialenost 1
 */
public class Panika extends Karta implements Dualna {
    //konstruktor inicializuje predka
    public Panika(FarbaKarty farba, int cisloKarty) {
        super(farba, cisloKarty, OkrajKarty.HNEDY);
    }

    @Override
    public String nazov() {
        return "Panika";
    }

    @Override
    public void pouzi(Hrac hracNaTahu, Hrac braniaci) {
        Optional<Karta> branaKarta = Prikazy.branieKartyHracovi(braniaci);
        if (branaKarta.isPresent()) {
            hracNaTahu.pridajKartu(branaKarta.get());
        }
    }


}
