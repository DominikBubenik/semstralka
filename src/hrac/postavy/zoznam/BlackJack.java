package hrac.postavy.zoznam;

import balikKariet.BalikKariet;
import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import hrac.Hrac;

/**
 * trieda vytvara postavu hraca ktora si na zaciatku tahu potiahne 2 karty, ak druha karta cervena, berie si este jednu
 * @author Dominik Bubeník
 */
public class BlackJack extends Hrac {
    //konstruktor inicializuje predka
    public BlackJack() {
        super(4);
    }

    /**
     * ak je druha karta srdcova/karova tak si zoberie este jednu
     */
    @Override
    public void faza1() {
        super.pridajKartu(BalikKariet.getInstance().dajKartu());
        Karta karta = BalikKariet.getInstance().dajKartu();
        super.pridajKartu(karta);
        if (karta.getFarbaKarty().equals(FarbaKarty.SRDCOVA) || karta.getFarbaKarty().equals(FarbaKarty.KAROVA)) {
            super.pridajKartu(BalikKariet.getInstance().dajKartu());
        }
    }
    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " moze hrat Bang ako Vedla a naopak");
    }
}
