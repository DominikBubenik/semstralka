package hrac.postavy.zoznam;

import balikKariet.karty.Karta;
import balikKariet.karty.zoznamInterface.Individualna;
import hra.Prikazy;
import hrac.Hrac;
import hrac.postavy.PotrebujeZoznam;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
/**
 * trieda vytvara postavu hraca ktora ked zahra Bang tak braniaci hrac musi pouzit 2 Vedle
 * @author Dominik Bubeník
 */
public class SlabTheKiller extends Hrac implements PotrebujeZoznam {
    private Collection<Hrac> zoznamHracov;
    //konstruktor inicializuje predka
    public SlabTheKiller() {
        super(4);
    }

    @Override
    public void nastavZoznam(Collection<Hrac> zoznam) {
        this.zoznamHracov = zoznam;
    }

    //ak zahra kartu Bang na nejakeho hraca efekt sa zopakuje 2x
    @Override
    public Optional<Karta> chceHratKartu(String nazov) {
        if (nazov.equals("Bang")) {
            Optional<Karta> karta = super.chceHratKartu("Bang");
            if (karta.isPresent()) {
                if (!this.getMozeHratBang()) {
                    System.out.println("Uz si zahral Bang");
                    return Optional.empty();
                }
                Individualna bang = (Individualna)karta.get();
                Optional<Hrac> braniaci = Prikazy.naKohoHratkartu(Collections.unmodifiableCollection(this.zoznamHracov), karta.get(), this);
                if (braniaci.isPresent()) {
                    int pocetZivotov = braniaci.get().getPocetZivotov();
                    bang.pouzi(braniaci.get());
                    if (pocetZivotov == braniaci.get().getPocetZivotov()) {
                        bang.pouzi(braniaci.get());
                    }
                }

                this.setMozeHratBang(false);
            }
            return Optional.empty(); // tuto da potom ten blby vypis ze Zadal si zlu kartu
        }
        return super.chceHratKartu(nazov);
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " na jeho Bang musi dat hrac 2 Vedla");
    }
}
