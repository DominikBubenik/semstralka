package hra;

import balikKariet.karty.zoznamKariet.Dynamit;
import balikKariet.karty.zoznamKariet.Vezenie;
import balikKariet.karty.zoznamInterface.Vylozitelna;
import balikKariet.karty.zoznamInterface.Dualna;
import balikKariet.karty.zoznamInterface.Individualna;
import balikKariet.karty.zoznamInterface.NaVsetkych;
import hrac.Charakter;
import hrac.Hrac;
import balikKariet.BalikKariet;
import hrac.postavy.zoznam.ElGringo;
import hrac.postavy.PotrebujeZoznam;
import hrac.postavy.zoznam.VultureSam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Optional;

/**
 * trieda zabezpecuje cely priebeh tahu- hranie kariet, vyhadzovanie, vykladanie...
 */
public class Tah {
    private Collection<Hrac> zoznamHracov;
    private Hrac hracNaTahu;
    private Hrac nasledujuciHrac;
    private BalikKariet balikKariet;
    private boolean koniecTahu;

    //inicializuje sa zoznam hracov, hracNaTahu a nasledujuciHrac
    public Tah(Collection<Hrac> zoznamHracov, Hrac hracNaTahu, Hrac nasledujuciHrac) {
        this.zoznamHracov = zoznamHracov;
        this.hracNaTahu = hracNaTahu;
        this.nasledujuciHrac = nasledujuciHrac;
        this.balikKariet = BalikKariet.getInstance();
        this.koniecTahu = false;

        this.speciPripady(); //kontrola specialnych pripadov
        this.otacanieKarietPredTahom(); //zistovanie efektu pre Dynamit, Vezenie
        if (!this.koniecTahu) {
            this.hracNaTahu.faza1();
        }
    }

    public Collection<Hrac> getZoznamHracov() {
        //ArrayList<Hrac> zH = new ArrayList<>(this.zoznamHracov);
        return Collections.unmodifiableCollection(this.zoznamHracov);
    }

    public Hrac getHracNaTahu() {
        return this.hracNaTahu;
    }

    //metoda zabezpecuje aby hraci mohli hrat karty podla ich vyberu
    public void zahrajKartu(String nazov) {

        if (this.hracNaTahu.getPocetKarietNaRuke() != 0) {
            var karta = this.hracNaTahu.chceHratKartu(nazov); //kontrola ci hrac ma kartu kt chce hrat
            karta.ifPresent(k -> BalikKariet.getInstance().zahodKartu(k));

            if (karta.isPresent()) {
                if (karta.get().nazov().equals("Bang")) {
                    if (this.hracNaTahu.getMozeHratBang()) {
                        this.hracNaTahu.setMozeHratBang(false);
                    } else {
                        System.out.println("Uz si zahral Bang");
                        return;
                    }
                }

                if (karta.get() instanceof Individualna kar) { //ak sa karta tyka len jedneho hraca
                    if (karta.get().hramNaSeba()) {
                        kar.pouzi(this.hracNaTahu);
                    } else {
                        Optional<Hrac> braniaci = Prikazy.naKohoHratkartu(this.zoznamHracov, karta.get(), this.hracNaTahu);
                        braniaci.ifPresent(kar::pouzi);
                    }

                } else if (karta.get() instanceof Dualna kar) { //ak sa karta tyka 2 hracov
                    Optional<Hrac> braniaci = Prikazy.naKohoHratkartu(this.zoznamHracov, karta.get(), this.hracNaTahu);
                    braniaci.ifPresent(hrac -> kar.pouzi(this.hracNaTahu, hrac));

                } else if (karta.get() instanceof NaVsetkych kar) { //ak sa hra karta na vsetkych
                    kar.pouzi(this.zoznamHracov, this.hracNaTahu);

                } else if (karta.get() instanceof Vylozitelna modra) { //ak je to modra karta ktora sa vyklada
                    if (karta.get().hramNaSeba()) { //pred seba
                        modra.vyloz(this.hracNaTahu);
                    } else { // pred ineho hraca
                        Optional<Hrac> hrac = Prikazy.naKohoHratkartu(this.zoznamHracov, karta.get(), this.hracNaTahu);
                        if (hrac.isPresent()) {
                            modra.vyloz(hrac.get());
                        }
                    }
                }
            }

        } else {
            System.out.println("Nemas karty na ruke");
        }
        this.kontrolaVyradenych();
    }

    //v metode sa upravuje zoznam hracov v hre resp odstranuju sa hraci kt maju 0 zivotov
    public void kontrolaVyradenych() {
        ArrayList<Hrac> zoznamZivych = new ArrayList<>();
        ArrayList<Hrac> zoznamVyradenych = new ArrayList<>();
        for (Hrac hrac : this.zoznamHracov) {
            if (hrac.getPocetZivotov() != 0) {
                zoznamZivych.add(hrac);
            } else {
                zoznamVyradenych.add(hrac);
            }
        }
        if (zoznamVyradenych.size() > 0) {
            Optional<Hrac> hrac = zoznamZivych.stream().filter(postava -> postava instanceof VultureSam).findAny();
            if (hrac.isPresent()) {
                VultureSam sam = (VultureSam)hrac.get();
                sam.pridajKarty(Collections.unmodifiableCollection(zoznamVyradenych));
            }

        }
        this.zoznamHracov = Collections.unmodifiableCollection(zoznamZivych);
    }

    //kontrola ci nema na ruke viac kariet ako zivotov
    public void koniecTahu() {
        while (this.hracNaTahu.getPocetKarietNaRuke() > this.hracNaTahu.getPocetZivotov()) {
            System.out.println("Mas viac kariet na ruke nez zivotov");
            Prikazy.odhadzovanieKartyZRuky(this.hracNaTahu);
        }
    }

    //ak ma hrac pred sebou nejaku kartu ktora vyzaduje otacanie kariet na zistenie efektu napr vezenie, dynamit...
    public void otacanieKarietPredTahom() {
        if (this.hracNaTahu.maToPredSebou("Dynamit")) {
            Dynamit dynamit = (Dynamit)this.hracNaTahu.zahodKartuZoStola("Dynamit").get();
            if (!dynamit.efektPlati(this.hracNaTahu)) {
                this.nasledujuciHrac.pridajKartuPredSeba(dynamit);
            }
        }
        if (this.hracNaTahu.maToPredSebou("Vezenie")) {
            Vezenie vezenie = (Vezenie)this.hracNaTahu.zahodKartuZoStola("Vezenie").get();
            if (!vezenie.efektPlati(this.hracNaTahu)) {
                this.koniecTahu = true;
            }
        }
    }

    public boolean ukoncenieTahuPredcasne() {
        return this.koniecTahu;
    }

    public void vypisHracov() {
        System.out.println("Hraci v hre: ");
        for (Hrac hrac : this.zoznamHracov) {
            if (hrac.getCharakter().equals(Charakter.SHERIF)) {
                System.out.print("[$]");
            }
            System.out.print(hrac.getMeno() + "(" + hrac.getVzdialenost() + ")" + "  ");
        }
        System.out.println();
    }

    //v metode kontrolujem specialne pripady postav ktore maju specificke vlastnosti
    public void speciPripady() {
        if (this.hracNaTahu instanceof PotrebujeZoznam potrebujeZoznam) {
            potrebujeZoznam.nastavZoznam(Collections.unmodifiableCollection(this.zoznamHracov));
        }
        if (this.zoznamHracov.stream().anyMatch(hrac -> hrac instanceof ElGringo)) {
            ElGringo elGringo = (ElGringo)this.zoznamHracov.stream().filter(hrac -> hrac instanceof ElGringo).findAny().get();
            elGringo.nastavHracaNaTahu(this.hracNaTahu);
        }
    }
}
