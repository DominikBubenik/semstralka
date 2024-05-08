package balikKariet.karty;

/**
 * predok vsetkych kariet
 */
public abstract class Karta  {
    private FarbaKarty farbaKarty;
    private int cisloKarty;
    private OkrajKarty okrajKarty;

    //konstruktor inicializuje farbu karty, okraj a cislo
    public Karta(FarbaKarty farba, int cisloKarty, OkrajKarty okrajKarty) {
        this.farbaKarty = farba;
        this.cisloKarty = cisloKarty;
        this.okrajKarty = okrajKarty;
    }

    public FarbaKarty getFarbaKarty() {
        return this.farbaKarty;
    }

    public int getCisloKarty() {
        return this.cisloKarty;
    }

    public abstract String nazov();
    //vrati false ak ju hrac nehra na seba
    public boolean hramNaSeba() {
        return false;
    }

    @Override
    public String toString() {
        return this.nazov() +
                " " + this.farbaKarty +
                " " + this.cisloKarty;
    }
}
