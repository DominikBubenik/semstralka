package hra;

import balikKariet.karty.Karta;
import hrac.Charakter;
import hrac.Hrac;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

/**
 * trieda sas vyuziva na komunikaciu s pouzivatelom ked sa musi rozhodnut na koho zahra kartu, ci sa chce/nechce branit...
 */
public class Prikazy {

    //vyber na ktoreho hraca vyuzie efekt karty
    public static Optional<Hrac> naKohoHratkartu(Collection<Hrac> zoznamHracov, Karta karta, Hrac hracnaTahu) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Na koho chces hrat " + karta.nazov() + "?  ");
        for (Hrac hrac : zoznamHracov) {
            if (hrac.equals(hracnaTahu)) {
                continue;
            }
            System.out.print(hrac.getMeno() + "  ");
        }
        System.out.println();
        while (true) {
            if (zoznamHracov.size() == 1) {
                System.out.println("Nemas na koho hrat");
                return Optional.empty();
            }
            try {
                String vstup = scanner.next();
                Optional<Hrac> vybranyHrac = zoznamHracov.stream().filter(hrac -> hrac.getMeno().equals(vstup)).findAny();
                if (vybranyHrac.isPresent()) {
                    int vzdialenost = vybranyHrac.get().getVzdialenost();
                    if (karta.nazov().equals("Bang")) {
                        if (vzdialenost <= hracnaTahu.getDostrelNaVzdialenost()) {
                            return vybranyHrac;
                        } else {
                            System.out.println("Nemas dostrel");
                            return Optional.empty();
                        }
                    } else if (karta.nazov().equals("Panika")) {
                        if (vzdialenost <= hracnaTahu.getVidiNaVzdialenost()) {
                            return vybranyHrac;
                        } else {
                            System.out.println("Nevidis na neho");
                            return Optional.empty();
                        }
                    } else if (karta.nazov().equals("Vezenie")) {
                        if (vybranyHrac.get().getCharakter().equals(Charakter.SHERIF)) {
                            System.out.println("Na serifa nemozes hrat vezenie");
                            return Optional.empty();
                        }
                        return vybranyHrac;
                    } else {
                        return vybranyHrac;
                    }
                } else {
                    throw new RuntimeException();
                }
            } catch (RuntimeException e) {
                System.out.println("Taky hrac tu nie je!");
            }
        }
    }
    //odpoved ci hrac chce/nechce hrat konkretnu kartu
    public static boolean odpovedAnoNie(Hrac hrac, String nazovKarty) {
        if (hrac.maTutoKartu(nazovKarty)) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println(hrac.getMeno() + ", chces zahrat " + nazovKarty + "? ano/nie");
                try {
                    String vstup = scanner.next();
                    if (vstup.equals("ano")) {
                        hrac.zahodKartu(nazovKarty);
                        return true;
                    } else if (vstup.equals("nie")) {
                        hrac.uberZivot();
                        return false;
                    } else {
                        throw new RuntimeException();
                    }
                    //break;
                } catch (RuntimeException e) {
                    System.out.println("Zly vstup!");
                }
            }
        } else {
            hrac.uberZivot();
        }
        return false;
    }

    //vyber karty kt chce hracovi zobrat zo stola/ruky
    public static Optional<Karta> branieKartyHracovi(Hrac hrac) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(hrac.getMeno() + " ma " + hrac.getPocetKarietNaRuke() + "  v ruke a " + hrac.getPocetKarietPredSebou() + " na stole");
        System.out.println("Chces brat karty zo stola/ruky? stol/ruka");
        String vstup = "";
        while (true) {
            try {
                vstup = scanner.next();
                if (vstup.equals("stol")) {
                    break;
                } else if (vstup.equals("ruka")) {
                    break;
                } else {
                    throw new RuntimeException();
                }
            } catch (RuntimeException e) {
                System.out.println("Zly vstup!");
            }
        }
        if (vstup.equals("ruka")) {
            if (hrac.getPocetKarietNaRuke() == 1) {
                return hrac.odovzdajKartuZRuky(0);
            } else if (hrac.getPocetKarietNaRuke() > 1) {
                System.out.println("Ktoru kartu chces zobrat od 0-" + (hrac.getPocetKarietNaRuke() - 1));
                while (true) {
                    try {
                        int cislo = scanner.nextInt();
                        return hrac.odovzdajKartuZRuky(cislo);
                    } catch (RuntimeException e) {
                        System.out.println("Zly vstup!");
                    }
                }
            } else {
                System.out.println(hrac.getMeno() + " nema ziadne karty na ruke");
                return Optional.empty();
            }
        } else if (vstup.equals("stol")) {
            if (hrac.getPocetKarietPredSebou() == 1) {
                return hrac.odovzdajKartuZoStola(0);
            } else if (hrac.getPocetKarietPredSebou() > 1) {
                System.out.println("Ktoru kartu chces zobrat od 0-" + (hrac.getPocetKarietPredSebou() - 1));
                while (true) {
                    try {
                        int cislo = scanner.nextInt();
                        return hrac.odovzdajKartuZoStola(cislo);
                    } catch (RuntimeException e) {
                        System.out.println("Zly vstup!");
                    }
                }
            } else {
                System.out.println(hrac.getMeno() + " nema ziadne karty na stole");
            }
        }
        return Optional.empty();
    }

    //vyber ktoru kartu chce hrac zahodit
    public static void odhadzovanieKartyZRuky(Hrac hrac) {
        Scanner scanner = new Scanner(System.in);
        if (hrac.getPocetKarietNaRuke() > 0) {
            System.out.println("Ktoru kartu chces zahodit?");
            hrac.vypisKarty();
            String vyber = scanner.next();
            if (hrac.maTutoKartu(vyber)) {
                hrac.zahodKartu(vyber);
            }
        }
    }
}
