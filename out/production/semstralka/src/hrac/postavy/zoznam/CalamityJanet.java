package hrac.postavy.zoznam;
import balikKariet.karty.zoznamKariet.Bang;
import balikKariet.karty.FarbaKarty;
import balikKariet.karty.Karta;
import balikKariet.karty.zoznamKariet.Vedla;
import hrac.Hrac;
import java.util.Optional;

/**
 * trieda vytvara postavu hraca ktora dokaze hrat kartu Bang ako kartu Vedla a naopak
 *
 * @author Dominik Bubeník
 *
 */
public class CalamityJanet extends Hrac {
    //konstruktor inicializuje predka
    public CalamityJanet() {
        super(4);
    }

    //metoda vracia true ak hladam Vedla/Bang a mam Vedla/Bang,
    @Override
    public boolean maTutoKartu(String nazov) {
        if (nazov.equals("Bang") || nazov.equals("Vedla")) {
            return Boolean.logicalOr(super.maTutoKartu("Bang"), super.maTutoKartu("Vedla"));
        } else {
            return super.maTutoKartu(nazov);
        }
    }
    //v metode sa rozhodujem ci sa chcem zbavit Bangu/Vedla
    @Override
    public void zahodKartu(String nazov) {
        if (nazov.equals("Bang") || nazov.equals("Vedla")) {
            if (nazov.equals("Bang")) {
                Optional<Karta> karta = super.chceHratKartu(nazov);
                if (karta.isPresent()) {
                    return;
                }
                super.chceHratKartu("Vedla");
            } else {
                Optional<Karta> karta = super.chceHratKartu("Vedla");
                if (karta.isPresent()) {
                    return;
                }
                super.chceHratKartu("Bang");
            }
        } else {
            super.zahodKartu(nazov);
        }
    }
    //metoda rozhoduje ci chce hrat Bang/Vedla ako kartu Bang/Vedla
    @Override
    public Optional<Karta> chceHratKartu(String nazov) {
        if (nazov.equals("Bang") || nazov.equals("Vedla")) {
            if (nazov.equals("Bang")) {
                Optional<Karta> karta = super.chceHratKartu(nazov);
                if (karta.isPresent()) {
                    return karta;
                }
                karta = super.chceHratKartu("Vedla");
                if (karta.isPresent()) {
                    return Optional.of(new Bang(FarbaKarty.SRDCOVA, 5));
                }
            } else {
                Optional<Karta> karta = super.chceHratKartu(nazov);
                if (karta.isPresent()) {
                    return karta;
                }
                karta = super.chceHratKartu("Vedla");
                if (karta.isPresent()) {
                    return Optional.of(new Vedla(FarbaKarty.SRDCOVA, 5));
                }
            }
        }
        return super.chceHratKartu(nazov);
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " si na zaciatku tahu potiahne 3 karty a 1 zahodi");
    }
}
