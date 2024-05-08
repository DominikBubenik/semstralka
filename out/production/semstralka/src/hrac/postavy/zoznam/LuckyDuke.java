package hrac.postavy.zoznam;

import balikKariet.BalikKariet;
import balikKariet.karty.Karta;
import hrac.Hrac;

import java.util.Scanner;

/**
 * trieda vytvara postavu hraca ktora ked otaca dve karty tak si moze vybrat ktoru chce
 * @author Dominik Bubeník
 */
public class LuckyDuke extends Hrac {
    //konstruktor inicializuje predka
    public LuckyDuke() {
        super(4);
    }

    /**
     * otoci 2 karty a vyberie si
     */
    public Karta otacanie() {
        Karta kartaPrva = BalikKariet.getInstance().dajKartu();
        Karta kartaDruha = BalikKariet.getInstance().dajKartu();
        System.out.println("Otocil si " + kartaPrva.toString() + " a " + kartaDruha.toString() + " ktoru chces?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String volba = sc.next();
            if (volba.equals(kartaPrva.nazov())) {
                BalikKariet.getInstance().zahodKartu(kartaPrva);
                return kartaPrva;
            } else if (volba.equals(kartaDruha.nazov())) {
                BalikKariet.getInstance().zahodKartu(kartaDruha);
                return kartaDruha;
            } else {
                System.out.println("zla volba");
            }
        }
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " vzdy ked otaca kartu otoci dve a jednu si vyberie");
    }
}
