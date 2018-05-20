/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KIRest;

import domen.resource.KorisnikResource;
import ki.domen.Korisnik;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;

/**
 *
 * @author Paun
 */

public class ZajednickiRest {


	public static void main(String[] args) {
		// Initialize the resource proxy.
		ClientResource cr = new ClientResource(
				"http://localhost:8080/CVSeeker/");
		// Workaround for GAE servers to prevent chunk encoding
		cr.setRequestEntityBuffering(true);
		cr.accept(MediaType.APPLICATION_JSON);

		KorisnikResource resource = cr.wrap(KorisnikResource.class);

		// Get the remote contact
		Korisnik korisnik = resource.retrieve();
		if (korisnik != null) {
			System.out.println("Ime: " + korisnik.getIme());
			System.out.println("Prezime: " + korisnik.getPrezime());
			System.out.println("Username: " + korisnik.getUsername());
		}

		// Update the contact
		korisnik.setIme("Nikola");
		resource.store(korisnik);
	}

}