package hrac.postavy.zoznam;

import balikKariet.BalikKariet;
import balikKariet.karty.Karta;
import balikKariet.karty.FarbaKarty;
import hrac.Hrac;

/**
 * trieda vytvara postavu hraca ktora ak je ohrozena Bangom otaca kartu, ak je srdcova netrafil ho bang
 * @author Dominik Bubeník
 */
public class Jordounnais extends Hrac {
    //konstruktor inicializuje predka
    public Jordounnais() {
        super(4);
    }

    /**
     *ak je otocena srdcova vrati true
     */
    public boolean otacanie() {
        Karta karta = BalikKariet.getInstance().dajKartu();
        System.out.println("otocil si " + karta.toString());
        BalikKariet.getInstance().zahodKartu(karta);

        if (karta.getFarbaKarty().equals(FarbaKarty.SRDCOVA)) {
            System.out.println("Schopnost vysla");
            return true;
        }
        System.out.println("Schopnost nevysla");
        return false;
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " ak je na neho Bang tak otaca kartu, ak je srdcova vystrel ho minul");
    }
}
