/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.Prodavac;
import forme.MainForm;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Marija
 */
public class Session {

    private static Session instance;
    private Socket socket;
    private Prodavac ulogovani;
    private MainForm mf;

    private Session() {
        try {
            socket = new Socket("localhost", 9000);
        } catch (IOException ex) {
//            ex.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUlogovani(Prodavac ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Prodavac getUlogovani() {
        return ulogovani;
    }

    public MainForm getMf() {
        return mf;
    }

    public void setMf(MainForm mf) {
        this.mf = mf;
    }

}
