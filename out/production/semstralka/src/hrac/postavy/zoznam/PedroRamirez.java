package hrac.postavy.zoznam;

import balikKariet.BalikKariet;
import balikKariet.karty.Karta;
import hrac.Hrac;

import java.util.Scanner;

/**
 * trieda vytvara postavu hraca ktora si moze na zaciatku tahu zobrat poslednu kartu ktora bola zahrana/odhodena
 * @author Dominik Bubeník
 */
public class PedroRamirez extends Hrac {
    //konstruktor inicializuje predka
    public PedroRamirez() {
        super(4);
    }

    //ak je karta odhodena alebo zahrana tak si ju moze zobrat vo faze 1
    @Override
    public void faza1() {
        if (BalikKariet.getInstance().dajZahodenu().isPresent()) {
            Scanner sc = new Scanner(System.in);
            Karta karta = BalikKariet.getInstance().dajZahodenu().get();
            System.out.println("Karta v odhadzovacom balicku - " + karta.nazov() + ", chces si ju zobrat?");
            while (true) {
                String volba = sc.next();
                if (volba.equals("ano")) {
                    super.pridajKartu(karta);
                    super.pridajKartu(BalikKariet.getInstance().dajKartu());
                    break;
                } else if (volba.equals("nie")) {
                    super.faza1();
                    break;
                } else {
                    System.out.println("zly vstup");
                }
            }
        } else {
            System.out.println("v odhadzovacom balicku nie su karty");
        }
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " na zaciatku tahu si moze zobrat poslednu kartu kt bola zahodena/zahrana");
    }
}
