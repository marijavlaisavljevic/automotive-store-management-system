/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Prodavac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Marija
 */
public class SOLogin extends AbstractSO {

    Prodavac ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Prodavac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Prodavac!");
        }

        Prodavac p = (Prodavac) ado;

        for (Prodavac prodavac : ServerController.getInstance().getUlogovaniProdavac()) {
            if (prodavac.getKorisnickoIme().equals(p.getKorisnickoIme())) {
                throw new Exception("Ovaj prodavac je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Prodavac p = (Prodavac) ado;

        ArrayList<Prodavac> prodavci
                = (ArrayList<Prodavac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Prodavac prodavac : prodavci) {
            if (prodavac.getKorisnickoIme().equals(p.getKorisnickoIme())
                    && prodavac.getLozinka().equals(p.getLozinka())) {
                ulogovani = prodavac;
                ServerController.getInstance().getUlogovaniProdavac().add(prodavac);
                return;
            }
        }

        throw new Exception("Ne postoji prodavac sa tim kredencijalima.");

    }

    public Prodavac getUlogovani() {
        return ulogovani;
    }

}
