/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.auto_oprema;


import db.DBBroker;
import domain.AbstractDomainObject;
import domain.AutoOprema;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Marija
 */
public class SOGetAllAutoOprema extends AbstractSO {

    private ArrayList<AutoOprema> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof AutoOprema)) {
            throw new Exception("Prosledjeni objekat nije instanca klase AutoOprema!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> listaAutoOpreme = DBBroker.getInstance().select(ado);
        lista = (ArrayList<AutoOprema>) (ArrayList<?>) listaAutoOpreme;
    }

    public ArrayList<AutoOprema> getLista() {
        return lista;
    }

}
