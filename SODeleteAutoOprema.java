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
public class SODeleteAutoOprema extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof AutoOprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase AutoOprema!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
