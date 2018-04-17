/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nn.cvserver.controller.rest;

import java.util.List;
import nn.cvserver.domen.Profil;
import nn.cvserver.service.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SOLUNAC, P
 */
@RestController
@RequestMapping("/profil")
public class ProfilRestController {
    
    @Autowired
    private ProfilService profilService;                                        

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getAll() {
        List<Profil> list = profilService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody Object get(@PathVariable Integer id) {
        Profil p = profilService.findByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
    
    @RequestMapping(value = "/getK/{id}", method = RequestMethod.GET)
    public @ResponseBody Object getK(@PathVariable Integer id) {
        Profil p = profilService.findByKorisnikID(id);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody Object delete(@PathVariable Integer id) {
        profilService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object save(@RequestBody Profil p) {
        Profil profil = profilService.save(p);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(profil);
        }
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        
    }
}
