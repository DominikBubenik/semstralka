package hrac.postavy;

import hrac.Hrac;
import hrac.postavy.zoznam.BartCassidy;
import hrac.postavy.zoznam.CalamityJanet;
import hrac.postavy.zoznam.PaulRegret;
import hrac.postavy.zoznam.PedroRamirez;
import hrac.postavy.zoznam.SidKetchum;
import hrac.postavy.zoznam.SuzyLafayete;
import hrac.postavy.zoznam.SlabTheKiller;
import hrac.postavy.zoznam.VultureSam;
import hrac.postavy.zoznam.WillyTheKid;
import hrac.postavy.zoznam.ElGringo;
import hrac.postavy.zoznam.KitCarlson;
import hrac.postavy.zoznam.BlackJack;
import hrac.postavy.zoznam.LuckyDuke;
import hrac.postavy.zoznam.Jordounnais;

import java.util.ArrayList;
import java.util.Collections;

/**
 * trieda vytvara zoznam vsetkych postav a v nahodnom poradi vie vratit jednu
 */
public class BalikPostav {
    private ArrayList<Hrac> zoznamPostav;
    //konstruktor inicializuje zoznamPostav a prida vsetky postavy
    public BalikPostav() {
        this.zoznamPostav = new ArrayList<>();
        this.zoznamPostav.add(new BartCassidy());
        this.zoznamPostav.add(new CalamityJanet());
        this.zoznamPostav.add(new PaulRegret());
        this.zoznamPostav.add(new PedroRamirez());
        this.zoznamPostav.add(new SidKetchum());
        this.zoznamPostav.add(new SlabTheKiller());
        this.zoznamPostav.add(new SuzyLafayete());
        this.zoznamPostav.add(new VultureSam());
        this.zoznamPostav.add(new WillyTheKid());
        this.zoznamPostav.add(new ElGringo());
        this.zoznamPostav.add(new KitCarlson());
        this.zoznamPostav.add(new BlackJack());
        this.zoznamPostav.add(new LuckyDuke());
        this.zoznamPostav.add(new Jordounnais());
        Collections.shuffle(this.zoznamPostav);
    }

    //metoda vyberie jednu postavu zo zoznamu
    public Hrac dajPostavu() {
        Hrac postava = this.zoznamPostav.get(0);
        this.zoznamPostav.remove(0);
        return postava;
    }
}
