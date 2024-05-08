package hrac.postavy.zoznam;

import balikKariet.BalikKariet;
import hrac.Hrac;

/**
 * trieda vytvara postavu hraca ktora vzdy ked je zasiahnuta tak si potiahne kartu
 * @author Dominik Bubeník
 */
public class KitCarlson extends Hrac {
    //konstruktor inicializuje predka
    public KitCarlson() {
        super(4);
    }

    @Override
    public void uberZivot() {
        super.pridajKartu(BalikKariet.getInstance().dajKartu());
        super.uberZivot();
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " ak je zasiahnuty potiahne si kartu");
    }
}
