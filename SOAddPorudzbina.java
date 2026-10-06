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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Marija
 */
public class SOAddPorudzbina extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Porudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Porudzbina!");
        }

        Porudzbina p = (Porudzbina) ado;

        if (!p.getDatumVremeIsporuke().after(new Date())) {
            throw new Exception("Datum isporuke mora biti posle danasnjeg datuma!");
        }

        if (p.getStavkePorudzbine().isEmpty()) {
            throw new Exception("Porudzbina mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        // Vracamo ps sa generisanim kljucem porudzbine koji
        // nam treba za stavke porudzbine jer je ona slab objekat
        // koji ima ID od jakog
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        // Uzimamo taj kljuc
        ResultSet tableKeys = ps.getGeneratedKeys(); // Vracamo tabelu sa generisanim kljucevima
        tableKeys.next(); // Ulazimo u red gde je taj novi kljuc
        int novaPorudzbinaID = tableKeys.getInt(1); // Citamo ga i pamtimo u novaPorudzbinaID

        // Setujemo ga za nasu porudzbinu jer je prethodno bio -1
        // pogledaj btnSacuvaj na MainFormi
        Porudzbina novaPorudzbina = (Porudzbina) ado;
        novaPorudzbina.setPorudzbinaID(novaPorudzbinaID);
        // Sad je ID porudzbine novogenerisani ID sto smo izvukli iznad

        // Dodajemo redom stavku po stavku nase porudzbine nakon sto setujemo
        // da potice iz nase nove porudzbine jer je prethodno porudzbina u stavci
        // bila NULL, vidi btnDodaj na MainFormi
        for (StavkaPorudzbine stavkaPorudzbine : novaPorudzbina.getStavkePorudzbine()) {
            stavkaPorudzbine.setPorudzbina(novaPorudzbina);
            DBBroker.getInstance().insert(stavkaPorudzbine);
        }
    }

}
