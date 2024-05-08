package hrac.postavy;

import hrac.Hrac;

import java.util.Collection;

/**
 * ak nejaka postava potrebuje zoznam hracov pre svoju vlastnost implementuje tento interface
 * @author Dominik Bubeník
 */
public interface PotrebujeZoznam {
    void nastavZoznam(Collection<Hrac> zoznam);//nastavi sa zoznam hracov kt su v hre
}
