package hrac.postavy.zoznam;

import hrac.Hrac;
import java.util.Collection;
/**
 * trieda vytvara postavu hraca ktora si berie vsetky karty vyradeneho hraca
 * @author Dominik Bubeník
 */
public class VultureSam extends Hrac {
    //konstruktor inicializuje predka
    public VultureSam() {
        super(4);
    }

    //metoda prida vsetky karty ktore mali hraci do zoznamu kariet
    public void pridajKarty(Collection<Hrac> zoznam) {
        for (Hrac hrac : zoznam) {
            for (int i = 0; i < hrac.getPocetKarietNaRuke(); i++) {
                super.pridajKartu(hrac.odovzdajKartuZRuky(i).get());
            }
            for (int i = 0; i < hrac.getPocetKarietPredSebou(); i++) {
                super.pridajKartu(hrac.odovzdajKartuZoStola(i).get());
            }
        }
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " berie si vsetky karty vyradeneho hraca");
    }
}
