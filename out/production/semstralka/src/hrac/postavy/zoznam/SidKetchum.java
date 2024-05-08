package hrac.postavy.zoznam;

import hrac.Hrac;
import hrac.postavy.PouzitelnaPocasTahu;

import java.util.Scanner;
/**
 * trieda vytvara postavu hraca ktora moze pocas tahu odhodit 2 karty a dobit si zivot
 * @author Dominik Bubeník
 */
public class SidKetchum extends Hrac implements PouzitelnaPocasTahu {
    //konstruktor inicializuje predka
    public SidKetchum() {
        super(4);
    }

    //vyber ktore 2 karty odhodi
    @Override
    public void pouzi() {
        if (super.getPocetKarietNaRuke() > 1) {
            Scanner scanner = new Scanner(System.in);
            boolean prvaKarta = false;
            boolean druhaKarta = false;
            super.vypisKarty();
            while (true) {
                if (!prvaKarta) {
                    System.out.println("Zadaj 1. kartu ktoru chces odhodit? ");
                    prvaKarta = super.chceHratKartu(scanner.next()).isPresent();
                } else if (!druhaKarta) {
                    System.out.println("Zadaj 2. kartu ktoru chces odhodit? ");
                    druhaKarta = super.chceHratKartu(scanner.next()).isPresent();
                }
                if (Boolean.logicalAnd(prvaKarta, druhaKarta)) {
                    super.pridajZivot();
                    break;
                }
            }
        }
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " pocas tahu moze odhodit 2 karty a dobit si zivot");
    }
}
