/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Grad;
import domain.Kupac;
import domain.Porudzbina;
import domain.Prodavac;
import domain.AutoOprema;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Marija
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                // Primi zahtev i procitaj ga
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                // Obrada zahteva, odradi neku operaciju i vrati odgovor
                Response response = handleRequest(request);
                // Posalji odgovor nazad klijentu
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_KUPAC:
                    ServerController.getInstance().addKupac((Kupac) request.getData());
                    break;
                case Operation.ADD_AUTO_OPREMA:
                    ServerController.getInstance().addAutoOprema((AutoOprema) request.getData());
                    break;
                case Operation.ADD_PORUDZBINA:
                    ServerController.getInstance().addPorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.DELETE_KUPAC:
                    ServerController.getInstance().deleteKupac((Kupac) request.getData());
                    break;
                case Operation.DELETE_AUTO_OPREMA:
                    ServerController.getInstance().deleteAutoOprema((AutoOprema) request.getData());
                    break;
                case Operation.DELETE_PORUDZBINA:
                    ServerController.getInstance().deletePorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.UPDATE_KUPAC:
                    ServerController.getInstance().updateKupac((Kupac) request.getData());
                    break;
                case Operation.UPDATE_AUTO_OPREMA:
                    ServerController.getInstance().updateAutoOprema((AutoOprema) request.getData());
                    break;
                case Operation.UPDATE_PORUDZBINA:
                    ServerController.getInstance().updatePorudzbina((Porudzbina) request.getData());
                    break;
                case Operation.GET_ALL_GRAD:
                    response.setData(ServerController.getInstance().getAllGrad((Grad) request.getData()));
                    break;
                case Operation.GET_ALL_KUPAC:
                    response.setData(ServerController.getInstance().getAllKupac((Kupac) request.getData()));
                    break;
                case Operation.GET_ALL_PORUDZBINA:
                    response.setData(ServerController.getInstance().getAllPorudzbina((Porudzbina) request.getData()));
                    break;
                case Operation.GET_ALL_AUTO_OPREMA:
                    response.setData(ServerController.getInstance().getAllAutoOprema((AutoOprema) request.getData()));
                    break;
                case Operation.LOGIN:
                    Prodavac prodavac = (Prodavac) request.getData();
                    Prodavac prod = ServerController.getInstance().login(prodavac);
                    response.setData(prod);
                    break;
                case Operation.LOGOUT:
                    Prodavac ulogovani = (Prodavac) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
