package hrac.postavy.zoznam;

import hrac.Hrac;
/**
 * trieda vytvara postavu hraca ktora ma o 1 vacsiu vzdialenost
 * @author Dominik Bubeník
 */
public class PaulRegret extends Hrac {
    //konstruktor inicializuje predka
    public PaulRegret() {
        super(3);
    }

    //vraciam o 1 vacsiu vzdialenost
    @Override
    public int getVzdialenost() {
        return super.getVzdialenost() + 1;
    }

    @Override
    public void vypisPostavy() {
        System.out.println(this.getClass().getSimpleName() + " jeho vzdialenost je o 1 vacsia");
    }
}
