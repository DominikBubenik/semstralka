package hrac.postavy.zoznam;

import balikKariet.BalikKariet;
import balikKariet.karty.Karta;
import hrac.Hrac;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
/**
 * trieda vytvara postavu hraca ktora si na zaciatku tahu potiahne 3 karty a vyberie si 2
 *
 * @author Dominik Bubeník
 *
 */
public class BartCassidy extends Hrac {
    //konstruktor inicializuje predka
    public BartCassidy() {
        super(4);
    }

    /**
     * metoda overriduje metodu z hraca kde si potiahne 3 karty a vyberie ktoru nechce
     */
    @Override
    public void faza1() {
        ArrayList<Karta> karty = new ArrayList<>();
        karty.add(BalikKariet.getInstance().dajKartu());
        karty.add(BalikKariet.getInstance().dajKartu());
        karty.add(BalikKariet.getInstance().dajKartu());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Ktoru kartu nechces? ");
                karty.iterator().forEachRemaining(karta -> System.out.print(karta.nazov() + "  "));
                System.out.println();
                String volba = scanner.next();
                Optional<Karta> k = karty.stream().filter(karta -> karta.nazov().equals(volba)).findAny();
                if (k.isPresent()) {
                    BalikKariet.getInstance().vratKartu(karty.get(karty.indexOf(k.get())));
                    karty.remove(k.get());
                    break;
                }
            } catch (Exception e) {
                System.out.println("Zly vstup");
            }
        }
        for (Karta karta : karty) {
            super.pridajKartu(karta);
        }
    }
    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " si na zaciatku tahu potiahne 3 karty a 1 zahodi");
    }
}
