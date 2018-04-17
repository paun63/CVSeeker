/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nn.cvserver.service;

import nn.cvserver.domen.Korisnik;


/**
 *
 * @author P
 */
public interface KorisnikService {
    Korisnik login(String username, String password);
    
    Korisnik register(Korisnik korisnik);

    Korisnik findByUsername(String username);

    String authenticate(Korisnik korisnik) throws Exception;
}
