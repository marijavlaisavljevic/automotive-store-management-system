/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.porudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Porudzbina;
import domain.StavkaPorudzbine;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Marija
 */
public class SOGetAllPorudzbina extends AbstractSO {

    private ArrayList<Porudzbina> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Porudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Porudzbina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Vracamo sve porudzbine bez stavki
        ArrayList<AbstractDomainObject> porudzbine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Porudzbina>) (ArrayList<?>) porudzbine;

        // Prolazimo kroz listu porudzbina i vracamo sve stavke te porudzbine
        for (Porudzbina trenutnaPorudzbina : lista) {

            // Pravimo novu stavku gde setujemo porudzbinu, da bi ovaj dole
            // DBBroker.getInstance().select(stavkaPorudzbine) imao u sebi 
            // tu porudzbinu i uradio uslovZaSelect() koji ce da vrati
            // WHERE PORUDZBINAID = porudzbinaID (od ove u kojoj smo)
            StavkaPorudzbine trenutnaStavka = new StavkaPorudzbine();
            trenutnaStavka.setPorudzbina(trenutnaPorudzbina);

            // Ovo nam vraca sve stavke nase trenutne porudzbine
            ArrayList<StavkaPorudzbine> stavkeTrenutnePorudzbine
                    = (ArrayList<StavkaPorudzbine>) (ArrayList<?>) DBBroker
                            .getInstance().select(trenutnaStavka);

            // Setujemo te stavke za nasu trenutnu porudzbinu
            trenutnaPorudzbina.setStavkePorudzbine(stavkeTrenutnePorudzbine);
        }
    }

    public ArrayList<Porudzbina> getLista() {
        return lista;
    }

}
