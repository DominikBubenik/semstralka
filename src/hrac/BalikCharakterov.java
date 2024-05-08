package hrac;

import java.util.ArrayList;
import java.util.Collections;

/**
 * trieda vytvara zoznam vsetkych charakterov v potredbnom pocte a v nahodnom poradi vie vratit jednu
 */
public class BalikCharakterov {
    private ArrayList<Charakter> zoznam;
    private int pocetHracov;

    public BalikCharakterov(int pocetHracov) {
        this.zoznam = new ArrayList<>();
        this.pocetHracov = pocetHracov;
        this.zoznam.add(Charakter.SHERIF);
        this.zoznam.add(Charakter.ODPADLIK);
        if (pocetHracov > 2) {
            this.pridajCharakter(pocetHracov - 2);
        }
        Collections.shuffle(this.zoznam);
    }

    private void pridajCharakter(int pocet) {
        while (pocet > 0) {
            if (pocet >= 3 && pocet < 5) {
                this.zoznam.add(Charakter.VICE);
            } else {
                this.zoznam.add(Charakter.BANDITA);
            }
            pocet--;
        }
    }

    public Charakter dajCharakter() {
        Charakter ch = this.zoznam.get(0);
        this.zoznam.remove(0);
        return ch;
    }
}
