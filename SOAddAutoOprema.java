/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.auto_oprema;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.AutoOprema;
import so.AbstractSO;

/**
 *
 * @author Marija
 */
public class SOAddAutoOprema extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof AutoOprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase AutoOprema!");
        }

        AutoOprema ao = (AutoOprema) ado;

        if (ao.getCena() <= 0) {
            throw new Exception("Cena mora biti veca od 0!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
