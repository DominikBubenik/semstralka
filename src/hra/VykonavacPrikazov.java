package hra;

import hrac.Hrac;
import hrac.postavy.PouzitelnaPocasTahu;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

/**
 * trieda nacita a vykonava prikazy
 */
public class VykonavacPrikazov {
    private Tah tah;
    private Hra hra;
    private Scanner scanner;
    private Collection<Hrac> zoznamHracov;
    //inicializuje zoznam hracov a hru
    public VykonavacPrikazov(Collection<Hrac> collection, Hra hra) {
        this.zoznamHracov = collection;
        this.scanner = new Scanner(System.in);
        this.hra = hra;
    }

    public void setTah(Tah tah) {
        this.tah = tah;
    }

    public boolean nacitajPrikaz() {
        System.out.print("-->");
        String prikaz = this.scanner.nextLine();
        if (prikaz.equals("koniec tahu")) {
            return true;
        } else if (this.tah.ukoncenieTahuPredcasne()) {
            return true;
        }

        String prvaCast = "";
        String druhaCast = "";

        Scanner tokenizer = new Scanner(prikaz);
        if (tokenizer.hasNext()) {
            prvaCast = tokenizer.next();
            if (tokenizer.hasNext()) {
                druhaCast = tokenizer.next();
            }
        } else {
            this.nacitajPrikaz();
        }
        //vykona sa dany prikaz
        if (prvaCast.equals("zobraz")) {
            if (druhaCast.equals("karty")) {
                this.tah.getHracNaTahu().vypisKarty();
            } else if (druhaCast.equals("hracov")) {
                this.tah.vypisHracov();
            } else {
                System.out.println("Zobraz co? karty / hracov");
            }
        } else if (prvaCast.equals("zahraj")) {
            this.tah.zahrajKartu(druhaCast);
        } else if (prvaCast.equals("pouzi")) {
            if (this.tah.getHracNaTahu() instanceof PouzitelnaPocasTahu hrac) {
                hrac.pouzi();
            }
        } else if (prvaCast.equals("info")) {
            String finalDruhaCast = druhaCast;
            Optional<Hrac> hrac = this.zoznamHracov.stream().filter(h -> h.getMeno().equals(finalDruhaCast)).findAny();
            if (hrac.isPresent()) {
                hrac.get().vypisPostavy();
                hrac.get().vypisInfo();
            } else {
                System.out.println("Neviem na akeho hraca sa pytas");
            }
        } else if (prvaCast.equals("?")) {
            System.out.println("prikazy su: zobraz hracov/karty; zahraj [nazov karty]; info [meno hraca]; pouzi; skonci; koniec tahu");
        } else if (prvaCast.equals("skonci")) {
            System.out.println("naozaj chces skoncit?");
            if (this.scanner.next().equals("ano")) {
                System.exit(0);
            }
        } else {
            System.out.println("Nerozumiem");
        }
        return false;
    }
}
