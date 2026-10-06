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
import java.util.ArrayList;
import so.auto_oprema.SOAddAutoOprema;
import so.auto_oprema.SODeleteAutoOprema;
import so.grad.SOGetAllGrad;
import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOGetAllKupac;
import so.kupac.SOUpdateKupac;
import so.login.SOLogin;
import so.porudzbina.SOAddPorudzbina;
import so.porudzbina.SODeletePorudzbina;
import so.porudzbina.SOGetAllPorudzbina;
import so.porudzbina.SOUpdatePorudzbina;
import so.auto_oprema.SOGetAllAutoOprema;
import so.auto_oprema.SOUpdateAutoOprema;

/**
 *
 * @author Marija
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Prodavac> ulogovaniProdavac = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Prodavac> getUlogovaniProdavac() {
        return ulogovaniProdavac;
    }

    public void setUlogovaniProdavac(ArrayList<Prodavac> ulogovaniProdavac) {
        this.ulogovaniProdavac = ulogovaniProdavac;
    }

    public Prodavac login(Prodavac prodavac) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(prodavac);
        return so.getUlogovani();
    }

    public void addKupac(Kupac kupac) throws Exception {
        (new SOAddKupac()).templateExecute(kupac);
    }

    public void addAutoOprema(AutoOprema autoOprema) throws Exception {
        (new SOAddAutoOprema()).templateExecute(autoOprema);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOAddPorudzbina()).templateExecute(porudzbina);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        (new SODeleteKupac()).templateExecute(kupac);
    }

    public void deleteAutoOprema(AutoOprema autoOprema) throws Exception {
        (new SODeleteAutoOprema()).templateExecute(autoOprema);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SODeletePorudzbina()).templateExecute(porudzbina);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        (new SOUpdateKupac()).templateExecute(kupac);
    }

    public void updateAutoOprema(AutoOprema autoOprema) throws Exception {
        (new SOUpdateAutoOprema()).templateExecute(autoOprema);
    }

    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOUpdatePorudzbina()).templateExecute(porudzbina);
    }

    public ArrayList<Kupac> getAllKupac(Kupac kupac) throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(kupac);
        return so.getLista();
    }

    public ArrayList<Porudzbina> getAllPorudzbina(Porudzbina porudzbina) throws Exception {
        SOGetAllPorudzbina so = new SOGetAllPorudzbina();
        so.templateExecute(porudzbina);
        return so.getLista();
    }

    public ArrayList<AutoOprema> getAllAutoOprema(AutoOprema autoOprema) throws Exception {
        SOGetAllAutoOprema so = new SOGetAllAutoOprema();
        so.templateExecute(autoOprema);
        return so.getLista();
    }

    public ArrayList<Grad> getAllGrad(Grad grad) throws Exception {
        SOGetAllGrad so = new SOGetAllGrad();
        so.templateExecute(grad);
        return so.getLista();
    }

    public void logout(Prodavac ulogovani) {
        ulogovaniProdavac.remove(ulogovani);
    }

}
