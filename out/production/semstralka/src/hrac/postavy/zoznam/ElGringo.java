package hrac.postavy.zoznam;

import balikKariet.karty.Karta;
import hrac.Hrac;

import java.util.Optional;
import java.util.Random;

/**
 * trieda vytvara postavu hraca ktora vzdy ked je zasiahnuta tak dostane kartu od hraca ktory ho zasiahol
 * @author Dominik Bubeník
 */
public class ElGringo extends Hrac {
    private Hrac hracNaTahu;
    //konstruktor inicializuje predka
    public ElGringo() {
        super(3);
    }

    @Override
    public void uberZivot() {
        if (!this.hracNaTahu.equals(this)) {
            System.out.println("Zasiahol si, ale zoberiem si kartu ");
            Random random = new Random();
            Optional<Karta> karta = this.hracNaTahu.odovzdajKartuZRuky(random.nextInt(this.hracNaTahu.getPocetKarietNaRuke()));
            if (karta.isPresent()) {
                super.pridajKartu(karta.get());
            } else {
                System.out.println("Nema karty");
            }
        }
        super.uberZivot();
    }

    public void nastavHracaNaTahu(Hrac hracNaTahu) {
        this.hracNaTahu = hracNaTahu;
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " ak je zasiahnuty berie si kartu od toho hraca");
    }
}
