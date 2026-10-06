/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Grad;
import domain.Kupac;
import domain.Porudzbina;
import domain.Prodavac;
import domain.AutoOprema;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Marija
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Prodavac login(Prodavac prodavac) throws Exception {
        return (Prodavac) sendRequest(Operation.LOGIN, prodavac);
    }

    public void logout(Prodavac ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.ADD_KUPAC, kupac);
    }

    public void addAutoOprema(AutoOprema autoOprema) throws Exception {
        sendRequest(Operation.ADD_AUTO_OPREMA, autoOprema);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.ADD_PORUDZBINA, porudzbina);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.DELETE_KUPAC, kupac);
    }

    public void deleteAutoOprema(AutoOprema autoOprema) throws Exception {
        sendRequest(Operation.DELETE_AUTO_OPREMA, autoOprema);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.DELETE_PORUDZBINA, porudzbina);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        sendRequest(Operation.UPDATE_KUPAC, kupac);
    }

    public void updateAutoOprema(AutoOprema autoOprema) throws Exception {
        sendRequest(Operation.UPDATE_AUTO_OPREMA, autoOprema);
    }

    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.UPDATE_PORUDZBINA, porudzbina);
    }

    public ArrayList<Kupac> getAllKupac(Kupac kupac) throws Exception {
        return (ArrayList<Kupac>) sendRequest(Operation.GET_ALL_KUPAC, kupac);
    }

    public ArrayList<Porudzbina> getAllPorudzbina(Porudzbina porudzbina) throws Exception {
        return (ArrayList<Porudzbina>) sendRequest(Operation.GET_ALL_PORUDZBINA, porudzbina);
    }

    public ArrayList<AutoOprema> getAllAutoOprema(AutoOprema autoOprema) throws Exception {
        return (ArrayList<AutoOprema>) sendRequest(Operation.GET_ALL_AUTO_OPREMA, autoOprema);
    }

    public ArrayList<Grad> getAllGrad(Grad grad) throws Exception {
        return (ArrayList<Grad>) sendRequest(Operation.GET_ALL_GRAD, grad);
    }

    private synchronized Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        // Posalji zahtev preko soketa 9000
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        // Primi odgovor od servera preko istog soketa
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        // Ako je ERROR, bacamo exception, ako je SUCCESS, vracamo odgovor
        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

}
