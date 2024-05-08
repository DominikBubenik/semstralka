package hrac.postavy.zoznam;

import hrac.Hrac;

/**
 * trieda vytvara postavu hraca ktory moze hrat tolko Bangov kolko chce
 * @author Dominik Bubeník
 */
public class WillyTheKid extends Hrac {
    //konstruktor inicializuje predka
    public WillyTheKid() {
        super(4);
    }

    //metoda vzdy vracia true
    @Override
    public void setMozeHratBang(boolean b) {
        super.setMozeHratBang(true);
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " moze hrat lubovolny pocet Bangov");
    }
}
